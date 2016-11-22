package org.hni.admin.service;

import javax.annotation.PostConstruct;

import org.apache.shiro.SecurityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.monitorjbl.json.JsonView;
import com.monitorjbl.json.JsonViewSerializer;

public class AbstractBaseController {

    protected ObjectMapper mapper = new ObjectMapper();
    protected SimpleModule module = new SimpleModule();
    
	@PostConstruct
    public void configureJackson() {
            module.addSerializer(JsonView.class, new JsonViewSerializer());
            mapper.registerModule(module);
    }
	
    protected boolean isPermitted(String domain, String permission, Long id) {
    	if ( SecurityUtils.getSubject().isPermitted(createPermission(domain,permission,id))) {
    		return true;
    	}
    	return false;
    	
    }

    private String createPermission(String domain, String action, Long instance)
    {
    	return String.format("%s:%s:%d", domain, action, instance);
    }

    
}
