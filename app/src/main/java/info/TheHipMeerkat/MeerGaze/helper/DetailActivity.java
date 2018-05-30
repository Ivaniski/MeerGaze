package info.TheHipMeerkat.MeerGaze.helper;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONObject;

import info.TheHipMeerkat.MeerGaze.R;

public class DetailActivity extends Fragment {

    public JSONObject jo = null;
    public JSONArray ja = null;

    public DetailActivity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onResume(){
        super.onResume();

        Intent i = getActivity().getIntent();
        String title = i.getStringExtra("title");
        String description = i.getStringExtra("description");
        String GPS = i.getStringExtra("GPS");
        String Time = i.getStringExtra("Time");
        String Date = i.getStringExtra("Date");
    }

}
