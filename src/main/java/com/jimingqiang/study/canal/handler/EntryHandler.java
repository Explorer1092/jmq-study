package com.jimingqiang.study.canal.handler;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.google.protobuf.InvalidProtocolBufferException;
import com.jimingqiang.study.canal.utils.FilterUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Component
public class EntryHandler extends AbstactHandler {

    private Logger _LOG = LoggerFactory.getLogger(EntryHandler.class);

    @Transactional
    @Override
    public void handleAndAck(List<Entry> entries, CanalConnector connector, long batchId)
            throws InvalidProtocolBufferException,SQLException {
        //拼接SQL
        List<String> sqls = generateSql(entries);
        // 执行SQL
        excuteSqls(sqls);
        //ack 保证原子性
        connector.ack(batchId);

    }


    @Override
    protected boolean hasTableName(String tableName) {
        Map<String, List<String>> sourceTableAndTargetTable = FilterUtils.getColumnsMap();
        return sourceTableAndTargetTable.containsKey(tableName);
    }
}
