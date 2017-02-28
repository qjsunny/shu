package com.shu.action.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 因为未注册用户，也是可以看直播的，所以这个controller用来显示所有内容，并且可以在没有用户session的情况下显示
 * Created by Administrator on 2017/2/28.
 */
@Controller
@RequestMapping(value = "index")
public class IndexAction {
}
