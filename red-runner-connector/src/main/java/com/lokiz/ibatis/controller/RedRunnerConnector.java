package com.lokiz.ibatis.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lokiz.ibatis.dao.ObejctDAO;
import com.lokiz.ibatis.service.PersistService;
import com.lokiz.ibatis.service.IbatisParameter;
import com.lokiz.ibatis.service.IbatisProcessor;
import com.lokiz.ibatis.util.ProgramConstants;

public class RedRunnerConnector implements IbatisProcessor, ConnectorService {

	private static final Log log = LogFactory.getLog(RedRunnerConnector.class);
	private final ObejctDAO objDAO;

	/**
	 * 
	 * @param tdateStr
	 * @throws Exception
	 */
	public RedRunnerConnector() {
		@SuppressWarnings("resource")
		final ApplicationContext ctx = new ClassPathXmlApplicationContext(ProgramConstants.APPLICATION_CONTEXT);
		objDAO = (ObejctDAO) ctx.getBean(ProgramConstants.OBJ_DAO);
	}

	public void persistObject(String queryId, PersistService object) {
		try {
			objDAO.insertQuery(queryId, object);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Drop all temp tables created by the program
	 * 
	 * @throws DropTableException
	 */
	private void cleanDB() {
		log.info("<------------- Cleaning up DB tables ------------->");
		for (int i = 1; i <= ProgramConstants.TEMP_TABLES; i++) {
			try {
				objDAO.dropTempTable(ProgramConstants.DROP_TEMP_TABLE + i, null);
			} catch (Exception e) {
				log.info("<--------------------- TEMP TABLE " + i + " was not removed --------------------->");
			}
		}
	}

	@Override
	public <T extends IbatisParameter> void process(Class<T> t) throws Exception {
		cleanDB();

		cleanDB();
	}
}
