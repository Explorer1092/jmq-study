package com.jimingqiang.study.elasticsearch;

import com.google.common.collect.Lists;
import com.jimingqiang.study.utils.ConfigUtil;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by QDHL on 2017/6/6.
 */
public class EsClientFactory {
    private static final Logger logger = LoggerFactory.getLogger(EsClientFactory.class);

    private static String clusterName;
    private static String clusterNodes;


    private static void initParams() {
        if (StringUtils.isBlank(clusterNodes)) {
//            clusterName = ConfigUtil.getProp("elasticsearch_cluster_name","jmq-cluster");
            clusterName = "jmq-cluster";
//            clusterNodes = ConfigUtil.getProp("elasticsearch_cluster_nodes","10.39.72.157:9300");
            clusterNodes = "127.0.0.1:9301";
        }
    }


    public static Client getClient() throws Exception {
        initParams();
        Settings settings = Settings.builder()
                .put("cluster.name", clusterName)
                .put("client.transport.sniff", false)
                .build();
        logger.info("获取es链接 clusterName:{},clusterNodes:{}",clusterName,clusterNodes);
        String[] nodes = clusterNodes.split(";");
        List<InetSocketTransportAddress> list = Lists.newArrayList();
        for (String node : nodes) {
            String[] n = node.split(":");
            logger.info("IP:{},prot:{}",n[0],n[1]);
            list.add(new InetSocketTransportAddress(InetAddress.getByName(n[0]), Integer.parseInt(n[1])));
        }
        InetSocketTransportAddress[] addresses = new InetSocketTransportAddress[list.size()];
        list.toArray(addresses);
        Client client = new PreBuiltTransportClient(settings).addTransportAddresses(addresses);
        return client;
    }

    public static void closeClient(Client esClient) {
        if (esClient != null) {
            esClient.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Client client = getClient();

        Map<String, Object> map = new HashMap<String, Object>();
        // map.put("name", "Smith Wang");
        map.put("name", "Smith Chen");
        // map.put("age", 20);
        map.put("age", 5);
        // map.put("interests", new String[]{"sports","film"});
        map.put("interests", new String[] { "reading", "film" });
        // map.put("about", "I love to go rock music");
        map.put("about", "I love to go rock climbing");


        IndexResponse response = client.prepareIndex("megacorp", "employee", "123")
                .setSource(map).get();
        System.out.println("写入数据结果=" + response.status().getStatus() + "！id=" + response.getId());
        closeClient(client);
    }



}
