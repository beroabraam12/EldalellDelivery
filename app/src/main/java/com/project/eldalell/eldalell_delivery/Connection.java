package com.project.eldalell.eldalell_delivery;

public class Connection {
    private String DeliveryHostIp = "http://192.168.1.108:4000";
    private String AdminHostIP = "http://192.168.1.108:8000";

    private String getItemswithItem = AdminHostIP + "/api/item-withItemShop/";
    private String loginDelivery = DeliveryHostIp + "/api/login-delivery";
    private String getAuthDelivery = DeliveryHostIp+ "/api/details";
    private String getOrders = AdminHostIP+ "/api/order-shop/";
    private String DeliveryTake = AdminHostIP + "/api/order-deliveryacception/";
    private String OrderDone = AdminHostIP + "/api/order-done/";
    private String getInvoice = AdminHostIP+"/api/invoiceRow-order/";
    private String getAddress = AdminHostIP+"/api/get-address-city-district/";

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

    public String getGetItemswithItem() {
        return getItemswithItem;
    }

    public String getGetInvoice() {
        return getInvoice;
    }

    public String getGetAddress() {
        return getAddress;
    }
}
