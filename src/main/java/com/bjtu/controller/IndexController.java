package com.bjtu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/back")
    public String back(){
        return "back";
    }
    @RequestMapping("/queryByYear")
    public String queryByYear(){
        return "queryByYear";
    }
    @RequestMapping("/queryByMiles")
    public String queryByMiles(){
        return "queryByMiles";
    }
    @RequestMapping("/queryByTime")
    public String queryByTime(){
        return "queryByTime";
    }
}
