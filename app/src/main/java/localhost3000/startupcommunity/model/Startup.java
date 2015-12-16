package localhost3000.startupcommunity.model;

import localhost3000.startupcommunity.R;

/**
 * Created by myriame on 12/15/15.
 */
public class Startup {
    public int id;
    public String name;
    public String image = "@drawable/startup1";
    public Startup(int i,String name, String image) {
        this.id = i;
        this.name = name;
        //this.image = image;
    }

}
