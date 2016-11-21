package org.hni.order.service;

public interface LockingService {

	boolean acquireLock(String key);
	boolean isLocked(String key);
	void releaseLock(String key);
	void shutdown();
}
