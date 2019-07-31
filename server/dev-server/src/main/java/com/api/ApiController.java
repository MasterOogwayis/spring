package com.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author ZhangShaowei on 2018/1/12 14:48
 */
@Validated
@RestController
@RequestMapping("dev")
public class ApiController {

    /**
     * logger
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * @param msg
     * @return
     */
    @GetMapping("log")
//    @ApiOperation("日志输出")
    public String log(@NotBlank(message = "不能为空") String msg, @NotNull @Range(min = 0, max = 10) Integer age) {
        this.logger.info("msg: {}, age: {}", msg, age);
        return "{\"message\": " + msg + "}";
    }

    @PostMapping("log")
//    @ApiOperation("日志输出")
    public String logPost(@Validated @RequestBody Message msg) {
        this.logger(msg);
        return "{\"success\": true}";
    }

    @PostMapping("email")
//    @ApiOperation("日志输出")
    public String email(@Validated @RequestBody Email email) {
        this.logger(email);
        return "{\"success\": true}";
    }

    @Data
    @NoArgsConstructor
    static class Message {
        @NotBlank(message = "msg不能为空串")
        @Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5]+$")
        private String message;

        @NotNull
        @Range(min = 0, max = 99)
        private Integer age;
    }

    @Data
    @NoArgsConstructor
    static class Email {

        @NotBlank
        private String address;

        @Valid
        private Message message;
    }

    private void logger(Object object) {
        this.logger.error("message error: {}", object);
        this.logger.warn("message warn: {}", object);
        this.logger.debug("message debug: {}", object);
        this.logger.info("message info: {}", object);
    }

}
