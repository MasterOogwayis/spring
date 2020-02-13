package com.zsw.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author ZhangShaowei on 2020/2/11 15:35
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Sms implements Serializable {
    private static final long serialVersionUID = 7021722418437904749L;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 短信内容
     */
    private String content;

}
