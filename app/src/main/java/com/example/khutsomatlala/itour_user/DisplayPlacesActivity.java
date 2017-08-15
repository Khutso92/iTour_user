package com.example.khutsomatlala.itour_user;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
//More like the  imageListActivity

public class DisplayPlacesActivity extends AppCompatActivity {

    public static final String FB_DATABASE_PATH = "images";
    private DatabaseReference mDatabaseRef;
    private List<ImageUpload> imgList;
    private ListView lv;
    private ImageListAdapter adapter;
    private ProgressDialog progressDialog;
    public static Boolean  stauts=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_places2);

        imgList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.ListViewImage);

        //Show progress dialog during list image loading
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait tour list loading ...");
        progressDialog.show();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH);



        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();

                //Fetch image data from firebase database
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    //ImageUpload class require default constructor
                    final ImageUpload img = snapshot.getValue(ImageUpload.class);
                    imgList.add(img);

                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

                            //Dialog

                            AlertDialog.Builder dialog = new AlertDialog.Builder(DisplayPlacesActivity.this);
                            //dialog.setTitle("");

                            dialog.setPositiveButton("view on map ", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    //Action for "Viewing the place on the map".

                                        Intent intent = new Intent(DisplayPlacesActivity.this, MapsActivity.class);

                                        ImageUpload imageUpload = (ImageUpload) lv.getItemAtPosition(i);
                                        intent.putExtra("selected_item", imageUpload);
                                         startActivity(intent);
                                     stauts =true;

                                }
                            });
                            dialog.show();
                        }
                    });
                }

                //Init adapter
                adapter = new ImageListAdapter(DisplayPlacesActivity.this, R.layout.image_item, imgList);

                //Set adapter for listview
                lv.setAdapter(adapter);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });


    }
}
