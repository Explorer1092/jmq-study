package com.jimingqiang.study.canal.utils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.jimingqiang.study.canal.entity.CanalTable;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * created by houys on 2017/4/18
 */
public class FilterUtils {
    private static Map<String, List<String>> columnsMap; // key:表名 ,val: 为列名

    public static void init() {
            List<CanalTable> canalTables = JSON.parseArray(Constant.tables, CanalTable.class);
            columnsMap = Maps.newHashMap();
            for (CanalTable table : canalTables) {
                String tableName = table.getTable();
                columnsMap.put(tableName, table.getColumns());
            }
    }


    public static boolean skipColumn(String table, String column) {
        if (StringUtils.isEmpty(table) || StringUtils.isEmpty(column))
            throw new NullPointerException("**** table or column is empty [table is " + table + " ,column is " + column + "] ****");

        List<String> strings = FilterUtils.columnsMap.get(table);
        if (strings.contains(column)) {
            return false;
        }
        return true;

    }

    public static Map<String, List<String>> getColumnsMap(){
        return columnsMap;
    }

}
