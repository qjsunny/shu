package com.shu.action.user;

import com.alibaba.fastjson.JSONObject;
import com.shu.db.model.live.TLiveRoom;
import com.shu.db.model.user.TUser;
import com.shu.db.model.user.TUserInfo;
import com.shu.services.live.TLiveRoomService;
import com.shu.services.user.TUserInfoService;
import com.shu.services.user.TUserService;
import com.shu.utils.Const;
import com.shu.utils.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/2/24.
 */
@Controller
@RequestMapping(value = "user")
public class UserAction {

    @Autowired
    TUserInfoService tUserInfoService;

    @Autowired
    TUserService tUserService;

    @Autowired
    TLiveRoomService tLiveRoomService;

    @RequestMapping(value = "userinfo", produces = "text/html;charset=UTF-8")
    public String userinfohtml(Model model, String userId, HttpServletRequest request){
        TUser user = (TUser) request.getSession().getAttribute("user");

        TUserInfo userInfo = new TUserInfo();
        userInfo.setUid(userId);
        List<TUserInfo> userInfoList = tUserInfoService.getUserinfoListByParam(userInfo, null, null);

        model.addAttribute("user", user);
        if(userInfoList.size() == 0){
            TUserInfo userInfo1 = new TUserInfo();
            userInfo1.setId(UUID.getID());
            userInfo1.setUid(userId);
            tUserInfoService.addUserinfo(userInfo1);
            model.addAttribute("userinfo", userInfo1);
            return "user/userinfo";
        }

        model.addAttribute("userinfo", userInfoList.get(0));

        return "user/userinfo";
    }

    @RequestMapping(value = "editinfo", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String editInfo(TUserInfo userInfo){
        JSONObject resObj = new JSONObject();
        tUserInfoService.modifyUserinfo(userInfo);
        resObj.put("status", Const.STATUS_SUCCESS);
        return resObj.toString();
    }

    @RequestMapping(value = "myZhibo", produces = "text/html;charset=UTF-8")
    public String goZhibo(Model model, String userId, HttpServletRequest request){
        //先检查有没有申请过直播间
        TUser user = (TUser) request.getSession().getAttribute("user");

        model.addAttribute("user", user);

        TUserInfo userInfo = new TUserInfo();
        userInfo.setUid(userId);
        List<TUserInfo> userInfoList = tUserInfoService.getUserinfoListByParam(userInfo, null, null);
        model.addAttribute("userinfo", userInfoList.get(0));

        //往model传入直播间object
        TLiveRoom roomQuery = new TLiveRoom();
        roomQuery.setUid(user.getId());

        List<TLiveRoom> list1 = tLiveRoomService.getLRoomListByParam(roomQuery, null, null);
        if(list1.size() == 0){
            //// TODO: 2017/2/28 这种情况不应该出现，如果出现，转跳去错误处理页面，待做
            return "user/error";
        }
        model.addAttribute("liveroom", list1.get(0));

        //不是主播就去转跳去申请主播的页面
        if(user.getIszhubo() == 0){
            model.addAttribute("iszhubo", 0);
            return "user/tobezhubo";
        }else{
            model.addAttribute("iszhubo", 1);
        }

        return "user/zhibo";
    }

    @RequestMapping(value = "tobezb", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String tobeZB(String uid, HttpServletRequest request){
        TUser user = (TUser) request.getSession().getAttribute("user");

        JSONObject resObj = new JSONObject();

        user.setIszhubo(1);
        tUserService.modifyUser(user);

        //还需要为他创建一个直播间
        TLiveRoom newRoom = new TLiveRoom();
        newRoom.setId(UUID.getID());
        newRoom.setUid(user.getId());
        newRoom.setIslive(0);
        newRoom.setApp("qunima");
        newRoom.setStream(user.getId());


        resObj.put("status", Const.STATUS_SUCCESS);
        return resObj.toString();
    }
}
