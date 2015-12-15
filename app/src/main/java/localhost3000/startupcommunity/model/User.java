package localhost3000.startupcommunity.model;

/**
 * Created by ebrahim on 12/15/15.
 */
public class User {
    public int id;
    public String first_name;
    public String last_name;
    public String email;
    public String country;
    public boolean gender;
    public String uid;
    public String authentication_token;
    public String profile_picture;
    public User(String first_name, String last_name, String email, String uid){
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.uid = uid;
    }


}
