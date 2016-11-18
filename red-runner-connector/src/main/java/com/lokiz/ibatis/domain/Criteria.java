package com.lokiz.ibatis.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.lokiz.ibatis.util.ProgramConstants;

public final class Criteria {

	private static SimpleDateFormat sdf = new SimpleDateFormat(ProgramConstants.DATE_FORMAT);
	private final Date tdate;
	private final String tdateStr;
	private String id;

	public String getTdateStr() {
		return tdateStr;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getTdate() {
		return tdate;
	}

	private Criteria(Date tdate, String tdateStr) {
		this.tdate = tdate;
		this.tdateStr = tdateStr;
	}

	/**
	 * Creates an object of criteria with date passed as parameter
	 * 
	 * @param tdateStr
	 * @return Criteria
	 * @throws ParseException
	 */
	public static Criteria setDateCriteria(String tdateStr) throws ParseException {
		Criteria criteria = new Criteria(sdf.parse(tdateStr), tdateStr);
		return criteria;
	}

}
