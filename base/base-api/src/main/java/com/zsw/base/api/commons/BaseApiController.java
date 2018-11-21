package com.zsw.base.api.commons;

import ch.qos.logback.classic.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zsw.base.client.ResultData;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @author ZhangShaowei on 2017/5/9 10:32
 */
public class BaseApiController {

    /**
     *
     */
    protected final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());


    /**
     *
     */
    protected final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();


    /**
     * @param ex exception
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    protected Object exceptionHandler(Exception ex) {
        if (logger.isDebugEnabled()) {
            ex.printStackTrace();
        }
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException me = (MethodArgumentNotValidException) ex;
            List<ObjectError> list = me.getBindingResult().getAllErrors();
            List<String> data = list.stream()
                    .map(FieldError.class::cast)
                    .map(fieldError -> fieldError.getField() + "=" + fieldError.getRejectedValue()
                            + ", error=" + fieldError.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResultData.failure(data.toString());
        } else if (ex instanceof ConstraintViolationException) {
            ConstraintViolationException ce = (ConstraintViolationException) ex;
            Set<ConstraintViolation<?>> set = ce.getConstraintViolations();
            return set.stream()
                    .map(constraintViolation -> {
                        PathImpl pathImpl = (PathImpl) constraintViolation.getPropertyPath();
                        return pathImpl.getLeafNode().getName() + "=" + pathImpl.getLeafNode().getValue()
                                + ", error=" + constraintViolation.getMessage();
                    })
                    .collect(Collectors.toList());
        }
        return ex;
    }

//    protected <T> MiniAppApiRequest<T> toApiRequest(String body, String key, Class<T> clazz) throws Exception {
//        MiniAppApiRequest<T> apiRequest = this.fromJson(this.decrpyt(body, key), new ParameterizedType() {
//            @Override
//            public Type[] getActualTypeArguments() {
//                return new Type[]{clazz};
//            }
//
//            @Override
//            public Type getRawType() {
//                return MiniAppApiRequest.class;
//            }
//
//            @Override
//            public Type getOwnerType() {
//                return null;
//            }
//        });
//        return apiRequest;
//    }

//    @PostMapping("print")
//    public ResponseEntity<byte[]> post(
//            @RequestParam String code,
//            @RequestParam String token,
//            HttpServletResponse response) throws Exception {
//        try {
//            String requestBody = this.decrpyt(code, token);
//            FlApiResult<?> flApiResult = this.refcService.handle(FlApiKey.API_534.getApiKey(), requestBody);
//            // 下载文件单独处理
//            //noinspection unchecked
//            StringMap<String> map = (StringMap<String>) flApiResult.getResult();
//
//            if (!"success".equals(flApiResult.getStatus())) {
//                throw new Exception(flApiResult.getMessage());
//            }
//
//            if (!map.containsKey("reportView")) {
//                throw new Exception(map.get("massge"));
//            }
//
//            Dto534 dto534 = this.gson.fromJson(requestBody, Dto534.class);
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
//            String fileName = URLEncoder.encode(
//                    PrintType.get(dto534.getConsultTypeUUid()).getDesc(), "utf-8");
//            headers.add(
//                    HttpHeaders.CONTENT_DISPOSITION,
//                    "attachment; filename=\"" + fileName + ".pdf\""
//            );
//            headers.add(HttpHeaders.PRAGMA, "no-cache");
//            headers.add(HttpHeaders.EXPIRES, "0");
//
//            // 16位2进制 字符
//            String reportView = map.get("reportView");
//            byte[] data = Hex.decode(reportView);
//            return ResponseEntity
//                    .ok()
//                    .headers(headers)
//                    .contentLength(data.length)
//                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                    .body(data);
//        } catch (Exception e) {
//            String message;
//            if (e instanceof JsonSyntaxException) {
//                message = "非法请求";
//            } else {
//                message = e.getMessage();
//            }
//            e.printStackTrace();
//            response.setStatus(403);
////            response.sendError(403);
//            response.setCharacterEncoding("utf-8");
//            response.setContentType("text/html;charset=utf-8");
//            try (PrintWriter writer = response.getWriter()) {
//                writer.write("<h1 style=\"text-align:center;\">Error</h1>");
//                writer.write("<h3 style=\"text-align:center;\">" + message + "</h3>");
//                writer.flush();
//            }
//            return null;
//        }
//    }

}
