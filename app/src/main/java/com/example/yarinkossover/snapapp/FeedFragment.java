package com.example.yarinkossover.snapapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.yarinkossover.snapapp.adapters.LazyAdapter;

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

    ListView list;
    LazyAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);


        ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();

      /*  XMLParser parser = new XMLParser();
        String xml = parser.getXmlFromUrl(URL); // getting XML from URL
        Document doc = parser.getDomElement(xml); // getting DOM element
*/
        //NodeList nl = doc.getElementsByTagName(KEY_SONG);
        int numberOfPosts = 5 ;
        // looping through all song nodes <song>
        for (int i = 0; i < numberOfPosts; i++) {
            // creating new HashMap
            HashMap<String, String> map = new HashMap<String, String>();
           // Element e = (Element) nl.item(i);
            // adding each child node to HashMap key => value
            map.put(KEY_ID, Integer.toString(i));
            map.put(KEY_TITLE, "Title");
            map.put(KEY_ARTIST, "Artist");
            map.put(KEY_DURATION, "3:00");
            //map.put(KEY_THUMB_URL, parser.getValue(e, KEY_THUMB_URL));

            // adding HashList to ArrayList
            songsList.add(map);
        }

        list = (ListView) rootView.findViewById(R.id.list);

        // Getting adapter by passing xml data ArrayList
        adapter = new LazyAdapter(getActivity(), songsList);
        list.setAdapter(adapter);

        // Click event for single list row
        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

            }
        });

        //todo bring back to the correct layout
        ((TextView) rootView.findViewById(R.id.textView)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((MainActivity) getActivity()).testClick();
            }
        });


        return rootView;
    }
}


