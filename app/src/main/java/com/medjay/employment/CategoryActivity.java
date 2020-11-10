package com.medjay.employment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.medjay.employment.adapters.CategoryUnderAdapter;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        ListView listView =findViewById(R.id.lv);

        ArrayList<String> categories=new ArrayList<>();

        categories.add("Murs & sols");
        categories.add("Plombrie & chauffage");
        categories.add("Mobiliers & décorations");
        categories.add("Cuisine & electroménagers");
        categories.add("Eléctricitié & domotique");
        categories.add("Portes & fenêtres");

        CategoryUnderAdapter adapter =new CategoryUnderAdapter(CategoryActivity.this, R.layout.category_under,categories);

        listView.setAdapter(adapter);

    }
}
