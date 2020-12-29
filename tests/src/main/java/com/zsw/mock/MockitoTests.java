package com.zsw.mock;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Matchers.shortThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author ZhangShaowei on 2020/12/28 16:15
 */
public class MockitoTests {

    public static void main(String[] args) {
        t2();
    }


    private static void t1() {
        List<String> mockList = mock(List.class);
        mockList.add("one");
        mockList.clear();
        verify(mockList, times(1)).add("one");
        verify(mockList, times(1)).clear();
        verify(mockList, times(1)).add("two");
    }

    private static void t2() {
        LinkedList mockedList = mock(LinkedList.class);
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());

        System.out.println(mockedList.get(0));
        System.out.println(mockedList.get(999));
//        System.out.println(mockedList.get(1));

        verify(mockedList).get(0);

    }

}
