package com.ums.SERVICE;

import java.util.List;

import com.ums.MODEL.Student;
import com.ums.MODEL.User;

public interface UserService {
	public List<User> getAllUsers() ;

    public User getUserById(Long id) ;
    public void saveUser(User user) ;

    public void deleteUser(Long id) ;

    }
