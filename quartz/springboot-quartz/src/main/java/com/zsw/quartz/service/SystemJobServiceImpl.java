package com.zsw.quartz.service;

import com.zsw.quartz.persistence.entity.SystemJob;
import com.zsw.quartz.persistence.mapper.SystemJobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class SystemJobServiceImpl implements SystemJobService {
    @Autowired
    private SystemJobMapper systemJobMapper;

    @Override
    public int getJobCount() {
        return systemJobMapper.getJobCount();
    }

    @Override
    public List<SystemJob> querySysJobList(HashMap<String, String> map) {
        return systemJobMapper.querySysJobList(map);
    }

    @Override
    public int insertSelective(SystemJob record) {
        return systemJobMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return systemJobMapper.deleteByPrimaryKey(id);
    }

    @Override
    public SystemJob selectByPrimaryKey(Integer id) {
        return systemJobMapper.selectByPrimaryKey(id);
    }

    @Override
    public SystemJob selectByBean(SystemJob bean) {
        return systemJobMapper.selectByBean(bean);
    }

    @Override
    public int updateByPrimaryKeySelective(SystemJob bean) {
        return systemJobMapper.updateByPrimaryKeySelective(bean);
    }

}
