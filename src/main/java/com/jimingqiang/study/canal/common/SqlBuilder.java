package com.jimingqiang.study.canal.common;

import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.EventType;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.jimingqiang.study.canal.utils.FilterUtils;
import com.jimingqiang.study.canal.utils.StrUtils;

import java.util.List;

public class SqlBuilder {

    public static final String INSERT = "insert";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";

    public static String buildSql(String schema, String table, EventType eventType, RowData rowData) {

        String sql = null;
        /*if (eventType == EventType.DELETE) {
            sql = buildSqlDelete(schema, table, rowData.getBeforeColumnsList());
        } else*/
        if (eventType == EventType.UPDATE) {
            sql = buildSqlUpdate(schema, table, rowData.getAfterColumnsList());
        } else if (eventType == EventType.INSERT) {
            sql = buildSqlInsert(schema, table, rowData.getAfterColumnsList());
        }

        return sql;
    }

    public static String buildSqlInsert(String schema, String table, List<Column> columns) {

        StringBuilder colunmBuffer = new StringBuilder();
        StringBuilder valueBuffer = new StringBuilder();

        for (Column column : columns) {
            if (FilterUtils.skipColumn(table, column.getName())) {
                continue;
            }
            colunmBuffer.append(column.getName()).append(",");
            String val = replaceStrForSpecial(column);
            valueBuffer.append(column.getIsNull() ? null : "'" + val + "'").append(",");
        }
        if (colunmBuffer.length() <= 0) {
            return null;
        }
        colunmBuffer.deleteCharAt(colunmBuffer.length() - 1);
        valueBuffer.deleteCharAt(valueBuffer.length() - 1);

        StringBuilder buffer = new StringBuilder();
        buffer.append(INSERT).append(" into ").append(table).append(" (").append(colunmBuffer).append(")")
                .append(" values( ").append(valueBuffer).append(")");

        return buffer.toString();

    }


    public static String buildSqlUpdate(String schema, String table, List<Column> columns) {

        StringBuilder valueBuffer = new StringBuilder();
        StringBuilder whereBuffer = new StringBuilder();

        for (Column column : columns) {
            if (column.getIsKey()) {
                // where段拼入and关键字， 用以支持联合主键, 删除SQL同理
                whereBuffer.append(" and " + column.getName() + "=" + "'" + replaceStrForSpecial(column) + "' ");
                continue;
            }

            if (column.getUpdated()) {
                if (FilterUtils.skipColumn(table, column.getName())) {
                    continue;
                }
                valueBuffer.append(column.getName()).append(" = ");
                String val = replaceStrForSpecial(column);
                valueBuffer.append(column.getIsNull() ? null : "'" + val + "'").append(",");
            }

        }

        if (valueBuffer.length() <= 0) {//
            return null;
        }

        String where = whereBuffer.toString().replaceFirst("and", "");
        valueBuffer.deleteCharAt(valueBuffer.length() - 1);

        StringBuilder buffer = new StringBuilder();
        buffer.append(UPDATE + " ").append(table).append(" set ");
        buffer.append(valueBuffer);
        buffer.append(" where ").append(where);

        return buffer.toString();
    }

    public static String buildSqlDelete(String schema, String table, List<Column> columns) {

        StringBuilder whereBuffer = new StringBuilder();
        for (Column column : columns) {
            if (column.getIsKey()) {
                whereBuffer.append(" and " + column.getName() + "=" + "'" + replaceStrForSpecial(column) + "' ");
            }
        }
        String where = whereBuffer.toString().replaceFirst("and", "");

        StringBuffer buffer = new StringBuffer();
        buffer.append(DELETE + " ").append("from ").append(table).append(" where ").append(where);
        return buffer.toString();
    }

    private static String replaceStrForSpecial(Column column) {
        String mysqlType = column.getMysqlType();
        String val = column.getValue();
        if (mysqlType.startsWith("varchar") || mysqlType.startsWith("char") || mysqlType.startsWith("blob") || mysqlType.startsWith("text")) {
            val = StrUtils.escapeSQL(column.getValue());
        }
        return val;
    }
}
  
