package info.TheHipMeerkat.MeerGaze.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import info.TheHipMeerkat.MeerGaze.R;
import info.TheHipMeerkat.MeerGaze.helper.DetailActivity;
import info.TheHipMeerkat.MeerGaze.helper.ListData;
//import info.TheHipMeerkat.MeerGaze.helper.SimpleImageListAdapter;


public class ExploreFragment extends Fragment {

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();

    private ImageView mImageView;

    public ListView list;
    public TextView text;
    public View view;

    public static String[] eatFoodyImages = {
            "http://i.imgur.com/rFLNqWI.jpg",
            "http://i.imgur.com/C9pBVt7.jpg",
            "http://i.imgur.com/rT5vXE1.jpg",
            "http://i.imgur.com/aIy5R2k.jpg",
            "http://i.imgur.com/MoJs9pT.jpg",
            "http://i.imgur.com/S963yEM.jpg",
            "http://i.imgur.com/rLR2cyc.jpg",
            "http://i.imgur.com/SEPdUIx.jpg",
            "http://i.imgur.com/aC9OjaM.jpg",
            "http://i.imgur.com/76Jfv9b.jpg",
            "http://i.imgur.com/fUX7EIB.jpg",
            "http://i.imgur.com/syELajx.jpg",
            "http://i.imgur.com/COzBnru.jpg",
            "http://i.imgur.com/Z3QjilA.jpg",
    };

    public ExploreFragment() {
        // Required empty public constructor
    }

    public static ExploreFragment newInstance(String param1, String param2) {
        ExploreFragment fragment = new ExploreFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        //ya
        ImageView mImageView =  view.findViewById(R.id.data_list_view);
        ImageView mImageView2 =  view.findViewById(R.id.data_list_view);
        TextView text =  (TextView) view.findViewById(R.id.text);
        //text.setVisibility(View.INVISIBLE);

        /*listView listView = (listview) findViewById(R.id.listview_example);

        listView.setAdapter(
                new SimpleImageListAdapter(
                        getActivity(), eatFoodyImages
                )
        );*/

        String internetUrl = "https://media.istockphoto.com/photos/green-natural-beech-tree-forest-illuminated-by-sunbeams-through-fog-picture-id540390024";

        Glide.with(getActivity())
                .load("http://via.placeholder.com/300.png")
                .into(mImageView);

        Glide.with(getActivity())
                .load("http://via.placeholder.com/300.png")
                .into(mImageView2);

        return view;
    }

    public void loadImageByInternetUrl(){

        //ImageView mImageView =  view.findViewById(R.id.data_list_view);
    }

    @Override
    public void onResume(){
        super.onResume();
        loadImageByInternetUrl();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection

        switch (item.getItemId()) {
            case R.id.action_favorite:


            default:
                super.onOptionsItemSelected(item);
        }
        return true;
    }

}
