package localhost3000.startupcommunity;

import android.app.Application;

import retrofit.RestAdapter;

/**
 * Created by myriame on 12/15/15.
 */

public class GlobalClass extends Application {
    public static RestAdapter restAdapter;

    //public static MyApi.Users usersApi;
    //public static MyApi.Messages messagesApi;
    public void onCreate() {
        super.onCreate();

        // your other code
        String API_URL = "localhost:3000/api/";
        restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.BASIC)
                .setEndpoint(API_URL)
                .build();

       // usersApi = restAdapter.create(MyApi.Users.class);
        //messagesApi = restAdapter.create(MyApi.Messages.class);
    }

}
