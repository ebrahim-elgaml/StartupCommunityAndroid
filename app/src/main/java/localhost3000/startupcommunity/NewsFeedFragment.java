package localhost3000.startupcommunity;
//import DetailedPost.java;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Intent;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//class DetailActivity { }

public class NewsFeedFragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnNewsFeedFragmentInteractionListener mListener;

    // TODO: Rename and change types and number of parameters
    public static NewsFeedFragment newInstance(String param1, String param2) {
        NewsFeedFragment fragment = new NewsFeedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public NewsFeedFragment() {
        // Required empty public constructor
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
        View rootView = inflater.inflate(R.layout.fragment_news_feed, container, false);
        String [] newsFeedArray ={
                "post no 1","post no 2","post no 3","post no 4","post no 5","post no 6"
        };
        List<String> newsFeed = new ArrayList<String>(Arrays.asList(newsFeedArray));
        final ArrayAdapter mNewsfeedAdapter = new ArrayAdapter(
                getActivity(),
                R.layout.list_item_feed,
                R.id.list_item_feed_textview,
                newsFeed
        );
        ListView listView = (ListView) rootView.findViewById(R.id.listview_feed);
        listView.setAdapter(mNewsfeedAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 String feed = String.valueOf(mNewsfeedAdapter.getItem(position));
                 Toast.makeText(getActivity(),feed,Toast.LENGTH_SHORT).show();
                 Intent intent = new Intent(getActivity(),DetailActivity.class)
                        .putExtra(Intent.EXTRA_TEXT,feed);
                startActivity(intent);
            }



        });

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(int position) {
        if (mListener != null) {
            //mListener.onFragmentInteraction(uri);
            mListener.onNewsFeedItemSelected(position);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnNewsFeedFragmentInteractionListener) activity;
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
    public interface OnNewsFeedFragmentInteractionListener {
        // TODO: Update argument type and name
//        public void onFragmentInteraction(Uri uri);
        public void onNewsFeedItemSelected(int position);
    }

}
