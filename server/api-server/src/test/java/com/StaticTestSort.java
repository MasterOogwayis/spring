package com;

import org.apache.commons.lang.math.RandomUtils;

import java.util.*;

/**
 * @author ZhangShaowei on 2018/10/10 15:33
 **/
public class StaticTestSort {


    public static void main(String[] args) {

        int size = 10000;

        List<Integer> list = new ArrayList<>(size);
        List<Integer> list2 = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            int rand = RandomUtils.nextInt(size);
            list.add(rand);
            list2.add(rand);
        }
        long timer = System.currentTimeMillis();
        list.sort(Integer::compareTo);
        System.out.println("传统排序耗时: " + (System.currentTimeMillis() - timer)
                + " max=" + list.get(list.size()-1) + " min=" + list.get(0));
        timer = System.currentTimeMillis();
        list2 = sort(list2);
        System.out.println("计数排序法：" + (System.currentTimeMillis() - timer)
                + " max=" + list2.get(list.size()-1) + " min=" + list2.get(0));


    }


    /**
     * 计数排序法
     *
     * @param list
     * @return
     */
    public static List<Integer> sort(List<Integer> list) {
        int max = list.stream().max(Integer::compareTo).get();
        int[] countArr = new int[max + 1];

        list.forEach(value -> countArr[value]++);

        List<Integer> sortedList = new ArrayList<>(list.size());

        for (int i = 0; i < countArr.length; i++) {
            for (int j = 0; j < countArr[i]; j++) {
                sortedList.add(i);
            }
        }
        return sortedList;
    }




}
