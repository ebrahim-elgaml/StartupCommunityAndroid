package localhost3000.startupcommunity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import localhost3000.startupcommunity.dummy.FriendListItem;
import localhost3000.startupcommunity.model.User;
import localhost3000.startupcommunity.model.currentUser;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link friend_profile.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link friend_profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class friend_profile extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public static int ID;
    private OnFragmentInteractionListener mListener;

    FriendListItem friendListItem;
    private ListAdapter mAdapter;
    private AbsListView mListView;


    public friend_profile() {
        // Required empty public constructor
    }


    public static friend_profile newInstance(String param1, String param2) {
        friend_profile fragment = new friend_profile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_profile, container, false);
        final List<User> friends = new ArrayList<User>();

        //10.0.2.2:3000/
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(getResources().getString(R.string.ENDPOINT)).build();
        MyApi api;
        api = adapter.create(MyApi.class);
        api.getFriends(currentUser.friend_id,new Callback<List<User>>() {

            @Override
            public void success(List<User> types, Response response) {
                Iterator<User> iterator = types.iterator();
                while (iterator.hasNext()) {
                    friends.add(iterator.next());
                }

                Toast.makeText(getActivity(),"friend of my friend",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });

        //Toast.makeText(getActivity(),friends.size() + "",Toast.LENGTH_SHORT).show();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<FriendListItem.Friend> a = new ArrayList<FriendListItem.Friend>();
                List<String> s = new ArrayList<String>();
                for (int i = 0; i < friends.size(); i++) {
                    a.add(new FriendListItem.Friend(friends.get(i).first_name + " " + friends.get(i).last_name, friends.get(i).id, friends.get(i).profile_picture));
                    s.add(friends.get(i).id + "");
                }
                friendListItem = new FriendListItem(getActivity(), a, s);

                mListView = (AbsListView) view.findViewById(R.id.listViewFriends);
                ((AdapterView<ListAdapter>) mListView).setAdapter(friendListItem);
            }
        }, 1000);



        api.getUser(currentUser.friend_id + "", new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                //R.string.current_user = 3;
                TextView name = (TextView) view.findViewById(R.id.fullscreen_content);
                name.setText(user.first_name + " " + user.last_name);
                //name.setText("betengan");
                ImageView image = (ImageView) view.findViewById(R.id.imageView);
                //image=  (ImageView)view.findViewById(R.id.imageView);
                String imgUrl = user.profile_picture;
                Picasso.with(getActivity())
                        .load(imgUrl)
                        .placeholder(R.drawable.ic_action_name) // optional
                        .error(R.drawable.sleep) // optional
                        .resize(200, 200) // optional
                        .centerCrop()
                        .into(image);

                //Toast.makeText(getActivity(), currentUser.id + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {
                //Toast.makeText(getActivity(), "No user with thst id", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
       // return inflater.inflate(R.layout.fragment_friend_profile, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
//        public void onFragmentInteraction(Uri uri);
        public void onFriendProfileItemSelected(int position);
    }

}
