package org.hni.security.service;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-applicationContext.xml"} )
@Transactional
public class TestCreateAuthCode {
	private static final Logger logger = LogManager.getLogger(TestCreateAuthCode.class);
	public TestCreateAuthCode() {	
		BasicConfigurator.configure();
	}

	@Test
	public void test1() {
		Long largePrime = 547L; // 992nd prime
		
		for(int i = 1; i < 10001; i++) {
			Long value = largePrime * i;
			System.out.println(String.format("%d - %s - %s",
					value
					,new String(Base64.encodeBase64(value.toString().getBytes())).toLowerCase()
					,new String(Base64.encodeBase64(new Integer(i).toString().getBytes())).toLowerCase()));
		}
	}

}
