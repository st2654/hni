package org.hni.security.service;

import javax.inject.Inject;

import org.hni.organization.service.OrganizationUserService;
import org.hni.security.om.ActivationCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-applicationContext.xml" })
@Transactional
public class TestActivationCodeService {

	@Inject private ActivationCodeService activationCodeService;
	@Inject private OrganizationUserService organizationUserService;

	//From test-data.sql
	private static final Long SUPPLIED_AUTH_CODE_1 = 123456L;
	private static final String INSERTED_ID_1 = "MTIzNDU2WX";

	private static final Long SUPPLIED_AUTH_CODE_2 = 987654L;
	private static final String INSERTED_ID_2 = "OTg3NjU0KZ";

	public TestActivationCodeService() {}

	@Test
	public void testGetAll() {
        List<ActivationCode> getAllResult = activationCodeService.getAll();
        assertEquals(getAllResult.size(), 2);
		for (ActivationCode code : getAllResult) {
			assertTrue(code.getId().equals(INSERTED_ID_1) || code.getId().equals(INSERTED_ID_2));
		}
	}

	@Test
	public void testGetByCode() {
		ActivationCode code = activationCodeService.getByActivationCode(123456L);
		assertNotNull(code);
		assertNull(code.getUser());
	}
	
	@Test
	public void testActivate() {
		ActivationCode code = activationCodeService.get(INSERTED_ID_1);
		assertNotNull(code);
		assertNull(code.getUser());
		code.setUser(organizationUserService.get(3L));
		code.setComments("mikey user");
		activationCodeService.save(code);
		
		ActivationCode codeValidate = activationCodeService.get(INSERTED_ID_1);
		assertNotNull(codeValidate);
		assertNotNull(codeValidate.getUser());
		assertEquals(3L, codeValidate.getUser().getId().intValue());
		assertEquals("mikey user", code.getComments());
	}

	@Test
	public void testValidateActivated() {
		assertTrue(activationCodeService.validate(SUPPLIED_AUTH_CODE_1));
		assertFalse(activationCodeService.validate(SUPPLIED_AUTH_CODE_2));
	}

	@Test
	public void testValidateMealsAuthorized() {
		ActivationCode code = activationCodeService.getByActivationCode(SUPPLIED_AUTH_CODE_1);
		code.setMealsAuthorized(5L);
		activationCodeService.update(code);
		assertTrue(activationCodeService.validate(SUPPLIED_AUTH_CODE_1));

		code.setMealsAuthorized(0L);
		activationCodeService.update(code);
		assertFalse(activationCodeService.validate(SUPPLIED_AUTH_CODE_1));
	}

	@Test
	public void testValidateMealsRemaining() {
		ActivationCode code = activationCodeService.getByActivationCode(SUPPLIED_AUTH_CODE_1);
		code.setMealsRemaining(5L);
		activationCodeService.update(code);
		assertTrue(activationCodeService.validate(SUPPLIED_AUTH_CODE_1));

		code.setMealsRemaining(0L);
		activationCodeService.update(code);
		assertFalse(activationCodeService.validate(SUPPLIED_AUTH_CODE_1));
	}

	//TODO Validation Org test?
}
