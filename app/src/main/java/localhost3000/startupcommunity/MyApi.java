package localhost3000.startupcommunity;

import java.util.List;

import localhost3000.startupcommunity.dummy.CommentsList;
import localhost3000.startupcommunity.dummy.NewsFeedList;
import localhost3000.startupcommunity.model.Startup;
import localhost3000.startupcommunity.model.User;
import localhost3000.startupcommunity.model.UserConnection;
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
    @GET("/users/{id}")
    void showUser(@Path("id") String userId, Callback<User> cb);
    //public static interface Users {
        @GET("/users/getUser/{user_id}.json")
        void getUser(@Path("user_id") String userId, Callback<User> cb);

        @GET("/users/getFollowedStartups/{user_id}.json")
        void getFollowedStartups(@Path("user_id") String userId, Callback<List<Startup>> users);

        @GET("/users/unfriend")
        void unfriend(@Path("user_a_id") int user_a_id,@Path("user_b_id") int user_b_id,Callback<List<User>> users);

         @GET("/user/friends/{user_id}")
        void getFriends(@Path("user_id") int user_id,Callback<List<User>> users);

    @FormUrlEncoded
        @POST("/posts")
        void createPost(@Field("text") String first, @Field("image") String last, @Field("user_id") int id, @Field("tagged_id") int tagged, @Field("startup_id") int startup, Callback<User> callback);

        @POST("/user.json")
        void createUser(@Body User user, Callback<User> cb);

        @PUT("/user/{user_id}.json")
        void updateUser(@Path("user_id") String deviceId, @Body User user, Callback<User> cb);


        @FormUrlEncoded
        @POST("/users")
        void createUser(@Field("user[first_name]") String first, @Field("user[last_name]") String last, @Field("user[email]") String email, @Field("user[uid]") String uid, @Field("user[gender]") boolean gender, @Field("user[country]") String country, Callback<User> callback);

        @GET("/user_connections/index/{user_id}")
        void getUserConnections(@Path("user_id") String userId, Callback<List<UserConnection>> cb);




    //}


        @GET("/posts/timeline/{user_id}")
        void getPosts(@Path("user_id") String userID,Callback<List<NewsFeedList.SinglePost>> posts);

        //adelo id eluser yeraga3leuser
        @GET("/posts/show_comments/{id}")
        void getComments(@Path("id") String id,Callback<List<CommentsList.SingleComment>> comments);

        @FormUrlEncoded
        @POST("/user_connections/accept")
        void acceptRequest(@Field("id") String requestId, Callback<UserConnection> callback);

        @FormUrlEncoded
        @POST("/user_connections/reject")
        void rejectRequest(@Field("id") String requestId, Callback<UserConnection> callback);

        @FormUrlEncoded
        @POST("/user_connections")
        void createRequest(@Field("user_connection[user_a_id]") String user_a_id, @Field("user_connection[user_b_id]") String user_b_id, Callback<UserConnection> callback);

        @GET("/users/index/{id}")
        void getNewUsers(@Path("id") String userId, Callback<List<User>> cb);


        @FormUrlEncoded
        @POST("/comments")
        void createComment(@Field("comment[text]") String first, @Field("comment[post_id]") String last, @Field("comment[user_id]") String u, Callback<User> callback);



}