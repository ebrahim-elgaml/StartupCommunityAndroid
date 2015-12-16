package localhost3000.startupcommunity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import localhost3000.startupcommunity.dummy.DummyContent;
import localhost3000.startupcommunity.dummy.NewUserItem;
import localhost3000.startupcommunity.model.User;
import localhost3000.startupcommunity.model.currentUser;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class NewUserRequestFragment extends android.support.v4.app.Fragment implements AbsListView.OnItemClickListener, NewUserItem.PlayToastAlert {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListenerRequestList mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;
    NewUserItem friendRequestAdapter;
    // TODO: Rename and change types of parameters
    public static NewUserRequestFragment newInstance(String param1, String param2) {
        NewUserRequestFragment fragment = new NewUserRequestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public NewUserRequestFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



        // TODO: Change Adapter to display your content
//        mAdapter = new ArrayAdapter<FriendRequestList>(getActivity(),
//                R.layout.single_friend_request, FriendRequestList.items);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View view = inflater.inflate(android.R.layout.simple_list_item_1, container, false);
        final View view = inflater.inflate(R.layout.list_user_fragment, container, false);
        final List<String> s = new ArrayList<String>();
        final List<NewUserItem.SingleRequest> a = new ArrayList<NewUserItem.SingleRequest>();
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(getResources().getString(R.string.ENDPOINT)).build();
        MyApi api;
        api = adapter.create(MyApi.class);
        api.getNewUsers("" + currentUser.id, new Callback<List<User>>() {
            @Override
            public void success(List<User> users, Response response) {
                Iterator<User> iterator = users.iterator();
                while (iterator.hasNext()) {
                    User conn = iterator.next();
                    final int id = conn.id;
                    s.add(id + "");
                    a.add(new NewUserItem.SingleRequest(conn.email, id, conn.profile_picture));
                }
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        friendRequestAdapter = new NewUserItem(getActivity(), a, s);
                        mListView = (AbsListView) view.findViewById(R.id.listview_new_users);
                        ((AdapterView<ListAdapter>) mListView).setAdapter(friendRequestAdapter);
                    }
                }, 2000);


            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListenerRequestList) activity;
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

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    @Override
    public void playToast(String id) {
        // Toast.makeText(getActivity(), id, Toast.LENGTH_SHORT).show();
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
    public interface OnFragmentInteractionListenerRequestList {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }

}
