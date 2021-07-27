package com.yh.mybatisgenwebsocket.service.Impl;

import com.yh.mybatisgenwebsocket.entity.Flow1;
import com.yh.mybatisgenwebsocket.mapper.Flow1Mapper;
import com.yh.mybatisgenwebsocket.service.Flow1Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Flow1ServiceImpl implements Flow1Service {
    @Resource
    private Flow1Mapper flow1Mapper;

    public int deleteByPrimaryKey(Long id)
    {
        return flow1Mapper.deleteByPrimaryKey(id);
    }

    public int insert(Flow1 record)
    {
        return flow1Mapper.insert(record);
    }

    public int insertSelective(Flow1 record)
    {
        return flow1Mapper.insertSelective(record);
    }

    public Flow1 selectByPrimaryKey(Long id)
    {
        return flow1Mapper.selectByPrimaryKey(id);
    }

    public List<Flow1> selectByTime(String createTime)
    {
        return flow1Mapper.selectByTime(createTime);
    }

    public int updateByPrimaryKeySelective(Flow1 record)
    {
        return flow1Mapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Flow1 record)
    {
        return flow1Mapper.updateByPrimaryKey(record);
    }
}
