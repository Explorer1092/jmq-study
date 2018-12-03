package com.jimingqiang.study.canal;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.Message;
import com.jimingqiang.study.canal.common.ConnectorUtil;
import com.jimingqiang.study.canal.filter.MessageFilter;
import com.jimingqiang.study.canal.handler.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataPull {
    private static Logger _LOG = LoggerFactory.getLogger(DataPull.class);

    @Value("${sub.rev.batchsize}")
    private String batchSize;
    @Value("${sub.rev.getmessage.interval}")
    private String messageInterval;
    @Value("${sub.system.name}")
    private String systemName;
    
    @Autowired
    private ConnectorUtil connectorUtil;
    @Autowired
    private MessageFilter filter;
    @Autowired
    private MessageHandler handler;

    /*@Autowired
    private MailSendUtils mailSendUtils;*/
    
    private boolean running = true;

    public void start() {
        CanalConnector connector = connectorUtil.connect(true);
        Message message = null;
        long batchId = -1L;
        int size = 0;

        while (running) {
            // 消息读取
            message = connector.getWithoutAck(Integer.parseInt(batchSize));
            batchId = message.getId();
            size = message.getEntries().size();//不会有NPE问题，构造函数已经初始化

            // 未收到消息， 稍后再请求
            if (batchId == -1 || size == 0) {
                try {
                    Thread.sleep(Integer.parseInt(messageInterval));
                } catch (InterruptedException e) {
                    _LOG.error("thread Interrupted : {}", e);
                }
                continue;
            }
            _LOG.info("-----> receive message , batchId : {}", batchId);
            try {
                // 消息过滤， 前移至此， 避免因接收到不需处理的消息而频繁开启/关闭下层事物
                List<Entry> entries = message.getEntries();
                filter.filter(entries); //过滤事务的binlog
                // 消息处理
                if (entries != null && entries.size() > 0) {
                    handler.handleAndAck(entries, connector, batchId);
                } else {
                    connector.ack(batchId);
                }
                _LOG.info("-----> message ack , batchId : {}", batchId);
            } catch (Exception e) {
                // 失败回滚
                connector.rollback(batchId);
                _LOG.error(" message handle error: {}", e);
                stop(systemName + " Application stop, reason : message HANDLE error :" + e, connector);
            }

        }
    }


    private void stop(String alarmContent, CanalConnector connector) {
        running = false;
        if (connector != null) {
            connector.disconnect();
        }
        //mailSendUtils.sendEmail("canal is error", alarmContent);
    }
}
  
