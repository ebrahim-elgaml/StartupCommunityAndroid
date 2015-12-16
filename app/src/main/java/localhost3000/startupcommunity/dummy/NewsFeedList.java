package localhost3000.startupcommunity.dummy;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import localhost3000.startupcommunity.R;


public class NewsFeedList extends ArrayAdapter<String>  implements View.OnClickListener{
    public static List<SinglePost> items = new ArrayList<>();
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
        SinglePost post;
        post = (SinglePost)items.get(i);
        if (view == null) // reuse existing view
            view = context.getLayoutInflater().inflate(R.layout.list_item_feed,
                    viewGroup, false);
        TextView t = (TextView)view.findViewById(R.id.list_item_string);
        t.setText(post.getText());
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
//        Picasso.with(this.context)
//                .load(post.imageUrl)
//                .placeholder(R.drawable.ic_action_name) // optional
//                .error(R.drawable.sleep) // optional
//                .resize(200, 200) // optional
//                .centerCrop()
//                .into(imageView);
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



//    public class Post {
//        public int id;
//        public int user_id;
//        public int tagged_id;
//        public String text;
//        public int startup_id;
////        public Post(int user_id, int tagged_id, String text, int startup_id){
////            this.user_id = user_id;
////            this.tagged_id = tagged_id;
////            this.text = text;
////            this.startup_id = startup_id;
////        }
//
//        public String getText() {
//            return text;
//        }
//        public Post(String text,int i,String z){
//            this.text = text;
////            this.requestId = request;
////            this.imageUrl = imageUrl;
//        }
//    }
    public static class SinglePost {
        private String text ;
        private int request_id ;
        private int user_id;
        private  int startup_id;
        public SinglePost(String text,int request_id,int user_id,int startup_id){
            this.text=text;
            this.request_id=request_id;
            this.user_id=user_id;
            this.startup_id=startup_id;
        }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getStartup_id() {
        return startup_id;
    }

    public void setStartup_id(int startup_id) {
        this.startup_id = startup_id;
    }
}
}
