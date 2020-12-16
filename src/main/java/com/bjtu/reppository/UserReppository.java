package com.bjtu.reppository;

import com.bjtu.entities.User;

import java.util.List;

public interface UserReppository {
    /**
     * 按出生年份查询,用户可以定义不同的年龄阶段
     * @param beginYear
     * @param endYear
     * @return
     */
    public List<User> queryByYears(int beginYear, int endYear);

    /**
     *按飞行里程查询,用户可以定义不同的飞行里程区间
     * @param beginMiles
     * @param endMiles
     * @return
     */
    public List<User> queryByMiles(int beginMiles, int endMiles);

    /**
     * 按飞行时间查询,用户可以定义不同的飞行时间区间
     * @param beginTime
     * @param endTime
     * @return
     */
    public List<User> queryByTime(int beginTime, int endTime);
}
