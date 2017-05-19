package com.example.ui;

import java.util.List;

/**
 * @author ZhangShaowei on 2017/5/12 9:44
 */

public class CommonQueryConfigDTO {

    /**
     * 通查名
     */
    private String name;

    /**
     * 通查标识
     */

    private String mark;


    /**
     * 每页显示条数
     */
    private Integer pageSize;

    /**
     * 定时刷新时间，0：不定时刷新
     */
    private Integer timeRefresh;

    /**
     * 是否初始查询，0：是，1：否
     */
    private String isQueryFrist;

    /**
     * 是否可选择列，0：是，1：否
     */
    private String isCanSelectColumn;

    /**
     * 列选择类型，0：单选，1：多选
     */
    private String columnSelectType;

    /**
     * 是否可查询，0：是，1：否
     */
    private String isCanSearch;

    /**
     * 查询组件类型，0：查询窗口，1：查询面板
     */
    private String searchModuleType;

    /**
     * 是否初始显示查询组件，0：是，1：否
     */
    private String isShowSearchModuleFrist;

    /**
     * 是否查询后隐藏组件，0：是，1：否
     */
    private String isHideSearchModuleQueried;

    /**
     * 是否可导出数据，0：是，1：否
     */
    private String isCanExportData;

    /**
     * 显示导出类型，0：显示所有，1：显示导出本页，2：显示按条件导出
     */
    private String showExportType;

    /**
     * 是否显示统计数据，0：是，1：否
     */
    private String isShowStat;

    /**
     * 是否列宽自适应，0：是，1：否
     */
    private String isToShrink;

    /**
     * 是否显示页码条，0：是，1：否
     */
    private String isShowPager;

    /**
     * 通查列配置
     */
    private List<CommonQueryColumnConfigDTO> columnConfigs;

    /**
     *
     */
    private List<CommonQueryColumnConfigDTO> columnConfigsForQueryComponent;


    /**  */
    public String getName() {
        return name;
    }

    /**  */
    public void setName(String name) {
        this.name = name;
    }

    /**  */
    public String getMark() {
        return mark;
    }

    /**  */
    public void setMark(String mark) {
        this.mark = mark;
    }

    /**  */
    public Integer getPageSize() {
        return pageSize;
    }

    /**  */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**  */
    public Integer getTimeRefresh() {
        return timeRefresh;
    }

    /**  */
    public void setTimeRefresh(Integer timeRefresh) {
        this.timeRefresh = timeRefresh;
    }

    /**  */
    public String getIsQueryFrist() {
        return isQueryFrist;
    }

    /**  */
    public void setIsQueryFrist(String isQueryFrist) {
        this.isQueryFrist = isQueryFrist;
    }

    /**  */
    public String getIsCanSelectColumn() {
        return isCanSelectColumn;
    }

    /**  */
    public void setIsCanSelectColumn(String isCanSelectColumn) {
        this.isCanSelectColumn = isCanSelectColumn;
    }

    /**  */
    public String getColumnSelectType() {
        return columnSelectType;
    }

    /**  */
    public void setColumnSelectType(String columnSelectType) {
        this.columnSelectType = columnSelectType;
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
    public String getSearchModuleType() {
        return searchModuleType;
    }

    /**  */
    public void setSearchModuleType(String searchModuleType) {
        this.searchModuleType = searchModuleType;
    }

    /**  */
    public String getIsShowSearchModuleFrist() {
        return isShowSearchModuleFrist;
    }

    /**  */
    public void setIsShowSearchModuleFrist(String isShowSearchModuleFrist) {
        this.isShowSearchModuleFrist = isShowSearchModuleFrist;
    }

    /**  */
    public String getIsHideSearchModuleQueried() {
        return isHideSearchModuleQueried;
    }

    /**  */
    public void setIsHideSearchModuleQueried(String isHideSearchModuleQueried) {
        this.isHideSearchModuleQueried = isHideSearchModuleQueried;
    }

    /**  */
    public String getIsCanExportData() {
        return isCanExportData;
    }

    /**  */
    public void setIsCanExportData(String isCanExportData) {
        this.isCanExportData = isCanExportData;
    }

    /**  */
    public String getShowExportType() {
        return showExportType;
    }

    /**  */
    public void setShowExportType(String showExportType) {
        this.showExportType = showExportType;
    }

    /**  */
    public String getIsShowStat() {
        return isShowStat;
    }

    /**  */
    public void setIsShowStat(String isShowStat) {
        this.isShowStat = isShowStat;
    }

    /**  */
    public String getIsToShrink() {
        return isToShrink;
    }

    /**  */
    public void setIsToShrink(String isToShrink) {
        this.isToShrink = isToShrink;
    }

    /**  */
    public String getIsShowPager() {
        return isShowPager;
    }

    /**  */
    public void setIsShowPager(String isShowPager) {
        this.isShowPager = isShowPager;
    }

    /**  */
    public List<CommonQueryColumnConfigDTO> getColumnConfigs() {
        return columnConfigs;
    }

    /**  */
    public void setColumnConfigs(List<CommonQueryColumnConfigDTO> columnConfigs) {
        this.columnConfigs = columnConfigs;
    }

    /**  */
    public List<CommonQueryColumnConfigDTO> getColumnConfigsForQueryComponent() {
        return columnConfigsForQueryComponent;
    }

    /**  */
    public void setColumnConfigsForQueryComponent(List<CommonQueryColumnConfigDTO> columnConfigsForQueryComponent) {
        this.columnConfigsForQueryComponent = columnConfigsForQueryComponent;
    }
}
