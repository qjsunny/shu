package com.shu.services.messageText;

import com.shu.db.model.Order;
import com.shu.db.model.Pager;
import com.shu.db.model.messageText.MessageText;

import java.util.List;

/**
 * Created by james on 2017/3/22.
 */
public interface MessageTextService {
    public List<MessageText> getMessageTextListByParam(MessageText messageText, Order order, Pager page);

    public void addMessageText(MessageText messageText);

    public void modifyMessageText(MessageText messageText);

    public MessageText getMessageTextById(String id);
}
