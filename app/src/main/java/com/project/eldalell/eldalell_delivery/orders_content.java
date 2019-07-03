package com.project.eldalell.eldalell_delivery;


public class orders_content {

    public String tvOrderCount;
    public String tvOrderName;
    public String tvOrderPrice;


    orders_content() {
    }

    public orders_content(String OrderCount, String OrderName, String OrderPrice) {

        this.tvOrderCount = OrderCount;
        this.tvOrderName = OrderName;
        this.tvOrderPrice = OrderPrice;

    }


    public String getTvOrderCount() {
        return tvOrderCount;
    }

    public void setTvOrderCount(String tvOrderCount) {
        this.tvOrderCount = tvOrderCount;
    }

    public String getTvOrderName() {
        return tvOrderName;
    }

    public void setTvOrderName(String tvOrderName) {
        this.tvOrderName = tvOrderName;
    }


    public String getTvOrderPrice() {
        return tvOrderPrice;
    }

    public void setTvOrderPrice(String tvOrderPrice) {
        this.tvOrderPrice = tvOrderPrice;
    }


}

