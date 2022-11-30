package org.medmota.restdemo.service;

import java.util.List;
import java.util.Optional;

import org.medmota.restdemo.entity.User;
import org.medmota.restdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public Optional<User> findUserById(Integer id) {
		return userRepository.findById(id);
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}

}
