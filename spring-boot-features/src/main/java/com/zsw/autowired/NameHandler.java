package com.zsw.autowired;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author ZhangShaowei on 2021/12/27 9:34
 */
@Getter
@Setter
@AllArgsConstructor
public class NameHandler {

    private final List<String> names;

}
