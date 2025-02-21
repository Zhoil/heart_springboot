package com.example.mind.Controller;

import com.example.mind.DTO.LoginResponse;
import com.example.mind.DTO.UserResDTO.UserDTOResponse;
import com.example.mind.Service.UserService;
import com.example.mind.annotation.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @LogAnnotation("用户登录")
    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponse login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
        System.out.println("username: " + username + " password: " + password);
        return userService.login(username,password);
    }

    @LogAnnotation("查询测试人名下的所有被试人")
    @ApiOperation(value = "查询测试人名下的所有被试人")
    @RequestMapping(value = "/query/testees", method = RequestMethod.POST)
    public UserDTOResponse query_testees(@RequestParam("id") String id) {
        System.out.println("id: " + id);
        return userService.query_testees(id);

    }

    /**
     * 根据 id 修改被测人信息
     * 前端调用示例：
     * PUT /api/users/update?id=xxx&name=张三&idcard=123456789012345678&phone=13800138000
     */
    @LogAnnotation("修改被测人信息")
    @ApiOperation(value = "修改被测人信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Map<String,Boolean>> updateUserInfo(
            @RequestParam("id") String id,
            @RequestParam("name") String name,
            @RequestParam("idcard") String idcard,
            @RequestParam("phone") String phone) {
        userService.updateUserInfo(id, name, idcard, phone);
        Map<String, Boolean> response = new HashMap<>();
        response.put("success", true);
        return ResponseEntity.ok(response);
    }

    @LogAnnotation("Api测试")
    @ApiOperation(value = "Api测试")
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String test() {
        return "Api测试成功";
    }




}
