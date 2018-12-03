package com.jimingqiang.study.canal.common;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("connectorUtil")
public class ConnectorUtil {

    private Logger _LOG = LoggerFactory.getLogger(ConnectorUtil.class);

    private CanalConnector connector;

    private String serverIp;

    private String serverPort;

    @Value("${canal.server.instance.name}")
    private String instanceName;

    @Value("${canal.server.instance.subscribe}")
    private String instanceSubscribe;

    private String retryNum;

    private String retryInterval;
    @Value("${canal.zk.addr}")
    private String zkAddr;
    private int retryCount = 0;

    public CanalConnector connect() {

        try {
            _LOG.debug(String.format("connect to Canal server[%s:%s, %s]", serverIp, serverPort, instanceName));
            doConnect();
        } catch (Exception e) {
            _LOG.error("connect fail, retry start: {}", e);
            retry();
        }

        return connector;
    }

    /**
     * canal自带重试机制，用户自己选择是否是有自带的重试机制
     *
     * @param useDefault true 是用自带重试机制 flase 在自带重试机制上增加自己的重试机制
     */
    public CanalConnector connect(boolean useDefault) {
        if (useDefault) {
            connector = CanalConnectors.newClusterConnector(zkAddr, instanceName, "", "");
            connector.connect();
            connector.subscribe(instanceSubscribe);
            return connector;
        }
        return connect();

    }

    public void doConnect() {
        //connector = CanalConnectors.newSingleConnector(new InetSocketAddress(serverIp, Integer.parseInt(serverPort)), instanceName, "", "");\
        connector = CanalConnectors.newClusterConnector(zkAddr, instanceName, "", "");
        connector.connect();
        connector.subscribe(instanceSubscribe);
    }


    private void retry() {
        if (retryCount < Integer.parseInt(retryNum)) {
            retryCount++;
            try {
                Thread.sleep(Integer.parseInt(retryInterval) * 1000);
            } catch (InterruptedException e) {
                _LOG.error("retry sleep error: {}", e);
            }
            try {
                doConnect();
                retryCount = 0;
            } catch (Exception e) {
                _LOG.debug("retry connect fail, time {}" + retryCount);
                retry();
            }
        } else {
            throw new RuntimeException(
                    String.format("canal server connect fail:[%s:%s instance - %s]", serverIp, serverPort, instanceName));
        }
    }
}
