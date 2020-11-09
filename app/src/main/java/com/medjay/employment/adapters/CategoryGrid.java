package com.medjay.employment.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.medjay.employment.R;
import com.medjay.employment.models.Category;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class CategoryGrid extends BaseAdapter {

    private Context mContext;
    private Activity mActivity;
    private List<Category> mListCategory;

    public CategoryGrid(Activity mActivity,Context context,List<Category> mListCategory){
        mContext=context;
        this.mActivity=mActivity;
        this.mListCategory=mListCategory;

    }

    @Override
    public int getCount() {
        return mListCategory.size();
    }

    public Object getItem(int position){
        return null;
    }


    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        View view1 = mActivity.getLayoutInflater().inflate(R.layout.category,null);


            //getting view in row_data
            ImageView logo = view1.findViewById(R.id.logo);
            TextView category = view1.findViewById(R.id.category);

            category.setText(mListCategory.get(i).getName());
            logo.setImageResource(mListCategory.get(i).getLogo());



        return view1;
    }
}
