package com.zsw.pdf;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * @author ZhangShaowei on 2021/11/11 16:17
 */
public class HtmlToPdfHelper {

    @SneakyThrows
    public static void main(String[] args) {
        File htmlFile = new ClassPathResource("static/content.html").getFile();
        String html = Files.readString(htmlFile.toPath());

        wrap(html);

    }

    /**
     * @param values 候选值
     * @return
     */
    public static String createCheckbox(Collection<String> values) {
        StringBuilder sb = new StringBuilder();
        sb.append("<div>");
        values.forEach(v -> {
            sb.append("<input type=\"checkbox\" value=\"").append(v).append("\" />");
        });
        sb.append("</div>");
        return sb.toString();
    }

    public static String createRadio(String name, Collection<String> values) {
        StringBuilder sb = new StringBuilder();
        sb.append("<div>");
        values.forEach(v -> {
            sb
                    .append("<input name=\"")
                    .append(name)
                    .append("\" type=\"radio\" value=\"")
                    .append(v)
                    .append("\" />");
        });
        sb.append("</div>");
        return sb.toString();
    }

    public static String inputText(String name) {
        return "<input name=\"" + name + "\" type=\"text\"/>";
    }

    public static String textarea(String name, int rows, int cols) {
        return "<textarea rows=\"" + rows + "\" cols=\"" + cols + "\"  name=\"" + name + "\" type=\"text\"></textarea>";
    }


    /**
     * @param options
     * @return
     */
    public static String createSelect(Map<String, String> options) {
        StringBuilder sb = new StringBuilder();
        sb.append("<div>");
        sb.append("<select>");
        options.forEach((k, v) -> {
            sb.append("<option value=\"").append(v).append("\">");
            sb.append(k);
            sb.append("</option");
        });
        sb.append("</select>");
        sb.append("</div>");
        return sb.toString();
    }

    /**
     * 将输入 html 中包含的 select, checkbox, ratio 标签装换成纯文字方便导出 pdf
     *
     * @param html 输入 html
     * @return 替换后的 html
     */
    public static String wrap(String html) {
        Document doc = Jsoup.parse(html, "UTF-8");
        doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml);


        // 1. select
        Elements select = doc.getElementsByTag("select");
        select.forEach(e -> {
            String val = e.children().select("option[selected='selected']").first().val();
            e.parent().html(val);
        });

        // 2. checkbox
        doc.select("input[type='checkbox']")
                .stream()
                .collect(groupingBy(Element::parent, collectingAndThen(toList(), Elements::new)))
                .forEach((parent, elements) -> {
                    String collect = elements.select("input[checked=checked]").stream().map(Element::val)
                            .collect(Collectors.joining(","));
                    parent.html(collect);
                });


        // 3. ratio
        doc.select("input[type='radio']")
                .stream()
                .collect(groupingBy(Element::parent, collectingAndThen(toList(), Elements::new)))
                .forEach((parent, elements) -> {
                    String collect = elements.select("input[checked=checked]").first().val();
                    parent.html(collect);
                });


        doc.select("input[type='date']")
                .forEach(e -> e.parent().html(e.val()));

        return doc.html();
    }

}
