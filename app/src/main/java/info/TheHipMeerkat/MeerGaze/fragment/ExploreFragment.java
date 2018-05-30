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
import android.widget.ListView;
import android.widget.TextView;

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


public class ExploreFragment extends Fragment {

    public JSONObject jos = null;
    public JSONArray ja = null;

    public ListView list;
    public TextView text;
    public View view;

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

        ListView list =  (ListView) view.findViewById(R.id.data_list_view);
        TextView text =  (TextView) view.findViewById(R.id.text);
        text.setVisibility(View.INVISIBLE);

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();

        jos = null;

        try{
            File f = new File(getContext().getFilesDir(), "file.ser");
            FileInputStream fi = new FileInputStream(f);
            ObjectInputStream o = new ObjectInputStream(fi);

            String j = null;
            try {
                j = (String) o.readObject();
            }
            catch(ClassNotFoundException c){
                c.printStackTrace();
            }
            try{
                jos = new JSONObject(j);
                ja = jos.getJSONArray("data");
            }
            catch(JSONException e){
                e.printStackTrace();
            }
            //show list
            final ArrayList<ListData> aList = new ArrayList<ListData>();
            for(int i = 0; i < ja.length(); i++){

                ListData ld = new ListData();
                try{
                    ld.title = ja.getJSONObject(i).getString("first");
                    ld.description = ja.getJSONObject(i).getString("second");
                    ld.GPS = ja.getJSONObject(i).getString("third");
                    ld.Time = ja.getJSONObject(i).getString("fourth");
                    ld.Date = ja.getJSONObject(i).getString("fifth");
                } catch (JSONException el) {
                    el.printStackTrace();
                }

                aList.add(ld);
            }
            String[] listItems = new String[aList.size()];

            for(int i = 0; i < aList.size(); i++){
                ListData listD = aList.get(i);
                listItems[i] = listD.title;
            }


            // Show the list view with the each list item an element from listItems
            ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listItems);
            list.setAdapter(adapter);
            //set an OnItemClickListener for each list item
            final Context context = getContext();
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    ListData selected = aList.get(position);

                    Intent detailIntent = new Intent(context, DetailActivity.class);


                    detailIntent.putExtra("title", selected.title);
                    detailIntent.putExtra("description", selected.description);
                    detailIntent.putExtra("GPS", selected.GPS);
                    detailIntent.putExtra("Time", selected.Time);
                    detailIntent.putExtra("Date", selected.Date);

                    startActivity(detailIntent);
                }
            });
        }
        catch(IOException e) {
            if(list != null){
                list.setEnabled(false);
                list.setVisibility(View.INVISIBLE);


            }else{
                //text.setVisibility(View.VISIBLE);
            }

        }
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
                /*the R.id.action_favorite is the ID of our button (defined in strings.xml).
                Change Activity here (if that's what you're intending to do, which is probably is).
                */

                //Intent i = new Intent(this, AddText.class);
                //startActivity(i);

            default:
                super.onOptionsItemSelected(item);
        }
        return true;
    }

}
