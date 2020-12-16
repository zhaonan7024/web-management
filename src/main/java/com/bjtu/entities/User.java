package com.bjtu.entities;

public class User {
    private Integer id;
    private Integer sex;
    private Integer birthYear;
    private Integer mileage;  //旅行里程
    private Integer travelTime; //旅行时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Integer getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(Integer travelTime) {
        this.travelTime = travelTime;
    }

    public User() {
    }

    public User(Integer id, Integer sex, Integer birthYear, Integer mileage, Integer travelTime) {
        this.id = id;
        this.sex = sex;
        this.birthYear = birthYear;
        this.mileage = mileage;
        this.travelTime = travelTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", sex=" + sex +
                ", birthYear=" + birthYear +
                ", mileage=" + mileage +
                ", travelTime=" + travelTime +
                '}';
    }


}
