package com.yh.mybatisgenwebsocket.service;

import com.yh.mybatisgenwebsocket.entity.Flow2;

public interface Flow2Service {
    int deleteByPrimaryKey(Long id);

    int insert(Flow2 record);

    int insertSelective(Flow2 record);

    Flow2 selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Flow2 record);

    int updateByPrimaryKey(Flow2 record);
}
