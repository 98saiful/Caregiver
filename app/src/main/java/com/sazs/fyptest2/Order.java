package com.sazs.fyptest2;

public class Order {
    private int order_id;
    private String elderly_name;
    private int elderly_age;
    private String elderly_gender;
    private int elderly_height;
    private int elderly_weight;
    private String elderly_location;
    private String elderly_info;
    private String elderly_note;
    private String user_name;

    public Order(int order_id, String elderly_name, int elderly_age, String elderly_gender, int elderly_height, int elderly_weight, String elderly_location, String elderly_info, String elderly_note, String user_name) {
        this.order_id = order_id;
        this.elderly_name = elderly_name;
        this.elderly_age = elderly_age;
        this.elderly_gender = elderly_gender;
        this.elderly_height = elderly_height;
        this.elderly_weight = elderly_weight;
        this.elderly_location = elderly_location;
        this.elderly_info = elderly_info;
        this.elderly_note = elderly_note;
        this.user_name = user_name;
    }

    public int getOrder_id() {
        return order_id;
    }


    public String getElderly_name() {
        return elderly_name;
    }


    public int getElderly_age() {
        return elderly_age;
    }


    public String getElderly_gender() {
        return elderly_gender;
    }


    public int getElderly_height() {
        return elderly_height;
    }


    public int getElderly_weight() {
        return elderly_weight;
    }


    public String getElderly_location() {
        return elderly_location;
    }


    public String getElderly_info() {
        return elderly_info;
    }


    public String getElderly_note() {
        return elderly_note;
    }


    public String getUser_name() {
        return user_name;
    }

}
