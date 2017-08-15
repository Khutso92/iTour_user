package com.example.khutsomatlala.itour_user;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Khutso Matlala on 8/7/2017.
 */

public class ImageListAdapter extends ArrayAdapter<ImageUpload>{

        private Activity context;
        private int resource;
        private List<ImageUpload> listImage;

    public ImageListAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<ImageUpload> objects) {
        super(context, resource, objects);

        this.context =   context;
        this.resource = resource;
        listImage = objects;
    }



    //displaying using the  custom image_item
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater Inflater = context.getLayoutInflater();
        View view =  Inflater.inflate( resource,null);


        ImageView img = view.findViewById(R.id.imgView);
        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvDesc = view.findViewById(R.id.tvDescription);
        TextView tvCategory = view.findViewById(R.id.tvCategory);

        //Retriving all the textViews
        tvName.setText(listImage.get(position).getName());
        tvDesc.setText(listImage.get(position).getDescription());
        tvCategory.setText(listImage.get(position).getCategory());

        //Getting the pic
        Glide.with(context).load(listImage.get(position).getUrI()).into(img);

        return  view;
    }
}
