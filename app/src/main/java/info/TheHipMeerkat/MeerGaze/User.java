package info.TheHipMeerkat.MeerGaze;

public class User {
    public int Points;
    public String Email;
    public String Name;

    public User(){

    }

    public User(String Email, String Name, int Points){
        this.Points = Points;
        this.Email = Email;
        this.Name = Name;
    }

    User createUser(String Email, String Name, int Points){
        User temp = new User(Email, Name, Points);

        return temp;
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
