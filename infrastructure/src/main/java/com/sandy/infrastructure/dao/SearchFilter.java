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
package com.sandy.infrastructure.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.util.StringUtils;

/**
 * 鏁版嵁搴撴煡璇㈡潯浠�
 * @author @author Sandy
 *
 */
public class SearchFilter {

	/** 鏁版嵁搴撹繃婊ゆ搷浣� */
	public enum Operator {
		EQ("="), LIKE("like"), GT(">"), LT("<"), GTE(">="), LTE("<=");
		
		private String symbol;		// 鎿嶄綔绗﹀彿
		
		Operator(String symbol) {
			this.symbol = symbol;
		}
		
		public String getSymbol() {
			return this.symbol;
		}
	}

	public String fieldName;
	public Object value;
	public Operator operator;

	public SearchFilter(String fieldName, Operator operator, Object value) {
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
	}
	
	/**
	 * searchParams涓璳ey鐨勬牸寮忎负OPERATOR_FIELDNAME
	 * @param searchParams
	 * @return
	 */
	public static Map<String, SearchFilter> parse(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
		for (Entry<String, Object> entry : searchParams.entrySet()) {
			// 杩囨护鎺夌┖鍊�
			String key = entry.getKey();
			Object value = entry.getValue();
			if (StringUtils.isEmpty((String) value)) {
				continue;
			}
			// 鎷嗗垎operator涓巉iledAttribute
			String[] names = StringUtils.split(key, "_");
			if (names.length != 2) {
				throw new IllegalArgumentException(key + " is not a valid search filter name");
			}
			String filedName = names[1];
			Operator operator = Operator.valueOf(names[0]);

			// 鍒涘缓searchFilter
			SearchFilter filter = new SearchFilter(filedName, operator, value);
			filters.put(key, filter);
		}
		return filters;
	}
}

