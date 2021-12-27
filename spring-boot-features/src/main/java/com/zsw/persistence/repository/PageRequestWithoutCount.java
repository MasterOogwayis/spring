//package com.zsw.persistence.repository;
//
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//
///**
// * @author ZhangShaowei on 2021/8/26 15:28
// */
//public class PageRequestWithoutCount extends PageRequest {
//    /**
//     * Creates a new {@link PageRequest} with sort parameters applied.
//     *
//     * @param page zero-based page index, must not be negative.
//     * @param size the size of the page to be returned, must be greater than 0.
//     * @param sort must not be {@literal null}, use {@link Sort#unsorted()} instead.
//     */
//    protected PageRequestWithoutCount(int page, int size, Sort sort) {
//        super(page, size, sort);
//    }
//
//    @Override
//    public boolean isPaged() {
//        return false;
//    }
//}
