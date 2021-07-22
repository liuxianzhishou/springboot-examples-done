package com.example.mybatisGen.dao;

import com.example.mybatisGen.entity.Show;

public interface ShowMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Show record);

    int insertSelective(Show record);

    Show selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Show record);

    int updateByPrimaryKey(Show record);
}