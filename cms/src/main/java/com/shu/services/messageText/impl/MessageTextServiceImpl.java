package com.shu.services.messageText.impl;

import com.shu.db.dao.messageText.MessageTextMapper;
import com.shu.db.model.Order;
import com.shu.db.model.Pager;
import com.shu.db.model.messageText.MessageText;
import com.shu.services.messageText.MessageTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by james on 2017/3/22.
 */
@Service("messageTextService")
@Transactional
public class MessageTextServiceImpl implements MessageTextService{
    @Autowired
    MessageTextMapper messageTextMapper;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<MessageText> getMessageTextListByParam(MessageText messageText, Order order, Pager page) {
        if (page != null) {
            int count = messageTextMapper.selectCountByParam(messageText);
            page.setRecordCount(count);
        }
        return messageTextMapper.selectListByParam(messageText, order, page);
    }

    @Override
    public void addMessageText(MessageText messageText) {
        messageTextMapper.insertSelective(messageText);
    }

    @Override
    public void modifyMessageText(MessageText messageText) {
        messageTextMapper.updateByPrimaryKeySelective(messageText);
    }

    @Override
    public MessageText getMessageTextById(String id) {
        return messageTextMapper.selectByPrimaryKey(id);
    }
}
