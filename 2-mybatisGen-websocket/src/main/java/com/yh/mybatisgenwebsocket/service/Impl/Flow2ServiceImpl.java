package com.yh.mybatisgenwebsocket.service.Impl;

import com.yh.mybatisgenwebsocket.entity.Flow2;
import com.yh.mybatisgenwebsocket.mapper.Flow2Mapper;
import org.springframework.stereotype.Service;
import com.yh.mybatisgenwebsocket.service.Flow2Service;

import javax.annotation.Resource;

@Service
public class Flow2ServiceImpl implements Flow2Service {
    @Resource
    private Flow2Mapper flow2Mapper;

    public int deleteByPrimaryKey(Long id)
    {
        return flow2Mapper.deleteByPrimaryKey(id);
    }

    public int insert(Flow2 record)
    {
        return flow2Mapper.insert(record);
    }

    public int insertSelective(Flow2 record)
    {
        return flow2Mapper.insertSelective(record);
    }

    public Flow2 selectByPrimaryKey(Long id)
    {
        return flow2Mapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Flow2 record)
    {
        return flow2Mapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Flow2 record)
    {
        return updateByPrimaryKeySelective(record);
    }
}
