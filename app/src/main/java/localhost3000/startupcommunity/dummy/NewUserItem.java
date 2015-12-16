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
import localhost3000.startupcommunity.model.currentUser;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class NewUserItem extends ArrayAdapter<String>  implements View.OnClickListener{
    public static List<SingleRequest> items = new ArrayList<SingleRequest>();
    private Activity context;
    PlayToastAlert soundToastAlert;

    public NewUserItem(Activity context, List<SingleRequest> l, List<String> requestId) {
        super(context, R.layout.single_friend_request, requestId);
        this.items = l;
        this.context = context;
        this.soundToastAlert = (PlayToastAlert)context;
    }
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
            view = context.getLayoutInflater().inflate(R.layout.single_new_user_fragment,
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
        Button b = (Button)view.findViewById(R.id.send_request);
        b.setTag(request.getUserId()+"");
        b.setOnClickListener(this);
        return view;
    }
    public void onClick(View view) {
        Button b = (Button) view;
        if (b != null) {
            String buttonID = (String)b.getTag();
            if (soundToastAlert != null) {
                soundToastAlert.playToast(buttonID);
                view.setVisibility(View.GONE);
                RestAdapter adapter = new RestAdapter.Builder().setEndpoint(context.getResources().getString(R.string.ENDPOINT)).build();
                MyApi api;
                api = adapter.create(MyApi.class);
                api.createRequest(buttonID, currentUser.id+"", new Callback<UserConnection>() {
                    @Override
                    public void success(UserConnection userConnection, Response response) {
                        Toast.makeText(context, "Send successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
    public interface PlayToastAlert {
        public void playToast(String id);
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    public static class SingleRequest {
        private String name ;
        private int user_id ;
        private String imageUrl;
        public SingleRequest(String name, int request, String imageUrl){
            this.name = name;
            this.user_id = request;
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

        public int getUserId() {
            return user_id;
        }

        public void setUserId(int requestId) {
            this.user_id = requestId;
        }

    }
}
