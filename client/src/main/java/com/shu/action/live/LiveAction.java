package com.shu.action.live;

import com.alibaba.fastjson.JSONObject;
import com.shu.db.model.live.TLiveRoom;
import com.shu.services.live.TLiveRoomService;
import com.shu.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/2/28.
 */
@Controller
@RequestMapping(value = "live")
public class LiveAction {

    @Autowired
    TLiveRoomService tLiveRoomService;

    /**
     * 请在传值前，把TLiveRoom的islive设置成开启或者关闭
     * @param room
     * @return
     */
    @RequestMapping(value = "openRoom", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String openRoom(TLiveRoom room){
        JSONObject resObj = new JSONObject();
        tLiveRoomService.modifyLRoom(room);
        resObj.put("status", Const.STATUS_SUCCESS);

        return resObj.toString();
    }

    @RequestMapping(value = "setCover", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String setCover(TLiveRoom room){
        JSONObject resObj = new JSONObject();
        tLiveRoomService.modifyLRoom(room);
        resObj.put("status", Const.STATUS_SUCCESS);

        return resObj.toString();
    }
}
