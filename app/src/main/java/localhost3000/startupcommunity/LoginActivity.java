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

import localhost3000.startupcommunity.model.User;
import localhost3000.startupcommunity.model.currentUser;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity {
    TextView emailTextView;
    EditText firstName;
    EditText lastName;
    EditText country;
    String id;
    ImageView image;
    boolean gender = true;
//    String country;

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
        country = (EditText) findViewById(R.id.country);
        country.setText("Egypt");
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
        String myCountry = country.getText().toString();
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(getResources().getString(R.string.ENDPOINT)).build();
        MyApi api;
        api = adapter.create(MyApi.class);
        api.createUser(fName, lName, emailTextView.getText().toString(), id, true, myCountry, new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                currentUser.id = user.id;
                Intent intent = new Intent(getApplicationContext(), NewsFeed.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
