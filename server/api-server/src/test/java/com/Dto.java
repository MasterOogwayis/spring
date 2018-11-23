package com;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;

import java.io.Serializable;
import java.util.List;

/**
 * @author Shaowei Zhang on 2018/11/21 22:57
 **/
@Data
@Builder
public class Dto implements Serializable {

    @NonNull
    private String name;

    @Singular("address")
    private List<String> list;

}
