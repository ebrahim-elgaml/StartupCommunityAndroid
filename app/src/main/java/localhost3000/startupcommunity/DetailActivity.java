package localhost3000.startupcommunity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import localhost3000.startupcommunity.dummy.CommentsList;
import localhost3000.startupcommunity.model.User;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DetailActivity extends ActionBarActivity {
    static ListView mListView;
    CommentsList newsFeedAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
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



        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View view = inflater.inflate(R.layout.fragment_detail3, container, false);
            Intent intent = getActivity().getIntent();
            String id = intent.getStringExtra("id");
            final List<String> comments = new ArrayList<String>();
            final List<CommentsList.SingleComment> a = new ArrayList<CommentsList.SingleComment>();
            final List<String> s = new ArrayList<String>();
            RestAdapter adapter = new RestAdapter.Builder().setEndpoint(getResources().getString(R.string.ENDPOINT)).build();
            final MyApi api= adapter.create(MyApi.class);
            api.getComments(id, new Callback<List<CommentsList.SingleComment>>(){

                @Override
                public void success(List<CommentsList.SingleComment> types, Response response) {
                    Iterator<CommentsList.SingleComment> iterator = types.iterator();
                    while (iterator.hasNext()) {
                        final CommentsList.SingleComment it = iterator.next();


                        api.showUser(it.getUser_id()+"", new Callback<User>() {
                            @Override
                            public void success(final User user, Response response) {
                                // Toast.makeText(getActivity(), posts.size() + "", Toast.LENGTH_SHORT).show();

                                    a.add(new CommentsList.SingleComment(user.first_name+" has commented  "+ it.getText(), it.getPost_id(), it.getUser_id(), user.profile_picture,it.getId()));
                                    int id=it.getId();
                                    s.add(id+"");
                            }

                            @Override
                            public void failure(RetrofitError error) {

                            }
                        });



                    }
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            CommentsList newsFeedAdapter = new CommentsList(getActivity(), a, s);
                            mListView = (ListView) view.findViewById(R.id.listview_comment);
                            ((AdapterView<ListAdapter>) mListView).setAdapter(newsFeedAdapter);


                        }
                    }, 1000);

                    //Toast.makeText(getActivity(), posts.size() + "", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void failure(RetrofitError error) {
                    //comments.add("Failure");
                    //Toast.makeText(getActivity(), "ana hena aho", Toast.LENGTH_SHORT).show();
                    throw error;
                }
            });




//
//        a.add(new NewsFeedList.SinglePost("Ebrahim has posted on 7asala StartUp You are amazing :P ", 1,  "https://graph.facebook.com/100004021915944/picture?type=large"));
//        a.add(new NewsFeedList.SinglePost("Myriame has posted on 7asala StartUp You are amazing :P ", 2, "https://graph.facebook.com/100004021915944/picture?type=large"));
//        a.add(new NewsFeedList.SinglePost("Renad has posted on 7asala StartUp You are amazing :P ", 3, "https://graph.facebook.com/1460404964/picture?type=large"));
//        List<String> s = new ArrayList<String>();
//        s.add("ebrahim");
//        s.add("myriame");
//        s.add("renad");
//        newsFeedAdapter = new NewsFeedList(getActivity(), a, s);
//        mListView = (AbsListView) view.findViewById(R.id.listview_requests);
//        ((AdapterView<ListAdapter>) mListView).setAdapter(newsFeedAdapter);
//

            return view;



//        String [] newsFeedArray ={
//                "Renad has posted on 7asala StartUp You are amazing :P","Renad has posted on 7asala StartUp You are amazing :P",
//                "Renad has posted on 7asala StartUp You are amazing :P"
//                ,"Renad has posted on 7asala StartUp You are amazing :P"
//                ,"Renad has posted on 7asala StartUp You are amazing :P","Renad has posted on 7asala StartUp You are amazing :P",
//                "Renad has posted on 7asala StartUp You are amazing :P","Renad has posted on 7asala StartUp You are amazing :P","Renad has posted on 7asala StartUp You are amazing :P",
//                "Renad has posted on 7asala StartUp You are amazing :P"
//        };
//        List<String> newsFeed = new ArrayList<String>(Arrays.asList(newsFeedArray));
//        final ArrayAdapter mNewsfeedAdapter = new ArrayAdapter(
//                getActivity(),
//                R.layout.list_item_feed,
//                R.id.list_item_feed_textview,
//                newsFeed
//        );
//        ListView listView = (ListView) rootView.findViewById(R.id.listview_feed);
//        listView.setAdapter(mNewsfeedAdapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                 String feed = String.valueOf(mNewsfeedAdapter.getItem(position));
//                 Toast.makeText(getActivity(),feed,Toast.LENGTH_SHORT).show();
//                 Intent intent = new Intent(getActivity(),DetailActivity.class)
//                        .putExtra(Intent.EXTRA_TEXT,feed);
//                startActivity(intent);
//            }
//
//
//
//        });

            //return rootView;
        }








}
}
