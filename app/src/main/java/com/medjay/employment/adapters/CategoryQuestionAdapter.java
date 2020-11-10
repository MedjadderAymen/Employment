package com.medjay.employment.adapters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.medjay.employment.CategoryQuestionActivity;
import com.medjay.employment.PresServActivity;
import com.medjay.employment.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CategoryQuestionAdapter extends ArrayAdapter<String> {


    Activity activity;
    int item_res;
    List<String> list;


    public CategoryQuestionAdapter (@NonNull Activity context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);

        activity=context;
        item_res=resource;
        list=objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View layout=convertView;
        if (convertView==null){
            LayoutInflater layoutInflater = (LayoutInflater) activity.getLayoutInflater();
            layout=layoutInflater.inflate(item_res,parent,false);
        }

        TextView _category = layout.findViewById(R.id.category);
        _category.setText(list.get(position));

        ImageView _send= layout.findViewById(R.id.send);
        _send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle=new Bundle();
                bundle.putString("category",list.get(position));
                activity.startActivity(new Intent(activity, PresServActivity.class).putExtras(bundle));

            }
        });

        return layout;

    }

}
