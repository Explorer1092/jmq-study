package com.jimingqiang.study.guava;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

/**
 * Created by QDHL on 2018/9/16.
 *
 * @author mingqiang ji
 *
 * 通常来说，当你想使用多个键做索引的时候，你可能会用类似Map<FirstName, Map<LastName, Person>>的实现，这种方式很丑陋，使用上也不友好。Guava为此提供了新集合类型Table，
 * 它有两个支持所有类型的键：”行”和”列”。Table提供多种视图，以便你从各种角度使用它：
 */
public class GuavaTable {


    @Test
    public void testTable(){

        Table<String, String, Integer> table= HashBasedTable.create();
        table.put("黎明", "javase", 80);
        table.put("黎明", "oracle", 100);
        table.put("郭富城", "javase", 90);
        table.put("刘德华", "oracle", 95);

        Set<Table.Cell<String, String, Integer>> cells=table.cellSet();
        //每行来输出
        for(Table.Cell<String, String, Integer> c:cells){
            System.out.println(c.getRowKey()+"----->"+c.getColumnKey()+"----->"+c.getValue());
        }



        System.out.println("=====================================");
        //得到装着学生的set再输出
        Set<String> stus=table.rowKeySet();
        for(String temp:stus){
            System.out.println(temp);
            Map<String, Integer> row = table.row(temp);

            for (Map.Entry<String, Integer> entry : row.entrySet()) {
                System.out.println(entry.getKey());
                System.out.println(entry.getValue());
            }

        }
        System.out.println("=====================================");
        Set<String> courses=table.columnKeySet();
        for(String c:courses){
            System.out.println(c);
            Map<String, Integer> scores=table.column(c);
            for (Map.Entry<String, Integer> entry : scores.entrySet()) {
                System.out.println(entry.getKey());
                System.out.println(entry.getValue());
            }
        }




    }
}
