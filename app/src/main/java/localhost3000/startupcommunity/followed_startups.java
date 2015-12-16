package localhost3000.startupcommunity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import localhost3000.startupcommunity.dummy.FriendListItem;
import localhost3000.startupcommunity.dummy.StartupListItem;
import localhost3000.startupcommunity.model.Startup;
import localhost3000.startupcommunity.model.User;
import localhost3000.startupcommunity.model.currentUser;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class followed_startups extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StartupListItem startupListItem;
    private ListAdapter mAdapter;
    private AbsListView mListView;
    private OnFragmentInteractionListener mListener;

    public followed_startups() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment followed_startups.
     */
    // TODO: Rename and change types and number of parameters
    public static followed_startups newInstance(String param1, String param2) {
        followed_startups fragment = new followed_startups();
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
        final View view = inflater.inflate(R.layout.fragment_followed_startups, container, false);
        final List<User> startups = new ArrayList<User>();
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(getResources().getString(R.string.ENDPOINT)).build();
        MyApi api;
        api = adapter.create(MyApi.class);
        api.getFollowedStartups(currentUser.id + "", new Callback<List<Startup>>() {
            @Override
            public void success(List<Startup> startups, Response response) {
                    Iterator<Startup> iterator = startups.iterator();
                    while (iterator.hasNext())
                        startups.add(iterator.next());
                    List<Startup> a = new ArrayList<Startup>();
                    List<String> s = new ArrayList<String>();
                    for (int i = 0; i < startups.size(); i++) {
                        a.add(new Startup(startups.get(i).id,startups.get(i).name,  startups.get(i).image));
                        s.add(startups.get(i).id + "");
                    }
                    startupListItem = new StartupListItem(getActivity(), a, s);

                    mListView = (AbsListView) view.findViewById(R.id.listViewFriends);
                    ((AdapterView<ListAdapter>) mListView).setAdapter(startupListItem);
                    Toast.makeText(getActivity(), "gowa el startups", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), "gowa el startups", Toast.LENGTH_SHORT).show();

            }
        });
        return view;
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
        public void onStartupsFollowedItemSelected(int position);
    }

}
