package com.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @项目：test
 * @创建者：linmin
 * @创建时间：2018/5/10
 * @公司：汽车易生活
 */
public class A {

    public static void main(String[] args) {
//        List<Integer> list = new CopyOnWriteArrayList<Integer>();
//        list.add(1);
//        list.add(2);
//        list.add(2);
//        Iterator<Integer> iterator = list.iterator();
//        while(iterator.hasNext()){
//
//            Integer next = iterator.next();
//            if(!(list.indexOf(next)==list.lastIndexOf(next))) {
//                list.remove(next);
//            }
//        }
//        System.out.println(list.size());
        List<String> list = new ArrayList<String>();
        long time = new Date().getTime();
        for(int i = 0; i < 1000000; i++){
            list.add("linmin"+i);
        }
        long time1 = new Date().getTime();
        long l = time1 - time;
        System.out.println("耗时："+l+"毫秒");

        long time2 = new Date().getTime();
        List<String> list1 = new LinkedList<String>();
        for(int i = 0; i < 1000000; i++){
            list1.add("linmin"+i);
        }
        long time3 = new Date().getTime();
        long l1 = time3 - time2;
        System.out.println("耗时："+l1+"毫秒");


    }
}

class ThreadPrintNum implements Runnable{

    @Override
    public void run() {

    }
}
