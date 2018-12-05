/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sandy.user.dao;


import java.beans.PropertyDescriptor;
import java.lang.reflect.ParameterizedType;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.sandy.infrastructure.common.AbstractDateEntity;
import com.sandy.infrastructure.jdbc.support.AbstractJdbcDaoSupport;
import com.sandy.infrastructure.jdbc.support.StatementParameter;
import com.sandy.infrastructure.util.PagingList;

/**
 * @author Sandy
 * @param <T>
 * @param <PK>
 * @since 1.0.0 11th 10 2018
 */
public abstract class AbstractDao<T extends AbstractDateEntity<PK>, PK> extends AbstractJdbcDaoSupport {
	
	protected Class<T> abstractModelClass;
	protected String tableName;
	
	@Resource(name = "dataSource")
	public final void initDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void initDao() throws Exception {
		this.abstractModelClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.tableName = underscoreName(abstractModelClass.getSimpleName());
		super.initDao();
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
	public T queryById(PK id) {
		StringBuilder sql = new StringBuilder("SELECT * FROM ").append(tableName).append(" WHERE ID=?");
		Object [] args = {id};
		List<T> list = this.getJdbcTemplate().query(sql.toString(), args, new BeanPropertyRowMapper<T>(abstractModelClass));
		return list.isEmpty() ? null : list.get(0);
	}
	
	public List<T> queryForList() {
		StringBuilder sql = new StringBuilder("SELECT * FROM ").append(tableName);
		List<T> list = this.getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<T>(abstractModelClass));
		return list;
	}
	
	public abstract PagingList<T> queryPageForList(Map<String, Object> criteria, Integer page, Integer pageSize) ;
	
	public T add(T record) throws SQLException {
		StringBuilder sql = new StringBuilder("INSERT INTO ").append(tableName).append("(");
		PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(abstractModelClass);
		BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(record);
		boolean isFrist = true;
		StringBuilder param = new StringBuilder();
		List<Object> paramList = new ArrayList<Object>();
		for (PropertyDescriptor pd : pds) {
			String propertyName = pd.getName();
			String underscoredName = underscoreName(propertyName);
			Object propertyValue = beanWrapper.getPropertyValue(propertyName);
			if (propertyValue != null && !propertyName.equals("class")) {
				if(isFrist) {
					isFrist = false; 
				}else {
					sql.append(",");
					param.append(",");
				}
				sql.append(underscoredName);
				param.append("?");
				paramList.add(propertyValue);
			}
		}
		sql.append(") VALUES ( ").append(param).append(");");
		return 0 < getJdbcTemplate().update(sql.toString(), paramList.toArray()) ? record : null;
	}
	public T update(T record) throws SQLException {
		StringBuilder sql = new StringBuilder("UPDATE ").append(this.tableName).append(" SET");
		StatementParameter param = new StatementParameter();
		PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(abstractModelClass);
		BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(record);
		for (PropertyDescriptor pd : pds) {
			String propertyName = pd.getName();
			String underscoredName = underscoreName(propertyName);
			Class<?> propertyType = beanWrapper.getPropertyType(propertyName);
			Object propertyValue = beanWrapper.getPropertyValue(propertyName);
			if (!propertyName.equals("id") && !propertyName.equals("class")) {
				if (param.size() > 0) { sql.append(","); }
				sql.append(" " + underscoredName + "=?");
				if (propertyValue == null) {
					param.setNull();
				} else if (propertyType.equals(Date.class)) {
					param.setDate((Date) propertyValue);
				} else if (propertyType.equals(String.class)) {
					param.setString((String) propertyValue);
				} else if (propertyType.equals(Boolean.class)) {
					param.setBool((Boolean) propertyValue);
				} else if (propertyType.equals(Integer.class)) {
					param.setInt((Integer) propertyValue);
				} else if (propertyType.equals(Long.class)) {
					param.setLong((Long) propertyValue);
				} else if (propertyType.equals(Double.class)) {
					param.setDouble((Double) propertyValue);
				} else if (propertyType.equals(Float.class)) {
					param.setFloat((Float) propertyValue);
				} else if (propertyType.equals(Short.class)) {
					param.setShort((Short) propertyValue);
				} else if (propertyType.equals(Object.class)) {
					param.setObject(propertyValue);
				}
			}
		}
		sql.append(" WHERE ID=?");
		param.setObject(record.getId());
		boolean updatedSuccess = false;
		if (param.size() > 0) {
			updatedSuccess = super.updateForBoolean(sql.toString(), param);
		} else {
			updatedSuccess = false;
		}
		return updatedSuccess ? record : null;
	}
	
	public int deleteById(PK id) {
		StringBuilder sql = new StringBuilder("DELTE FROM ").append(tableName).append(" WHERE ID = ?");
		StatementParameter param = new StatementParameter();
		param.setObject(id);
		return super.updateForRecord(sql.toString(), param);
	}
}
