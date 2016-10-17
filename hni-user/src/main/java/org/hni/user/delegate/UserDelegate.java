package org.hni.user.delegate;

import java.util.List;

import org.hni.common.delegate.BaseDelegate;
import org.hni.user.om.User;

public interface UserDelegate extends BaseDelegate<User> {

	 List<User> byMobilePhone(String byMobilePhone);
	 List<User> byLastName(String lastName);
}
