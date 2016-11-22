package com.lokiz.ibatis.controller;

import com.lokiz.ibatis.service.PersistService;

public interface ConnectorService {
	public void persistObject(String queryId, PersistService object);
}
