package com.lokiz.ibatis.domain;

import com.lokiz.ibatis.service.CSVObjectWriter;
import com.lokiz.ibatis.service.Utils;

public class POJO implements CSVObjectWriter {

	private String name;
	private int id;
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public String objectWriter() {
		StringBuilder sb = new StringBuilder();
		sb.append(Utils.getStringValue(getName()));
		sb.append(',');
		sb.append(Utils.getStringValue(getId()));
		sb.append(',');
		sb.append(Utils.getStringValue(getDescription()));
		return sb.toString();
	}

}
