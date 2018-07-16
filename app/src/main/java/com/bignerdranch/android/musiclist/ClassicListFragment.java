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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class ClassicListFragment extends Fragment {

    Realm realm;
    private RecyclerView mClassicRecyclerView;
    private ClassicAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_list, container, false);
        mClassicRecyclerView = view.findViewById(R.id.music_recycler_view);
        mClassicRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //viewRecord();
        Realm.init(getActivity());
        realm = Realm.getDefaultInstance();
        loadJSON();

        return view;
    }

    public void addRecordsToRealmDB(ClassicLab classicLab) {

        try {
            realm.beginTransaction();
            for (Classic cl : classicLab.getResults()) {
                Classic classic = realm.createObject(Classic.class);
                classic.setArtistName(cl.getArtistName());
                classic.setCollectionName(cl.getCollectionName());
                classic.setArtworkUrl100(cl.getArtworkUrl100());
                classic.setTrackPrice(cl.getTrackPrice());

            }
            realm.commitTransaction();
            realm.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


        // classic.setArtistName("ArtistName");
        //classic.setCollectionName("Collection Name");
        // classic.setArtworkUrl100("https://is1-ssl.mzstatic.com/image/thumb/Music122/v4/ef/ca/a4/efcaa44a-517a-98e3-2353-5bff15471ed5/source/100x100bb.jpg");
        // classic.setTrackPrice(99.00f);


    }

    public boolean isInternetAvailable() {
        try {
            ConnectionVerifier connectionVerifier = new ConnectionVerifier(getActivity());
            return connectionVerifier.isOnline();

        } catch (Exception e) {
            return false;
        }
    }

    private void loadJSON() {

        if (isInternetAvailable()) {

            NetworkService.getInstance()
                    .getClassicJSONApi()
                    .getClassics()
                    .enqueue(new Callback<ClassicLab>() {
                        @Override
                        public void onResponse(@NonNull Call<ClassicLab> call, @NonNull Response<ClassicLab> response) {
                            ClassicLab classic = response.body();


                            addRecordsToRealmDB(classic);


                            // Set RecyclerView Adapter
                            mAdapter = new ClassicAdapter(classic.getResults());
                            mClassicRecyclerView.setAdapter(mAdapter);
                        }

                        @Override
                        public void onFailure(@NonNull Call<ClassicLab> call, @NonNull Throwable t) {

                            t.printStackTrace();
                        }
                    });

        } else {

            RealmResults<Classic> results = realm.where(Classic.class).findAll();
           /* Classic classic = null;
            List myList = new ArrayList();

            for(Classic cl : results){
                 //classic.setArtistName(results.get(i).getArtistName());
                 //classic.setCollectionName(results.get(i).getCollectionName());
                myList.add(cl);
            }*/

            Toast.makeText(getActivity(), "No internet connection - offline information is displayed", Toast.LENGTH_LONG).show();

            // Set RecyclerView Adapter
            mAdapter = new ClassicAdapter(results);
            mClassicRecyclerView.setAdapter(mAdapter);

            //for(Classic classic : results){
            // text.append(student.getRoll_no() + " " + student.getName() + "\n");
            // }


        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isResumed()) { // fragment is visible and have created
            // mAdapter.clear();
            // loadJSON();
        }
    }

    public class ClassicHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleTextView;
        private TextView mArtistTextView;
        private TextView mPriceTextView;

        private ImageView mSongImageView;

        private Classic mClassic;


        private ClassicHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_music, parent, false));


            itemView.setOnClickListener(this);


            mTitleTextView = itemView.findViewById(R.id.song_title);
            mArtistTextView = itemView.findViewById(R.id.artist_name);
            mPriceTextView = itemView.findViewById(R.id.track_price);

            mSongImageView = itemView.findViewById(R.id.song_image);


        }


        @Override
        public void onClick(View view) {

            Toast.makeText(getActivity(), mClassic.getArtistName() + " clicked!", Toast.LENGTH_SHORT).show();
            playMusic(mClassic.getPreviewUrl());

        }

        public void playMusic(String url) {
            Intent intent = new Intent();
            intent.setAction(android.content.Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.parse(url), "audio/*");
            startActivity(intent);

        }

        public void bind(Classic Classic) {
            mClassic = Classic;

            mArtistTextView.setText(mClassic.getArtistName());
            mTitleTextView.setText(mClassic.getCollectionName());
            mPriceTextView.setText("£" + mClassic.getTrackPrice().toString());
            Context context = mSongImageView.getContext(); //<----- Add this line


            //change context here
            Picasso.with(context).load(mClassic.getArtworkUrl100())
                    .resize(250, 200)
                    .into(mSongImageView);
        }
    }

    private class ClassicAdapter extends RecyclerView.Adapter<ClassicHolder> {

        private List<Classic> mClassics;

        public ClassicAdapter(List<Classic> list) {
            this.mClassics = list;
        }


        @NonNull
        @Override
        public ClassicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ClassicHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ClassicHolder holder, int position) {

            Classic Classic = mClassics.get(position);
            holder.bind(Classic);


        }

        @Override
        public int getItemCount() {
            return mClassics.size();
        }

        public void clear() {
            final int size = mClassics.size();
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    mClassics.remove(0);
                }

                notifyItemRangeRemoved(0, size);
            }
        }
    }


}
