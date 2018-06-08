package info.TheHipMeerkat.MeerGaze;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class UserListAdapter extends ArrayAdapter<User> {

    Context c;
    LayoutInflater inflater;


    private ArrayList<User> mUsers = new ArrayList<>();
    //private static LayoutInflater inflater = null;


    public UserListAdapter(Activity context, int position, ArrayList<User> mUsers){
        super(context, R.layout.row_listview, mUsers);
        try {
            this.c = context;
            this.mUsers = mUsers;

            //inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e) {
        }
    }

    public int getCount() {
        return mUsers.size();
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView display_name;
        public TextView display_number;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        if(convertView==null){
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_listview, null);
        }

        //initlization
        TextView nameTv = (TextView) convertView.findViewById(R.id.user_tv);
        TextView pointTv = (TextView) convertView.findViewById(R.id.point_tv);

        //set texts
        User current = mUsers.get(position);
        nameTv.setText(current.Name);
        pointTv.setText(current.Points);

        return convertView;

        /*View vi = convertView;
        final ViewHolder holder;
        try{
            if(convertView == null){
                vi = inflater.inflate(R.layout.fragment_leaderboard, null);
                holder = new ViewHolder();

                holder.display_name = (TextView) vi.findViewById(R.id.user_list);
                holder.display_number = (TextView) vi.findViewById(R.id.points_list);

                vi.setTag(holder);
            }else{
                holder = (ViewHolder) vi.getTag();
            }
        }catch(Exception e){

        }
        return vi;*/
    }

}
