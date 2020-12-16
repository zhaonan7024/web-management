package com.bjtu.model;

import com.bjtu.entities.User;
import java.util.List;

public interface UserModel {
    public List<User> queryByYears(int beginYear, int endYear);
    public List<User> queryByMiles(int beginMiles, int endMiles);
    public List<User> queryByTime(int beginTime, int endTime);
}
