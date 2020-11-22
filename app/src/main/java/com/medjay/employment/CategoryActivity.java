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

        String Demande=getIntent().getExtras().getString("Demande");

        assert Demande != null;
        switch (Demande){

            case "Maison":
                categories.add("Murs & sols");
                categories.add("Plombrie & chauffage");
                categories.add("Mobiliers & décorations");
                categories.add("Cuisine & électroménagers");
                categories.add("Eléctricitié & domotique");
                categories.add("Portes & fenêtres");
                break;

            case "Transport et démenagement":
                categories.add("Déménagement");
                categories.add("Transport");
                break;

            case "Jardins et extérieur":
                categories.add("Entretien de jardins & terrasses");
                categories.add("Mobiliers & abris de jardins");
                categories.add("Toiture & façades");
                categories.add("Clotures & portails");
                categories.add("Terrasses & balcons");
                categories.add("Piscines & jacuzzis");
                break;

        }

        CategoryUnderAdapter adapter =new CategoryUnderAdapter(CategoryActivity.this, R.layout.category_under,categories);

        listView.setAdapter(adapter);

    }
}
