package com.zsw.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.springframework.core.io.ClassPathResource;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author ZhangShaowei on 2021/11/11 8:58
 */
public class PdfBuilderBackup extends PdfPageEventHelper {

    /**
     * 页眉
     */
    public String header = "页眉";

    /**
     * 文档字体大小，页脚页眉最好和文本大小一致
     */
    public int presentFontSize = 12;

    /**
     * 文档页面大小，最好前面传入，否则默认为A4纸张
     */
    public Rectangle pageSize = PageSize.A4;

    // 模板
    public PdfTemplate total;


    public BaseFont bf = null;


    /**
     * 利用基础字体生成的字体对象，一般用于生成中文文字
     */
    public Font fontDetail = null;

    //我是图片.png logo
    public static final String IMAGE_PATH = "C:\\Users\\ZhangShaowei\\Desktop\\2.png";

///-------------------------------我是分界线---------------------------------------//
    /**
     * leftHeader
     */
    private Phrase leftHeader;
    private Phrase rigntHeader;

    static final int marginX = 90;
    static final int marginY = 140;
    static final int marginR = 100;


    public static PdfTemplate tpl;

    private static BaseFont baseFont;
    /**
     * 生成下划线空白占位符
     */
    private static String Blank;
    /**
     * 页眉字体
     */
    public static Font font;
    /**
     * 下划线字体
     */
    private static Phrase blankPhrase;

    public PdfBuilderBackup(String[] header) {
        this.leftHeader = new Phrase(header[0], PdfBuilderBackup.font);
        this.rigntHeader = new Phrase(header[1], PdfBuilderBackup.font);
    }

    static {
        try {
            // 中文字体依赖itext得itext-asian包
            baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 211; i++) {
                sb.append("\u00a0");
            }
            Blank = sb.toString();
            font = new Font(PdfBuilderBackup.baseFont, 12, Font.UNDEFINED);
            blankPhrase = new Phrase(PdfBuilderBackup.Blank, new Font(PdfBuilderBackup.baseFont, Font.DEFAULTSIZE, Font.UNDERLINE));
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Creates a new instance of PdfReportM1HeaderFooter 构造方法.
     *
     * @param yeMei           页眉字符串
     * @param presentFontSize 数据体字体大小
     * @param pageSize        页面文档大小，A4，A5，A6横转翻转等Rectangle对象
     */
    public PdfBuilderBackup(String yeMei, int presentFontSize, Rectangle pageSize) {
        this.header = yeMei;
        this.presentFontSize = presentFontSize;
        this.pageSize = pageSize;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setPresentFontSize(int presentFontSize) {
        this.presentFontSize = presentFontSize;
    }


    /**
     * 档打开时创建模板
     */
    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {
        // 共 页 的矩形的长宽高
        total = writer.getDirectContent().createTemplate(50, 50);
    }


    /**
     * @param writer
     * @param document
     */
    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        int yMargin = -20;
        float top = document.top(yMargin);
        float bottom = document.bottom(-65);

        try {
            if (bf == null) {
                bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", false);
            }
            if (fontDetail == null) {
                // 数据体字体
                fontDetail = new Font(bf, presentFontSize, Font.NORMAL);
            }
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }

//        Image image = image = Image.getInstance(IMAGE_PATH);
        //Image对象
//        image.setAlignment(Image.LEFT | Image.UNDERLYING);
//        image.setBorder(Image.BOX);
//        //大小
//        image.scaleToFit(520, 330);
//        //位置
//        image.setAbsolutePosition(50, 750);
//        try {
//            document.add(image);
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }

        //生成下划线，使用空格占位
        ColumnText.showTextAligned(writer.getDirectContent(),
                Element.ALIGN_LEFT, PdfBuilderBackup.blankPhrase,
                document.right(-25), top, 180);
        //底部下划线
        ColumnText.showTextAligned(writer.getDirectContent(),
                Element.ALIGN_LEFT, PdfBuilderBackup.blankPhrase,
                document.right(-25), bottom, 180);

        //生成左侧页眉
        ColumnText.showTextAligned(writer.getDirectContent(),
                Element.ALIGN_LEFT, leftHeader,
                document.left(), 38, 0);
        //生成右侧页眉
        ColumnText.showTextAligned(writer.getDirectContent(),
                Element.ALIGN_RIGHT, rigntHeader,
                document.right(), 0, 0);

        //生成页脚页数
        ColumnText.showTextAligned(writer.getDirectContent(),
                Element.ALIGN_RIGHT, new Phrase("注：对本模板如有疑问，请及时与java开发组联系 ", new Font(PdfBuilderBackup.baseFont, 8, Font.UNDEFINED)), document.right(), document.bottom(-45), 0);
//        ColumnText.showTextAligned(writer.getDirectContent(),
//                Element.ALIGN_LEFT, new Phrase("合同编号:", PdfBuilder.font), document.left(), document.bottom(-80), 0);

        // 1.写入页眉
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase(header, fontDetail), document.left(), document.top() + 20, 0);

        // 2.写入前半部分的 第 X页/共
        int pageS = writer.getPageNumber();
        String foot1 = "第 " + pageS + " 页 /共";
        Phrase footer = new Phrase(foot1, fontDetail);

        // 3.计算前半部分的foot1的长度，后面好定位最后一部分的'Y页'这俩字的x轴坐标，字体长度也要计算进去 = len
        float len = baseFont.getWidthPoint(foot1, presentFontSize);

        // 4.拿到当前的PdfContentByte
        PdfContentByte cb = writer.getDirectContent();

        // 5.写入页脚 X=215 Y= -80
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, footer, (document.rightMargin()
                + document.right() + document.leftMargin() - document.left() - len) / 2.0F + 215F, document.bottom() - 80, 0);

        // 6.写入页脚2的模板
        cb.addTemplate(total, (document.rightMargin() + document.right() + document.leftMargin()
                // 调节模版显示的位置
                - document.left()) / 2.0F + 215F, document.bottom() - 80);
    }

    /**
     * 关闭文档时，替换模板，完成整个页眉页脚组件
     */
    @Override
    public void onCloseDocument(PdfWriter writer, Document document) {
        // 关闭文档的时候，将模板替换成实际的 Y 值
        total.beginText();
        // 生成的模版的字体、颜色
        total.setFontAndSize(baseFont, presentFontSize);
        String foot2 = " " + (writer.getPageNumber()) + " 页";
        //模版显示的内容
        total.showText(foot2);
        total.endText();
        total.closePath();
    }


    public static void main(String[] args) {

        String recountNew = "NO0000000001";

        // 创建一个文档
        Document document = new Document(PageSize.A4, marginX, marginR, marginY, marginY);
        String path = "C:\\Users\\ZhangShaowei\\Desktop\\2.pdf";
        // pdf输出流
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PdfWriter pdfWriter = null;
        try {
            pdfWriter = PdfWriter.getInstance(document, outputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        // 添加页眉页脚
        pdfWriter.setPageEvent(new PdfBuilderBackup(new String[]{"合同编号:" + recountNew, ""}));

        document.open();

        try (InputStream inputStream = new ClassPathResource("static/content.html").getInputStream()) {
            XMLWorkerHelper.getInstance().parseXHtml(
                    pdfWriter,
                    document,
                    inputStream,
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        document.close();
    }


}
