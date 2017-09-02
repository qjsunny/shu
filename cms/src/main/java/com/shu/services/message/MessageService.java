package com.shu.services.message;

import com.shu.db.model.Order;
import com.shu.db.model.Pager;
import com.shu.db.model.message.Message;

import java.util.List;

/**
 * Created by james on 2017/3/21.
 */
public interface MessageService {
    public List<Message> getMessageListByParam(Message message, Order order, Pager page);

    public void addMessage(Message message);

    public void modifyMessage(Message message);

    public Message getMessageById(String id);
}
