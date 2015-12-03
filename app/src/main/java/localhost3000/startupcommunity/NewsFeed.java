package localhost3000.startupcommunity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.view.View;
import android.widget.Button;

import localhost3000.startupcommunity.dummy.FriendRequestList;

public class NewsFeed extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks , ProfileFragment.OnFragmentInteractionListener, NewsFeedFragment.OnNewsFeedFragmentInteractionListener, RequestFragment.OnRequestFragmentInteractionListener, FriendRequestListFragment.OnFragmentInteractionListenerRequestList
        , FriendRequestList.PlayToastAlert, startups.OnFragmentInteractionListener, followed_startups.OnFragmentInteractionListener,Edit_Profile.OnFragmentInteractionListener{


    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        //FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment f ;
        switch(position){
            case 0:   f = new NewsFeedFragment(); break;
            case 1:   f = new ProfileFragment();break;
            case 2:   f = new FriendRequestListFragment();break;
            default: f = new FriendRequestListFragment();
        }
        fragmentManager.beginTransaction().replace(R.id.container, f).commit();
//        fragmentManager.beginTransaction()
//                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
//                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.news_feed, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//        //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
        startActivity(new Intent(this, SettingsActivity.class));
        return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onProfileItemSelected(int position) {

    }
    @Override
    public void onEdit_ProfileItemSelected(int position) {

    }

    @Override
    public void onStartupsItemSelected(int position) {

    }

    public void onStartupsFollowedItemSelected(int position) {

    }

    @Override
    public void onNewsFeedItemSelected(int position) {

    }

    @Override
    public void onRequestFragmentInteraction(int position) {

    }
    public void onClick(View v) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.Fragment f = new ProfileFragment();;

        if (v.getId() == R.id.friends)
            f = new ProfileFragment();
        else if (v.getId() == R.id.MyStartups)
            f = new startups();
        else if (v.getId() == R.id.FollowedStartups)
            f = new followed_startups();
        else if(v.getId() == R.id.EditProfile)
            f = new Edit_Profile();
        else if(v.getId() == R.id.saveEdits)
            f = new ProfileFragment();
        fragmentManager.beginTransaction().replace(R.id.container, f).commit();

    }

    @Override
    public void onFragmentInteraction(String id) {

    }

    @Override
    public void playToast(String id) {
        Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
    }

    /**
     * A placeholder fragment containing a simple view.
     */

//    public static class PlaceholderFragment extends Fragment {
//        /**
//         * The fragment argument representing the section number for this
//         * fragment.
//         */
//        private static final String ARG_SECTION_NUMBER = "section_number";
//
//        /**
//         * Returns a new instance of this fragment for the given section
//         * number.
//         */
//        public static PlaceholderFragment newInstance(int sectionNumber) {
//            PlaceholderFragment fragment = new PlaceholderFragment();
//            Bundle args = new Bundle();
//            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//            fragment.setArguments(args);
//            return fragment;
//        }
//
//        public PlaceholderFragment() {
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_news_feed, container, false);
//            return rootView;
//        }
//
//        @Override
//        public void onAttach(Activity activity) {
//            super.onAttach(activity);
//            ((NewsFeed) activity).onSectionAttached(
//                    getArguments().getInt(ARG_SECTION_NUMBER));
//        }
//    }

}
