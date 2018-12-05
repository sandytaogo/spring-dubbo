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
package com.sandy.infrastructure.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PagingArraryList<T> implements PagingList<T>, Serializable {

	private static final long serialVersionUID = -3944306626906645264L;

	protected List<T> data = new ArrayList<T>();
	//current page
	protected int page;
	protected int pageSize;
	protected int pages;
	protected int total;
	
	@Override
	public Iterator<T> iterator() {
		return data.iterator();
	}

	public List<T> getData() {
		return data;
	}
	
	/**
	 * Appends the specified element to the end of this list (optional
     * operation).
	 * @param e
	 * @return
	 */
	public boolean add(T e) {
		return this.data.add(e);
	}
	
	public void setData(List<T> data) {
		this.data.addAll(data);
	}
	
	public int getTotal() {
		return this.total;
	}
	
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	public int getPage() {
		if(this.page < 1) {
			this.page = 1;
		}
		return this.page;
	}
	
	public void setPage(Integer page) {
		this.page = page;
	}
	
	public int getPageSize() {
		return this.pageSize;
	}
	
	public void setPageSize(Integer pageSize) {
		if(null != pageSize) {
			this.pageSize = pageSize;
		}
	}

	public int getPages() {
		this.pages = this.total / this.pageSize;
		if(0 < this.total % this.pageSize) {
			this.pages ++;
		}
		return this.pages;
	}
}

