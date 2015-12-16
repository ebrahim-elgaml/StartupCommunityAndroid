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
import localhost3000.startupcommunity.model.Startup;

/**
 * Created by myriame on 12/15/15.
 */
public class StartupListItem extends ArrayAdapter<String> implements View.OnClickListener {

    public static List<Startup> items = new ArrayList<Startup>();
    private Activity context;
    PlayToastAlert soundToastAlert;

    public StartupListItem(Activity context, List<Startup> l, List<String> requestId) {
        super(context, R.layout.single_startup, requestId);
        this.items = l;
        this.context = context;
    }
    public interface PlayToastAlert {
        public void playToast(String id);
    }
    private static void addItem(Startup item) {
        items.add(item);
    }

    @Override
    public Activity getContext() {
        return context;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        Startup request = (Startup)items.get(i);

        if (view == null) // reuse existing view
            view = context.getLayoutInflater().inflate(R.layout.single_startup,
                    viewGroup, false); //TODO
        TextView t = (TextView)view.findViewById(R.id.name);
        t.setText(request.name);
        ImageView imageView = (ImageView) view.findViewById(R.id.profile_image);
        Picasso.with(this.context)
                .load(request.image)
                .placeholder(R.drawable.ic_action_name) // optional
                .error(R.drawable.sleep) // optional
                .resize(200, 200) // optional
                .centerCrop()
                .into(imageView);
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
    /*
    public static class Startup {
        private String name ;
        private int startupId ;
        private String imageUrl;
        public Startup(String name, int id, String imageUrl){
            this.name = name;
            this.startupId = id;
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
    }
    */
}
