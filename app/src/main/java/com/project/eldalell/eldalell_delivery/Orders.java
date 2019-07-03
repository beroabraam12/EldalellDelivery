package com.project.eldalell.eldalell_delivery;


public class Orders {

    private String id;

    private float Subtotal;
    private float Total;
    private float DeliveryCost;
    private String note_for_delivery,delivery_man_accepted,order_done,user_id,shop_id,address_id;


    Orders() {
    }

    public Orders(String id, float subtotal, float total, float deliveryCost, String note_for_delivery, String delivery_man_accepted, String order_done, String user_id, String shop_id, String address_id) {
        this.id = id;
        Subtotal = subtotal;
        Total = total;
        DeliveryCost = deliveryCost;
        this.note_for_delivery = note_for_delivery;
        this.delivery_man_accepted = delivery_man_accepted;
        this.order_done = order_done;
        this.user_id = user_id;
        this.shop_id = shop_id;
        this.address_id = address_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(float subtotal) {
        Subtotal = subtotal;
    }

    public float getTotal() {
        return Total;
    }

    public void setTotal(float total) {
        Total = total;
    }

    public float getDeliveryCost() {
        return DeliveryCost;
    }

    public void setDeliveryCost() {
        DeliveryCost = Total - Subtotal;
    }

    public String getNote_for_delivery() {
        return note_for_delivery;
    }

    public void setNote_for_delivery(String note_for_delivery) {
        this.note_for_delivery = note_for_delivery;
    }

    public String getDelivery_man_accepted() {
        return delivery_man_accepted;
    }

    public void setDelivery_man_accepted(String delivery_man_accepted) {
        this.delivery_man_accepted = delivery_man_accepted;
    }

    public String getOrder_done() {
        return order_done;
    }

    public void setOrder_done(String order_done) {
        this.order_done = order_done;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }
}

