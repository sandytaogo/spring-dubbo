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
package com.sandy.infrastructure.context;

/**
 * Spring profile 甯哥敤鏂规硶涓巔rofile鍚嶇О銆�
 * 
 * @author Sandy
 * @since 1.0.0 05th 12 2018
 */
public final class Profiles {

	public static final String ACTIVE_PROFILE = "spring.profiles.active";

	public static final String DEFAULT_PROFILE = "spring.profiles.default";

	public static final String DEVELOPMENT = "dev";
	/**
	 * Unit test tasks include: 1. Module interface test; 2. module local data
	 * structure test; 3. Module boundary condition test; 4. Test all
	 * independent execution paths in the module; 5. Module error handling path
	 * test.
	 */
	public static final String UNIT_TEST = "test";
	/**
	 * System Integration Testing public static final String UNIT_SIT = "sit";
	 * /**User Acceptance Testing
	 */
	public static final String UNIT_UAT = "uat";
	/**
	 * production
	 * 
	 */
	public static final String PRODUCTION = "prod";

	public static void setProfileAsSystemProperty(String profile) {
		System.setProperty(ACTIVE_PROFILE, profile);
	}
}
