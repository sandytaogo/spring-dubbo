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
package com.sandy.user.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sandy.infrastructure.common.AbstractDateEntity;
import com.sandy.infrastructure.util.PagingList;
import com.sandy.user.dao.AbstractDao;

/**
 * Abstract Service
 * @author Sandy
 * @param <T>
 * @param <PK>
 * @since 1.0.0 11th 10 2018
 */
public abstract class AbstractService<T extends AbstractDateEntity<PK>, PK> {

	protected AbstractDao<T, PK> abstractDao;
	
	public void setAbstractDao(AbstractDao<T, PK> abstractDao) {
		this.abstractDao = abstractDao;
	}
	
	public T queryById(PK id) { 
		return abstractDao.queryById(id);
	}
	
	public List<T> queryForList() {
		return  abstractDao.queryForList();
	}
	
	/**
	 * query page for list
	 * @param criteria
	 * @param fieled
	 * @return PageList<T>
	 */
	public PagingList<T> queryPageForList(Map<String, Object> criteria, Integer page, Integer pageSize) {
		return abstractDao.queryPageForList(criteria, page, pageSize);
	}
	
	public T add(T record) throws SQLException {
		return abstractDao.add(record);
	}
	
	public T update(T record) throws SQLException {
		return abstractDao.update(record);
	}
	
	public boolean delete(PK id) {
		return 0 < abstractDao.deleteById(id);
	}
	
	public boolean delete(T record) {
		
		return 0 < abstractDao.deleteById(record.getId());
	}
}
