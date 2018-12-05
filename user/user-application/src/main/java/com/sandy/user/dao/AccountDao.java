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

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sandy.infrastructure.jdbc.support.StatementParameter;
import com.sandy.infrastructure.util.PagingArraryList;
import com.sandy.infrastructure.util.PagingList;
import com.sandy.user.domain.Account;

/**
 * account data access object
 *  
 * @author Sandy
 * @since 1.0.0 11th 10 2018
 */
@Repository("accountDao")
public class AccountDao extends AbstractDao<Account, Long> {

	@Override
	protected void initDao() throws Exception {
		super.initDao();
		this.tableName = "SYSTEM_USER";
	}

	@Override
	public PagingList<Account> queryPageForList(Map<String, Object> criteria, Integer page, Integer pageSize) {
		Long id = (Long) criteria.get("id");
		StringBuilder sql = new StringBuilder("SELECT * FROM ").append(tableName);
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(ID) FROM ").append(tableName);
		StatementParameter param = new StatementParameter();
		if(null != id) {
			sql.append(" WHERE ID=? ");
			param.setLong(id);
		}
		Integer total = queryForInt(sqlCount.toString(), param);
		sql.append(" LIMIT ?,? ");
		param.setInt((page -1) * pageSize );
		param.setInt(pageSize);
		List<Account> list = queryForList(sql.toString(), param, abstractModelClass);
		PagingArraryList<Account> pageList = new PagingArraryList<Account>();
		pageList.setPage(page);
		pageList.setData(list);
		pageList.setTotal(total);
		return pageList;
	}
	
	/**
	 * query by mobile
	 * @param mobile 
	 * @return system user
	 */
	public Account queryByMobile(String mobile) {
		StringBuilder sql = new StringBuilder(" SELECT * FROM ").append(tableName).append(" WHERE MOBILE=?");
		Object [] args = {mobile};
		return queryForObject(sql.toString(), args, Account.class);
	}
	
	/**
	 * query by email
	 * @param email
	 * @return
	 */
	public Account queryByEmail(String email) {
		StringBuilder sql = new StringBuilder(" SELECT * FROM ").append(tableName).append(" WHERE EMAIL=?");
		Object [] args = {email};
		return queryForObject(sql.toString(), args, Account.class);
	}
	
	/**
	 * query by user name
	 * @param userName
	 * @return
	 */
	public Account queryByUserName(String userName) {
		StringBuilder sql = new StringBuilder(" SELECT * FROM ").append(tableName).append(" WHERE USER_NAME=?");
		Object [] args = {userName};
		return queryForObject(sql.toString(), args, Account.class);
	}
}
