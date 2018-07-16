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

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RockListFragment extends Fragment {

    private RecyclerView mRockRecyclerView;
    private RockAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_list, container, false);
        mRockRecyclerView = view.findViewById(R.id.music_recycler_view);
        mRockRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        loadJSON();

        return view;
    }

    private void loadJSON() {

        NetworkService.getInstance()
                .getRockJSONApi()
                .getRocks()
                .enqueue(new Callback<RockLab>() {
                    @Override
                    public void onResponse(@NonNull Call<RockLab> call, @NonNull Response<RockLab> response) {
                        RockLab rock = response.body();
                        // Set RecyclerView Adapter


                        mAdapter = new RockAdapter(rock.getResults());
                        mRockRecyclerView.setAdapter(mAdapter);
                        //mAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onFailure(@NonNull Call<RockLab> call, @NonNull Throwable t) {

                        t.printStackTrace();
                    }

                });


    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isResumed()) { // fragment is visible and have created
            //mAdapter.clear();
            //loadJSON();
        }

    }

    public class RockHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleTextView;
        private TextView mArtistTextView;
        private TextView mPriceTextView;

        private ImageView mSongImageView;

        private Rock mRock;


        private RockHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_music, parent, false));


            itemView.setOnClickListener(this);


            mTitleTextView = itemView.findViewById(R.id.song_title);
            mArtistTextView = itemView.findViewById(R.id.artist_name);
            mPriceTextView = itemView.findViewById(R.id.track_price);

            mSongImageView = itemView.findViewById(R.id.song_image);


        }


        @Override
        public void onClick(View view) {

            Toast.makeText(getActivity(), mRock.getArtistName() + " clicked!", Toast.LENGTH_SHORT).show();
            playMusic(mRock.getPreviewUrl());

        }

        public void playMusic(String url) {
            Intent intent = new Intent();
            intent.setAction(android.content.Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.parse(url), "audio/*");
            startActivity(intent);

        }

        public void bind(Rock rock) {
            mRock = rock;

            mArtistTextView.setText(mRock.getArtistName());
            mTitleTextView.setText(mRock.getCollectionName());
            mPriceTextView.setText("£" + mRock.getTrackPrice().toString());
            Context context = mSongImageView.getContext(); //<----- Add this line


            //change context here
            Picasso.with(context).load(mRock.getArtworkUrl100())
                    .resize(250, 200)
                    .into(mSongImageView);
        }
    }

    private class RockAdapter extends RecyclerView.Adapter<RockHolder> {

        private List<Rock> mRocks;


        public RockAdapter(List<Rock> list) {

            this.mRocks = list;
        }


        @NonNull
        @Override
        public RockHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new RockHolder(layoutInflater, parent);
        }


        @Override
        public void onBindViewHolder(@NonNull RockHolder holder, int position) {




          /*  holder.mArtistTextView.setText(mRocks.get(position).getArtistName());
            holder.mTitleTextView.setText(mRocks.get(position).getTrackName());

            Context context = holder.mSongImageView.getContext(); //<----- Add this line


            //change context here
            Picasso.with(context).load(mRocks.get(position)
                    .getArtworkUrl100())
                    .resize(250, 200)
                    .into(holder.mSongImageView);*/

            Rock rock = mRocks.get(position);
            holder.bind(rock);


        }

        @Override
        public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);

        }

        @Override
        public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
            super.onDetachedFromRecyclerView(recyclerView);

        }


        @Override
        public int getItemCount() {
            return mRocks.size();
        }

        public void clear() {
            final int size = mRocks.size();
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    mRocks.remove(0);
                }

                notifyItemRangeRemoved(0, size);
                //notifyDataSetChanged();
            }
        }
    }


}
