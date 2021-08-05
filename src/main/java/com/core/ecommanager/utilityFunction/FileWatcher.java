/*
 * File: FileWatcher.java
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
 * Last updated / updated by: May 22, 2009 3:45:26 PM / bnsgg1
 */
package com.core.ecommanager.utilityFunction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;

/**
 * The file watcher class responsible to watch the file
 *
 */
public class FileWatcher {
	private static final Logger logger = LoggerFactory.getLogger(FileWatcher.class);
	private Map<File, WatchedFile> files = new Hashtable<File, WatchedFile>(); // A map of (File -> WatchedFile)
	private static boolean threadStarted = false;
	private WatcherThread thread = null; // the thread; null means no thread
											// is alive now
	private long lastScan = 0L; // the time since the last scan
	private boolean stayAlive = true; // true if the thread should continue
										// examining
	private long interval = 0L; // how long to wait between scans

	/**
	 * Inner class
	 *
	 */
	private class WatchedFile {
		public WatchedFile(final long time) {
			m = time;
		}
		private long m = 0L; // the last known mod time
		private List<FileChangedListener> who = new ArrayList<FileChangedListener>(); // the list of FileChangedListeners subscribed
	}

	/**
	 * Inner class representing the Thread which watches the file
	 *
	 */
	private class WatcherThread extends Thread {
		/**
		 * Constructor
		 *
		 */
		public WatcherThread() {
			super();
			if (!threadStarted) {
				try {
					start();
				} catch (Exception e) {
					logger.error("Exception occurs while starting thread", e);
				}
				threadStarted = true;
			}
		}

		/**
		 * Method which is executed when a thread runs
		 */
		public void run() {
			while (stayAlive) {
				lastScan = System.currentTimeMillis();
				Iterator<File> e = files.keySet().iterator();
				while (e.hasNext()) {
					File f = (File) e.next();
					examineAndNotify(f);
				}
				long now = System.currentTimeMillis();
				if (now - lastScan < interval) {
					try {
						Thread.sleep(interval - (now - lastScan));
					} catch (InterruptedException ie) {
						logger.error("Catch in run", ie);
					}
				}
			}
			thread = null;
		}
	}

	/**
	 * Constructor
	 *
	 */
	public FileWatcher() {
	}

	/**
	 * Constructor
	 *
	 * @param ms  Interval in milliseconds between checking for file changes.
	 */
	public FileWatcher(final long ms) {
		interval = ms;
	}

	/**
	 * Method to do a file watch
	 *
	 * @param l
	 * @param f
	 * @param timeInterval
	 */
	public void watchFile(final FileChangedListener l, final File f, final long timeInterval) {
		interval = timeInterval;
		watchFile(l, f, false);
	}

	/**
	 * Method to do a file watch
	 *
	 * @param l	Listener to be notified of change to file
	 * @param f File to watch
	 * @param initialNotify If true, notify the listener on start-up when file is added to the list of watched files
	 */
	public void watchFile(final FileChangedListener l, final File f, final boolean initialNotify) {
		if (l == null) {
			throw new SystemException("Can't notify a null listener.");
		}
		if (f == null) {
			throw new SystemException("Can't watch a null file.");
		}
		examineAndNotify(f);
		WatchedFile node = files.get(f);
		if (node != null) {
			if (node.who.indexOf(l) != -1) {
				return;
			}
		} else {
			node = new WatchedFile(f.lastModified());
			files.put(f, node);
		}
		if (initialNotify) {
			l.handleFileChanged(this, f);
		}
		node.who.add(l);
		controlThread();
	}

	/**
	 * Method to unsubscribe the listener to a file
	 *
	 * @param l	Listener to remove
	 * @param f File to remove from list of watched files for this listener
	 */
	public void unwatchFile(final FileChangedListener l, final File f) {
		if (l == null) {
			throw new SystemException("Can't unnotify a null listener");
		}
		if (f == null) {
			throw new SystemException("Can't unwatch a null file");
		}
		removeWatcherFromTable(l, f);
		controlThread();
	}

	/**
	 * Method to unsubscribe listeners to all files
	 *
	 * @param l Listener to unsubscribe
	 */
	public void unwatchAllFiles(final FileChangedListener l) {
		if (l == null) {
			throw new SystemException("Can't unnotify a null listener");
		}
		Iterator<File> e = files.keySet().iterator();
		while (e.hasNext()) {
			removeWatcherFromTable(l, e.next());
		}
		controlThread();
	}

	/**
	 * Removes a specific file listener for a file without doing thread control
	 *
	 * @param l Listener to unsubscribe
	 * @param f File to remove from watched file for this listener
	 */
	private void removeWatcherFromTable(final FileChangedListener l, final File f) {
		WatchedFile node = files.get(f);
		if (node != null) {
			node.who.remove(l);
			if (node.who.isEmpty()) {
				files.remove(f);
			}
		}
	}

	/**
	 * Method to do thread control
	 *
	 */
	private void controlThread() {
		if (files.isEmpty()) {
			stayAlive = false;
		} else {
			stayAlive = true;
			if (thread == null) {
				thread = new WatcherThread();
			}
		}
	}

	/**
	 * Method to examine a file and notify if any changes made
	 *
	 * @param f
	 */
	public void examineAndNotify(final File f) {
		if (f != null) {
			WatchedFile node = files.get(f);
			if (node == null) {
				return;
			}
			long modTime = f.lastModified();
			if (node.m != modTime) {
				node.m = modTime;
				for (int i = 0; i < node.who.size(); i++) {
					((FileChangedListener) node.who.get(i)).handleFileChanged(this, f);
				}
			}
		}
	}
}
