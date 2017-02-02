package com.shu.services.mail.impl;

import com.shu.db.dao.mail.TMailMapper;
import com.shu.db.model.Order;
import com.shu.db.model.Pager;
import com.shu.db.model.mail.TMail;
import com.shu.services.mail.TMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by admin on 2017/2/2.
 */
@Service("mailService")
@Transactional
public class TMailServiceImpl implements TMailService {

    @Autowired
    TMailMapper tMailMapper;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<TMail> getMailListByParam(TMail mail, Order order, Pager page) {
        if (page != null) {
            int count = tMailMapper.selectCountByParam(mail);
            page.setRecordCount(count);
        }
        return tMailMapper.selectListByParam(mail, order, page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addMail(TMail mail) {
        tMailMapper.insertSelective(mail);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyMail(TMail mail) {
        tMailMapper.updateByPrimaryKeySelective(mail);
    }

    @Override
    @Transactional(readOnly = true)
    public TMail getMailById(String id) {
        return tMailMapper.selectByPrimaryKey(id);
    }
}
