package org.springframework.beans;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Static convenience methods for JavaBeans: for instantiating beans,
 * checking bean property types, copying bean properties, etc.
 *
 * <p>Mainly for use within the framework, but to some degree also
 * useful for application classes.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @author Rob Harrop
 * @author Sam Brannen
 * @author Sebastien Deleuze
 */
public abstract class BeanUtilsSpring {

    private static final Log logger = LogFactory.getLog(org.springframework.beans.BeanUtils.class);

    private static final Set<Class<?>> unknownEditorTypes =
            Collections.newSetFromMap(new ConcurrentReferenceHashMap<>(64));


    /**
     * Convenience method to instantiate a class using its no-arg constructor.
     *
     * @param clazz class to instantiate
     * @return the new instance
     * @throws BeanInstantiationException if the bean cannot be instantiated
     * @see Class#newInstance()
     * @deprecated as of Spring 5.0, following the deprecation of
     * {@link Class#newInstance()} in JDK 9
     */
    @Deprecated
    public static <T> T instantiate(Class<T> clazz) throws BeanInstantiationException {
        Assert.notNull(clazz, "Class must not be null");
        if (clazz.isInterface()) {
            throw new BeanInstantiationException(clazz, "Specified class is an interface");
        }
        try {
            return clazz.newInstance();
        } catch (InstantiationException ex) {
            throw new BeanInstantiationException(clazz, "Is it an abstract class?", ex);
        } catch (IllegalAccessException ex) {
            throw new BeanInstantiationException(clazz, "Is the constructor accessible?", ex);
        }
    }


    /**
     * Find a method with the given method name and the given parameter types,
     * declared on the given class or one of its superclasses. Prefers public methods,
     * but will return a protected, package access, or private method too.
     * <p>Checks {@code Class.getMethod} first, falling back to
     * {@code findDeclaredMethod}. This allows to find public methods
     * without issues even in environments with restricted Java security settings.
     *
     * @param clazz      the class to check
     * @param methodName the name of the method to find
     * @param paramTypes the parameter types of the method to find
     * @return the Method object, or {@code null} if not found
     * @see Class#getMethod
     * @see #findDeclaredMethod
     */
    @Nullable
    public static Method findMethod(Class<?> clazz, String methodName, Class<?>... paramTypes) {
        try {
            return clazz.getMethod(methodName, paramTypes);
        } catch (NoSuchMethodException ex) {
            return findDeclaredMethod(clazz, methodName, paramTypes);
        }
    }

    /**
     * Find a method with the given method name and the given parameter types,
     * declared on the given class or one of its superclasses. Will return a public,
     * protected, package access, or private method.
     * <p>Checks {@code Class.getDeclaredMethod}, cascading upwards to all superclasses.
     *
     * @param clazz      the class to check
     * @param methodName the name of the method to find
     * @param paramTypes the parameter types of the method to find
     * @return the Method object, or {@code null} if not found
     * @see Class#getDeclaredMethod
     */
    @Nullable
    public static Method findDeclaredMethod(Class<?> clazz, String methodName, Class<?>... paramTypes) {
        try {
            return clazz.getDeclaredMethod(methodName, paramTypes);
        } catch (NoSuchMethodException ex) {
            if (clazz.getSuperclass() != null) {
                return findDeclaredMethod(clazz.getSuperclass(), methodName, paramTypes);
            }
            return null;
        }
    }

    /**
     * Find a method with the given method name and minimal parameters (best case: none),
     * declared on the given class or one of its superclasses. Prefers public methods,
     * but will return a protected, package access, or private method too.
     * <p>Checks {@code Class.getMethods} first, falling back to
     * {@code findDeclaredMethodWithMinimalParameters}. This allows for finding public
     * methods without issues even in environments with restricted Java security settings.
     *
     * @param clazz      the class to check
     * @param methodName the name of the method to find
     * @return the Method object, or {@code null} if not found
     * @throws IllegalArgumentException if methods of the given name were found but
     *                                  could not be resolved to a unique method with minimal parameters
     * @see Class#getMethods
     * @see #findDeclaredMethodWithMinimalParameters
     */
    @Nullable
    public static Method findMethodWithMinimalParameters(Class<?> clazz, String methodName)
            throws IllegalArgumentException {

        Method targetMethod = findMethodWithMinimalParameters(clazz.getMethods(), methodName);
        if (targetMethod == null) {
            targetMethod = findDeclaredMethodWithMinimalParameters(clazz, methodName);
        }
        return targetMethod;
    }

