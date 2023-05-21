package top.canoe0.blog.controller;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.canoe0.blog.entity.user.Admin;
import top.canoe0.blog.entity.user.RegularUser;
import top.canoe0.blog.entity.user.User;
import top.canoe0.blog.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    //session
    @GetMapping("/getSession")
    public JSONObject getSession(HttpSession session) throws Exception {
        JSONObject sessionJSON = userService.getSession(session);
        System.out.println("sessionJSON = " + sessionJSON);
        return sessionJSON;
    }


    //显示所有普通用户
    @GetMapping("/listAllRegularUser")
    public List<RegularUser> listAllRegularUser() {
        return userService.listAllRegularUser();
    }

    //注册或更新普通用户
    @PostMapping("/registerOrUpdateRegularUser")
    public RegularUser registerOrUpdeateRegularUser(String account, String password) {
        RegularUser regularUser = new RegularUser();
        regularUser.setAccount(account);
        regularUser.setPassword(password);
        RegularUser regularUserRes = userService.saveRegularUser(regularUser);
        return regularUserRes;
    }

    @PostMapping("/registerOrUpdateAdminUser")
    //添加或更新管理员用户
    public Admin addOrUpdateAdminUser(String account, String password) {
        Admin admin = new Admin();
        admin.setAccount(account);
        admin.setPassword(password);
        return userService.saveAdmin(admin);
    }

    //todo 登录与注册代码可优化

    //    登录管理员用户
    @PostMapping("loginAdmin")
    @ResponseBody
    public Admin loginAdmin(@RequestParam String account, @RequestParam String password, HttpSession session) {
        Admin admin = new Admin();
        admin.setAccount(account);
        admin.setPassword(password);
        return userService.loginAdmin(admin, session);
    }

    @PostMapping("/loginRegularUser")
    @ResponseBody
    public RegularUser loginRegularUser(@RequestParam String account, @RequestParam String password, HttpSession session) {
        RegularUser regularUser = new RegularUser();
        regularUser.setAccount(account);
        regularUser.setPassword(password);
        return userService.loginRegularUser(regularUser, session);
    }


}
