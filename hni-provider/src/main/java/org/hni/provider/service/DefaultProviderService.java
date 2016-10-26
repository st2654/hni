package org.hni.provider.service;

import javax.inject.Inject;

import org.hni.common.service.AbstractService;
import org.hni.provider.dao.ProviderDAO;
import org.hni.provider.om.Provider;

public class DefaultProviderService extends AbstractService<Provider> implements ProviderService {

	private ProviderDAO providerDao;
	
	@Inject
	public DefaultProviderService(ProviderDAO providerDao) {
		super(providerDao);
		this.providerDao = providerDao;
	}


}
