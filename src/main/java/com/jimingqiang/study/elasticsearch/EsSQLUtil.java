package com.jimingqiang.study.elasticsearch;

import com.jimingqiang.study.elasticsearch.annotation.EsDocument;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: libo
 * @Date: 2018/8/1 18:42
 * @Description:
 */
public class EsSQLUtil {
    /**
     * in
     *
     * @param qb
     * @param list
     * @param field
     */
    public static void in(BoolQueryBuilder qb, List list, String field) {
        BoolQueryBuilder processModeIdQuery = QueryBuilders.boolQuery();
        if (list != null && list.size() > 0) {
            for (Object obj : list) {
                processModeIdQuery.should(QueryBuilders.matchQuery(field, obj));
            }
            qb.must(processModeIdQuery);
        }
    }



    public static void and(BoolQueryBuilder qb, Object obj, String field) {
        if (obj != null && StringUtils.isNotEmpty(obj.toString())) {
            qb.must(QueryBuilders.matchQuery(field, obj));
        }
    }

    /**
     * 多个条件或 相当于 sql  and （条件 or 条件）
     *
     * @param qb
     * @param fieldMap
     */
    public static void orExt(BoolQueryBuilder qb, Map<String, Object> fieldMap) {
        BoolQueryBuilder sub = QueryBuilders.boolQuery();
        if (fieldMap != null && fieldMap.size() > 0) {
            Set<String> fields = fieldMap.keySet();
            for (String field : fields) {
                Object o = fieldMap.get(field);
                if(o == null)continue;
                if(o instanceof Collection){
                    if(((Collection) o).size() == 0)continue;
                    for(Object oj : (Collection)o){
                        sub.should(QueryBuilders.matchQuery(field, oj));
                    }
                }else{
                    sub.should(QueryBuilders.matchQuery(field, o));
                }
            }
            qb.must(sub);
        }
    }

    /**
     * 多个条件或 相当于 sql  not
     *
     * @param qb
     * @param fieldMap
     */
    public static void orExtNotEq(BoolQueryBuilder qb, Map<String, Object> fieldMap) {
        BoolQueryBuilder sub = QueryBuilders.boolQuery();
        if (fieldMap != null && fieldMap.size() > 0) {
            Set<String> fields = fieldMap.keySet();
            for (String field : fields) {
                Object o = fieldMap.get(field);
                if(o == null)continue;
                sub.must(QueryBuilders.matchQuery(field, o));
            }
            qb.mustNot(sub);
        }
    }

    /**
     * 不能等于
     * @param qb
     * @param v
     * @param field
     */
    public static void notEq(BoolQueryBuilder qb,Object v,String field){
        if (v != null && StringUtils.isNotEmpty(v.toString())) {
            qb.mustNot(QueryBuilders.matchQuery(field,v));
        }
    }

    /**
     * 多个条件或 相当于 sql  and （ like条件 or like条件）
     *
     * @param qb
     * @param fieldMap
     */
    public static void orExtLike(BoolQueryBuilder qb, Map<String, Object> fieldMap) {
        BoolQueryBuilder sub = QueryBuilders.boolQuery();
        if (fieldMap != null && fieldMap.size() > 0) {
            Set<String> fields = fieldMap.keySet();
            for (String field : fields) {
                Object o = fieldMap.get(field);
                if(o == null || StringUtils.isEmpty(o.toString()))continue;
                if(o instanceof Collection){
                    for(Object oj : (Collection)o){
                        sub.should(QueryBuilders.termQuery(field,  oj.toString() ));
                    }
                }else{
                    sub.should(QueryBuilders.termQuery(field, o.toString() ));
                }
            }
            qb.must(sub);
        }
    }

    public static void like(BoolQueryBuilder qb, Object obj, String field) {
        if (obj != null && StringUtils.isNotEmpty(obj.toString())) {
            qb.must(QueryBuilders.termQuery(field,  obj.toString()));
        }
    }
    public static void query(BoolQueryBuilder qb, Object obj, String field) {
        if (obj != null && StringUtils.isNotEmpty(obj.toString())) {
            qb.must(QueryBuilders.matchPhraseQuery(field,obj.toString()));

        }
    }

    /**
     * 范围
     *
     * @param bqb
     */
    public static void toRangeQuery(BoolQueryBuilder bqb, String field, Object start, Object end) {

        if (start != null
                && StringUtils.isNotEmpty(field)) {
            bqb.must(QueryBuilders.rangeQuery(field).gte(start));
        }
        if (end != null && StringUtils.isNotEmpty(field)) {
            bqb.must(QueryBuilders.rangeQuery(field).lte(end));
        }
    }

    /**
     * 获取注解对象
     *
     * @param cls
     * @return
     */
    public static EsDocument getEsDocument(Class cls) {
        try {
            return (EsDocument) cls.getAnnotation(EsDocument.class);
        } catch (Exception e) {
            System.out.println("获取EsDocument对象失败");
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("_____________" + EsSQLUtil.getEsDocument(OrderEsBean.class).indexName());
    }
}
