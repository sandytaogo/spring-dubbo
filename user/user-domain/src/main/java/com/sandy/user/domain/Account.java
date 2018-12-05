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
package com.sandy.user.domain;

import com.sandy.infrastructure.common.AbstractDateEntity;

/**
 * Account
 * 
 * @author Sandy
 * @since 1.0.0 11th 10 2018
 */
public class Account extends AbstractDateEntity<Long> {

	private static final long serialVersionUID = -8086719111874165087L;

	private String nickName;
	private String userName;
	private String headPortrait;
	private String email;
	private String mobile;
	private String password;
	private Integer isLocked = 0x00;
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getHeadPortrait() {
		return headPortrait;
	}
	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getIsLocked() {
		return isLocked;
	}
	public void setIsLocked(Integer isLocked) {
		this.isLocked = isLocked;
	}
	
	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder(200);
		buffer.append(getClass().getName()).append('[');
        buffer.append("id=").append(getId());
    	buffer.append(",nickName=").append(nickName);
    	buffer.append(",headPortrait=").append(headPortrait);
    	buffer.append(",userName=").append(userName);
    	buffer.append(",email=").append(email);
    	buffer.append(",mobile=").append(mobile);
    	buffer.append(",password=").append(password);
    	buffer.append(",isLocked=").append(isLocked);
        buffer.append(",createdId=").append(getCreatedId());
        buffer.append(",createdTime=").append(getCreatedTime());
        buffer.append(",updatedId=").append(getUpdatedId()); 
        buffer.append(",updatedTime=").append(getUpdatedTime());
        buffer.append(']');
		return buffer.toString();
	}
}
