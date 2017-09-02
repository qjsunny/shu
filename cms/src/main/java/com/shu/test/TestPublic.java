package com.shu.test;

import com.shu.db.model.message.Message;
import com.shu.db.model.messageText.MessageText;
import com.shu.services.message.MessageService;
import com.shu.services.messageText.MessageTextService;
import com.shu.utils.UUID;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by james on 2017/3/22.
 * 公共测试类
 */
public class TestPublic {

    String[] paths = new String[] { "spring/applicationContext-bo.xml" };
    ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);

    MessageTextService mts = (MessageTextService) ctx.getBean("messageTextService");
    MessageService ms = (MessageService) ctx.getBean("messageService");

    /**
     * 添加MessageText
     */
    @Test
    public void test_messageText() {
        MessageText messageText = new MessageText();
        messageText.setId(UUID.getID());
        messageText.setSendid("1");
        messageText.setSendtype(0);
        messageText.setTitle("标题4");
        messageText.setContents("正文4");
        messageText.setType(1);
        messageText.setReceivetype(0);
        mts.addMessageText(messageText);
    }


    /**
     * 添加Message
     */
    @Test
    public void test_message() {
        Message message = new Message();
        message.setId(UUID.getID());
        message.setReceiveid("2");
        message.setReceivetype(0);
        message.setMessageid("3");
        message.setStatue(1);
        message.setIsdelete(null);
        message.setUpdateUser(null);
        ms.addMessage(message);
    }

    /**
     * 1对1私信未查看
     */
    @Test
    public void test1() {
        Message message = new Message();

        message.setReceiveid("2");
        message.setReceivetype(0);

        message.setStatue(0);
        List<Message> messageList = ms.getMessageListByParam(message, null, null);
        for (Message m:
             messageList) {
            String id = m.getMessageid();
            MessageText messageText = mts.getMessageTextById(id);
        }
    }

    /**
     * 1对1私信查看
     */
    @Test
    public void test2() {
        Message message = new Message();

        message.setReceiveid("2");
        message.setReceivetype(0);

        message.setStatue(1);
        List<Message> messageList = ms.getMessageListByParam(message, null, null);
        for (Message m:
                messageList) {
            String id = m.getMessageid();
            MessageText messageText = mts.getMessageTextById(id);
        }
    }

    /**
     * 1对多公告(未)查看
     */
    @Test
    public void test3() {
        Message message = new Message();

        message.setReceiveid("2");
        message.setReceivetype(0);

        MessageText messageText = new MessageText();

        messageText.setType(1);
        messageText.setReceivetype(0);

        List<MessageText> messageTextList = mts.getMessageTextListByParam(messageText, null, null);

        List<Message> messageList = ms.getMessageListByParam(message, null, null);
        for (MessageText mt:
                messageTextList) {
            String id = mt.getId();
            boolean k = false;
            for (Message m:
                    messageList) {
                k = id.equals(m.getMessageid());
            }
            if (k)
                System.out.println("已查公告id:" + id);
            else
                System.out.println("未查公告id:" + id);
        }
    }
}
