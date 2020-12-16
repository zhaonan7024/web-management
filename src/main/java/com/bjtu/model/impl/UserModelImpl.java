package com.bjtu.model.impl;

import com.bjtu.entities.User;
import com.bjtu.model.UserModel;
import com.bjtu.reppository.UserReppository;
import com.bjtu.reppository.impl.UserReppositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserModelImpl implements UserModel {

    @Autowired
    UserReppository userReppository = new UserReppositoryImpl();
    @Override
    public List<User> queryByYears(int beginYear, int endYear) {
        return userReppository.queryByYears(beginYear,endYear);
    }

    @Override
    public List<User> queryByMiles(int beginMiles, int endMiles) {
        return userReppository.queryByMiles(beginMiles,endMiles);
    }

    @Override
    public List<User> queryByTime(int beginTime, int endTime) {
        return userReppository.queryByTime(beginTime,endTime);
    }
}
