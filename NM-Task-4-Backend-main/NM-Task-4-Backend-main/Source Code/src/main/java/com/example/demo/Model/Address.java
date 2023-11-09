package com.example.demo.Model;


import jakarta.persistence.*;

@Entity
public class Address {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private  int  addressId;
     private String  addressOne;
     private  String addressTwo;
     private String  addressThree;
     private String city;
     private String State;
     private String Country;
     private String pinCode;
     @OneToOne(mappedBy = "address")
     private UserDetail user;

     public int getAddressId() {
          return addressId;
     }

     public void setAddressId(int addressId) {
          this.addressId = addressId;
     }

     public String getAddressOne() {
          return addressOne;
     }

     public void setAddressOne(String addressOne) {
          this.addressOne = addressOne;
     }

     public String getAddressTwo() {
          return addressTwo;
     }

     public void setAddressTwo(String addressTwo) {
          this.addressTwo = addressTwo;
     }

     public String getAddressThree() {
          return addressThree;
     }

     public void setAddressThree(String addressThree) {
          this.addressThree = addressThree;
     }

     public String getCity() {
          return city;
     }

     public void setCity(String city) {
          this.city = city;
     }

     public String getState() {
          return State;
     }

     public void setState(String state) {
          State = state;
     }

     public String getCountry() {
          return Country;
     }

     public void setCountry(String country) {
          Country = country;
     }

     public String getPinCode() {
          return pinCode;
     }

     public void setPinCode(String pinCode) {
          this.pinCode = pinCode;
     }

     public UserDetail getUser() {
          return user;
     }

     public void setUser(UserDetail user) {
          this.user = user;
     }


}
