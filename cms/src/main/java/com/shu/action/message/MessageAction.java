package com.shu.action.message;

import com.alibaba.fastjson.JSONObject;
import com.shu.db.model.Order;
import com.shu.db.model.OrderSort;
import com.shu.db.model.enterprise.Enterprise;
import com.shu.db.model.manager.Manager;
import com.shu.db.model.message.Message;
import com.shu.db.model.messageText.MessageText;
import com.shu.services.enterprise.EnterpriseService;
import com.shu.services.manager.ManagerService;
import com.shu.services.message.MessageService;
import com.shu.services.messageText.MessageTextService;
import com.shu.utils.Const;
import com.shu.utils.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by james on 2017/4/15.
 */
@Controller
@RequestMapping(value = "/Message")
public class MessageAction {

    @Autowired
    MessageService messageService;

    @Autowired
    MessageTextService messageTextService;

    @Autowired
    ManagerService managerService;

    @Autowired
    EnterpriseService enterpriseService;

    @RequestMapping(value = "/Private/readStatus/", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String privateReadStatus(String id) {
        Message message = new Message();
        message.setMessageid(id);
        List<Message> messageList = messageService.getMessageListByParam(message, null, null);
        message = messageList.get(0);
        message.setStatue(1);
        messageService.modifyMessage(message);
        return new JSONObject().toString();
    }

    @RequestMapping(value = "/Private/UnreadCount/{id}_{type}", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String privateUnread(@PathVariable("id") String id, @PathVariable("type") int type) {
        JSONObject jsonObject = new JSONObject();
        Message message = new Message();
        message.setReceiveid(id);
        message.setReceivetype(type);
        message.setStatue(0);
        List<Message> messageList = messageService.getMessageListByParam(message, null, null);
        jsonObject.put("count", messageList.size());
        return jsonObject.toString();
    }

    @RequestMapping(value = "/Private/Message/{id}_{type}", produces = "text/html;charset=UTF-8")
    public String privateMessage(@PathVariable("id") String id, @PathVariable("type") int type, Model model) {
        Message message = new Message();
        message.setReceiveid(id);
        message.setReceivetype(type);
        Order order = new Order();
        order.addOrder("createTime", OrderSort.ASC);
        List<Message> messageList = messageService.getMessageListByParam(message, null, null);
        MessageText messageText = new MessageText();
        messageText.setType(0);
        List<MessageText> messageTextList = messageTextService.getMessageTextListByParam(messageText, order, null);

        Map<MessageText, String> messageTextMap= new TreeMap<>(new Comparator<MessageText>(){
            /**
             * int compare(Object o1, Object o2) 返回一个基本类型的整型，
             * 返回负数表示：o1小于o2，
             * 返回0 表示：o1和o2相等，
             * 返回正数表示：o1大于o2。
             */
            @Override
            public int compare(MessageText m1, MessageText m2) {
//                return (int) (m2.getCreatetime().getTime()-m1.getCreatetime().getTime());
                return (m2.getCreatetime().compareTo(m1.getCreatetime()));
            }
        });
        for (Message m :
                messageList) {
            for (MessageText mt :
                    messageTextList) {
                if (mt.getId().equals(m.getMessageid())) {
                    if (m.getStatue() == 0)
                        messageTextMap.put(mt, "0");
                    else
                        messageTextMap.put(mt, "1");
                }
            }
        }
        model.addAttribute("messageTextList", messageTextMap);
        return "message/private";
    }

    @RequestMapping(value= "/Private/Send", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String privateSend(
            @RequestParam(value = "receiveName")String receiveName,
            @RequestParam(value = "receiveType")String receiveType,
            @RequestParam(value = "title")String title,
            @RequestParam(value = "contents")String contents,
            @RequestParam(value = "sendId")String sendId,
            @RequestParam(value = "sendType")String sendType) {
        JSONObject jsonObject = new JSONObject();
        String receiveId = null;
        if (Objects.equals(receiveType, "0")) {
            Manager manager = new Manager();
            manager.setUsername(receiveName);
            List<Manager> managerList = managerService.getManagerListByParam(manager, null, null);
            if (managerList.size() == 0) {
                jsonObject.put("status", Const.STATUS_FAIL);
                return jsonObject.toString();
            }
            receiveId = managerList.get(0).getId();
        } else if (Objects.equals(receiveType, "1")) {
            Enterprise enterprise = new Enterprise();
            enterprise.setUsername(receiveName);
            List<Enterprise> enterpriseList = enterpriseService.getEnterpriseListByParam(enterprise, null, null);
            if (enterpriseList.size() == 0) {
                jsonObject.put("status", Const.STATUS_FAIL);
                return jsonObject.toString();
            }
            receiveId = enterpriseList.get(0).getId();
        }
        MessageText messageText = new MessageText();
        Message message = new Message();
        messageText.setId(UUID.getID());
        messageText.setSendid(sendId);
        messageText.setSendtype(Integer.parseInt(sendType));
        messageText.setTitle(title);
        messageText.setContents(contents);
        messageText.setType(0);

        message.setId(UUID.getID());
        message.setReceiveid(receiveId);
        message.setReceivetype(Integer.parseInt(receiveType));
        message.setMessageid(messageText.getId());
        message.setStatue(0);
        messageTextService.addMessageText(messageText);
        messageService.addMessage(message);
        jsonObject.put("status", Const.STATUS_SUCCESS);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/Public/readStatus/", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String publicReadStatus(String id, String receiveType, String receiveId) {
        Message message = new Message();
        message.setMessageid(id);
        message.setReceivetype(Integer.parseInt(receiveType));
        message.setReceiveid(receiveId);
        message.setId(UUID.getID());
        message.setStatue(1);
        messageService.addMessage(message);
        return new JSONObject().toString();
    }

    @RequestMapping(value = "/Public/UnreadCount/{id}_{type}", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String publicUnread(@PathVariable("id") String id, @PathVariable("type") int type) {
        JSONObject jsonObject = new JSONObject();
        Message message = new Message();
        message.setReceiveid(id);
        message.setReceivetype(type);
        MessageText messageText = new MessageText();
        messageText.setType(1);
        messageText.setReceivetype(type);

        List<MessageText> messageTextList = messageTextService.getMessageTextListByParam(messageText, null, null);
        List<Message> messageList = messageService.getMessageListByParam(message, null, null);
        int count = 0;
        for (MessageText mt:
                messageTextList) {
            boolean k = false;
            for (Message m:
                    messageList) {
                if (mt.getId().equals(m.getMessageid())) {
                    k = true;
                    break;
                }
            }
            if (!k)
                ++count;
        }
        jsonObject.put("count", count);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/Public/Message/{id}_{type}", produces = "text/html;charset=UTF-8")
    public String publicMessage(@PathVariable("id") String id, @PathVariable("type") int type, Model model) {
        Message message = new Message();
        message.setReceiveid(id);
        message.setReceivetype(type);
        MessageText messageText = new MessageText();
        messageText.setType(1);
        messageText.setReceivetype(type);

        Order order = new Order();
        order.addOrder("createTime", OrderSort.ASC);
        List<MessageText> messageTextList = messageTextService.getMessageTextListByParam(messageText, order, null);
        List<Message> messageList = messageService.getMessageListByParam(message, null, null);

        Map<MessageText, String> messageTextMap= new TreeMap<>(new Comparator<MessageText>(){
            /**
             * int compare(Object o1, Object o2) 返回一个基本类型的整型，
             * 返回负数表示：o1小于o2，
             * 返回0 表示：o1和o2相等，
             * 返回正数表示：o1大于o2。
             */
            @Override
            public int compare(MessageText m1, MessageText m2) {
//                return (int) (m2.getCreatetime().getTime()-m1.getCreatetime().getTime());
                return (m2.getCreatetime().compareTo(m1.getCreatetime()));
            }
        });
        for (MessageText mt:
                messageTextList) {
            boolean k = false;
            for (Message m:
                    messageList) {
                if (mt.getId().equals(m.getMessageid())) {
                    k = true;
                    break;
                }
            }
            if (!k)
                messageTextMap.put(mt, "0");
            else
                messageTextMap.put(mt, "1");
        }
        model.addAttribute("messageTextList", messageTextMap);
        return "message/public";
    }

    @RequestMapping(value= "/Public/Send", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String publicSend(
            @RequestParam(value = "receiveType")String receiveType,
            @RequestParam(value = "title")String title,
            @RequestParam(value = "contents")String contents,
            @RequestParam(value = "sendId")String sendId,
            @RequestParam(value = "sendType")String sendType) {
        JSONObject jsonObject = new JSONObject();
        MessageText messageText = new MessageText();
        messageText.setId(UUID.getID());
        messageText.setSendid(sendId);
        messageText.setSendtype(Integer.parseInt(sendType));
        messageText.setTitle(title);
        messageText.setContents(contents);
        messageText.setType(1);
        messageText.setReceivetype(Integer.parseInt(receiveType));

        messageTextService.addMessageText(messageText);
        jsonObject.put("status", Const.STATUS_SUCCESS);
        return jsonObject.toString();
    }
}
