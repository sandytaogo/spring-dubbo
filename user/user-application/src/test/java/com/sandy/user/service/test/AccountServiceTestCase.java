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
package com.sandy.user.service.test;

import java.sql.SQLException;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sandy.user.domain.Account;
import com.sandy.user.service.AccountService;


@DirtiesContext
@ActiveProfiles(profiles = "test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class AccountServiceTestCase extends AbstractTransactionalJUnit4SpringContextTests {

	protected final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AccountService userService;
	
	@Test(timeout = 200)
	public void findByEmailTestCase() {
		String email = "test@163.com";
		Account user = userService.queryByEmail(email);
		System.out.println(user);
	}
	
	@Ignore
	@Test(timeout = 200)
	public void regTest() throws SQLException {
		Account user = new Account();
		user.setId(System.currentTimeMillis());
		user.setNickName("sandy");
		user.setMobile("32432434343");
		user.setPassword("123456");
		user.setCreatedId(System.currentTimeMillis());
		user.setCreatedTime(new Date());
		user.setHeadPortrait("headPortrait/default.png");
		user = userService.add(user);
		LOGGER.info(user.toString());
	}
}
