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
package com.sandy.user.facade;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandy.infrastructure.common.BeanUtils;
import com.sandy.infrastructure.util.PagingArraryList;
import com.sandy.infrastructure.util.PagingList;
import com.sandy.user.domain.Account;
import com.sandy.user.dto.AccountDTO;
import com.sandy.user.service.AccountService;

/**
 * account service facade
 * 
 * @author Sandy
 * @since 1.0.0 08th 10 2018
 */
@Service("accountServiceFacade")
//@Service(protocol = "rest")
public class AccountServiceFacade implements IAccountServiceFacade {

	protected final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AccountService userService;

	@Override
	public AccountDTO findById(Long id) {
		Account user = userService.queryById(id);
		if(null == user) {
			return null;
		}
		AccountDTO userDTO = convertDTO(user);
		return userDTO;
	}
	
	@Override
	public AccountDTO findByMobile(String mobile) {
		Account user = userService.queryByMobile(mobile);
		if(null == user) {
			return null;
		}
		return convertDTO(user);
	}

	@Override
	public AccountDTO findByEmail(String email) {
		Account user = userService.queryByEmail(email);
		if(null == user) {
			return null;
		}
		return convertDTO(user);
	}

	@Override
	public AccountDTO findByUserName(String userName) {
		Account user = userService.queryByUserName(userName);
		if(null == user) {
			return null;
		}
		return convertDTO(user);
	}

	@Override
	public PagingList<AccountDTO> list(Long regionId, String userName, Integer page, Integer pageSize) {
		PagingArraryList<AccountDTO> pageList = new PagingArraryList<AccountDTO>();
		pageList.setPage(page);
		pageList.setPageSize(pageSize);
		Map<String, Object> criteria = new HashMap<String, Object>();
		PagingList<Account> userList = userService.queryPageForList(criteria, page, pageSize);
		List<AccountDTO> listDTO = BeanUtils.mapList(userList.getData(), AccountDTO.class);
		pageList.setPage(page);
		pageList.setData(listDTO);
		pageList.setTotal(userList.getTotal());
		pageList.getPages();
		return pageList;
	}

	@Override
	public AccountDTO add(AccountDTO user) {
		Account systemUser = new Account();
		try {
			systemUser = userService.add(systemUser);
			user.setId(systemUser.getId());
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return user;
	}

	@Override
	public AccountDTO update(AccountDTO user) {
		Account systemUser = convertUser(user);
		try {
			systemUser = userService.update(systemUser);
			if(null == systemUser) {
				return null;
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return user;
	}

	@Override
	public boolean delete(Long id) {
		return userService.delete(id);
	}
	
	private AccountDTO convertDTO(Account user) {
		AccountDTO userDTO = new AccountDTO();
		BeanUtils.copyProperties(user, userDTO);
		return userDTO;
	}
	
	private Account convertUser(AccountDTO userDTO) {
		Account systemUser = new Account();
		BeanUtils.copyProperties(userDTO, systemUser);
		return systemUser;
	}
}
