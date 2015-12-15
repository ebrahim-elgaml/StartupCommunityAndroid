package localhost3000.startupcommunity.model;

/**
 * Created by renadibrahim on 12/15/15.
 */
public class Post {
    public int id;
    public int user_id;
    public int tagged_id;
    public String text;
    public int startup_id;
    public Post(int user_id, int tagged_id, String text, int startup_id){
        this.user_id = user_id;
        this.tagged_id = tagged_id;
        this.text = text;
        this.startup_id = startup_id;
    }
}
