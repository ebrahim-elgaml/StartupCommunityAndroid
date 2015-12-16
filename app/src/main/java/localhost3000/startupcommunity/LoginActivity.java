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

public class LoginActivity extends AppCompatActivity {
    TextView emailTextView;
    EditText firstName;
    EditText lastName;
    EditText description;
    String id;
    ImageView image;


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
}
