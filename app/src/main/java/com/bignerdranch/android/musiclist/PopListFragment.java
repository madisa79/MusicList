package com.bignerdranch.android.musiclist;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopListFragment extends Fragment {

    private RecyclerView mPopRecyclerView;
    private PopAdapter mAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_list, container, false);

        mPopRecyclerView = view.findViewById(R.id.music_recycler_view);
        mPopRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        loadJSON();
        return view;
    }

    private void loadJSON() {

        NetworkService.getInstance()
                .getPopJSONApi()
                .getPops()
                .enqueue(new Callback<PopLab>() {
                    @Override
                    public void onResponse(@NonNull Call<PopLab> call, @NonNull Response<PopLab> response) {
                        PopLab pop = response.body();
                        // Set RecyclerView Adapter
                        mAdapter = new PopAdapter(pop.getResults());
                        mPopRecyclerView.setAdapter(mAdapter);

                    }

                    @Override
                    public void onFailure(@NonNull Call<PopLab> call, @NonNull Throwable t) {

                        t.printStackTrace();
                    }
                });


    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isResumed()) { // fragment is visible and have created

            // mAdapter.clear();
            //loadJSON();
            //mAdapter.notifyDataSetChanged();
        }
    }

    public class PopHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleTextView;
        private TextView mArtistTextView;
        private TextView mPriceTextView;

        private ImageView mSongImageView;

        private Pop mPop;


        private PopHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_music, parent, false));


            itemView.setOnClickListener(this);


            mTitleTextView = itemView.findViewById(R.id.song_title);
            mArtistTextView = itemView.findViewById(R.id.artist_name);
            mPriceTextView = itemView.findViewById(R.id.track_price);

            mSongImageView = itemView.findViewById(R.id.song_image);


        }


        @Override
        public void onClick(View view) {

            Toast.makeText(getActivity(), mPop.getArtistName() + " clicked!", Toast.LENGTH_SHORT).show();
            playMusic(mPop.getPreviewUrl());

        }

        public void playMusic(String url) {
            Intent intent = new Intent();
            intent.setAction(android.content.Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.parse(url), "audio/*");
            startActivity(intent);

        }

        public void bind(Pop pop) {
            mPop = pop;

            mArtistTextView.setText(mPop.getArtistName());
            mTitleTextView.setText(mPop.getCollectionName());
            mPriceTextView.setText("£" + mPop.getTrackPrice().toString());
            Context context = mSongImageView.getContext(); //<----- Add this line


            //change context here
            Picasso.with(context).load(mPop.getArtworkUrl100())
                    .resize(250, 200)
                    .into(mSongImageView);
        }
    }

    private class PopAdapter extends RecyclerView.Adapter<PopHolder> {

        private List<Pop> mPops;

        public PopAdapter(List<Pop> list) {
            this.mPops = list;
        }


        @NonNull
        @Override
        public PopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new PopHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull PopHolder holder, int position) {




          /*  holder.mArtistTextView.setText(mPops.get(position).getArtistName());
            holder.mTitleTextView.setText(mPops.get(position).getTrackName());

            Context context = holder.mSongImageView.getContext(); //<----- Add this line


            //change context here
            Picasso.with(context).load(mPops.get(position)
                    .getArtworkUrl100())
                    .resize(250, 200)
                    .into(holder.mSongImageView);*/

            Pop pop = mPops.get(position);
            holder.bind(pop);


        }

        @Override
        public int getItemCount() {
            return mPops.size();
        }

        public void clear() {
            final int size = mPops.size();
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    mPops.remove(0);
                }

                notifyItemRangeRemoved(0, size);
            }
        }
    }


}
