/*
 * Copyright 2016-2017 the original author or authors.
 *
 * Licensed under the BLK  License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 *      http://www.blk.com
 */
package com.sandy.infrastructure.jdbc.util;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.dao.support.DataAccessUtils;

/**
 * Abstract Data Access Utils
 * @description
 * @author Sandy
 * @since 1.0.0 05th 12 2018
 */
public abstract class AbstractDataAccessUtils extends DataAccessUtils {
	/**
	 * Return a single result object from the given Collection.
	 * <p>Throws an exception if 0 or more than 1 element found.
	 * @param results the result Collection (can be {@code null})
	 * @return the single result object
	 * @throws IncorrectResultSizeDataAccessException if more than one
	 * element has been found in the given Collection
	 * @throws EmptyResultDataAccessException if no element at all
	 * has been found in the given Collection
	 */
	public static <T> T requiredSingleResult(Collection<T> results) throws IncorrectResultSizeDataAccessException {
		int size = (results != null ? results.size() : 0);
		if (size == 0) {
			return null;
		}
		if (results.size() > 1) {
			throw new IncorrectResultSizeDataAccessException(1, size);
		}
		return results.iterator().next();
	}
}

