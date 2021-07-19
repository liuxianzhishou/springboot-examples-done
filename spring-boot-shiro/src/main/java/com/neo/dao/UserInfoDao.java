package com.neo.dao;

import com.neo.model.UserInfo;
import org.springframework.data.repository.CrudRepository;

//CrudRepository提供了最基本的对实体类的增、删、改、查操作
public interface UserInfoDao extends CrudRepository<UserInfo,Long> {
    /**通过username查找用户信息;*/
    public UserInfo findByUsername(String username);
}