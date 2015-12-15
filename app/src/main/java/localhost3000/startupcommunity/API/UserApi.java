package localhost3000.startupcommunity.API;

import localhost3000.startupcommunity.model.User;
import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by ebrahim on 12/15/15.
 */
public interface UserApi {
   @FormUrlEncoded
   @POST("/users")
   //void createUser(@Field("user[first_name]") String first, @Field("user[last_name]") String last, @Field("user[email]") String email, @Field("user[uid]") String uid, @Field("user[gender]") boolean gender, Callback<User> callback);
   // Call<User> createUser(@Body User user);
   Call<User> createUser(@Field("user[first_name]") String first, @Field("user[last_name]") String last, @Field("user[email]") String email, @Field("user[uid]") String uid, @Field("user[gender]") boolean gender );
    //public void getFeed(@Field("user") String user, Callback<User> response);

}
