package org.hni.common.delegate;

import java.util.List;

public interface BaseDelegate<T> {
	T get(Object id);
	T insert(T obj);
	T update(T obj);
	T save(T obj); 	
	T delete(T obj);
	List<T> getAll();
}
