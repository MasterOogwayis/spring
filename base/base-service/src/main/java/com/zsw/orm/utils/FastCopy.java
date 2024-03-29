package com.zsw.orm.utils;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * 对象属性复制工具类，生成赋值代码编译运行，和手动编写代码一样快。
 *
 * @author ZhangShaowei
 * @since 2019年07月16日
 */
public class FastCopy {

    private static final ClassPool CLASS_POOL = ClassPool.getDefault();

    private static final Map<CopierKey, Copier> COPIER_MAP = new ConcurrentHashMap<>(32);

    public static <S, T> T copyNotNullProperties(S source, T target) {
        return copyProperties(source, target, Boolean.FALSE, Boolean.TRUE);
    }

    /**
     * 复制字段名含义相同的属性，即忽略字段名中的大小写和下划线。
     * 字段类型必须兼容目标字段类型，包装类型和基本类型之间不会复制。
     */
    public static <S, T> T copySynonyms(S source, T target) {
        return copyProperties(source, target, Boolean.TRUE, Boolean.FALSE);
    }

    public static <S, T> T copyProperties(S source, T target) {
        return copyProperties(source, target, Boolean.FALSE, Boolean.FALSE);
    }

    /**
     * 拷贝对象 source 属性到 target
     * 字段类型必须兼容目标字段类型，包装类型和基本类型之间不会复制。
     *
     * @param source   源对象
     * @param target   目标对象
     * @param synonym  是否忽略字段名中的大小写和下划线
     * @param nullable 目标属性是 null 的时候才拷贝
     * @param <S>      S
     * @param <T>      T
     * @return T
     */
    @SuppressWarnings("unchecked")
    private static <S, T> T copyProperties(S source, T target, boolean synonym, boolean nullable) {
        if (source == null || target == null) {
            return target;
        }
        CopierKey copierKey = new CopierKey(source.getClass(), target.getClass(), synonym, nullable);
        Copier copier = COPIER_MAP.computeIfAbsent(copierKey, FastCopy::create);
        return (T) copier.copy(source, target);
    }

    private static Copier create(CopierKey copierKey) {
        String copyCode = generateCopyCode(copierKey);
        try {
            CtClass copierClass = CLASS_POOL.get(Copier.class.getName());
            CtClass copyClass = CLASS_POOL.makeClass(copierKey.toString());
            copyClass.addInterface(copierClass);
            CtMethod ctMethod = CtMethod.make(copyCode, copyClass);
            copyClass.addMethod(ctMethod);
            return (Copier) copyClass.toClass().getConstructor().newInstance();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private static String generateCopyCode(CopierKey copierKey) {
        if (copierKey.source == null || copierKey.target == null) {
            return "";
        }
        StringBuilder content = new StringBuilder();
        content.append("\npublic Object copy(Object source, Object target) {");
        content.append("\nif (source == null || target == null)");
        content.append("\nreturn target;");
        content.append("\n").append(copierKey.source.getName()).append(" src = (")
                .append(copierKey.source.getName()).append(") source;");
        content.append("\n").append(copierKey.target.getName()).append(" tar = (")
                .append(copierKey.target.getName()).append(") target;");

        try {
            BeanInfo sourceBeanInfo = Introspector.getBeanInfo(copierKey.source);
            BeanInfo targetBeanInfo = Introspector.getBeanInfo(copierKey.target);

            Stream.of(targetBeanInfo.getPropertyDescriptors())
                    .filter(tpd -> tpd.getReadMethod() != null && tpd.getWriteMethod() != null)
                    .forEach(tpd -> Stream.of(sourceBeanInfo.getPropertyDescriptors())
                            .filter(pd -> tpd.getPropertyType().isAssignableFrom(pd.getPropertyType()))
                            .filter(pd -> copierKey.synonym ?
                                    tpd.getName().replace("_", "")
                                            .equalsIgnoreCase(pd.getName().replace("_", ""))
                                    : tpd.getName().equals(pd.getName()))
                            .findFirst()
                            .ifPresent(pd -> {
                                if (copierKey.nullable) {
                                    content.append("\nif(java.util.Objects.isNull(")
                                            .append("tar.").append(tpd.getReadMethod().getName()).append("())) {");
                                }
                                content.append("\ntar.").append(tpd.getWriteMethod().getName())
                                        .append("(").append("src.").append(pd.getReadMethod().getName()).append("());");
                                if (copierKey.nullable) {
                                    content.append("\n}");
                                }
                            }));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

        content.append("\nreturn target;");
        content.append("\n}");
        return content.toString();
    }

    public interface Copier {
        /**
         * 属性拷贝 源 -> 目标
         *
         * @param source 源
         * @param target 目标
         * @return target
         */
        Object copy(Object source, Object target);
    }

    private static class CopierKey {
        final Class source;
        final Class target;
        final boolean synonym;

        final boolean nullable;

        CopierKey(Class source, Class target, boolean synonym) {
            this.source = source;
            this.target = target;
            this.synonym = synonym;
            this.nullable = false;
        }

        CopierKey(Class source, Class target, boolean synonym, boolean nullable) {
            this.source = source;
            this.target = target;
            this.synonym = synonym;
            this.nullable = nullable;
        }

        @Override
        public String toString() {
            return source.getName() + "." + target.getName() + "." + synonym;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof CopierKey)) {
                return false;
            }
            CopierKey copierKey = (CopierKey) o;
            return synonym == copierKey.synonym && Objects.equals(source, copierKey.source)
                    && Objects.equals(target, copierKey.target) && Objects.equals(this.nullable, copierKey.nullable);
        }

        @Override
        public int hashCode() {
            return Objects.hash(source, target, synonym);
        }
    }

}
