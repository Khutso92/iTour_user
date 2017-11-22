package com.example.khutsomatlala.itour_user;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

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
    private List<model> imgList;
    private ListView lv;
    private TextView call;
    private Adapter adapter;
    private ProgressDialog progressDialog;
    public static Boolean stauts = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_places2);

        imgList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.ListViewImage);
        call = (TextView) findViewById(R.id.call);

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

                    //model class require default constructor
                    final model img = snapshot.getValue(model.class);
                    imgList.add(img);

                }

                //Init adapter
                adapter = new Adapter(DisplayPlacesActivity.this, R.layout.list_item, imgList);

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
