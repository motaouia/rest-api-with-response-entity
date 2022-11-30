package org.medmota.restdemo.service;

import java.util.List;
import java.util.Optional;

import org.medmota.restdemo.entity.User;

public interface IUserService {

	public List<User> getAllUsers();
	public void save(User user);
	public Optional<User> findUserById(Integer id);
	public void delete(User user);

}
