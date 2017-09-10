package com.gwf.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.gwf.dto.User;
import com.gwf.dto.UserQueryCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaowenfeng on 2017/9/10.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 用户查询
     * @param condition
     * @param pageable
     * @return
     */
    @GetMapping
    @JsonView(User.UserSimpleView.class)
    public List<User> query(UserQueryCondition condition,
                            @PageableDefault(page = 1,size = 10,sort = "username desc") Pageable pageable){
        System.out.println(condition.getUsername());
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    /**
     * 用户详情的获取
     * @param id
     * @return
     */
    @GetMapping("/{id:\\d+}")  //正则表达式控制只能接受数字
    @JsonView(User.UserDetailView.class)  //使用jsonview第三步,在Controller方法上指定视图
    public User getInfo(@PathVariable String id){
        System.out.println(id);
        User user = new User();
        user.setUsername("tom");
        return user;
    }
}
