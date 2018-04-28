package school.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.domain.User;
import school.mapper.UserMapper;
import school.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User login(String username, String password) {
		User user = new User();
		user.setName(username);
		user.setPassword(password);
		return userMapper.selectOne(user);
	}
}