package com.jimingqiang.study.elasticsearch.test;

import com.jimingqiang.study.elasticsearch.EsClientFactory;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class ElasticSearchTest {


    @Test
    public void indexDocumentTest() throws Exception {
        Client client = EsClientFactory.getClient();

        IndexResponse response = client.prepareIndex("twitter", "doc", "1")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("user", "kimchy")
                        .field("postDate", new Date())
                        .field("message", "trying out Elasticsearch")
                        .endObject()
                )
                .get();

        System.out.println(response.getIndex());
        System.out.println(response.getType());
        System.out.println(response.getId());
        System.out.println(response.status());

        EsClientFactory.closeClient(client);

    }



}
