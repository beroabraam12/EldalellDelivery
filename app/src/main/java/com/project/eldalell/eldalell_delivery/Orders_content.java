package com.project.eldalell.eldalell_delivery;


public class Orders_content {

    private int OrderCount;
    private String OrderName;
    private float OrderPrice;
    private String item_shop_id;

    Orders_content() {
    }

    public int getOrderCount() {
        return OrderCount;
    }

    public void setOrderCount(int orderCount) {
        OrderCount = orderCount;
    }

    public void setOrderPrice(float orderPrice) {
        OrderPrice = orderPrice;
    }

    public String getOrderName() {
        return OrderName;
    }

    public void setOrderName(String orderName) {
        OrderName = orderName;
    }

    public String getItem_shop_id() {
        return item_shop_id;
    }

    public void setItem_shop_id(String item_shop_id) {
        this.item_shop_id = item_shop_id;
    }

    public float CalcTotalPrice(){
        float total = this.OrderCount * this.OrderPrice;
        return total;
    }
}

