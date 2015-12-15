package localhost3000.startupcommunity;

import java.util.List;

import localhost3000.startupcommunity.model.User;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by myriame on 12/15/15.
 */
public interface MyApi {
    public static final String TAG = "MyApi";

    //public static interface Users {
        @GET("/user/{user_id}.json")
        void getUser(@Path("user_id") String userId, Callback<User> cb);

        @GET("/user/friends")
        void getFriends(Callback<List<User>> users);

        @POST("/user.json")
        void createUser(@Body User user, Callback<User> cb);

        @PUT("/user/{user_id}.json")
        void updateUser(@Path("user_id") String deviceId, @Body User user, Callback<User> cb);

        @FormUrlEncoded
        @POST("/users")
        void createUser(@Field("user[first_name]") String first, @Field("user[last_name]") String last, @Field("user[email]") String email, @Field("user[uid]") String uid, @Field("user[gender]") boolean gender, @Field("user[country]") String country, Callback<User> callback);
    //}

    //public interface Messages {
      //  @GET("/messages/{message_id}.json")
        //void getMessage(@Path("message_id") String messageId, Callback<Message> cb);

        // etc
    //}
}