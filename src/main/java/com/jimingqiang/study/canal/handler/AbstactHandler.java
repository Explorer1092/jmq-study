package com.jimingqiang.study.canal.handler;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.google.protobuf.InvalidProtocolBufferException;
import com.jimingqiang.study.canal.common.SqlBuilder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanglei on 10/04/2017.
 */
public abstract class AbstactHandler implements MessageHandler {
    /*@Autowired
    protected DBMapper mapper;*/
    /*@Autowired
    private SqlSessionTemplate sqlSessionTemplate;*/

    private Logger _LOG = LoggerFactory.getLogger(AbstactHandler.class);

    protected List<String> generateSql(List<CanalEntry.Entry> entries) throws InvalidProtocolBufferException {
        List<String> sqls = new ArrayList<String>();
        // 消息解析 拼装SQL
        for (CanalEntry.Entry entry : entries) {
            String schemaName = entry.getHeader().getSchemaName();
            String tableName = entry.getHeader().getTableName();
            //过滤掉不关心的表
            if (!hasTableName(tableName)) {
                _LOG.info("*****表名为 {}  已经过滤 **", tableName);
                continue;
            }
            CanalEntry.RowChange rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
            // DDL操作,忽略
            if (rowChange.getIsDdl()) {
                continue;
            }

            _LOG.info(String.format("======> binlog[%s:%s] , talbe[%s.%s] , entryType: %s, eventType : %s",
                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(), entry.getEntryType(),
                    entry.getHeader().getEventType()));

            // 数据操作, 装配SQL
            CanalEntry.EventType eventType = rowChange.getEventType();
            List<CanalEntry.RowData> rowDatas = rowChange.getRowDatasList();
            for (CanalEntry.RowData rowData : rowDatas) {
                //转换表明 从 longfor_crm_qa 库转换到 qding_dw库中
                String sql = SqlBuilder.buildSql(schemaName, tableName, eventType, rowData);
                if (!StringUtils.isEmpty(sql)) {
                    sqls.add(sql);
                }
            }
        }
        return sqls;
    }

    /**
     * 批处理
     * @param sqls
     */
    protected void excuteSqls(List<String> sqls) throws SQLException{
        /*if (CollectionUtils.isNotEmpty(sqls)) {
            SqlSession sqlSession = sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
            try {
                for (String sql : sqls) {
                    if(sql.startsWith(SqlBuilder.INSERT)){
                        sqlSession.insert(getStatement(DBMapper.class, "excute"), sql);
                    } else if(sql.startsWith(SqlBuilder.UPDATE)){
                        sqlSession.update(getStatement(DBMapper.class, "excute"), sql);
                    } else if(sql.startsWith(SqlBuilder.DELETE)){
                        sqlSession.delete(getStatement(DBMapper.class, "excute"), sql);
                    }
                    _LOG.info("sql is {}", sql);
                }
                sqlSession.commit();
            } catch (Exception e) {
                sqlSession.rollback();
                _LOG.error("Batch execution failed {}"+e.getMessage());
                throw new SQLException(e.getMessage());
            } finally {
                if(sqlSession != null){
                    sqlSession.close();
                }
            }
        }*/
    }

    private String getStatement(Class<?> cls, String mapperId){
        return cls.getName()+"."+mapperId;
    }

    /**
     * 验证sub系统是否有该表
     */
    protected abstract boolean hasTableName(String tableName);
}
