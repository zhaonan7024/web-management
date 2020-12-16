package com.bjtu.controller;

import com.bjtu.entities.User;
import com.bjtu.model.UserModel;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

@Controller
@RequestMapping("/ajax")
public class AjaxController {
    @Autowired
    UserModel userModel;
    @RequestMapping("/queryByYear")
    @ResponseBody
    public String queryByYear(Integer begin, Integer end) throws IOException {
        List<User> list = userModel.queryByYears(begin,end);
        Gson gson = new Gson();
        String reslut = gson.toJson(list);
        return reslut;
    }
    @RequestMapping("/queryByMilse")
    @ResponseBody
    public String quryByMilse(Integer begin, Integer end) throws IOException {
        List<User> list = userModel.queryByMiles(begin,end);
        Gson gson = new Gson();
        String reslut = gson.toJson(list);
        return reslut;
    }
    @RequestMapping("/queryByTime")
    @ResponseBody
    public String quryByTime(Integer begin, Integer end) throws IOException {
        List<User> list = userModel.queryByTime(begin,end);
        Gson gson = new Gson();
        String reslut = gson.toJson(list);
        return reslut;
    }

    @RequestMapping("/queryChartByMilse")
    @ResponseBody
    public String queryChartByMilse(Integer begin, Integer end,Integer range) throws IOException {
        List<User> list = userModel.queryByMiles(begin,end);
        Gson gson = new Gson();
        TreeMap<Integer,Integer> treeMap = new TreeMap<Integer,Integer>();
        for(int i = 0;i<list.size();i++){
           int a =  list.get(i).getMileage()/range;
           if(treeMap.containsKey(a)){
               int count  = treeMap.get(a);
               count++;
               treeMap.put(a,count);
           }
           else{
               treeMap.put(a,1);
           }

        }

        String reslut = gson.toJson(treeMap);
        return reslut;
    }

    @RequestMapping("/queryChartByTime")
    @ResponseBody
    public String queryChartByTime(Integer begin, Integer end,Integer range) throws IOException {
        List<User> list = userModel.queryByTime(begin,end);
        Gson gson = new Gson();
        TreeMap<Integer,Integer> treeMap = new TreeMap<Integer,Integer>();
        for(int i = 0;i<list.size();i++){
            int a =  list.get(i).getTravelTime()/range;
            if(treeMap.containsKey(a)){
                int count  = treeMap.get(a);
                count++;
                treeMap.put(a,count);
            }
            else{
                treeMap.put(a,1);
            }
        }

        String reslut = gson.toJson(treeMap);
        return reslut;
    }

    @RequestMapping("/queryChartByYear")
    @ResponseBody
    public String queryChartByYear(Integer begin, Integer end,Integer range) throws IOException {
        List<User> list = userModel.queryByYears(begin,end);
        Gson gson = new Gson();
        TreeMap<Integer,Integer> treeMap = new TreeMap<Integer,Integer>();
        for(int i = 0;i<list.size();i++){
            int a =  list.get(i).getBirthYear()/range;
            if(treeMap.containsKey(a)){
                int count  = treeMap.get(a);
                count++;
                treeMap.put(a,count);
            }
            else{
                treeMap.put(a,1);
            }
        }

        String reslut = gson.toJson(treeMap);
        return reslut;
    }

}
