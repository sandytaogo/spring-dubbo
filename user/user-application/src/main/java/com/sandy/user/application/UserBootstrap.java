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
package com.sandy.user.application;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.ClassUtils;

import com.google.common.util.concurrent.AbstractIdleService;
import com.sandy.infrastructure.context.Profiles;

import sun.management.VMManagement;

/**
 * application Bootstrap 
 * 
 * 
 * @author Sandy
 * @since 1.0.0 11th 10 2018
 */
public class UserBootstrap extends AbstractIdleService {

	private final Logger LOGGER = LoggerFactory.getLogger(UserBootstrap.class);
	
	public static final Object LOCK_KEY = new Object();
	
	protected ClassPathXmlApplicationContext applicationCentext;
	
	protected Long start,end;
	
	@Override
	protected void startUp() throws Exception {
		start = System.currentTimeMillis();
		LOGGER.info("user service initialization start up begin");
		try {
			//SpringApplication.setAdditionalProfiles("test"); 
			//ConfigurableEnvironment.setActiveProfiles("test");
			applicationCentext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
			// add a shutdown hook for the above context... 
			applicationCentext.registerShutdownHook();
			applicationCentext.start();
	        // app runs here...
	        // main method exits, hook is called prior to the app shutting down...
		} catch (Exception e) {
			LOGGER.error(e.getMessage() , e);
			throw e;
		}
        // app runs here...
        // main method exits, hook is called prior to the app shutting down...
		LOGGER.info("application user service start up successful");
		end = System.currentTimeMillis();
		
		RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
		Field jvm = runtime.getClass().getDeclaredField("jvm");
		jvm.setAccessible(true);
		VMManagement mgmt = (VMManagement) jvm.get(runtime);
		Method pidMethod = mgmt.getClass().getDeclaredMethod("getProcessId");
		pidMethod.setAccessible(true);
		int pid = (Integer) pidMethod.invoke(mgmt);
		
		double uptime = runtime.getUptime() / 1000.0;
		String applicationName = ClassUtils.getShortName(getClass());
		LOGGER.info(String.format("Started %s PID %s in %s seconds (JVM running for %s)", applicationName, pid, ((end-start) / 1000.0), uptime));
	}

	@Override
	protected void shutDown() throws Exception {
		applicationCentext.stop();
	}
	
	public static void main(String [] args) throws Exception {
		String profile = System.getProperty(Profiles.ACTIVE_PROFILE);
		if(null != profile && !"".equals(profile)) {
			Profiles.setProfileAsSystemProperty(profile);
		}else {
			Profiles.setProfileAsSystemProperty(Profiles.DEVELOPMENT);		
		}
		UserBootstrap bootStrap = new UserBootstrap();
		bootStrap.startAsync();
		synchronized (LOCK_KEY) {
			LOCK_KEY.wait();
		}
	}
}