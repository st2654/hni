package org.hni.security.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.hni.organization.service.OrganizationUserService;
import org.hni.security.om.ActivationCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-applicationContext.xml" })
@Transactional
public class TestActivationCodeService {

	@Inject private ActivationCodeService activationCodeService;
	@Inject private OrganizationUserService organizationUserService;
	
	public TestActivationCodeService() {}
	
	@Test
	public void testGetAll() {
		for (ActivationCode code : activationCodeService.getAll()) {
			System.out.println(code.getId());
		}
	}
	@Test
	public void testGetCode() {
		String id = "1234567890";
		ActivationCode code = activationCodeService.get(id);
		assertNotNull(code);
		assertNull(code.getUser());
	}
	
	@Test
	public void testAddUser() {
		String id = "1234567890";
		ActivationCode code = activationCodeService.get(id);
		assertNotNull(code);
		assertNull(code.getUser());
		code.setUser(organizationUserService.get(3L));
		code.setComments("mikey user");
		activationCodeService.save(code);
		
		ActivationCode codeValidate = activationCodeService.get(id);
		assertNotNull(codeValidate);
		assertNotNull(codeValidate.getUser());
		assertEquals(3L, codeValidate.getUser().getId().intValue());
	}
}
