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
package com.sandy.infrastructure.common;

import java.util.Date;

/**
 * abstract date entity
 * 
 * @author Sandy
 * @param <K>
 * @since 1.0.0 11th 10 2018
 */
public abstract class AbstractDateEntity<PK> extends AbstractIdEntity<PK> {

	private static final long serialVersionUID = -5941607383559935670L;

	private PK createdId;
	private Date createdTime;
	private PK updatedId;
	private Date updatedTime;
	
	public PK getCreatedId() {
		return createdId;
	}
	public void setCreatedId(PK createdId) {
		this.createdId = createdId;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public PK getUpdatedId() {
		return updatedId;
	}
	public void setUpdatedId(PK updatedId) {
		this.updatedId = updatedId;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder(200);
        buffer.append(getClass().getName()).append('[');
        buffer.append("id=").append(getId());
        buffer.append(",createdId=").append(createdId);
        buffer.append(",createdTime=").append(createdTime);
        buffer.append(",updatedId=").append(updatedId); 
        buffer.append(",updatedTime=").append(updatedTime);
        buffer.append(']');
		return buffer.toString();
	}
}

