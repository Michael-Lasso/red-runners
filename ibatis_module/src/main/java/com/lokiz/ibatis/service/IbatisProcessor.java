package com.lokiz.ibatis.service;

public interface IbatisProcessor {
	public <T extends IbatisParameter> void process(Class<T> t) throws Exception;
}
