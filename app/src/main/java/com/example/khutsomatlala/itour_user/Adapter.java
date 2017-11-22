package com.example.khutsomatlala.itour_user;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Khutso Matlala on 8/7/2017.
 */

public class Adapter extends ArrayAdapter<model>{

        private Activity context;
        private int resource;
        private List<model> listImage;

    public Adapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<model> objects) {
        super(context, resource, objects);

        this.context =   context;
        this.resource = resource;
        listImage = objects;
    }



    //displaying using the  custom list_item
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater Inflater = context.getLayoutInflater();
        View view =  Inflater.inflate( resource,null);


        ImageView img = view.findViewById(R.id.imgView);
        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvDesc = view.findViewById(R.id.tvDescription);
        TextView tvCategory = view.findViewById(R.id.tvCategory);
        TextView tvCall = view.findViewById(R.id.call);
        final TextView tvMap = view.findViewById(R.id.map);

        //Retriving all the textViews
        tvName.setText(listImage.get(position).getName());
        tvDesc.setText(listImage.get(position).getDescription());
        tvCategory.setText(listImage.get(position).getCategory());

        final String lat = listImage.get(position).getLatitude();
        final  String lon = listImage.get(position).getLongtiude();
        final  String name = listImage.get(position).getName();
        final  String call = listImage.get(position).getCall();

        //Getting the pic
        Glide.with(context).load(listImage.get(position).getUrI()).into(img);

        //calling
        tvCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+call));
                context.startActivity(intent);
            }
        });

        tvMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MapsActivity.class);
                intent.putExtra("lat",lat);
                intent.putExtra("lon",lon);
                intent.putExtra("name",name);

              context.startActivity(intent);

            }
        });
        return  view;
    }
}
