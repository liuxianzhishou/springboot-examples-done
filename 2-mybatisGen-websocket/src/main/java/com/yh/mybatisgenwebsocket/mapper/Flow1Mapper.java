package com.yh.mybatisgenwebsocket.mapper;

import com.yh.mybatisgenwebsocket.entity.Flow1;

import java.util.List;

public interface Flow1Mapper {
    int deleteByPrimaryKey(Long id);

    int insert(Flow1 record);

    int insertSelective(Flow1 record);

    Flow1 selectByPrimaryKey(Long id);

    List<Flow1> selectByTime(String createTime);

    int updateByPrimaryKeySelective(Flow1 record);

    int updateByPrimaryKey(Flow1 record);
}