package com.lokiz.ibatis.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lokiz.ibatis.dao.ObejctDAO;
import com.lokiz.ibatis.domain.Coordinates;
import com.lokiz.ibatis.service.SurveillanceParameter;
import com.lokiz.ibatis.service.SurveillanceProcessor;
import com.lokiz.ibatis.util.ProgramConstants;

public class Surveillance implements SurveillanceProcessor {

	private static final Log log = LogFactory.getLog(Surveillance.class);
	private final ObejctDAO objDAO;

	/**
	 * 
	 * @param tdateStr
	 * @throws Exception
	 */
	public Surveillance() throws Exception {
		@SuppressWarnings("resource")
		final ApplicationContext ctx = new ClassPathXmlApplicationContext(ProgramConstants.APPLICATION_CONTEXT);
		objDAO = (ObejctDAO) ctx.getBean(ProgramConstants.TMP_MCIS_DAO);
	}

	/**
	 * Execute queries
	 * 
	 * @throws Exception
	 * 
	 */
	private void executeQueries() throws Exception {
		log.info("Executing Queries");
		List<Coordinates> list = objDAO.getList(ProgramConstants.GET_LIST, null);
		log.info(list.size());
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
	public <T extends SurveillanceParameter> void process(Class<T> t) throws Exception {
		cleanDB();
		executeQueries();// program execute functions here
		cleanDB();
	}
}
