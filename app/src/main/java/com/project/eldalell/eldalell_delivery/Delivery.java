package com.project.eldalell.eldalell_delivery;

public class Delivery {
    private String id,first_name,last_name,phone_number,national_id_number,image,birthday,gender
            ,confirm,shop_id;

    public Delivery() {
    }

    public Delivery(String id, String first_name, String last_name, String phone_number, String national_id_number, String image, String birthday, String gender, String confirm, String shop_id) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.national_id_number = national_id_number;
        this.image = image;
        this.birthday = birthday;
        this.gender = gender;
        this.confirm = confirm;
        this.shop_id = shop_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getNational_id_number() {
        return national_id_number;
    }

    public void setNational_id_number(String national_id_number) {
        this.national_id_number = national_id_number;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }
}
