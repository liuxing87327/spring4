package com.dooioo.dao;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 功能说明：QueryDao
 * 作者：liuxing(2015-01-11 01:38)
 */
public class QueryDao extends SqlMapClientDaoSupport {

    public int count(String sqlId, Serializable id) {
        return (Integer)getSqlMapClientTemplate().queryForObject(sqlId, id);
    }

    public Object queryForObject(String sqlId, Serializable id) {
        return getSqlMapClientTemplate().queryForObject(sqlId, id);
    }

    public Object queryForObject(String sqlId, Map<String, Object> bindParams) {
        return getSqlMapClientTemplate().queryForObject(sqlId, bindParams);
    }

    public <T> int count(String sqlId, T entityObject) {
        return (Integer)getSqlMapClientTemplate().queryForObject(sqlId, entityObject);
    }

    public <T> T findById(String sqlId, Serializable id) {
        return (T)getSqlMapClientTemplate().queryForObject(sqlId, id);
    }

    public Map<String, Object> findForMap(String sqlId, Serializable id) {
        return (Map) getSqlMapClientTemplate().queryForObject(sqlId, id);
    }

    public Map<String, Object> findForMap(String sqlId, Map<String, Object> bindParams) {
        return (Map) getSqlMapClientTemplate().queryForObject(sqlId, bindParams);
    }

    public <T> T queryForBean(String sqlId, Map<String, Object> bindParams) {
        return (T)getSqlMapClientTemplate().queryForObject(sqlId, bindParams);
    }

    public <T> T queryForBean(String sqlId, T entityObject) {
        return (T)getSqlMapClientTemplate().queryForObject(sqlId, entityObject);
    }

    public <T> List<T> queryForList(String sqlId) {
        return getSqlMapClientTemplate().queryForList(sqlId, null);
    }

    public <T> List<T> queryForList(String sqlId, Map<String, Object> bindParams) {
        return getSqlMapClientTemplate().queryForList(sqlId, bindParams);
    }

    public <T> List<T> queryForList(String sqlId, T entityObject) {
        return getSqlMapClientTemplate().queryForList(sqlId, entityObject);
    }

    public <T> List<T> queryForList(String sqlId, Serializable id) {
        return getSqlMapClientTemplate().queryForList(sqlId, id);
    }

    public List<String> queryForListStr(String sqlId, Map<String, Object> bindParams) {
        return getSqlMapClientTemplate().queryForList(sqlId, bindParams);
    }

    public List<Object> queryForListObj(String sqlId) {
        return getSqlMapClientTemplate().queryForList(sqlId);
    }

    public List<Object> queryForListObj(String sqlId, Object entityObject) {
        return getSqlMapClientTemplate().queryForList(sqlId, entityObject);
    }

    public int update(String sqlId, Object entityObject) {
        return getSqlMapClientTemplate().update(sqlId, entityObject);
    }

    public int update(String sqlId, Serializable id) {
        return getSqlMapClientTemplate().update(sqlId, id);
    }

    public int update(String sqlId, Map<String, Object> bindParams) {
        return getSqlMapClientTemplate().update(sqlId, bindParams);
    }

    public int delete(String sqlId, Serializable id) {
        return getSqlMapClientTemplate().delete(sqlId, id);
    }

    public int delete(String sqlId, Object entityObject) {
        return getSqlMapClientTemplate().delete(sqlId, entityObject);
    }

    public int delete(String sqlId, Map<String, Object> bindParams) {
        return getSqlMapClientTemplate().delete(sqlId, bindParams);
    }

    public int insert(String sqlId, Object entityObject) {
        return getSqlMapClientTemplate().update(sqlId, entityObject);
    }

    public int insert(String sqlId, Map<String, Object> bindParams) {
        return getSqlMapClientTemplate().update(sqlId, bindParams);
    }

    public Integer insertAndReturnId(String sqlId, Object entityObject) {
        return (Integer) getSqlMapClientTemplate().insert(sqlId, entityObject);
    }

    public Integer insertAndReturnId(String sqlId, Map<String, Object> bindParams) {
        return (Integer) getSqlMapClientTemplate().insert(sqlId, bindParams);
    }

    public boolean batchInsert(final String sqlId ,final List<? extends Object> params){
        try {
            if(params != null && params.size()>0){
                getSqlMapClientTemplate().execute(new SqlMapClientCallback(){
                    @Override
                    public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
                        executor.startBatch();
                        for(int i=0;i<params.size();i++){
                            executor.insert(sqlId, params.get(i));
                        }
                        executor.executeBatch();
                        return null;
                    }
                });
                return true;
            }else{
                return false;
            }
        } catch (DataAccessException e) {
            return false;
        }
    }

    public int count(String sqlId, Map<String, Object> params) {
        return (Integer)getSqlMapClientTemplate().queryForObject(sqlId, params);
    }

}
