package com.example.ramesh.kotlinbasics;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by hp on 8/31/2017.
 */

public class VideoTutorials extends Fragment{

    ArrayList<HashMap<String, String>> arrayList;
    ListView list_videos;
    ProgressDialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.video_tutorials, container, false);
        ((MainActivity)getActivity()).setActionBarTitle("Video Tutorials");
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE
                }, 100);
            }
            return;
        }

        list_videos = (ListView)view.findViewById(R.id.list_videos);
        dialog = new ProgressDialog(getActivity(), R.style.AppCompatAlertDialogStyle);
        dialog.setMessage("Please Wait");
        dialog.show();
        new RetrievingData().execute();
    }

    public class RetrievingData extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            retrieveData();
            return null;
        }
    }

    private void setAdapter(ArrayList arrayList){
        this.arrayList = arrayList;
        list_videos.setAdapter(new SetAdapter(arrayList));
    }

    public class SetAdapter extends BaseAdapter {

        ArrayList<HashMap<String, String>> list;

        SetAdapter(ArrayList<HashMap<String, String>> arrayList){
            list = arrayList;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView( int position, View convertView, ViewGroup parent) {
            LayoutInflater inflate = getActivity().getLayoutInflater();
            convertView = inflate.inflate(R.layout.video_item, null);
            TextView name = (TextView) convertView.findViewById(R.id.title_video);
            name.setText(list.get(position).get("Name"));
            ImageView image = (ImageView)convertView.findViewById(R.id.image_video);
            downloadImage(image, position);
            return convertView;
        }
    }

    private void downloadImage(final ImageView image, int position){
        StorageReference storage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://kotlinbasics.appspot.com/kotlin_videos");
        StorageReference myStorage = storage.child(arrayList.get(position).get("Image"));
        if(arrayList.get(position).get("Image").equals("empty")){
            image.setImageResource(R.drawable.kotlin_image);
        }
        else {
            myStorage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Picasso.with(getActivity()).load(uri).error(R.drawable.kotlin_image).into(image);
                    dialog.dismiss();
                }
            });
        }
    }

    private void retrieveData(){
        final DatabaseReference databaseReference_v= FirebaseDatabase.getInstance().getReferenceFromUrl("https://kotlinbasics.firebaseio.com/Videos");
        databaseReference_v.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                arrayList = new ArrayList<>();
                for(DataSnapshot postSnapshot: snapshot.getChildren()) {
                    String videoName = postSnapshot.child("Name").getValue(String.class);
                    String videoLink = postSnapshot.child("Path").getValue(String.class);
                    String videoImage = postSnapshot.child("Image").getValue(String.class);

                    HashMap<String, String> map = new HashMap<>();
                    map.put("Name", videoName);
                    map.put("Path", videoLink);
                    map.put("Image", videoImage);
                    arrayList.add(map);
                }
                setAdapter(arrayList);
            }
            @Override
            public void onCancelled(DatabaseError firebaseError) {
                //String message = "Server error. Refresh page";
                //Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            }
        });

        list_videos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if(isOnline()) {
                    Intent i = new Intent(getActivity(), PlayingVideo.class);
                    i.putExtra("name_video", arrayList.get(position).get("Name"));
                    i.putExtra("link_video", arrayList.get(position).get("Path"));
                    startActivity(i);
                }else{
                    Toast.makeText(getActivity(), "Check your Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
