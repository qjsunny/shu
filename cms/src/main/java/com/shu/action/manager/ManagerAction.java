package com.shu.action.manager;

import com.alibaba.fastjson.JSONObject;
import com.shu.db.model.Order;
import com.shu.db.model.OrderSort;
import com.shu.db.model.manager.Manager;
import com.shu.services.manager.ManagerService;
import com.shu.utils.Const;
import com.shu.utils.MD5Util;
import com.shu.utils.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by james on 2017/3/11.
 */
@Controller
@RequestMapping(value="/Manager")
public class ManagerAction {

    @Autowired
    ManagerService managerService;

    /**
     * 管理员页面跳转
     * @return
     */
    @RequestMapping(value = "/", produces = "text/html;charset=UTF-8")
    public String index() {
        return "manager/index";
    }

    /**
     * 管理员表显示
     * @return
     */
    @RequestMapping(value = "/show", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String show() {
        Manager manager = new Manager();
        Order order = new Order();
        order.addOrder("username", OrderSort.ASC);
        List<Manager> managerList = managerService.getManagerListByParam(manager, order, null);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", managerList);
        return jsonObject.toString();
    }

    /**
     * 单个管理员信息显示
     * @param name
     * @return
     */
    @RequestMapping(value = "/show/{name}", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String showOne(@PathVariable("name") String name) {
        JSONObject jsonObject = new JSONObject();
        Manager manager = new Manager();
        manager.setUsername(name);
        List<Manager> managerList = managerService.getManagerListByParam(manager, null, null);
        jsonObject.put("manager", managerList.get(0));
        return jsonObject.toString();
    }

    /**
     * 管理员新增页面跳转
     * @return
     */
    @RequestMapping(value = "/addIndex", produces = "text/html;charset=UTF-8")
    public String addIndex() {
        return "manager/add";
    }

    /**
     * 管理员新增
     * @param manager
     * @param session
     * @return
     */
    @RequestMapping(value = "/add", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String add(Manager manager, HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        Manager manager1 = (Manager) session.getAttribute("manager");
        if (! manager1.getLevel().equals(3)) {
            jsonObject.put("status", Const.STATUS_FAIL);
            return jsonObject.toString();
        }

        Manager manager2 = new Manager();
        manager2.setUsername(manager.getUsername());
        List<Manager> managerList = managerService.getManagerListByParam(manager2, null, null);
        if (!managerList.isEmpty()) {
            jsonObject.put("status", Const.STATUS_FAIL);
            return jsonObject.toString();
        }
        manager.setId(UUID.getID());
        manager.setPassword(MD5Util.MD5(MD5Util.MD5(manager.getPassword())));
        manager.setCreateuser(manager1.getUsername());
        manager.setUpdateUser(manager1.getUsername());
        manager.setIsdelete(0);
        managerService.addManager(manager);
        jsonObject.put("status", Const.STATUS_SUCCESS);
        return jsonObject.toString();
    }

    /**
     * 管理员更新页面跳转
     * @return
     */
    @RequestMapping(value = "/updateIndex/{name}", produces = "text/html;charset=UTF-8")
    public String updateIndex() {
        return "manager/update";
    }

    /**
     * 管理员更新
     * @param manager
     * @param name
     * @param session
     * @return
     */
    @RequestMapping(value = "/update/{name}", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String update(Manager manager, @PathVariable("name") String name, HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        Manager manager1 = (Manager) session.getAttribute("manager");
        if (! manager1.getLevel().equals(3)) {
            jsonObject.put("status", Const.STATUS_FAIL);
            return jsonObject.toString();
        }

        if (!name.equals(manager.getUsername())) {
            jsonObject.put("status", Const.STATUS_FAIL);
            return jsonObject.toString();
        }

        Manager manager2 = new Manager();
        manager2.setUsername(name);
        List<Manager> managerList = managerService.getManagerListByParam(manager2, null, null);

        manager.setId(managerList.get(0).getId());
        manager.setPassword(MD5Util.MD5(MD5Util.MD5(manager.getPassword())));
        managerService.modifyManager(manager);
        jsonObject.put("status", Const.STATUS_SUCCESS);
        return jsonObject.toString();
    }

    /**
     * 管理员删除
     * @param name
     * @return
     */
    @RequestMapping(value = "/delete/{name}", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String deleteIndex(@PathVariable("name") String name) {
        JSONObject jsonObject = new JSONObject();
        Manager manager = new Manager();
        manager.setUsername(name);
        List<Manager> managerList = managerService.getManagerListByParam(manager, null, null);
        if (managerList.isEmpty()) {
            jsonObject.put("status", Const.STATUS_FAIL);
            return jsonObject.toString();
        }
        managerService.removeManagerById(managerList.get(0).getId());
        jsonObject.put("status", Const.STATUS_SUCCESS);
        return jsonObject.toString();
    }
}
