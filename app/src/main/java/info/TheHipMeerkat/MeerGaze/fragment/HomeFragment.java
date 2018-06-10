package info.TheHipMeerkat.MeerGaze.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import info.TheHipMeerkat.MeerGaze.R;
//import info.TheHipMeerkat.MeerGaze.helper.SimpleImageListAdapter;


public class HomeFragment extends Fragment {

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference().child("images");


    private ImageView mImageView;

    public ListView list;
    public TextView text;
    public View view;
    public double patchNum = 1.00;
    public boolean allowRefresh = true;


    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public void onResume() {
        super.onResume();
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.meerkat_image);

        TextView greetingmsg = (TextView) view.findViewById(R.id.textView9);
        TextView scrollingText = (TextView) view.findViewById(R.id.scrollingText);

        //SimpleDateFormat currentTime = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
        //currentTime.setTimeZone(TimeZone.getTimeZone("PST"));
       // Date date = currentTime.parse();


        DateFormat dateFormat = new SimpleDateFormat("EEE MMM d HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));


        String timeStamp = dateFormat.format(Calendar.getInstance(TimeZone.getTimeZone("America/Los_Angeles")).getTime());
                //.format(Calendar.getInstance(TimeZone.getTimeZone("PST")).getTime());

        scrollingText.setText("It is " + timeStamp + " and our meerkat friends think that's a great time go outside and explore!");

        greetingmsg.setText("Welcome to MeergazeV"+patchNum + "!");


        Glide.with(getActivity()).load("https://firebasestorage.googleapis.com/v0/b/meergaze.appspot.com/o/images%2Fmeerkat.png?alt=media&token=29db65ff-ba69-45d0-97e8-9d2f849b4664")
                .into(imageView);

        return view;

    }



//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_home, container, false);
//
//        //items = getActivity().getResources().getStringArray(R.array.test);
//        list = (ListView) getActivity().findViewById(R.id.data_list_view);
//
//        /*list.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(),
//                android.R.layout.simple_list_item_1 , items));*/
//
//        return view;
//    }
//
//    public void loadImageByInternetUrl(){
//
//        //ImageView mImageView =  view.findViewById(R.id.data_list_view);
//    }
//
//    @Override
//    public void onResume(){
//        super.onResume();
//        loadImageByInternetUrl();

//    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_main, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle item selection
//
//        switch (item.getItemId()) {
//            case R.id.action_favorite:
//
//
//            default:
//                super.onOptionsItemSelected(item);
//        }
//        return true;
//    }

}
