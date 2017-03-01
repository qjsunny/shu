package com.shu.action.index;

import com.alibaba.fastjson.JSONObject;
import com.shu.db.model.Order;
import com.shu.db.model.OrderSort;
import com.shu.db.model.admin.TAdmin;
import com.shu.services.admin.TAdminService;
import com.shu.services.user.TUserService;
import com.shu.utils.Const;
import com.shu.utils.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by james on 2017/2/12.
 */
@Controller
@RequestMapping(value = "index")
public class IndexAction {

    @Autowired
    TAdminService tAdminService;

    @RequestMapping(value = "/", produces = "text/html;charset=UTF-8")
    public String indexHtml(Model model) {
        TAdmin admin  = new TAdmin();
        Order order = new Order();
        order.addOrder("name", OrderSort.ASC);
        List<TAdmin> list1 = tAdminService.getAdminListByParam(admin, order, null);
        model.addAttribute("adminList", list1);
        return "index/index";
    }

    @RequestMapping(value = "/addHtml", produces = "text/html;charset=UTF-8")
    public String addHtml() {
        return "index/add";
    }

    @RequestMapping(value = "/addAdmin", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addAdmin(TAdmin admin, HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        TAdmin tAdmin = new TAdmin();
        String name = (String) session.getAttribute("name");
        tAdmin.setName(name);
        List<TAdmin> list2 = tAdminService.getAdminListByParam(tAdmin, null, null);
        for (TAdmin x : list2) {
            if (x.getAddAdmin() != 1) {
                jsonObject.put("status", Const.STATUS_ERROR);
                return jsonObject.toString();
            }
        }

        tAdmin.setName(admin.getName());
        List<TAdmin> list1 = tAdminService.getAdminListByParam(tAdmin, null, null);
        if (list1.isEmpty()) {
            admin.setId(UUID.getID());
            tAdminService.addAdmin(admin);
            jsonObject.put("status", Const.STATUS_SUCCESS);
            return jsonObject.toString();
        }
        jsonObject.put("status", Const.STATUS_FAIL);
        return jsonObject.toString();
    }
}
