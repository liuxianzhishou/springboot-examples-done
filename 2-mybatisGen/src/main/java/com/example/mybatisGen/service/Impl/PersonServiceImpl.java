package com.example.mybatisGen.service.Impl;

import com.example.mybatisGen.entity.Person;
import com.example.mybatisGen.dao.PersonMapper;
import com.example.mybatisGen.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 出错5——没有加@Service
 */
@Service
public class PersonServiceImpl implements PersonService {
    @Resource
    private PersonMapper personMapper;

    //删
    public int deleteByPrimaryKey(Long id)
    {
        return personMapper.deleteByPrimaryKey(id);
    }

    //增
    public int insert(Person record)
    {
        return personMapper.insert(record);
    }

    //非空增，也就是说，只增加record里面有数据的部分，其余的数据不更新，保存原来的
    public int insertSelective(Person record)
    {
        return personMapper.insertSelective(record);
    }

    //查
    public Person selectByPrimaryKey(Long id)
    {
        return personMapper.selectByPrimaryKey(id);
    }

    //更新
    public int updateByPrimaryKeySelective(Person record)
    {
        return personMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Person record)
    {
        return personMapper.updateByPrimaryKey(record);
    }
}
