package info.TheHipMeerkat.MeerGaze;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UserListAdapter extends ArrayAdapter<User> {

    private Activity activity;
    private ArrayList<User> mUsers;
    private static LayoutInflater inflater = null;


    public UserListAdapter(Context context, int resource, int position, ArrayList<User> mUsers){
        super(context, resource, position, mUsers);
        try {
            this.activity = activity;
            this.mUsers = mUsers;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e) {

        }
    }

    public int getCount() {
        return mUsers.size();
    }

    /*public Product getItem(Product position) {
        return position;
    }*/

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView display_name;
        public TextView display_number;

    }

}
