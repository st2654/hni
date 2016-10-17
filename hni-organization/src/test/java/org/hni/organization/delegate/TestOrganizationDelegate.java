package org.hni.organization.delegate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.BasicConfigurator;
import org.hni.organization.om.Organization;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-applicationContext.xml"} )
@Transactional
public class TestOrganizationDelegate {

	@Inject private OrganizationDelegate organizationDelegate;
	
	public TestOrganizationDelegate() {
		BasicConfigurator.configure();
	}

	@Test
	public void getOrg() {
		Organization org = organizationDelegate.get(2L);
		assertNotNull(org);
		assertEquals("Samaritan House", org.getName() );
	}
	
	@Test
	public void testAddOrg() {
		Organization org = new Organization();
		org.setName("Test org");
		organizationDelegate.save(org);
		
		Organization orgValidate = organizationDelegate.get(org.getId());
		assertNotNull(orgValidate);		
	}

	@Test
	public void testGetAll() {
		List<Organization> list = organizationDelegate.getAll();
		assertEquals(2, list.size());
	}
}
