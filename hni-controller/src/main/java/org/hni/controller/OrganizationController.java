package org.hni.controller;

import java.util.List;

import javax.inject.Inject;

import org.hni.organization.om.Organization;
import org.hni.organization.service.OrganizationUserService;
import org.hni.user.om.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrganizationController {
	
	@Inject
	OrganizationUserService ous;
	
	@RequestMapping("/org/{orgid}/users")
    public List<User> allOrgUsers(@PathVariable(value="orgid") long orgid) {
      Organization org = new Organization();
      org.setId(orgid);
	  return ous.getAllUsers(org);
	  
    }
	
	@RequestMapping("/org/{orgid}/user/last/{lastName}")
    public List<User> orgUserByLastName(@PathVariable(value="lastName") String lastName,@PathVariable(value="orgid") long orgid) {
      Organization org = new Organization();
      org.setId(orgid);
	  return ous.byLastName(lastName);
    }
	
	
	@RequestMapping("/org/{orgid}/user/phone/{phoneNumber}")
    public List<User> orgUserByPhoneNumber(@PathVariable(value="phoneNumber") String phoneNumber,@PathVariable(value="orgid") long orgid) {
      Organization org = new Organization();
      org.setId(orgid);
	  return ous.byMobilePhone(phoneNumber);
    }
	
	@RequestMapping(method=RequestMethod.DELETE,value="/org/{orgid}/user/{userid}")
    public User deleteUser(@PathVariable(value="userid") long userid, @PathVariable(value="orgid") long orgid) {
      User user = new User();
      Organization org = new Organization();
      org.setId(orgid);
      return ous.delete(user, org);
      
    }
	
	@RequestMapping(method=RequestMethod.POST,value="/org/{orgid}/user")
    public ResponseEntity<User> actionUser(@PathVariable(value="orgid") long orgid,@RequestParam(defaultValue="add",value="action",required=true) String action,@RequestBody(required=true) User user) {
	  Organization org = new Organization();
	  org.setId(orgid);
      switch(action){
	      case "archive":
	    	 return new ResponseEntity<User>(ous.archive(user, org),HttpStatus.OK);
	      case "add":
	    	 return new ResponseEntity<User>(ous.save(user, org),HttpStatus.OK);
	      default:
	    	 return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
      }
    }
}
