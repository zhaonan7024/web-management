package com.bjtu;

import com.bjtu.controller.AjaxController;
import com.bjtu.entities.User;
import com.bjtu.reppository.UserReppository;
import com.bjtu.reppository.impl.UserReppositoryImpl;
import com.google.gson.Gson;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class userTest {

    UserReppository userReppository = new UserReppositoryImpl();

    @Test
    public void queryByYearsTest(){
        System.out.println(userReppository.queryByYears(2000,2000));
        System.out.println(userReppository.queryByMiles(100,2000));
        System.out.println(userReppository.queryByTime(1,20));
    }

    @Test
    public void query() throws IOException {
        AjaxController ajaxController = new AjaxController();
        UserReppository userReppository = new UserReppositoryImpl();
        List<User> list = userReppository.queryByYears(1000,2000);
        System.out.println(list);
    }

}
