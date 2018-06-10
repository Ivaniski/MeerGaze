package info.TheHipMeerkat.MeerGaze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class User {
    public int Points;
    public String Email;
    public String Name;
    //public boolean locs_Found[] = {false, false, false, false, false, false, false, false};
    public List<Boolean> locs_Found;


    public User(){

    }

    public User(String Email, String Name, int Points, List<Boolean> locs_Found){
        this.Points = Points;
        this.Email = Email;
        this.Name = Name;
        this.locs_Found = locs_Found;
    }

    User createUser(String Email, String Name, int Points, List<Boolean> locs_Found){
        User temp = new User(Email, Name, Points, locs_Found);

        return temp;
    }

    public boolean locFound_true(int position){
        if(this.locs_Found.get(position) == true){
            return false;
        }else{
            this.locs_Found.set(position, true);
            return true;
        }

    }

    public User locFound(int position){
        this.Points = this.Points+500;
        this.locs_Found.set(position, true);
        return this;
        }

    /*public int getPoints(){
        return this.Points;
    }

    @Override
    public int compareTo(User user){
        return (this.getPoints() < user.getPoints() ? -1 :
                (this.getPoints() == user.getPoints() ? 0 : 1));
    }*/

    @Override
    public String toString(){
        return this.Name + ": " + this.Points;
    }



}
