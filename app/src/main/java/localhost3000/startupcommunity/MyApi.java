package localhost3000.startupcommunity;

import android.os.Message;

import java.util.List;

import localhost3000.startupcommunity.model.Post;
import localhost3000.startupcommunity.model.Startup;
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
        @GET("/users/getUser/{user_id}.json")
        void getUser(@Path("user_id") String userId, Callback<User> cb);

        @GET("/users/getFollowedStartups/{user_id}.json")
        void getFollowedStartups(@Path("user_id") String userId, Callback<List<Startup>> users);

        @GET("/user/friends")
        void getFriends(Callback<List<User>> users);


        @FormUrlEncoded
        @POST("/posts")
        void createPost(@Field("text") String first, @Field("image") String last, @Field("user_id") int id, @Field("tagged_id") int tagged, @Field("startup_id") int startup, Callback<User> callback);

    @POST("/user.json")
        void createUser(@Body User user, Callback<User> cb);

        @PUT("/user/{user_id}.json")
        void updateUser(@Path("user_id") String deviceId, @Body User user, Callback<User> cb);
    //}

    //public interface Messages {
      //  @GET("/messages/{message_id}.json")
        //void getMessage(@Path("message_id") String messageId, Callback<Message> cb);

        // etc
    //}
}