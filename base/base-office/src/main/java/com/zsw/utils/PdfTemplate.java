package com.zsw.utils;

import java.io.OutputStream;

/**
 * @author ZhangShaowei on 2021/11/11 11:50
 */
public interface PdfTemplate {

    /**
     * html 导出 pdf
     *
     * @param html         input html
     * @param outputStream os
     */
    void htmlToPdf(String html, OutputStream outputStream);

}
