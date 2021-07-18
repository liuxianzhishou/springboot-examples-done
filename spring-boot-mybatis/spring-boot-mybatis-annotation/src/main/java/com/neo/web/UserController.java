package com.neo.web;

import java.util.List;

import com.neo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neo.model.User;
import com.neo.mapper.UserMapper;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/getUsers")
	public List<User> getUsers() {
		List<User> users=userService.getAll();
		return users;
	}

	//http://localhost:8080/getUser?id=2
    @RequestMapping("/getUser")
    public User getUser(Long id) {
    	User user=userService.getOne(id);
        return user;
    }
    
    @RequestMapping("/add")
    public void save(User user) {
    	userService.insert(user);
    }
    
    @RequestMapping(value="update")
    public void update(User user) {
    	userService.update(user);
    }

    //將@PathVariable中的id傳到地址欄
    @RequestMapping(value="/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
    	userService.delete(id);
    }
    
    
}