    /**
     * Find a method with the given method name and minimal parameters (best case: none),
     * declared on the given class or one of its superclasses. Will return a public,
     * protected, package access, or private method.
     * <p>Checks {@code Class.getDeclaredMethods}, cascading upwards to all superclasses.
     *
     * @param clazz      the class to check
     * @param methodName the name of the method to find
     * @return the Method object, or {@code null} if not found
     * @throws IllegalArgumentException if methods of the given name were found but
     *                                  could not be resolved to a unique method with minimal parameters
     * @see Class#getDeclaredMethods
     */
    @Nullable
    public static Method findDeclaredMethodWithMinimalParameters(Class<?> clazz, String methodName)
            throws IllegalArgumentException {

        Method targetMethod = findMethodWithMinimalParameters(clazz.getDeclaredMethods(), methodName);
        if (targetMethod == null && clazz.getSuperclass() != null) {
            targetMethod = findDeclaredMethodWithMinimalParameters(clazz.getSuperclass(), methodName);
        }
        return targetMethod;
    }

    /**
     * Find a method with the given method name and minimal parameters (best case: none)
     * in the given list of methods.
     *
     * @param methods    the methods to check
     * @param methodName the name of the method to find
     * @return the Method object, or {@code null} if not found
     * @throws IllegalArgumentException if methods of the given name were found but
     *                                  could not be resolved to a unique method with minimal parameters
     */
    @Nullable
    public static Method findMethodWithMinimalParameters(Method[] methods, String methodName)
            throws IllegalArgumentException {

        Method targetMethod = null;
        int numMethodsFoundWithCurrentMinimumArgs = 0;
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                int numParams = method.getParameterCount();
                if (targetMethod == null || numParams < targetMethod.getParameterCount()) {
                    targetMethod = method;
                    numMethodsFoundWithCurrentMinimumArgs = 1;
                } else if (!method.isBridge() && targetMethod.getParameterCount() == numParams) {
                    if (targetMethod.isBridge()) {
                        // Prefer regular method over bridge...
                        targetMethod = method;
                    } else {
                        // Additional candidate with same length
                        numMethodsFoundWithCurrentMinimumArgs++;
                    }
                }
            }
        }
        if (numMethodsFoundWithCurrentMinimumArgs > 1) {
            throw new IllegalArgumentException("Cannot resolve method '" + methodName +
                    "' to a unique method. Attempted to resolve to overloaded method with " +
                    "the least number of parameters but there were " +
                    numMethodsFoundWithCurrentMinimumArgs + " candidates.");
        }
        return targetMethod;
    }

    /**
     * Parse a method signature in the form {@code methodName[([arg_list])]},
     * where {@code arg_list} is an optional, comma-separated list of fully-qualified
     * type names, and attempts to resolve that signature against the supplied {@code Class}.
     * <p>When not supplying an argument list ({@code methodName}) the method whose name
     * matches and has the least number of parameters will be returned. When supplying an
     * argument type list, only the method whose name and argument types match will be returned.
     * <p>Note then that {@code methodName} and {@code methodName()} are <strong>not</strong>
     * resolved in the same way. The signature {@code methodName} means the method called
     * {@code methodName} with the least number of arguments, whereas {@code methodName()}
     * means the method called {@code methodName} with exactly 0 arguments.
     * <p>If no method can be found, then {@code null} is returned.
     *
     * @param signature the method signature as String representation
     * @param clazz     the class to resolve the method signature against
     * @return the resolved Method
     * @see #findMethod
     * @see #findMethodWithMinimalParameters
     */
    @Nullable
    public static Method resolveSignature(String signature, Class<?> clazz) {
        Assert.hasText(signature, "'signature' must not be empty");
        Assert.notNull(clazz, "Class must not be null");
        int startParen = signature.indexOf('(');
        int endParen = signature.indexOf(')');
        if (startParen > -1 && endParen == -1) {
            throw new IllegalArgumentException("Invalid method signature '" + signature +
                    "': expected closing ')' for args list");
        } else if (startParen == -1 && endParen > -1) {
            throw new IllegalArgumentException("Invalid method signature '" + signature +
                    "': expected opening '(' for args list");
        } else if (startParen == -1) {
            return findMethodWithMinimalParameters(clazz, signature);
        } else {
            String methodName = signature.substring(0, startParen);
            String[] parameterTypeNames =
                    StringUtils.commaDelimitedListToStringArray(signature.substring(startParen + 1, endParen));
            Class<?>[] parameterTypes = new Class<?>[parameterTypeNames.length];
            for (int i = 0; i < parameterTypeNames.length; i++) {
                String parameterTypeName = parameterTypeNames[i].trim();
                try {
                    parameterTypes[i] = ClassUtils.forName(parameterTypeName, clazz.getClassLoader());
                } catch (Throwable ex) {
                    throw new IllegalArgumentException("Invalid method signature: unable to resolve type [" +
                            parameterTypeName + "] for argument " + i + ". Root cause: " + ex);
                }
            }
            return findMethod(clazz, methodName, parameterTypes);
        }
    }


    /**
     * Retrieve the JavaBeans {@code PropertyDescriptor}s of a given class.
     *
     * @param clazz the Class to retrieve the PropertyDescriptors for
     * @return an array of {@code PropertyDescriptors} for the given class
     * @throws BeansException if PropertyDescriptor look fails
     */
    public static PropertyDescriptor[] getPropertyDescriptors(Class<?> clazz) throws BeansException {
        CachedIntrospectionResults cr = CachedIntrospectionResults.forClass(clazz);
        return cr.getPropertyDescriptors();
    }

    /**
     * Retrieve the JavaBeans {@code PropertyDescriptors} for the given property.
     *
     * @param clazz        the Class to retrieve the PropertyDescriptor for
     * @param propertyName the name of the property
     * @return the corresponding PropertyDescriptor, or {@code null} if none
     * @throws BeansException if PropertyDescriptor lookup fails
     */
    @Nullable
    public static PropertyDescriptor getPropertyDescriptor(Class<?> clazz, String propertyName)
            throws BeansException {

        CachedIntrospectionResults cr = CachedIntrospectionResults.forClass(clazz);
        return cr.getPropertyDescriptor(propertyName);
    }

    /**
     * Find a JavaBeans {@code PropertyDescriptor} for the given method,
     * with the method either being the read method or the write method for
     * that bean property.
     *
     * @param method the method to find a corresponding PropertyDescriptor for,
     *               introspecting its declaring class
     * @return the corresponding PropertyDescriptor, or {@code null} if none
     * @throws BeansException if PropertyDescriptor lookup fails
     */
    @Nullable
    public static PropertyDescriptor findPropertyForMethod(Method method) throws BeansException {
        return findPropertyForMethod(method, method.getDeclaringClass());
    }

    /**
     * Find a JavaBeans {@code PropertyDescriptor} for the given method,
     * with the method either being the read method or the write method for
     * that bean property.
     *
     * @param method the method to find a corresponding PropertyDescriptor for
     * @param clazz  the (most specific) class to introspect for descriptors
     * @return the corresponding PropertyDescriptor, or {@code null} if none
     * @throws BeansException if PropertyDescriptor lookup fails
     * @since 3.2.13
     */
    @Nullable
    public static PropertyDescriptor findPropertyForMethod(Method method, Class<?> clazz) throws BeansException {
        Assert.notNull(method, "Method must not be null");
        PropertyDescriptor[] pds = getPropertyDescriptors(clazz);
        for (PropertyDescriptor pd : pds) {
            if (method.equals(pd.getReadMethod()) || method.equals(pd.getWriteMethod())) {
                return pd;
            }
        }
        return null;
    }


    /**
     * Determine the bean property type for the given property from the
     * given classes/interfaces, if possible.
     *
     * @param propertyName the name of the bean property
     * @param beanClasses  the classes to check against
     * @return the property type, or {@code Object.class} as fallback
     */
    public static Class<?> findPropertyType(String propertyName, @Nullable Class<?>... beanClasses) {
        if (beanClasses != null) {
            for (Class<?> beanClass : beanClasses) {
                PropertyDescriptor pd = getPropertyDescriptor(beanClass, propertyName);
                if (pd != null) {
                    return pd.getPropertyType();
                }
            }
        }
        return Object.class;
    }

    /**
     * Obtain a new MethodParameter object for the write method of the
     * specified property.
     *
     * @param pd the PropertyDescriptor for the property
     * @return a corresponding MethodParameter object
     */
    public static MethodParameter getWriteMethodParameter(PropertyDescriptor pd) {
        if (pd instanceof GenericTypeAwarePropertyDescriptor) {
            return new MethodParameter(((GenericTypeAwarePropertyDescriptor) pd).getWriteMethodParameter());
        } else {
            Method writeMethod = pd.getWriteMethod();
            Assert.state(writeMethod != null, "No write method available");
            return new MethodParameter(writeMethod, 0);
        }
    }


    /**
     * Copy the property values of the given source bean into the target bean.
     * <p>Note: The source and target classes do not have to match or even be derived
     * from each other, as long as the properties match. Any bean properties that the
     * source bean exposes but the target bean does not will silently be ignored.
     * <p>This is just a convenience method. For more complex transfer needs,
     * consider using a full BeanWrapper.
     *
     * @param source the source bean
     * @param target the target bean
     * @throws BeansException if the copying failed
     * @see BeanWrapper
     */
    public static void copyProperties(Object source, Object target) throws BeansException {
        copyProperties(source, target, null, (String[]) null);
    }

    /**
     * Copy the property values of the given source bean into the given target bean,
     * only setting properties defined in the given "editable" class (or interface).
     * <p>Note: The source and target classes do not have to match or even be derived
     * from each other, as long as the properties match. Any bean properties that the
     * source bean exposes but the target bean does not will silently be ignored.
     * <p>This is just a convenience method. For more complex transfer needs,
     * consider using a full BeanWrapper.
     *
     * @param source   the source bean
     * @param target   the target bean
     * @param editable the class (or interface) to restrict property setting to
     * @throws BeansException if the copying failed
     * @see BeanWrapper
     */
    public static void copyProperties(Object source, Object target, Class<?> editable) throws BeansException {
        copyProperties(source, target, editable, (String[]) null);
    }

    /**
     * Copy the property values of the given source bean into the given target bean,
     * ignoring the given "ignoreProperties".
     * <p>Note: The source and target classes do not have to match or even be derived
     * from each other, as long as the properties match. Any bean properties that the
     * source bean exposes but the target bean does not will silently be ignored.
     * <p>This is just a convenience method. For more complex transfer needs,
     * consider using a full BeanWrapper.
     *
     * @param source           the source bean
     * @param target           the target bean
     * @param ignoreProperties array of property names to ignore
     * @throws BeansException if the copying failed
     * @see BeanWrapper
     */
    public static void copyProperties(Object source, Object target, String... ignoreProperties) throws BeansException {
        copyProperties(source, target, null, ignoreProperties);
    }

    /**
     * Copy the property values of the given source bean into the given target bean.
     * <p>Note: The source and target classes do not have to match or even be derived
     * from each other, as long as the properties match. Any bean properties that the
     * source bean exposes but the target bean does not will silently be ignored.
     *
     * @param source           the source bean
     * @param target           the target bean
     * @param editable         the class (or interface) to restrict property setting to
     * @param ignoreProperties array of property names to ignore
     * @throws BeansException if the copying failed
     * @see BeanWrapper
     */
    private static void copyProperties(Object source, Object target, @Nullable Class<?> editable,
                                       @Nullable String... ignoreProperties) throws BeansException {

        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");

        Class<?> actualEditable = target.getClass();
        if (editable != null) {
            if (!editable.isInstance(target)) {
                throw new IllegalArgumentException("Target class [" + target.getClass().getName() +
                        "] not assignable to Editable class [" + editable.getName() + "]");
            }
            actualEditable = editable;
        }
        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
        List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);

        for (PropertyDescriptor targetPd : targetPds) {
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
                PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null &&
                            ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
                        try {
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }
                            Object value = readMethod.invoke(source);
                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }
                            writeMethod.invoke(target, value);
                        } catch (Throwable ex) {
                            throw new FatalBeanException(
                                    "Could not copy property '" + targetPd.getName() + "' from source to target", ex);
                        }
                    }
                }
            }
        }
    }


}