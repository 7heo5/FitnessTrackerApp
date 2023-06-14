package com.example.fitnesstrackerapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fitnesstrackerapp.R;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    private final String[] mobileValues;

    public ImageAdapter(Context context, String[] mobileValues) {
        this.context = context;
        this.mobileValues = mobileValues;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.mobile, null);

            // set value into textview
            TextView textView = (TextView) gridView
                    .findViewById(R.id.grid_item_label);
            textView.setText(mobileValues[position]);

            // set image based on selected text
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.grid_item_image);

            String mobile = mobileValues[position];

//changes images based on array of value called MOBILE_OS in HomeFragment.java
            if (mobile.equals("Bullying")) {
                imageView.setImageResource(R.mipmap.test_image_afro_samurai_foreground);
            } else if (mobile.equals("Hitting")) {
                imageView.setImageResource(R.mipmap.test_image_afro_samurai_foreground);
            } else if (mobile.equals("Hungry")) {
                imageView.setImageResource(R.mipmap.test_image_afro_samurai_foreground);
            } else if (mobile.equals("Shouting")){
                imageView.setImageResource(R.mipmap.test_image_afro_samurai_foreground);
            } else if (mobile.equals("Torture")){
                imageView.setImageResource(R.mipmap.test_image_afro_samurai_foreground);
            }

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return mobileValues.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}

