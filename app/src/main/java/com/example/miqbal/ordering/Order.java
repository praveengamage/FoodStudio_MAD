package com.example.miqbal.ordering;

public class Order {

    public String name;
    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public String contact;
    public String getContact(){return contact;}
    public void setContact(String contact){this.contact = contact;}

    public String address;
    public String getAddress(){return address;}
    public void setAddress(String address){this.address = address;}

    public String payment;
    public String getPayment(){return payment;}
    public void setPayment(String payment){this.payment = payment;}

    public String value;
    public String getValue(){return value;}
    public void setValue(String value){this.value = value;}

    public String getItem(){return item;}

    public int getCode() {return code;}

    public void setCode(int code) {this.code = code;}

    public String item;
    public int code;

    public Order(String name, String item, String contact, String address){
        this.name = name;
        this.item = item;
        this.contact = contact;
        this.address = address;
        this.payment = payment;
        this.value = value;
    }
}
