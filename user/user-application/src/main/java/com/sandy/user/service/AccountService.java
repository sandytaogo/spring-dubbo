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

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandy.user.dao.AbstractDao;
import com.sandy.user.dao.AccountDao;
import com.sandy.user.domain.Account;

/**
 * account service
 * 
 * @author Sandy
 * @since 1.0.0 11th 10 2018
 */
@Service("accountService")
public class AccountService extends AbstractService<Account, Long> {

	@Autowired
	private AccountDao accountDao;

	@Override
	@Resource(name = "accountDao")
	public void setAbstractDao(AbstractDao<Account, Long> abstractDao) {
		super.setAbstractDao(abstractDao);
	}

	/**
	 * find by mobile
	 * @param mobile
	 * @return system user
	 */
	public Account queryByMobile(String mobile) {
		if(null == mobile) {
			return null;
		}
		return accountDao.queryByMobile(mobile);
	}

	/**
	 * find by email
	 * @param email
	 * @return system user
	 */
	public Account queryByEmail(String email) {
		if(null == email) {
			return null;
		}
		return accountDao.queryByEmail(email);
	}

	/**
	 * find by user name
	 * @param email
	 * @return system user
	 */
	public Account queryByUserName(String userName) {
		if(null == userName) {
			return null;
		}
		return accountDao.queryByUserName(userName);
	}

	/**
	 * create new user
	 * @param user
	 * @return SystemUser
	 * @exception SQLException
	 */
	public Account add(Account user) throws SQLException {
		return super.add(user);
	}
}
