package com.zsw.quartz.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LayuiData {

    private Integer count;
    private Integer code;
    private String msg;
    private List data;
}
