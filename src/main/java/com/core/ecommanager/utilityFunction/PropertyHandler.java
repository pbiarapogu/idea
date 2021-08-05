/*
 * File: PropertyHandler.java
 * Copyright 2009 cobornsdelivers Inc.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * cobornsdelivers INC. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with cobornsdelivers Inc.
 *
 * Created on: May 22, 2009 / 3:45:26 PM
 * Created by: bnsgg1
 * Version: 1.0
 * Project Name: OnlineGrocery - SiteApp
 * Last updated / updated by: May 22, 2009 3:45:26 PM /bnsgg1 
 */

package com.core.ecommanager.utilityFunction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class is used to read the configurations from the Properties dynamically
 * @author bnsgg1
 *
 */
public class PropertyHandler implements FileChangedListener {

	
	private static Logger logger = LoggerFactory.getLogger(PropertyHandler.class);
	private Map<String, Properties> propertiesMap = null; 
	private FileWatcher watcher;
	private long  interval = 1000L;

	/**
	 * Denotes the time interval after which the property file is loaded once modified
	 */
	public static final long TIME_INTERVAL = 120000L;

	/**
	 * Method to get an instance of PropertyHandler
	 * @return PropertyHandler
	 */
	public PropertyHandler() {
		propertiesMap = new ConcurrentHashMap<String, Properties>();
	}
	
	public Properties getProperties(final String fileName) {
		Properties properties = null;
		if (fileName != null) {
			properties = getPropertiesInstance(fileName);
			if (properties == null) {
				try {
					loadProperties(fileName);
					logger.info("properties in: " + fileName + " has been loaded. Time: " + new Date());
					properties = getPropertiesInstance(fileName);
				} catch (ApplicationException appe) {
					logger.info("unable to load properties in: " + fileName + " Time: " + new Date(), appe);
				}
			}
		}
		return properties;
	}

	/**
	 * Method to get property
	 * @param propertyName
	 * @return String
	 */
	public String getProperty(final String fileName, final String propertyName) {
		String propertyValue = null;
		if (fileName != null && propertyName != null) {
			Properties properties = getPropertiesInstance(fileName);
			if (properties == null) {
				try {
					loadProperties(fileName);
					logger.info("properties in: " + fileName + " has been loaded. Time: " + new Date());
					properties = getPropertiesInstance(fileName);
				} catch (ApplicationException appe) {
					logger.info("unable to load properties in: " + fileName + " Time: " + new Date(), appe);
				}
			}
			if (properties != null) {
				propertyValue = properties.getProperty(propertyName);
			}
		}
		return propertyValue;
	}

	/**
	 * Method called when notified by the filewatcher
	 * @param w
	 * @param changedFile
	 */
	public void handleFileChanged(final FileWatcher w, final File changedFile) {
		logger.info("reloading properties...");
		try {
			loadProperties(changedFile.getAbsolutePath());
			logger.info("properties in: " + changedFile.getName() + " has been re-loaded. Time: " + new Date());
		} catch (ApplicationException appe) {
			logger.info("unable to re-load properties in: " + changedFile.getName() + " Time: " + new Date(), appe);
		}
	}

	private Properties getPropertiesInstance(final String fileName) {
		return propertiesMap.get(fileName);
	}

	/**
	 * Method to load properties
	 * @param fileName
	 * @throws ApplicationException
	 */
	private void loadProperties(final String fileName) throws ApplicationException {
		try {
			FileResolver resolver = new FileResolver();
			File file = resolver.findExistingFile(fileName);
			Properties properties;
			if (file != null) {
				properties=loadPropertiesFromFile(file);
				FileWatcher watcher = new FileWatcher();
				watcher.watchFile(this, file, interval);
			} else {
				properties=loadPropertiesFromResource(fileName);
			}
			propertiesMap.put(fileName, properties);
		} catch (FileNotFoundException fne) {
			throw new ApplicationException(fne);
		} catch (IOException ioe) {
			throw new ApplicationException(ioe);
		}
	}
	
	private Properties loadPropertiesFromFile(File file) throws FileNotFoundException, IOException {
		PropertyResourceBundle bundle = new PropertyResourceBundle(new FileInputStream(file));
		Enumeration<String> resourceEnum = bundle.getKeys();
		// 1. Traverse through the Enumaration
		// 2. Get the key from the enumaration
		// 3. Get the value for the key from resourcebundle
		// 4. Put the key and value in the in-memory HashMap which serves as cache
		// 5. Instantiate FileWatcher and let it spawn a thread to watch this file
		Properties properties = new Properties();
		while (resourceEnum.hasMoreElements()) {
			String strKey = (String) resourceEnum.nextElement();
			String strValue = bundle.getString(strKey);
			properties.put(strKey, strValue);
		}
		return properties;
	}
	
	private Properties loadPropertiesFromResource(String fileName) throws IOException {
		InputStream stream=getClass().getClassLoader().getResourceAsStream(fileName);
		Properties properties=new Properties();
		if (stream!=null) {
			properties.load(stream);
			stream.close();
		}
		return properties;
	}
}
