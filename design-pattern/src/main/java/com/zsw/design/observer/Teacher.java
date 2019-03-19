package com.zsw.design.observer;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Observable;

/**
 * @author Shaowei Zhang on 2019/3/17 22:04
 **/
@Data
@AllArgsConstructor
public class Teacher extends Observable {

    private String name;

    public void setHomework(String homework) {
        super.setChanged();
        super.notifyObservers(homework);
        super.clearChanged();
    }

}
