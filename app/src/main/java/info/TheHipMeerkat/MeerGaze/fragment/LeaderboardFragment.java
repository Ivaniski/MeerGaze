package info.TheHipMeerkat.MeerGaze.fragment;


import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import info.TheHipMeerkat.MeerGaze.R;
import info.TheHipMeerkat.MeerGaze.User;

import static com.android.volley.VolleyLog.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeaderboardFragment extends Fragment {

    DatabaseReference mDatabase;

    private ListView mUserList;
    private TextView mNameView;
    private FirebaseUser mUser;
    FirebaseAuth mAuth;


    public ArrayList<User> mUsernames = new ArrayList<>();

    User newUser;

    public LeaderboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_leaderboard, container, false);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("user");
        mUserList = view.findViewById(R.id.user_list);

        final ArrayAdapter<User> arrayAdapter = new ArrayAdapter<User>(getActivity(), R.layout.fragment_leaderboard, R.id.user_list, mUsernames);
        mUserList.setAdapter(arrayAdapter);

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for(DataSnapshot child : dataSnapshot.getChildren()){
                    User user = child.getValue(User.class);
                    Log.v(TAG, "object =" + user.Name);
                    Log.v(TAG, "object =" + user);
                }

                //mUsernames.add(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

}
