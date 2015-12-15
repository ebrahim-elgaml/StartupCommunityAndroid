package localhost3000.startupcommunity.dummy;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import localhost3000.startupcommunity.R;

/**
 * Created by myriame on 12/15/15.
 */
public class FriendListItem extends ArrayAdapter<String> implements View.OnClickListener {

    public static List<Friend> items = new ArrayList<Friend>();
    private Activity context;
    PlayToastAlert soundToastAlert;

    public FriendListItem(Activity context, List<Friend> l, List<String> requestId) {
        super(context, R.layout.single_friend, requestId);
        this.items = l;
        this.context = context;
 //       this.soundToastAlert = (PlayToastAlert)context;
    }
    public interface PlayToastAlert {
        public void playToast(String id);
    }
    private static void addItem(Friend item) {
        items.add(item);
    }

    @Override
    public Activity getContext() {
        return context;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        Friend request = (Friend)items.get(i);

        if (view == null) // reuse existing view
            view = context.getLayoutInflater().inflate(R.layout.single_friend,
                    viewGroup, false); //TODO
        TextView t = (TextView)view.findViewById(R.id.name);
        t.setText(request.getName());
        ImageView imageView = (ImageView) view.findViewById(R.id.profile_image);
        Picasso.with(this.context)
                .load(request.imageUrl)
                .placeholder(R.drawable.ic_action_name) // optional
                .error(R.drawable.sleep) // optional
                .resize(200, 200) // optional
                .centerCrop()
                .into(imageView);
        //Button b = (Button)view.findViewById(R.id.list_item_button);
        //b.setTag(request.getName());
        //b.setOnClickListener(this);
        return view;
    }
    public void onClick(View view) {
        Button b = (Button) view;
        if (b != null) {
            String buttonID = (String)b.getTag();
            if (soundToastAlert != null) {
                soundToastAlert.playToast(""+buttonID);
            }
        }
    }
    public void setContext(Activity context) {
        this.context = context;
    }

    public static class Friend {
        private String name ;
        private int friendId ;
        private String imageUrl;
        public Friend(String name, int id, String imageUrl){
            this.name = name;
            this.friendId = id;
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

        public int getFriendId() {
            return friendId;
        }

        public void setFriendId(int requestId) {
            this.friendId = requestId;
        }

    }

}
