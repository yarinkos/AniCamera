package com.example.yarinkossover.snapapp;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.yarinkossover.snapapp.adapters.PostAdapter;
import com.example.yarinkossover.snapapp.media.CameraHelper;
import com.example.yarinkossover.snapapp.model.Post;
import com.example.yarinkossover.snapapp.model.VideoPost;

/**
 * Created by Yarin.kossover on 10/23/2016.
 */
public class FeedFragment extends Fragment {

    //Static variables
    public static final String KEY_SONG = "song"; // parent node
    public static final String KEY_ID = "id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_ARTIST = "artist";
    public static final String KEY_DURATION = "duration";
    public static final String KEY_THUMB_URL = "thumb_url";
    public static final String KEY_POST_ID = "post_id";

    ListView list;
    PostAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);


        final ArrayList<Post> postList = buildDemoPosts();

        list = (ListView) rootView.findViewById(R.id.list);

        // Getting adapter by passing xml data ArrayList
        adapter = new PostAdapter(getActivity(), buildDemoPosts());
        list.setAdapter(adapter);

        // Click event for single list row
        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                ((MainActivity) getActivity()).playPost(postList.get(position));
            }
        });

        //todo bring back to the correct layout
        ((TextView) rootView.findViewById(R.id.textView)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((MainActivity) getActivity()).playPost(postList.get(0));
            }
        });


        return rootView;
    }


    private ArrayList<Post> buildDemoPosts(){
        ArrayList<Post> postListGen = new ArrayList<Post>();
        for (int i = 0; i < 5; i++) {
            // creating new HashMap
            VideoPost post = new VideoPost();
            post.setTimeStamp(233);
            File videoFile = CameraHelper.getOutputMediaFile(CameraHelper.MEDIA_TYPE_VIDEO);
            Uri uri =Uri.parse(videoFile.toString());
            post.setVideoURI(uri);
            post.setUserName("Rihanna");

            postListGen.add(post);
        }
        return postListGen;
    }
}


