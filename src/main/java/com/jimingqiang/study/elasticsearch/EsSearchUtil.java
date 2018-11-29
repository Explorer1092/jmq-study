package com.jimingqiang.study.elasticsearch;

import com.jimingqiang.study.elasticsearch.annotation.EsDocument;
import com.jimingqiang.study.elasticsearch.annotation.EsField;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by ZSX on 2017/11/15.
 *
 * @author ZSX
 */
public class EsSearchUtil {
    private static final Logger logger = LoggerFactory.getLogger(EsSearchUtil.class);


    /**
     * 创建索引
     *
     * @param indexName
     * @return
     */
    public static Boolean createIndex(String indexName) {
        Client client = null;
        try {
            client = EsClientFactory.getClient();
            CreateIndexResponse response = client.admin().indices()
                    .prepareCreate(indexName).get();
            return response.isAcknowledged();
        } catch (Exception e) {
            logger.error("es创建索引【{}】出现异常，{}", indexName, e.getMessage(), e);
        } finally {
            EsClientFactory.closeClient(client);
        }
        return false;
    }

    /**
     * 创建索引（支持自定义分词器）
     *
     * @param indexName
     * @return
     */
    public static Boolean createIndexWithAnalyzer(String indexName) {
        Client client = null;
        try {
            client = EsClientFactory.getClient();
            XContentBuilder settingsBuilder = XContentFactory.jsonBuilder()
                    .startObject()
                    .startObject("analysis")
                    .startObject("analyzer")
                    .startObject("ngram_analyzer")
                    .field("tokenizer", "wain_ngram_tokenizer")
                    .endObject()
                    .endObject()

                    .startObject("tokenizer")
                    .startObject("wain_ngram_tokenizer")
                    .field("type", "ngram")
                    .field("min_gram", 1)
                    .field("max_gram", 50)
                    //                    .field("token_chars", "letter, digit") // 不设置默认为全部
                    .endObject()
                    .endObject()
                    .endObject()
                    .endObject();

            CreateIndexResponse response = client.admin().indices()
                    .prepareCreate(indexName)
                    .setSettings(settingsBuilder)
                    .get();
            return response.isAcknowledged();
        } catch (Exception e) {
            logger.error("es创建索引【{}】出现异常，{}", indexName, e.getMessage(), e);
        } finally {
            EsClientFactory.closeClient(client);
        }
        return false;
    }

    /**
     * 判断索引是否已经存在
     *
     * @param indexName
     * @return
     */
    public static Boolean existIndex(String indexName) {
        Client client = null;
        try {
            client = EsClientFactory.getClient();
            IndicesExistsResponse existsResponse = client.admin().indices().prepareExists(indexName).get();
            return existsResponse.isExists();
        } catch (Exception e) {
            logger.error("es判断索引【{}】是否已存在时出现异常，{}", indexName, e.getMessage(), e);
        } finally {
            EsClientFactory.closeClient(client);
        }
        return false;
    }

    /**
     * 删除索引 **警告，该操作不可逆，请谨慎操作**
     *
     * @param indexName
     * @return
     */
    private static Boolean deleteIndex(String indexName) {
        Client client = null;
        try {
            client = EsClientFactory.getClient();
            DeleteIndexResponse response = client.admin().indices().prepareDelete(indexName).get();
            return response.isAcknowledged();
        } catch (Exception e) {
            logger.error("es删除索引【{}】时出现异常，{}", indexName, e.getMessage(), e);
        } finally {
            EsClientFactory.closeClient(client);
        }
        return false;
    }

    /**
     * 给索引添加数据类型
     *
     * @param indexName
     * @param indexType
     * @param builder
     * @return
     */
    public static Boolean addMapping(String indexName, String indexType, XContentBuilder builder) {
        Client client = null;
        try {
            client = EsClientFactory.getClient();
            PutMappingRequest mappingRequest = Requests.putMappingRequest(indexName).type(indexType).source(builder);
            PutMappingResponse response = client.admin().indices().putMapping(mappingRequest).actionGet();
            return response.isAcknowledged();
        } catch (Exception e) {
            logger.error("es给索引【{}】添加数据类型【{}】时出现异常，{}", indexName, indexType, e.getMessage(), e);
        } finally {
            EsClientFactory.closeClient(client);
        }
        return false;
    }

    public static Boolean addMapping(Class clazz) throws IOException {
        EsDocument esDocument = (EsDocument) clazz.getAnnotation(EsDocument.class);
        if (esDocument != null) {
            XContentBuilder builder = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("properties")
                    .startObject();
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field field : declaredFields) {
                EsField esField = field.getAnnotation(EsField.class);
                if (esField != null) {
                    String name = esField.name();
                    String type = esField.type().getValue();
                    String index = esField.index().getValue();
                    String analyzer = esField.analyzer();
                    builder.field(name).startObject().field("type", type);
                    if (StringUtils.isNotEmpty(index)) {
                        builder.field("index", index);
                    }
                    if (StringUtils.isNotEmpty(analyzer)) {
                        builder.field("analyzer", analyzer);
                    }
                    builder.endObject();
                }

            }
            builder.endObject().endObject();
            return EsSearchUtil.addMapping(esDocument.indexName(), esDocument.indexType(), builder);
        } else {
            return false;
        }
    }

    /**
     * 添加数据
     *
     * @param indexName
     * @param indexType
     * @param source
     * @return
     */
    /*public static Boolean addData(String indexName, String indexType, String source) {
        Client client = null;
        try {
            client = EsClientFactory.getClient();
            IndexResponse response = client.prepareIndex(indexName, indexType).setRefresh(true)
                    .setSource(source).get();
            return response.isCreated();
        } catch (Exception e) {
            logger.error("往es索引【{}】添加数据【{}】时出现异常，{}", indexName, source, e.getMessage(), e);
        } finally {
            EsClientFactory.closeClient(client);
        }
        return false;
    }*/

    /**
     * 根据 id 修改数据
     *
     * @param indexName
     * @param indexType
     * @param id
     * @param builder
     * @return
     */
    public static Boolean updateById(String indexName, String indexType, String id, XContentBuilder builder) {
        Client client = null;
        try {
            client = EsClientFactory.getClient();
            UpdateResponse response = client.prepareUpdate(indexName, indexType, id)
                    .setDoc(builder).get();
            return true;
        } catch (Exception e) {
            logger.error("修改es索引【{}】的数据【{}】时出现异常，{}", indexName, builder.toString(), e.getMessage(), e);
        } finally {
            EsClientFactory.closeClient(client);
        }
        return false;
    }

    public static Boolean deleteById(String indexName, String indexType, String id) {
        Client client = null;
        try {
            client = EsClientFactory.getClient();
            DeleteResponse response = client.prepareDelete(indexName, indexType, id).get();
            return true;
        } catch (Exception e) {
            logger.error("删除es数据【{}】时出现异常，{}", id, e.getMessage(), e);
        } finally {
            EsClientFactory.closeClient(client);
        }
        return false;
    }

    /**
     * 转换成es json
     *
     * @param obj
     * @return
     */
    public static String getSourceString(Object obj) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (obj == null) return null;
        Class cls = obj.getClass();
        Field[] fields = cls.getDeclaredFields();

        XContentBuilder builder = XContentFactory.jsonBuilder().startObject();
        for (Field field : fields) {
            String name = field.getName();
            String getMethodName = "get"
                    + name.substring(0, 1).toUpperCase()
                    + name.substring(1);
            builder.field(name, cls.getMethod(getMethodName, null).invoke(obj, null));
        }
        builder.endObject();
        return builder.string();

    }


}
