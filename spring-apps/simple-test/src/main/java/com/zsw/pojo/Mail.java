package com.zsw.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author ZhangShaowei on 2020/2/11 15:35
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Mail implements Serializable {
    private static final long serialVersionUID = 7021722418437904749L;

    /**
     * 邮件地址
     */
    private String mailAddress;

    /**
     * 邮件内容
     */
    private String content;

    /**
     * 附件路径
     */
    @Singular
    private List<String> attachmentPaths;

}
