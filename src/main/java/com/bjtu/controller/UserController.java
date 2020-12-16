package com.bjtu.controller;

import com.bjtu.entities.User;
import com.bjtu.model.UserModel;
import com.bjtu.model.impl.UserModelImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

 //   User user = new User();
    //查询所有信息返回
    @GetMapping("list")
    public String list(){
        return "list";
    }

    @Autowired
    UserModel userModel;

    @GetMapping("queryByYears")
    public void queryByYears(@RequestParam() Integer beginYear,@RequestParam()  Integer endYear){
      //  userModel.queryByYears(beginYear,endYear);
        System.out.println(beginYear);
        System.out.println( userModel.queryByYears(beginYear,endYear));
    }
}
