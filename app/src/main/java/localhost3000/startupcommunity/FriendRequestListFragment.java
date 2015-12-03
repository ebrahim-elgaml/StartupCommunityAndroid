package localhost3000.startupcommunity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import localhost3000.startupcommunity.dummy.DummyContent;
import localhost3000.startupcommunity.dummy.FriendRequestList;


public class FriendRequestListFragment extends android.support.v4.app.Fragment implements AbsListView.OnItemClickListener, FriendRequestList.PlayToastAlert {

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
    FriendRequestList friendRequestAdapter;
    // TODO: Rename and change types of parameters
    public static FriendRequestListFragment newInstance(String param1, String param2) {
        FriendRequestListFragment fragment = new FriendRequestListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FriendRequestListFragment() {
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
        View view = inflater.inflate(R.layout.fragment_request, container, false);

        // Set the adapter


//        friendRequestAdapter =  new FriendRequestList()
        //FriendRequestList f = new FriendRequestList(this, a,s);
        // Set OnItemClickListener so we can be notified on item clicks
        List<FriendRequestList.SingleRequest> a = new ArrayList<FriendRequestList.SingleRequest>();
        a.add(new FriendRequestList.SingleRequest("Ebrahim Elgaml", 1, "https://graph.facebook.com/10205421895761103/picture?type=large"));
        a.add(new FriendRequestList.SingleRequest("Myriame Ayman", 2, "https://graph.facebook.com/100004021915944/picture?type=large"));
        a.add(new FriendRequestList.SingleRequest("renad Shabaan", 3, "https://graph.facebook.com/1460404964/picture?type=large"));
        List<String> s = new ArrayList<String>();
        s.add("ebrahim");
        s.add("myriame");
        s.add("renad");
        friendRequestAdapter = new FriendRequestList(getActivity(), a, s);


        //mListView.setOnItemClickListener(this);
        //mListView = (AbsListView) view.findViewById(android.R.id.list);
        mListView = (AbsListView) view.findViewById(R.id.listview_requests);
        //((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);
        ((AdapterView<ListAdapter>) mListView).setAdapter(friendRequestAdapter);


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
        Toast.makeText(getActivity(), id, Toast.LENGTH_SHORT).show();
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