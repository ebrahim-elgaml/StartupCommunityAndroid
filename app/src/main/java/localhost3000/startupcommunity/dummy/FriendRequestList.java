package localhost3000.startupcommunity.dummy;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import localhost3000.startupcommunity.MyApi;
import localhost3000.startupcommunity.R;
import localhost3000.startupcommunity.model.UserConnection;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class FriendRequestList extends ArrayAdapter<String>  implements View.OnClickListener{
    public static List<SingleRequest> items = new ArrayList<SingleRequest>();
    private Activity context;
    PlayToastAlert soundToastAlert;

//    public FriendRequestList(Context context, int resource, int textViewResourceId) {
//        super(context, resource, textViewResourceId);
//    }
    public FriendRequestList(Activity context, List<SingleRequest> l, List<String> requestId) {
        super(context, R.layout.single_friend_request, requestId);
        this.items = l;
        this.context = context;
        this.soundToastAlert = (PlayToastAlert)context;
    }

//    public FriendRequestList(Activity context, List<SingleRequest> l, List<String> requestId) {
//        super(context, R.layout.single_friend_request, requestId);
//        this.items = l;
//        this.context = context;
//        this.soundToastAlert = (PlayToastAlert)context;
//    }
//    public FriendRequestList(Activity context, List<SingleRequest> l) {
//        super(context, R.layout.single_friend_request);
//        this.items = l;
//        this.context = context;
//        this.soundToastAlert = (PlayToastAlert)context;
//    }


    private static void addItem(SingleRequest item) {
        items.add(item);
    }

    @Override
    public Activity getContext() {
        return context;
    }
    public View getView(int i, View view, ViewGroup viewGroup) {
        SingleRequest request = (SingleRequest)items.get(i);
        if (view == null) // reuse existing view
            view = context.getLayoutInflater().inflate(R.layout.single_friend_request,
                    viewGroup, false);
        TextView t = (TextView)view.findViewById(R.id.list_item_string);
        t.setText(request.getName());
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        Picasso.with(this.context)
                .load(request.imageUrl)
                .placeholder(R.drawable.ic_action_name) // optional
                .error(R.drawable.sleep) // optional
                .resize(200, 200) // optional
                .centerCrop()
                .into(imageView);
        Button b = (Button)view.findViewById(R.id.list_item_button);
        b.setTag("accept,"+request.getRequestId());
        b.setOnClickListener(this);
        Button b2 = (Button)view.findViewById(R.id.list_item_button_reject);
        b2.setTag("reject,"+request.getRequestId());
        b2.setOnClickListener(this);
        return view;
    }
    public void onClick(View view) {
        Button b = (Button) view;
        if (b != null) {
            String buttonID = (String)b.getTag();
            String[] a = buttonID.split(",");
            if(a[0].equalsIgnoreCase("accept")){
                if (soundToastAlert != null) {
                    soundToastAlert.playToast(a[1]);
                    view.setVisibility(View.GONE);
                    //Button b2 = (Button)view.findViewById(R.id.list_item_button_reject);
                    //ViewGroup layout = (ViewGroup) view.getParent();
                    ((View) view.getParent()).findViewById(R.id.list_item_button_reject).setVisibility(View.GONE);
//                    if(null!=layout) //for safety only  as you are doing onClick
//                    layout.removeView(view.findViewById(R.id.list_item_button_reject));
//                    if(b2!=null)
//                        b2.setVisibility(View.GONE);
                    RestAdapter adapter = new RestAdapter.Builder().setEndpoint(context.getResources().getString(R.string.ENDPOINT)).build();
                    MyApi api;
                    api = adapter.create(MyApi.class);
                    api.acceptRequest(a[1], new Callback<UserConnection>() {
                        @Override
                        public void success(UserConnection userConnection, Response response) {
                            Toast.makeText(context, "accepted successfully", Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void failure(RetrofitError error) {
                            Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }else {
                if (soundToastAlert != null) {
                    soundToastAlert.playToastReject(a[1]);
                    view.setVisibility(View.GONE);
                    ((View) view.getParent()).findViewById(R.id.list_item_button).setVisibility(View.GONE);
//                    ViewGroup layout = (ViewGroup) view.getParent();
//                    if(null!=layout) //for safety only  as you are doing onClick
//                        layout.removeView(view.findViewById(R.id.list_item_button));
                    RestAdapter adapter = new RestAdapter.Builder().setEndpoint(context.getResources().getString(R.string.ENDPOINT)).build();
                    MyApi api;
                    api = adapter.create(MyApi.class);
                    api.rejectRequest(a[1], new Callback<UserConnection>() {
                        @Override
                        public void success(UserConnection userConnection, Response response) {
                            Toast.makeText(context, "rejected successfully", Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void failure(RetrofitError error) {
                            Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        }
    }
    public interface PlayToastAlert {
        public void playToast(String id);
        public void playToastReject(String id);
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    public static class SingleRequest {
        private String name ;
        private int requestId ;
        private String imageUrl;
        public SingleRequest(String name, int request, String imageUrl){
            this.name = name;
            this.requestId = request;
            this.imageUrl = imageUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getRequestId() {
            return requestId;
        }

        public void setRequestId(int requestId) {
            this.requestId = requestId;
        }

    }
}
