package org.hni.common.delegate;

import java.util.List;

import org.hni.common.dao.BaseDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractDelegate<T> implements BaseDelegate<T> {
	private static final Logger logger = LoggerFactory.getLogger(AbstractDelegate.class);
	protected BaseDAO<T> dao;

	public AbstractDelegate(BaseDAO<T> dao) {
		setDao(dao);
	}
	
	public BaseDAO<T> getDao() {
		return dao;
	}

	public void setDao(BaseDAO<T> dao) {
		this.dao = dao;
	}

	@Override
	public T get(Object id) {
		return dao.get(id);
	}

	@Override
	@Transactional
	public T insert(T obj) {
		return dao.insert(obj);
	}

	@Override
	@Transactional
	public T update(T obj) {
		return dao.update(obj);
	}

	@Override
	@Transactional
	public T save(T obj) {
		return dao.save(obj);
	}

	@Override
	@Transactional
	public T delete(T obj) {
		return dao.delete(obj);
	}

	@Override
	public List<T> getAll() {
		return dao.getAll();
	}


}
