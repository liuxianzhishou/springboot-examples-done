package com.neo.service;

import com.neo.mapper.UserMapper;
import com.neo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    UserMapper userMapper;

     /*
        Service层介于controller和dao之间作为服务层进行一些逻辑处理，
        这里逻辑太简单所以知识单纯调用dao所以不做注释
     */
     public List<User> getAll()
     {
         return userMapper.getAll();
     }
    public User getOne(Long id)
    {
        return userMapper.getOne(id);
    }
    public void insert(User user)
    {
        userMapper.insert(user);
    }
    public void update(User user)
    {
        userMapper.update(user);
    }
    public void delete(Long id)
    {
        userMapper.delete(id);
    }
}
