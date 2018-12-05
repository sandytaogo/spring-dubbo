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

import com.sandy.infrastructure.util.PagingList;
import com.sandy.user.dto.AccountDTO;

/**
 * @category <blockquote>  
 * @author Sandy
 * @version 1.0.0 04th 12 2018
 */
public interface IAccountServiceFacade {
	/**
	 * findById
	 * @param id
	 * @return UserDTO
	 */
	public AccountDTO findById(Long id);
	/**
	 * find user by mobile
	 * @param mobile
	 * @return UserDTO
	 */
	public AccountDTO findByMobile(String mobile);
	/**
	 * find user by email
	 * @param email
	 * @return userDTO
	 */
	public AccountDTO findByEmail(String email);
	/**
	 * query find by user user name
	 * @param userName
	 * @return userDTO
	 */
	public AccountDTO findByUserName(String userName);
	/**
	 * get list
	 * @param regionId
	 * @param userName
	 * @param page
	 * @param pageSize
	 * @return PageList<UserDTO>
	 */
	public PagingList<AccountDTO> list(Long regionId, String userName, Integer page, Integer pageSize);
	/**
	 * create new user
	 * @param user
	 * @return UserDTO
	 */
	public AccountDTO add(AccountDTO user);
	/**
	 * update user information by id
	 * @param user
	 * @return UserDTO
	 */
	public AccountDTO update(AccountDTO user);
	/**
	 * delete by user id
	 * @param id
	 * @return boolean
	 */
	public boolean delete(Long id);
}
