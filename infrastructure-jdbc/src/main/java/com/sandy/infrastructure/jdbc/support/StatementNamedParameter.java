/*
 * Copyright 2016-2017 the original author or authors.
 *
 * Licensed under the BLK  License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 *      http://www.blk.com
 */
package com.sandy.infrastructure.jdbc.support;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Statement Named Parameter
 * 
 * @description
 * @author Sandy
 * @since 1.0.0 05th 12 2018
 */
public class StatementNamedParameter {
	
	private Map<String, Object> map = new HashMap<String, Object>();

	private String table;
	private String keyColumn;

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getKeyColumn() {
		return keyColumn;
	}

	public void setKeyColumn(String keyColumn) {
		this.keyColumn = keyColumn;
	}

	public StatementNamedParameter(String table, String keyColumn) {
		this.table = table;
		this.keyColumn = keyColumn;
	}

	public void setDate(String column, Date value) {
		checkParameter(column, value);
		map.put(column, value);
	}

	public void setString(String column, String value) {
		checkParameter(column, value);
		map.put(column, value);
	}

	public void setBool(String column, Boolean value) {
		checkParameter(column, value);
		map.put(column, value ? 1 : 0);
	}

	public void setInt(String column, Integer value) {
		checkParameter(column, value);
		map.put(column, value);
	}

	public void setLong(String column, Long value) {
		checkParameter(column, value);
		map.put(column, value);
	}

	public void setDouble(String column, Double value) {
		checkParameter(column, value);
		map.put(column, value);
	}

	public void setFloat(String column, Float value) {
		checkParameter(column, value);
		map.put(column, value);
	}
	
	public void setShort(String column, Short value) {
		checkParameter(column, value);
		map.put(column, value);
	}
	
	public void setObject(String column, Object value) {
		checkParameter(column, value);
		map.put(column, value);
	}
	
	public void checkParameter(String column, Object value){
		if(null == value) {
			throw new SQLParameterException(column+" Parameter value not null");
		}
	}

	public Map<String, Object> getParameters() {
		return map;
	}
}

