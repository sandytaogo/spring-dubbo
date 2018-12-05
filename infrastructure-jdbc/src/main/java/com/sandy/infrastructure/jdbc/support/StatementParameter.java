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
package com.sandy.infrastructure.jdbc.support;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
/**
 * Statement Parameter
 * 
 * @author Sandy
 * @since 1.0.0 05th 12 2018
 */
public class StatementParameter {
	
	private List<Object> list = new ArrayList<Object>();
	
	public void setDate(Date value) {
		if (value == null) {
			throw new RuntimeException("Parameter[" + list.size() + "]can not be empty.");
		}
		list.add(value);
	}

	public void setString(String value) {
		if (value == null) {
			throw new RuntimeException("Parameter[" + list.size() + "]can not be empty.");
		}
		list.add(value);
	}

	public void setBool(Boolean value) {
		if (value == null) {
			throw new RuntimeException("Parameter[" + list.size() + "]can not be empty.");
		}
		list.add(value ? 1 : 0);
	}

	public void setInt(Integer value) {
		if (value == null) {
			throw new RuntimeException("Parameter[" + list.size() + "]can not be empty.");
		}
		list.add(value);
	}

	public void setLong(Long value) {
		if (value == null) {
			throw new RuntimeException("Parameter[" + list.size() + "]can not be empty.");
		}
		list.add(value);
	}

	public void setDouble(Double value) {
		if (value == null) {
			throw new RuntimeException("Parameter[" + list.size() + "]can not be empty.");
		}
		list.add(value);
	}

	public void setFloat(Float value) {
		if (value == null) {
			throw new RuntimeException("Parameter[" + list.size() + "]can not be empty.");
		}
		list.add(value);
	}
	
	public void setShort(Short value) {
		if (value == null) {
			throw new RuntimeException("Parameter[" + list.size() + "]can not be empty.");
		}
		list.add(value);
	}
	
	public void setObject(Object value) {
		if (value == null) {
			throw new RuntimeException("Parameter[" + list.size() + "]can not be empty.");
		}
		list.add(value);
	}
	
	public void setNull(){ 
		list.add(null);
	}

	public Object[] getArgs(){
		return list.toArray();
	}
	
	public int size(){
		return list.size();
	}
	
	public PreparedStatementSetter getParameters() {
		if (list.size() == 0) {
			return null;
		}
		PreparedStatementSetter param = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) {
				try {
					this.setValues2(pstmt);
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
			
			public void setValues2(PreparedStatement pstmt) throws SQLException {
				int i = 1;
				for (Object value : list) {
					if (value instanceof String) {
						pstmt.setString(i, (String) value);
					} else if (value instanceof Date) {
						pstmt.setString(i, getTime((Date) value));
					} else if (value instanceof Integer) {
						pstmt.setInt(i, (Integer) value);
					} else if (value instanceof Long) {
						pstmt.setLong(i, (Long) value);
					} else if (value instanceof Double) {
						pstmt.setDouble(i, (Double) value);
					} else if (value instanceof Float) {
						pstmt.setFloat(i, (Float) value);
					}else if (value instanceof BigDecimal){
						pstmt.setBigDecimal(i, (BigDecimal) value);
					} else if (value instanceof Short) {
						pstmt.setShort(i, (Short) value);
					}  else if (value == null){
						pstmt.setNull(i, Types.INTEGER);
					} else {
						throw new RuntimeException("Unknown parameter type[" + i + "." + value + "].");
					}
					i++;
				}
			}
		};
		return param;
	}
	
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private String getTime(Date date) {
		return df.format(date);
	}
}