package org.hni.security.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.apache.log4j.BasicConfigurator;
import org.hni.security.om.Permission;
import org.hni.security.service.PermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-applicationContext.xml" })
@Transactional
public class TestPermissionService {

	@Inject
	private PermissionService permissionService;

	public TestPermissionService() {
		BasicConfigurator.configure();
	}

	@Test
	public void testGet() {
		Permission permission = permissionService.get(1L);
		assertEquals("Create Organization", permission.getName());
	}

	@Test
	public void testUpdate() {
		Permission permission = permissionService.get(1L);
		String updatedName = "Create Org";
		permission.setName(updatedName);
		permissionService.update(permission);
		Permission updatedPermission = permissionService.get(1L);
		assertEquals(updatedName, updatedPermission.getName());
	}

	@Test
	public void testDelete() {
		Permission permission = permissionService.get(1L);
		permissionService.delete(permission);
		Permission shouldBeNullPermission = permissionService.get(1L);
		assertNull(shouldBeNullPermission);
	}

	@Test
	public void testInsert() {
		Permission permission = new Permission();
		String insertedName = "Ad Hoc Permission";
		permission.setName(insertedName);
		Permission insertedPermission = permissionService.insert(permission);
		assertEquals(insertedName, insertedPermission.getName());
		assertTrue(insertedPermission.getId() > 64);
	}
}