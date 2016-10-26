package org.hni.provider.service;

import java.util.Collection;

import javax.inject.Inject;

import org.hni.common.service.AbstractService;
import org.hni.provider.dao.MenuDAO;
import org.hni.provider.om.Menu;
import org.hni.provider.om.Provider;

public class DefaultMenuService extends AbstractService<Menu> implements MenuService {

	private MenuDAO menuDao;
	
	@Inject
	public DefaultMenuService(MenuDAO menuDao) {
		super(menuDao);
		this.menuDao = menuDao;
	}

	@Override
	public Collection<Menu> get(Provider provider) {
		return menuDao.get(provider);
	}



}
