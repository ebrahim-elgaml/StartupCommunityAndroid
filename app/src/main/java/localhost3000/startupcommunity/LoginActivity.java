package localhost3000.startupcommunity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import localhost3000.startupcommunity.API.UserApi;
import localhost3000.startupcommunity.model.User;
import localhost3000.startupcommunity.util.BaseUrl;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginActivity extends AppCompatActivity implements Callback<User> {
    TextView emailTextView;
    EditText firstName;
    EditText lastName;
    EditText description;
    String id ;
    ImageView image;
//    public static RestAdapter restAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String first_name = intent.getStringExtra("first_name");
        String last_name = intent.getStringExtra("last_name");
        String id = intent.getStringExtra("uid");
        emailTextView = (TextView) findViewById(R.id.email);
        emailTextView.setText(email);
        firstName = (EditText) findViewById(R.id.first_name);
        firstName.setText(first_name);
        lastName = (EditText) findViewById(R.id.last_name);
        lastName.setText(last_name);
        description = (EditText) findViewById(R.id.description);
        description.setText("Write something about you");
        image=  (ImageView)findViewById(R.id.profile_image);
        String imgUrl = "https://graph.facebook.com/"+id+"/picture?type=large";
        Picasso.with(this)
                .load(imgUrl)
                .placeholder(R.drawable.ic_action_name) // optional
                .error(R.drawable.sleep) // optional
                .resize(200, 200) // optional
                .centerCrop()
                .into(image);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }
    public void loginLatter(View view) {
        Intent intent = new Intent(this, NewsFeed.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Enjoy surfing", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void register(View view) {
        String fName = firstName.getText().toString();
        String lName = lastName.getText().toString();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl.link)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BaseUrl.link)
//                .build();
       // RestAdapter adapter = new RestAdapter.Builder().setEndpoint(getResources().getString(R.string.ENDPOINT)).build();
//        UserApi userApi = retrofit.create(UserApi.class);
//
//        userApi.createUser(fName, lName, emailTextView.getText().toString(), id, true, new Callback<User>() {
//            public void onResponse(Response<User> response, Retrofit retrofit) {
//                //Intent intent = new Intent(getApplicationContext(), NewsFeed.class);
//                //startActivity(intent);
//                Toast.makeText(getApplicationContext(), ""+response.body(), Toast.LENGTH_SHORT).show();
//            }
//            public void success(User user, Response response) {
////                String Name = user.getName();
////                String email = user.getEmail();
////                String phone = user.getPhone();
//                Intent intent = new Intent(getApplicationContext(), NewsFeed.class);
//                startActivity(intent);
//                Toast.makeText(getApplicationContext(), user.email, Toast.LENGTH_SHORT).show();
//            }
//            @Override
//            public void onFailure(Throwable t) {
//                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });

        UserApi userApi = retrofit.create(UserApi.class);
        User u = new User(fName, lName,  emailTextView.getText().toString(), id);
       //Call<User> createUser(@Field("first_name") String first, @Field("last_name") String last, @Field("email") String email, @Field("uid") String uid);
      //  Call<User> call = userApi.createUser(u);
        Call<User> call = userApi.createUser(u.first_name, u.last_name, "saddsa@gmail.com", u.uid, true );
        call.enqueue(this);
    }

    @Override
    public void onResponse(Response<User> response, Retrofit retrofit) {
        Intent intent = new Intent(this, NewsFeed.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), ""+response.body(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }
}
