package localhost3000.startupcommunity;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Intent intent = getActivity().getIntent();
            View rootView = inflater.inflate(R.layout.fragment_detail3, container, false);


            String [] CommentsArray ={
                    "Comment no 1 Comment no 1 Comment no 1 Comment no 1 Comment no 1",
                    "Comment no 2 Comment no 2 Comment no 2 Comment no 2 Comment no 2" ,
                    "Comment no 3 Comment no 3 Comment no 3 Comment no 3 Comment no 3" ,
                    "Comment no 4 Comment no 4 Comment no 4 Comment no 4 Comment no 4" ,
                    "Comment no 5 Comment no 5 Comment no 5 Comment no 5 Comment no 5"

            };
            List<String> comments = new ArrayList<String>(Arrays.asList(CommentsArray));
            final ArrayAdapter mCommentsAdapter = new ArrayAdapter(
                    getActivity(),
                    R.layout.list_item_comment,
                    R.id.list_item_feed_textview,
                    comments
            );
            ListView listView = (ListView) rootView.findViewById(R.id.listview_comment);
            listView.setAdapter(mCommentsAdapter);


            if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
                String forecastStr = intent.getStringExtra(Intent.EXTRA_TEXT);
                ((TextView) rootView.findViewById(R.id.detail_text))
                        .setText(forecastStr);
            }
            return rootView;
        }
    }
}
