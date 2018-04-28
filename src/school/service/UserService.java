package school.service;

import school.domain.User;

public interface UserService {

	/**
	 * 登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	User login(String username, String password);
}