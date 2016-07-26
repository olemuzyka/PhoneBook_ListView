package com.gmail.olemuzyka.phonebook_listview;

import java.io.Serializable;
import java.util.Random;

public class Contact implements Serializable {
    private String name;
    private String phoneNumber;
    private String phoneNumber2;
    private int photo;

    public Contact() {
        this.photo = addPhoto();
        this.name = name();
        this.phoneNumber = phoneNumber();
        this.phoneNumber2 = null;

    }

    public int addPhoto() {
        Random random = new Random();
        int count = random.nextInt(3);
        switch (count) {
            case 0:
                return R.drawable.user_01;
            case 1:
                return R.drawable.user_02;
            case 2:
                return R.drawable.user_03;
        }
        return 0;
    }

    public String name() {
        Random randomName = new Random();
        int count = randomName.nextInt(5);
        switch (count) {
            case 0:
                return "Oleg";
            case 1:
                return "Vova";
            case 2:
                return "Valick";
            case 3:
                return "Boba";
            case 4:
                return "Biba";
        }
        return null;
    }

    public String phoneNumber() {
        Random randomNumber = new Random();
        int count = randomNumber.nextInt(3);
        switch (count) {
            case 0:
                return "(056)52535455";
            case 1:
                return "(063)256545825";
            case 2:
                return "(052)4653464135";
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (photo != contact.photo) return false;
        if (name != null ? !name.equals(contact.name) : contact.name != null) return false;
        return phoneNumber != null ? phoneNumber.equals(contact.phoneNumber) : contact.phoneNumber == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + photo;
        return result;
    }

    public void setPhoneNumber2(String phoneNumber2) {

        this.phoneNumber2 = phoneNumber2;
    }

    public int getPhoto() {
        return photo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }
    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
