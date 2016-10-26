package org.hni.provider.dao;

import org.hni.common.dao.AbstractDAO;
import org.hni.provider.om.Provider;

public class DefaultProviderDAO extends AbstractDAO<Provider> implements ProviderDAO {

	protected DefaultProviderDAO() {
		super(Provider.class);
	}

}
