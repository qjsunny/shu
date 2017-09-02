package com.shu.action.liveStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by james on 2017/5/13.
 */
@Controller
@RequestMapping(value = "/LiveStream")
public class LiveStreamAction {

    @RequestMapping(value = "/Classify", produces = "text/html;charset=UTF-8")
    public String classifyIndex() {
        return "liveStream/classify";
    }

    @RequestMapping(value = "/{classify}", produces = "text/html;charset=UTF-8")
    public String specificClassifyIndex(@PathVariable("classify") String classify) {
        return "liveStream/" + classify;
    }

    @RequestMapping(value = "/anchorDistribution", produces = "text/html;charset=UTF-8")
    public String anchorDistribution(){
        return "liveStream/anchorDistribution";
    }

    @RequestMapping(value = "/userDistribution", produces = "text/html;charset=UTF-8")
    public String userDistribution(){
        return "liveStream/userDistribution";
    }

    @RequestMapping(value = "/userOnlineNumber", produces = "text/html;charset=UTF-8")
    public String userOnlineNumber(){
        return "liveStream/userOnlineNumber";
    }

    @RequestMapping(value = "/anchorOnlineNumber", produces = "text/html;charset=UTF-8")
    public String anchorOnlineNumber(){
        return "liveStream/anchorOnlineNumber";
    }
}
