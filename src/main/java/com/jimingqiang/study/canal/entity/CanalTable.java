package com.jimingqiang.study.canal.entity;

import java.util.List;

/**
 * Created by wanglei on 26/03/2017.
 * JSON序列化
 */
public class CanalTable {
    private String table;
    private List<String> columns;
    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "CanalTable{" +
                "table='" + table + '\'' +
                ", columns=" + columns +
                '}';
    }

}
