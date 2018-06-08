package info.TheHipMeerkat.MeerGaze.helper;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.renderscript.Sampler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import info.TheHipMeerkat.MeerGaze.R;
import info.TheHipMeerkat.MeerGaze.User;
import info.TheHipMeerkat.MeerGaze.fragment.SocialFragment;

//import info.androidhive.bottomnavigation.R;


public class RegisterFragment extends Fragment implements View.OnClickListener {

    private EditText mEmailField;
    private EditText mPasswordField;
    private EditText mPasswordField2;
    private EditText mNameField;
    private Button mRgstrBtn;
    private RegisterFragment fragment;

    private FirebaseAuth firebaseAuth;

    private DatabaseReference mDatabase;

    //Map<String, User> users = new HashMap<>();

    public RegisterFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_register, container, false);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        mEmailField = (EditText) view.findViewById(R.id.RemailField);
        mPasswordField = (EditText) view.findViewById(R.id.Rpassword);
        mPasswordField2 = (EditText) view.findViewById(R.id.Rpassword2);
        mRgstrBtn = (Button) view.findViewById(R.id.Rregister);
        mNameField = (EditText) view.findViewById(R.id.nameField);

        mRgstrBtn.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        return view;
    }

    @Override
    public void onClick(View view){
        if(view == mRgstrBtn){
            registerUser();
        }
    }

    public void onBackPressed() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.remove(fragment);
        trans.commit();
        manager.popBackStack();
        // Do extra stuff here
    }

    public void registerUser(){
        final String email = mEmailField.getText().toString().trim().toLowerCase();
        final String name = mNameField.getText().toString().trim();
        String password = mPasswordField.getText().toString().trim();
        String confirm = mPasswordField2.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(RegisterFragment.this.getActivity(), "Please enter your email", Toast.LENGTH_SHORT).show();

        }
        if(TextUtils.isEmpty(password) || TextUtils.isEmpty(confirm)){
                    Toast.makeText(RegisterFragment.this.getActivity(), "Please enter a " +
                        "password in both fields", Toast.LENGTH_SHORT).show();
                return;
        }
        if (!password.equals(confirm)){
            Toast.makeText(RegisterFragment.this.getActivity(), "Please enter your " +
                    "password identically in both fields", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegisterFragment.this.getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterFragment.this.getActivity(), "Registered successfully. Please Login."
                                    , Toast.LENGTH_SHORT).show();

                            final DatabaseReference numUsersChild = FirebaseDatabase.getInstance().getReference().child("user");

                            numUsersChild
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    User temp = new User(email, name, 1000);

                                    String userId = email.split("@")[0];

                                    numUsersChild.child(userId).setValue(temp);

                                    getFragmentManager().popBackStackImmediate();
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                        }else{
                            Toast.makeText(RegisterFragment.this.getActivity(), "Email already in use" +
                                    "", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
