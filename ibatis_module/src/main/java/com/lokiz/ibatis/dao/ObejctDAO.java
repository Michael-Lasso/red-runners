package com.lokiz.ibatis.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

@SuppressWarnings("deprecation")
public class ObejctDAO extends SqlMapClientDaoSupport {
	private static final Log log = LogFactory.getLog(ObejctDAO.class);

	/**
	 * Insert query with queryID in Datasource.xml. Pass null as the second
	 * argument if no parameters are needed for the query
	 * 
	 * @param queryID
	 * @param criteria
	 * @throws Exception
	 */
	public void insertQuery(String queryID, Object criteria) throws Exception {
		log.info("-----------------------------------------------");
		log.info("Insert query: " + queryID);
		log.info("Rows affected: " + getSqlMapClientTemplate().insert(queryID, criteria));
		log.info("Successful query");
		log.info("-----------------------------------------------\n");
	}

	/**
	 * Insert query with queryID in Datasource.xml. Pass null as the second
	 * argument if no parameters are needed for the query
	 * 
	 * @param queryID
	 * @param criteria
	 * @throws Exception
	 */
	public void updateQuery(String queryID, Object criteria) throws Exception {
		log.info("-----------------------------------------------");
		log.info("Update query: " + queryID);
		log.info("Rows affected: " + getSqlMapClientTemplate().update(queryID, criteria));
		log.info("Successful query");
		log.info("-----------------------------------------------\n");
	}

	/**
	 * Executes query with queryId in Datasource.xml
	 * 
	 * @param queryId
	 * @param criteria
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> getList(String queryId, Object criteria) throws Exception{
		log.info("-----------------------------------------------");
		log.info("List query: " + queryId);
		List<T> list = getSqlMapClientTemplate().queryForList(queryId, criteria);
		log.info("Rows affected: " + list.size());
		log.info("-----------------------------------------------\n");
		return list;
	}

	public void dropTempTable(String queryId, Object criteria) throws Exception {
		getSqlMapClientTemplate().delete(queryId, criteria);
	}
}