package localhost3000.startupcommunity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import localhost3000.startupcommunity.dummy.DummyContent;
import localhost3000.startupcommunity.dummy.FriendListItem;
import localhost3000.startupcommunity.dummy.FriendRequestList;
import localhost3000.startupcommunity.model.User;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ProfileFragment extends android.support.v4.app.Fragment implements AbsListView.OnItemClickListener, FriendRequestList.PlayToastAlert {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    FriendListItem friendListItem;
    private ListAdapter mAdapter;
    private AbsListView mListView;
    private OnFragmentInteractionListener mListener;

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_profile, container, false);
        final List<String> friends = new ArrayList<String>();

        //10.0.2.2:3000/
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint("https://startup-community-myriame-ayman.c9users.io/api").build();
        MyApi api;
        api = adapter.create(MyApi.class);
        api.getFriends(new Callback<List<User>>() {

            @Override
            public void success(List<User> types, Response response) {
                Iterator<User> iterator = types.iterator();
                while (iterator.hasNext()) {
                    String s = iterator.next().first_name;
                    friends.add(s);

                }

                Toast.makeText(getActivity(),friends.size() + "",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {
                friends.add("Failure");
                Toast.makeText(getActivity(),"ana hena aho",Toast.LENGTH_SHORT).show();
                //throw error;
            }
        });

        Toast.makeText(getActivity(),friends.size() + "",Toast.LENGTH_SHORT).show();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<FriendListItem.Friend> a = new ArrayList<FriendListItem.Friend>();
                a.add(new FriendListItem.Friend(friends.size() + "", 1, "https://graph.facebook.com/10205421895761103/picture?type=large"));
                //a.add(new FriendListItem.Friend(friends.get(1), 2, "https://graph.facebook.com/100004021915944/picture?type=large"));
                //a.add(new FriendListItem.Friend(friends.get(2), 3, "https://graph.facebook.com/1460404964/picture?type=large"));
                List<String> s = new ArrayList<String>();
                s.add("ebrahim");
                //s.add("myriame");
                //s.add("renad");
                friendListItem = new FriendListItem(getActivity(), a, s);

                mListView = (AbsListView) view.findViewById(R.id.listViewFriends);
                ((AdapterView<ListAdapter>) mListView).setAdapter(friendListItem);

            }
            }, 1000);
            return view;
     }


    private void updateTab(String tabId, int placeholder) {
      }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);
        }
    }
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    @Override
    public void playToast(String id) {
        Toast.makeText(getActivity(), id, Toast.LENGTH_SHORT).show();
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }


}
