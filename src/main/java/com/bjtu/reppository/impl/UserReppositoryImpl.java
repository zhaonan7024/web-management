package com.bjtu.reppository.impl;

import com.bjtu.entities.User;
import com.bjtu.reppository.UserReppository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserReppositoryImpl extends BaseReppository implements UserReppository {

    @Override
    public List<User> queryByYears(int beginYear, int endYear) {
        String sql="select id,sex,birth_year as birthYear,mileage,travel_time as travelTime from travel_user where birth_year between ? and ?";
        try {
            return queryForList(User.class,sql,beginYear,endYear);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<User>();
    }

    @Override
    public List<User> queryByMiles(int beginMiles, int endMiles) {
        String sql="select id,sex,birth_year as birthYear,mileage,travel_time  as travelTime  from travel_user where mileage between ? and ?";
        try {
            return queryForList(User.class,sql,beginMiles,endMiles);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<User>();
    }

    @Override
    public List<User> queryByTime(int beginTime, int endTime) {
        String sql="select id,sex,birth_year as birthYear,mileage,travel_time  as travelTime  from travel_user where travel_time  between ? and ?";
        try {
            return queryForList(User.class,sql,beginTime,endTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<User>();
    }
}
