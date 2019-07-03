package com.project.eldalell.eldalell_delivery;

public class Connection {
    private String DeliveryHostIp = "http://192.168.0.105:4000";
    private String AdminHostIP = "http://192.168.0.105:8000";

    private String loginDelivery = DeliveryHostIp + "/api/login-delivery";
    private String getAuthDelivery = DeliveryHostIp+ "/api/details";
    private String getOrders = AdminHostIP+ "/api/order-shop/";
    private String DeliveryTake = AdminHostIP + "/api/order-deliveryacception/";
    private String OrderDone = AdminHostIP + "/api/order-done/";

    public Connection() {
    }

    public String getDeliveryHostIp() {
        return DeliveryHostIp;
    }

    public String getAdminHostIP() {
        return AdminHostIP;
    }

    public String getLoginDelivery() {
        return loginDelivery;
    }

    public String getGetAuthDelivery() {
        return getAuthDelivery;
    }

    public String getGetOrders() {
        return getOrders;
    }

    public String getDeliveryTake() {
        return DeliveryTake;
    }

    public String getOrderDone() {
        return OrderDone;
    }
}
