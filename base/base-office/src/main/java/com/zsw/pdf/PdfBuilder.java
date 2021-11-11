package com.zsw.pdf;

import java.io.OutputStream;

/**
 * @author ZhangShaowei on 2021/11/11 11:50
 */
public interface PdfBuilder {

    /**
     * html 导出 pdf
     *
     * @param html         input html
     * @param outputStream os
     */
    void htmlToPdf(String html, OutputStream outputStream);

}
