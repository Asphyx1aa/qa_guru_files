package model;

import java.util.List;

public class PersonModel {
    String name;
    int age;
    PersonAddressModel address;
    List<PersonFriendsModel> friends;

    public List<PersonFriendsModel> getFriends() {
        return friends;
    }

    public void setFriends(List<PersonFriendsModel> friends) {
        this.friends = friends;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public PersonAddressModel getAddress() {
        return address;
    }

    public void setAddress(PersonAddressModel address) {
        this.address = address;
    }
}
