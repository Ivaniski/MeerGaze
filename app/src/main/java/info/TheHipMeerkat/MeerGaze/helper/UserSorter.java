package info.TheHipMeerkat.MeerGaze.helper;

import java.util.ArrayList;
import java.util.Collections;

import info.TheHipMeerkat.MeerGaze.User;

public class UserSorter {
    ArrayList<User> UserList = new ArrayList<>();

    public UserSorter(ArrayList<User> UserList){
        this.UserList = UserList;
    }

    public ArrayList<User> getSortedUsersByPoints() {
        //Collections.sort(UserList);
        return UserList;
    }
}
