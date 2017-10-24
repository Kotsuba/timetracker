package com.touchsoft.timetracker.api.service;

import com.touchsoft.timetracker.api.dao.UtilDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UtilService {

    @Resource
    private UtilDao utilDao;

    @Transactional(readOnly = true)
    public Boolean isColumnExistsInReporting(String column){
        return utilDao.isColumnExistsInReporting(column);
    }
}
