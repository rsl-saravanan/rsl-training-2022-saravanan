package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;




public class MainActivity extends AppCompatActivity implements DetailAdapter.OnItemClickListener {
    private RecyclerView mRecyclerView;
    private DetailAdapter mAdapter;
    private List<DetailModel> mData;
    private Object Utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mData = addItemsFromJSON();
        mAdapter = new DetailAdapter(this);
        mAdapter.setList(mData);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private List<DetailModel> addItemsFromJSON() {
        final List<DetailModel> list = new ArrayList<>();
        try {

            String jsonDataString = readJSONDataFromFile();
            JSONArray jsonArray = new JSONArray(jsonDataString);

            for (int i = 1; i < jsonArray.length(); ++i) {
                JSONObject itemObj = jsonArray.getJSONObject(i);
                final int bool;
                if(i%3==1)bool=0;
                else bool=1;
                String name = itemObj.getString("name");
                String login = itemObj.getString("login");
                String location = itemObj.getString("location");
                int contributions = itemObj.getInt("contributions");
                int followers = itemObj.getInt("followers");
                String organizations = itemObj.getString("organizations");
                list.add(new DetailModel(name,login,location,contributions,followers,organizations,bool));
                Log.d("data###", location);
            }
          return list;

        } catch (IOException | JSONException e) {
            Log.d("addItemsFromJSON: ", String.valueOf(e));
        }
        return list;
    }

    private String readJSONDataFromFile() throws IOException{
        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try {

            String jsonString = null;
            inputStream = getResources().openRawResource(R.raw.github_users);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8"));

            while ((jsonString = bufferedReader.readLine()) != null) {
                builder.append(jsonString);
            }

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return new String(builder);
    }

    public void onItemClicked(int position) {
        final DetailModel model = mData.get(position);
        Toast.makeText(MainActivity.this, "Followers: " + model.getFollowers() + ", Contributions: " + model.getContributions(), Toast.LENGTH_SHORT).show();
    }
}

class DetailItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView mName;
    private TextView mFollowers;
    private TextView mContributions;
    private DetailAdapter.OnItemClickListener mItemClickListener;

    public DetailItemViewHolder(@NonNull View itemView, DetailAdapter.OnItemClickListener listener) {
        super(itemView);
        mName = itemView.findViewById(R.id.name);
        mFollowers = itemView.findViewById(R.id.followers);
        mContributions = itemView.findViewById(R.id.contributions);

        mItemClickListener = listener;
        itemView.setOnClickListener(this);
    }

    public void bind(@NonNull DetailModel model) {
        mName.setText("Name: "+model.getName());
        mFollowers.setText("Followers: "+String.valueOf(model.getFollowers()));
        mContributions.setText("Contributions: "+String.valueOf(model.getContributions()));
    }

    @Override
    public void onClick(View v) {
        mItemClickListener.onItemClicked(getAdapterPosition());
    }
}

class DetailItemWithImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView mName;
    private TextView mLogin;
    private TextView mLocation;
    private TextView mContributions;
    private TextView mFollowers;
    private TextView mOrganizations;
    private DetailAdapter.OnItemClickListener mItemClickListener;

    public DetailItemWithImageViewHolder(@NonNull View itemView, DetailAdapter.OnItemClickListener listener) {
        super(itemView);
        mName = itemView.findViewById(R.id.name);
        mLogin = itemView.findViewById(R.id.login);
        mLocation = itemView.findViewById(R.id.location);
        mFollowers = itemView.findViewById(R.id.followers);
        mContributions = itemView.findViewById(R.id.contributions);
        mOrganizations = itemView.findViewById(R.id.organizations);
        mItemClickListener = listener;
        itemView.setOnClickListener(this);
    }

    public void bind(@NonNull DetailModel model) {
        mName.setText("Name: "+model.getName());
        mLogin.setText("Login: "+model.getLogin());
        mLocation.setText("Location: "+model.getLocation());
        mFollowers.setText("Followers: "+String.valueOf(model.getFollowers()));
        mContributions.setText("Contributions: "+String.valueOf(model.getContributions()));
        mOrganizations.setText("Organizations: "+model.getOrganizations());
    }

    @Override
    public void onClick(View v) {
        mItemClickListener.onItemClicked(getAdapterPosition());
    }
}


class DetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_WITHOUT_IMAGE = 0;
    private static final int VIEW_TYPE_WITH_IMAGE = 1;

    private List<DetailModel> mDetails = new ArrayList<>();
    private OnItemClickListener mItemClickListener;

    DetailAdapter(@NonNull OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_WITHOUT_IMAGE) {
            final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cell_a, parent, false);
            return new DetailItemViewHolder(view, mItemClickListener);
        } else {
            final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cell_b, parent, false);
            return new DetailItemWithImageViewHolder(view, mItemClickListener);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == VIEW_TYPE_WITHOUT_IMAGE) {
            ((DetailItemViewHolder) holder).bind(mDetails.get(position));
        } else {
            ((DetailItemWithImageViewHolder) holder).bind(mDetails.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mDetails.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mDetails.get(position).getImageRes() == 0) {
            return VIEW_TYPE_WITHOUT_IMAGE;
        }
        return VIEW_TYPE_WITH_IMAGE;
    }

    public void setList(@NonNull List<DetailModel> list) {
        mDetails.clear();
        mDetails.addAll(list);
        notifyDataSetChanged();
    }
    public void updateItem(@NonNull DetailModel model, int position) {
        mDetails.set(position, model);
        notifyItemChanged(position);
    }
    interface OnItemClickListener {
        void onItemClicked(int position);
    }
}