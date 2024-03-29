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


public class CommentsList extends ArrayAdapter<String>  implements View.OnClickListener{
    public static List<SingleComment> items = new ArrayList<>();
    private Activity context;
    PlayToastAlert soundToastAlert;

    public CommentsList(FragmentActivity context, List<SingleComment> l, List<String> requestId) {
        super(context, R.layout.list_item_feed, requestId);
        this.items = l;
        this.context = context;
//        this.soundToastAlert = (PlayToastAlert)context;
    }




    private static void addItem(SingleComment item) {
        items.add(item);
    }

    @Override
    public Activity getContext() {
        return context;
    }
    public View getView(int i, View view, ViewGroup viewGroup) {
        SingleComment post;
        post = (SingleComment)items.get(i);
        if (view == null) // reuse existing view
            view = context.getLayoutInflater().inflate(R.layout.fragment_detail3,
                    viewGroup, false);
        TextView t = (TextView)view.findViewById(R.id.list_item_comment);
        t.setText(post.getText());
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        //Button b = (Button)view.findViewById(R.id.list_item_button);
       // b.setTag("accept,"+request.getRequestId());
       // b.setOnClickListener(this);
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

    public static class SingleComment {
        private String text ;
        private int post_id ;
        private int user_id;
        private int id;
        private String image;
        //private  int startup_id;
        public SingleComment(String text,int post_id,int user_id,String image,int id){
            this.text=text;
            this.user_id=user_id;
            this.post_id=post_id;
            this.image=image;
            this.id=id;
            //this.startup_id=startup_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }


        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getPost_id() {
            return post_id;
        }

        public void setPost_id(int post_id) {
            this.post_id = post_id;
        }
    }
}
