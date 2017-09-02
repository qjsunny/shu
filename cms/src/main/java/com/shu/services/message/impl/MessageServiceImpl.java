package com.shu.services.message.impl;

import com.shu.db.dao.manager.ManagerMapper;
import com.shu.db.dao.message.MessageMapper;
import com.shu.db.model.Order;
import com.shu.db.model.Pager;
import com.shu.db.model.message.Message;
import com.shu.services.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by james on 2017/3/21.
 */
@Service("messageService")
@Transactional
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageMapper messageMapper;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<Message> getMessageListByParam(Message message, Order order, Pager page) {
        if (page != null) {
            int count = messageMapper.selectCountByParam(message);
            page.setRecordCount(count);
        }
        return messageMapper.selectListByParam(message, order, page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addMessage(Message message) {
        messageMapper.insertSelective(message);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyMessage(Message message) {
        messageMapper.updateByPrimaryKeySelective(message);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Message getMessageById(String id) {
        return messageMapper.selectByPrimaryKey(id);
    }
}
