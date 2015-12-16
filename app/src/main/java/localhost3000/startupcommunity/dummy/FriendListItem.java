package localhost3000.startupcommunity.dummy;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
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

import localhost3000.startupcommunity.NavigationDrawerFragment;
import localhost3000.startupcommunity.NewsFeed;
import localhost3000.startupcommunity.ProfileFragment;
import localhost3000.startupcommunity.R;
import localhost3000.startupcommunity.SettingsActivity;
import localhost3000.startupcommunity.friend_profile;
import localhost3000.startupcommunity.model.currentUser;

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
        final Friend request = (Friend)items.get(i);
        final boolean[] clicked = {true};
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
         t.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View v) {
                 //getContext().startActivity(new Intent(getContext(), NewsFeed.class));
                 //FragmentManager fragmentManager = get.getFragmentManager();
                 android.support.v4.app.Fragment f = new ProfileFragment();
                 ;
                 f = new friend_profile();
                 currentUser.friend_id = request.getFriendId();
                 clicked[0] = true;
                 ((NewsFeed) getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.container, f).commit();
                 //viewV = context.getLayoutInflater().inflate(R.layout.single_friend,viewG, false);
                 Toast.makeText(getContext(), request.getFriendId() + "", Toast.LENGTH_SHORT).show();
             }
         });
        if (clicked[0]) {
            Toast.makeText(getContext(),"yarab ba2a", Toast.LENGTH_SHORT).show();
            //return context.getLayoutInflater().inflate(R.layout.fragment_friend_profile, viewGroup, false);
        }
        //else
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
