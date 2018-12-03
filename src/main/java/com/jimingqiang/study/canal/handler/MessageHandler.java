package com.jimingqiang.study.canal.handler;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.google.protobuf.InvalidProtocolBufferException;

import java.sql.SQLException;
import java.util.List;

public interface MessageHandler {

    void handleAndAck(List<Entry> entries, CanalConnector connector, long batchId) throws InvalidProtocolBufferException,SQLException;
}
  
