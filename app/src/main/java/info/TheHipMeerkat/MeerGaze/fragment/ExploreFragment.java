package info.TheHipMeerkat.MeerGaze.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import info.TheHipMeerkat.MeerGaze.Movie;
import info.TheHipMeerkat.MeerGaze.app.MyApplication;
import info.TheHipMeerkat.MeerGaze.R;
import info.TheHipMeerkat.MeerGaze.MapFragment;


public class ExploreFragment extends Fragment {

    private static final String TAG = ExploreFragment.class.getSimpleName();

    // url to fetch shopping items
    private static final String URL = "https://firebasestorage.googleapis.com/v0/b/meergaze.appspot.com/o/Loc_Database.json?alt=media&token=090be587-a0b8-43f2-b586-73d69ac843b5";

    private RecyclerView recyclerView;
    private List<Movie> itemsList;
    private StoreAdapter mAdapter;


    public ExploreFragment() {
        // Required empty public constructor
    }

    public ExploreFragment newInstance(String param1, String param2) {
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        itemsList = new ArrayList<>();
        mAdapter = new StoreAdapter(getActivity(), itemsList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( 2, vertical);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(8), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);

        fetchStoreItems();

        Log.v(TAG, "recycle view = " + recyclerView);
        Log.v(TAG, "itemsList = " + itemsList);
        Log.v(TAG, "mAdapter = " + mAdapter);


        return view;
    }

    /**
     * fetching shopping item by making http call
     */
    private void fetchStoreItems() {
        JsonArrayRequest request = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response == null) {
                            Toast.makeText(getActivity(), "Couldn't fetch the store items! Pleas try again.", Toast.LENGTH_LONG).show();
                            return;
                        }

                        List<Movie> items = new Gson().fromJson(response.toString(), new TypeToken<List<Movie>>() {
                        }.getType());

                        itemsList.clear();
                        itemsList.addAll(items);

                        // refreshing recycler view
                        mAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // error in getting json
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getActivity(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        MyApplication.getInstance().addToRequestQueue(request);
    }

    // grid spacing
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    /**
     * RecyclerView adapter class to render items
     * This class can go into another separate class, but for simplicity
     */
    class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.MyViewHolder> {
        private Context context;
        private List<Movie> movieList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView name, price;
            public ImageView thumbnail;

            public MyViewHolder(View view) {
                super(view);
                name = view.findViewById(R.id.title);
                price = view.findViewById(R.id.price);
                thumbnail = view.findViewById(R.id.thumbnail);
            }
        }


        public StoreAdapter(Context context, List<Movie> movieList) {
            this.context = context;
            this.movieList = movieList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.store_item_row, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            final Movie movie = movieList.get(position);
            holder.name.setText(movie.getTitle());
            holder.price.setText(movie.getPrice());


            Glide.with(context)
                    .load(movie.getImage())
                    .into(holder.thumbnail);


            holder.thumbnail.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick (View view){

                    Toast.makeText(view.getContext(), "Recycle Click" + " " + (position + 1), Toast.LENGTH_SHORT).show();

                    long temp = (long) position;
                    Intent intent = new Intent(getActivity(), MapFragment.class);
                    intent.putExtra("position", temp);

                    startActivity(intent);


                }
            });

            holder.name.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View view) {

                    Toast.makeText(view.getContext(), "Recycle Click" + " " + (position + 1), Toast.LENGTH_SHORT).show();

                }
            });

            holder.price.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View view) {

                    Toast.makeText(view.getContext(), "Recycle Click" + " " + (position + 1), Toast.LENGTH_SHORT).show();

                }
            });





//            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    Toast.makeText(v.getContext(), "Recycle Click" + position, Toast.LENGTH_SHORT).show();
//                    return true;
//                }
//            });


        }


        @Override
        public int getItemCount() {
            return movieList.size();
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

            default:
                super.onOptionsItemSelected(item);
        }
        return true;
    }
}
