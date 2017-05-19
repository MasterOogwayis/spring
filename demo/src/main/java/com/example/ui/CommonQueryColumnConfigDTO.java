package com.example.ui;

/**
 * @author ZhangShaowei on 2017/5/12 9:42
 */

public class CommonQueryColumnConfigDTO {

    /**
     * 字段名
     */
    private String name;

    /**
     * 显示列名
     */
    private String title;

    /**
     * 数据类型，0：text文本，1：number数字，2：date日期...
     */
    private String dataType;

    /**
     * 可见性，0：显示，1：隐藏，2：完全隐藏
     */
    private String visibility;

    /**
     * 显示顺序
     */
    private Integer showIndex;

    /**
     * 对齐方式，0：左对齐，1：居中，2：右对齐
     */
    private String align;

    /**
     * 宽度
     */
    private Integer width;

    /**
     * 是否可调整宽度，0：是，1：否
     */
    private String isCanResize;

    /**
     * 是否可排序，0：是，1：否
     */
    private String isCanSort;

    /**
     * 排序类型，0：text按文本，1：int按整数，2：float按浮点数，3：date按日期...
     */
    private String sortType;

    /**
     * 显示格式，0：text按文本，1：integer按整数，2：number按浮点数，3：currency货币，4：date按日期，5：url超链接，6：键值对，
     * 7：复选框，8：Email，99：自定义...
     */
    private String formatter;

    /**
     * 显示格式参数
     */
    private String formatOptions;

    /**
     * 用于页面的其它属性
     */
    private String attrs;

    /**
     * 是否加密，0：是，1：否
     */
    private String isEncrypt;

    /**
     * 加密算法，0：Base64
     */
    private String encryptionAlgorithm;

    /**
     * 是否可查询，0：是，1：否
     */
    private String isCanSearch;

    /**
     * 查询列名，为空是使用显示列名
     */
    private String searchTitle;

    /**
     * 查询条件的操作符，eq：等于，ne：不等于...
     */
    private String searchRuleOperator;

    /**
     * 查询控件，0：TextField，1：Combox，2：Checkbox，3：Radio，4：TextArea，5：DateField，6：EditCombox...
     */
    private String searchWidget;

    /**
     * 查询控件参数
     */
    private String searchWidgetOptions;

    /**
     * 统计标题，如：合计，小计等 和统计类型互斥统计标题优先级高
     */
    private String statTitel;

    /**
     * 统计类型，0：求和，1：求平均... 和统计标题互斥统计标题优先级高
     */
    private String statType;

    /**
     * 查询显示顺序
     */
    private String searchIndex;


    /**  */
    public String getName() {
        return name;
    }

    /**  */
    public void setName(String name) {
        this.name = name;
    }

    /**  */
    public String getTitle() {
        return title;
    }

    /**  */
    public void setTitle(String title) {
        this.title = title;
    }

    /**  */
    public String getDataType() {
        return dataType;
    }

    /**  */
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    /**  */
    public String getVisibility() {
        return visibility;
    }

    /**  */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    /**  */
    public Integer getShowIndex() {
        return showIndex;
    }

    /**  */
    public void setShowIndex(Integer showIndex) {
        this.showIndex = showIndex;
    }

    /**  */
    public String getAlign() {
        return align;
    }

    /**  */
    public void setAlign(String align) {
        this.align = align;
    }

    /**  */
    public Integer getWidth() {
        return width;
    }

    /**  */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**  */
    public String getIsCanResize() {
        return isCanResize;
    }

    /**  */
    public void setIsCanResize(String isCanResize) {
        this.isCanResize = isCanResize;
    }

    /**  */
    public String getIsCanSort() {
        return isCanSort;
    }

    /**  */
    public void setIsCanSort(String isCanSort) {
        this.isCanSort = isCanSort;
    }

    /**  */
    public String getSortType() {
        return sortType;
    }

    /**  */
    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    /**  */
    public String getFormatter() {
        return formatter;
    }

    /**  */
    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

    /**  */
    public String getFormatOptions() {
        return formatOptions;
    }

    /**  */
    public void setFormatOptions(String formatOptions) {
        this.formatOptions = formatOptions;
    }

    /**  */
    public String getAttrs() {
        return attrs;
    }

    /**  */
    public void setAttrs(String attrs) {
        this.attrs = attrs;
    }

    /**  */
    public String getIsEncrypt() {
        return isEncrypt;
    }

    /**  */
    public void setIsEncrypt(String isEncrypt) {
        this.isEncrypt = isEncrypt;
    }

    /**  */
    public String getEncryptionAlgorithm() {
        return encryptionAlgorithm;
    }

    /**  */
    public void setEncryptionAlgorithm(String encryptionAlgorithm) {
        this.encryptionAlgorithm = encryptionAlgorithm;
    }

    /**  */
    public String getIsCanSearch() {
        return isCanSearch;
    }

    /**  */
    public void setIsCanSearch(String isCanSearch) {
        this.isCanSearch = isCanSearch;
    }

    /**  */
    public String getSearchTitle() {
        return searchTitle;
    }

    /**  */
    public void setSearchTitle(String searchTitle) {
        this.searchTitle = searchTitle;
    }

    /**  */
    public String getSearchRuleOperator() {
        return searchRuleOperator;
    }

    /**  */
    public void setSearchRuleOperator(String searchRuleOperator) {
        this.searchRuleOperator = searchRuleOperator;
    }

    /**  */
    public String getSearchWidget() {
        return searchWidget;
    }

    /**  */
    public void setSearchWidget(String searchWidget) {
        this.searchWidget = searchWidget;
    }

    /**  */
    public String getSearchWidgetOptions() {
        return searchWidgetOptions;
    }

    /**  */
    public void setSearchWidgetOptions(String searchWidgetOptions) {
        this.searchWidgetOptions = searchWidgetOptions;
    }

    /**  */
    public String getStatTitel() {
        return statTitel;
    }

    /**  */
    public void setStatTitel(String statTitel) {
        this.statTitel = statTitel;
    }

    /**  */
    public String getStatType() {
        return statType;
    }

    /**  */
    public void setStatType(String statType) {
        this.statType = statType;
    }

    /**  */
    public String getSearchIndex() {
        return searchIndex;
    }

    /**  */
    public void setSearchIndex(String searchIndex) {
        this.searchIndex = searchIndex;
    }
}
