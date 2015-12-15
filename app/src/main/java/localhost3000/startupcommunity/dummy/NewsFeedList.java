package localhost3000.startupcommunity.dummy;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
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


public class NewsFeedList extends ArrayAdapter<String>  implements View.OnClickListener{
    public static List<SinglePost> items = new ArrayList<SinglePost>();
    private Activity context;
    PlayToastAlert soundToastAlert;

    public NewsFeedList(FragmentActivity context, List<SinglePost> l, List<String> requestId) {
        super(context, R.layout.list_item_feed, requestId);
        this.items = l;
        this.context = context;
//        this.soundToastAlert = (PlayToastAlert)context;
    }




    private static void addItem(SinglePost item) {
        items.add(item);
    }

    @Override
    public Activity getContext() {
        return context;
    }
    public View getView(int i, View view, ViewGroup viewGroup) {
        SinglePost post = (SinglePost)items.get(i);
        if (view == null) // reuse existing view
            view = context.getLayoutInflater().inflate(R.layout.list_item_feed,
                    viewGroup, false);
        TextView t = (TextView)view.findViewById(R.id.list_item_string);
        t.setText(post.getName());
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        Picasso.with(this.context)
                .load(post.imageUrl)
                .placeholder(R.drawable.ic_action_name) // optional
                .error(R.drawable.sleep) // optional
                .resize(200, 200) // optional
                .centerCrop()
                .into(imageView);
//        Button b = (Button)view.findViewById(R.id.list_item_button);
//        b.setTag(post.getName());
//        b.setOnClickListener(this);
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
    public interface PlayToastAlert {
        public void playToast(String id);
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    public static class SinglePost {
        private String name ;
        private int requestId ;
        private String imageUrl;
        public SinglePost(String name, int request, String imageUrl){
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
