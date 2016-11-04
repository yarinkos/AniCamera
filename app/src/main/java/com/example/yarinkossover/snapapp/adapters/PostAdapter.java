package com.example.yarinkossover.snapapp.adapters;
 
import java.util.ArrayList;
import java.util.HashMap;
 
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yarinkossover.snapapp.FeedFragment;
import com.example.yarinkossover.snapapp.R;
import com.example.yarinkossover.snapapp.model.Post;

public class PostAdapter extends BaseAdapter {
 
    private Activity activity;
    private ArrayList<Post> data;
    private static LayoutInflater inflater=null;
  //  public ImageLoader imageLoader;





    public PostAdapter(Activity a, ArrayList<Post> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 //       imageLoader=new ImageLoader(activity.getApplicationContext());
    }
 
    public int getCount() {
        return data.size();
    }
 
    public Object getItem(int position) {
        return position;
    }
 
    public long getItemId(int position) {
        return position;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_row, null);
 
        TextView user = (TextView)vi.findViewById(R.id.user); // title
        TextView when = (TextView)vi.findViewById(R.id.when); // artist name
        Post post = data.get(position);

 
        // Setting all values in listview
        user.setText(post.getUserName());
        when.setText(Long.toString(post.getTimeStamp()));
       // imageLoader.DisplayImage(song.get(KEY_THUMB_URL), thumb_image);
        return vi;
    }
}