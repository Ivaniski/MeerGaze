package info.TheHipMeerkat.MeerGaze.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import info.TheHipMeerkat.MeerGaze.R;
import info.TheHipMeerkat.MeerGaze.helper.RegisterFragment;


public class SocialFragment extends Fragment {
    View view;
    private FirebaseAuth mAuth; //instant of FirebaseAuth
    private FirebaseUser mUser;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private EditText mEmailField;
    private EditText mPasswordField;

    private Button mLgnBtn;
    private Button mRgstrBtn;


    public SocialFragment() {
        // Required empty public constructor
    }

    public static SocialFragment newInstance(String param1, String param2) {
        SocialFragment fragment = new SocialFragment();
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
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_social, container, false);

        mEmailField = (EditText) view.findViewById(R.id.emailField);
        mPasswordField = (EditText) view.findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        mLgnBtn = (Button) view.findViewById(R.id.button);

        mRgstrBtn = (Button) view.findViewById(R.id.register);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                    String userEmail = firebaseUser.getEmail();

                    LeaderboardFragment LeaderboardFragment = new LeaderboardFragment();
                    FragmentManager manager = getFragmentManager();
                    Bundle args = new Bundle();

                    args.putString("email", userEmail.split("@")[0].toLowerCase().trim());
                    LeaderboardFragment.setArguments(args);

                    manager.beginTransaction().replace(((ViewGroup)(getView().getParent())).getId(), LeaderboardFragment, LeaderboardFragment.getTag())
                            .addToBackStack(null).commit();
                }
            }
        };

        mLgnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startSignIn();
            }
        });

        mRgstrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                RegisterFragment registerFragment = new RegisterFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(((ViewGroup)(getView().getParent())).getId(), registerFragment, registerFragment.getTag())
                .addToBackStack(null).commit();
    }
});

        return view;
    }

    public void onStart(){
        super.onStart();
        //updateUI(currentUser); need to create an updateUI method

        mAuth.addAuthStateListener(mAuthListener);
    }

    private void startSignIn(){
        final String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();


        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(SocialFragment.this.getActivity(), "Field(s) are empty.", Toast.LENGTH_LONG).show();
        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task){

                    if(!task.isSuccessful()){
                        Toast.makeText(SocialFragment.this.getActivity(), "Invalid log in", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(SocialFragment.this.getActivity(), "Sign in Success", Toast.LENGTH_LONG).show();

                        LeaderboardFragment LeaderboardFragment = new LeaderboardFragment();
                        FragmentManager manager = getFragmentManager();
                        Bundle args = new Bundle();
                        args.putString("email", email.split("@")[0].toLowerCase().trim());
                        LeaderboardFragment.setArguments(args);

                        manager.beginTransaction().replace(((ViewGroup)(getView().getParent())).getId(), LeaderboardFragment, LeaderboardFragment.getTag())
                                .addToBackStack(null).commit();
                    }

                }
            });
        }
    }
}
