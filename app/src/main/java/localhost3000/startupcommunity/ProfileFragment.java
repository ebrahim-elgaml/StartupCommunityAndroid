package localhost3000.startupcommunity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
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

import localhost3000.startupcommunity.dummy.DummyContent;
import localhost3000.startupcommunity.dummy.FriendListItem;
import localhost3000.startupcommunity.dummy.FriendRequestList;
import localhost3000.startupcommunity.model.User;
import localhost3000.startupcommunity.model.currentUser;
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
        final List<User> friends = new ArrayList<User>();

        //10.0.2.2:3000/
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(getResources().getString(R.string.ENDPOINT)).build();
        MyApi api;
        api = adapter.create(MyApi.class);
        api.getFriends(currentUser.id,new Callback<List<User>>() {
            @Override
            public void success(List<User> types, Response response) {
                Iterator<User> iterator = types.iterator();
                while (iterator.hasNext()) {
                    friends.add(iterator.next());
                }

                Toast.makeText(getActivity(),"fffff",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {
                throw error;
                //Toast.makeText(getActivity(),"fdddddddf",Toast.LENGTH_SHORT).show();

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



        api.getUser(currentUser.id + "", new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                //R.string.current_user = 3;
                TextView name = (TextView)view.findViewById(R.id.fullscreen_content);
                name.setText(user.first_name + " " + user.last_name);
                //name.setText("betengan");
                ImageView image = (ImageView)view.findViewById(R.id.imageView);
                //image=  (ImageView)view.findViewById(R.id.imageView);
                String imgUrl = user.profile_picture;
                Picasso.with(getActivity())
                        .load(imgUrl)
                        .placeholder(R.drawable.ic_action_name) // optional
                        .error(R.drawable.sleep) // optional
                        .resize(200, 200) // optional
                        .centerCrop()
                        .into(image);

                //Toast.makeText(getActivity(), currentUser.id + "",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(),"No user with thst id",Toast.LENGTH_SHORT).show();
            }
        });

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

    @Override
    public void playToastReject(String id) {

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }


}
