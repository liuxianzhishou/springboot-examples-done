package com.example.mybatisGen.service.Impl;

import com.example.mybatisGen.entity.Show;
import com.example.mybatisGen.dao.ShowMapper;
import com.example.mybatisGen.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowServiceImpl implements ShowService {
    @Autowired
    private ShowMapper showMapper;

    public int deleteByPrimaryKey(Long id) {
        return showMapper.deleteByPrimaryKey(id);
    }

    public int insert(Show record) {
        return showMapper.insert(record);
    }

    public int insertSelective(Show record) {
        return showMapper.insertSelective(record);
    }

    public Show selectByPrimaryKey(Long id) {
        return showMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Show record) {
        return showMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Show record) {
        return showMapper.updateByPrimaryKey(record);
    }
}
