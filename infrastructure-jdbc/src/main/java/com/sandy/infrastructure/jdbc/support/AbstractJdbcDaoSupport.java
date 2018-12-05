/*
 * Copyright 2016-2017 the original author or authors.
 *
 * Licensed under the BLK  License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 *      http://www.blk.com
 */
package com.sandy.infrastructure.jdbc.support;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.sandy.infrastructure.jdbc.util.AbstractDataAccessUtils;
/**
 * description 
 * 
 * @author Sandy
 * @since 1.0.0 05th 12 2018
 */
public abstract class AbstractJdbcDaoSupport extends JdbcDaoSupport {
	
	@Autowired(required = false)
	protected DataSource dataSource;
	
	@Override
	protected void checkDaoConfig() {
		setDataSource(dataSource);
		super.checkDaoConfig();
	}
	
	/**
	 * Convert a name in camelCase to an underscored name in lower case. Any
	 * upper case letters are converted to lower case with a preceding underscore.
	 * @param name the string containing original name
	 * @return the converted name
	 */
	protected String underscoreName(String name) {
		StringBuilder result = new StringBuilder();
		if (name != null && name.length() > 0) {
			result.append(name.substring(0, 1).toUpperCase());
			for (int i = 1; i < name.length(); i++) {
				String s = name.substring(i, i + 1);
				if (s.equals(s.toUpperCase())) {
					result.append("_");
					result.append(s);
				} else {
					result.append(s.toUpperCase());
				}
			}
		}
		return result.toString();
	}
	
	public <T> List<T> queryForList(String sql, Class<T> elementType) {
		return this.getJdbcTemplate().query(sql, new BeanPropertyRowMapper<T>(elementType));
	}
	
	public <T> List<T> queryForList(String sql, Object[] args, Class<T> elementType) {
		return this.getJdbcTemplate().query(sql, args, new BeanPropertyRowMapper<T>(elementType));
	}
	
	public <T> List<T> queryForList(String sql, Class<T> elementType, Object... args) throws DataAccessException {
		return this.getJdbcTemplate().query(sql, new BeanPropertyRowMapper<T>(elementType), args);
	}

	public <T> List<T> queryForList(String sql, StatementParameter param, Class<T> elementType) {
		return this.getJdbcTemplate().query(sql, param.getArgs(), new BeanPropertyRowMapper<T>(elementType));
	}
	
	public <T> T queryForObject(String sql, Class<T> elementType) throws DataAccessException {
		Object [] args = {};
		return queryForObject(sql, args, elementType);
	}
	
	public <T> T queryForObject(String sql, Object[] args, Class<T> elementType) {
		List<T> results = queryForList(sql, args, elementType);
		return AbstractDataAccessUtils.requiredSingleResult(results);
	}
	
	public int queryForInt(String sql){
		Number number = this.getJdbcTemplate().queryForObject(sql, Integer.class);
		return (number != null ? number.intValue() : 0);
	}
	
	public int queryForInt(String sql, Object[] args) {
		Number number = this.getJdbcTemplate().queryForObject(sql, args, Integer.class);
		return null != number ? number.intValue() : 0;
	}
	
	public int queryForInt(String sql, StatementParameter param){
		Number number = this.getJdbcTemplate().queryForObject(sql, param.getArgs(), Integer.class);
		return (number != null ? number.intValue() : 0);
	}
	
	public long queryForLong(String sql, Object[] args) {
		Number number = this.getJdbcTemplate().queryForObject(sql, args, Long.class);
		return null != number ? number.longValue() : 0L;
	}
	
	public long queryForLong(String sql, StatementParameter param){
		Number number = this.getJdbcTemplate().queryForObject(sql, param.getArgs(), Long.class);
		return number != null ? number.longValue() : 0L;
	}
	
	protected Long executeAndReturnKey(StatementNamedParameter param) {
		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(this.getJdbcTemplate().getDataSource()).withTableName(param.getTable()).usingGeneratedKeyColumns(param.getKeyColumn());
		Number newId = insertActor.executeAndReturnKey(param.getParameters());
		return newId.longValue();
	}
	
	protected Long execute(StatementNamedParameter param) {
		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(this.getJdbcTemplate().getDataSource()).withTableName(param.getTable()).usingGeneratedKeyColumns(param.getKeyColumn());
		Number newId = insertActor.execute(param.getParameters());
		return newId.longValue();
	}
	
	protected int updateForRecord(String sql, StatementParameter param) {
		return this.getJdbcTemplate().update(sql, param.getParameters());
	}
	
	public boolean updateForBoolean(String sql, StatementParameter param) {
		return 0 < updateForRecord(sql, param);
	}
}

