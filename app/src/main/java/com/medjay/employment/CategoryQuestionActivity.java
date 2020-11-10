package com.medjay.employment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.medjay.employment.adapters.CategoryQuestionAdapter;
import com.medjay.employment.adapters.CategoryUnderAdapter;

import java.util.ArrayList;

public class CategoryQuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_question);

        String category=getIntent().getExtras().getString("category");

        ArrayList<String> categories=new ArrayList<>();

        switch (category){
            case "Murs & sols":
            {categories.add("Peiture d'intérieur");
                categories.add("Pose ou réparation de parquet");
                categories.add("Pose de carrelage");
                categories.add("Rénovation de maison ou d'apparetement");
                categories.add("Enduit de mur");
                categories.add("Pose de papier peint");
                categories.add("Démolition");
                categories.add("Pose de lino");
                categories.add("Bouchage de trou ou fissure");
                categories.add("Décollage de papier peint");
                categories.add("Pose de béton ciré");
                categories.add("Pose ou remplacement de moquette");
                categories.add("Assainissement de cave");
                categories.add("Pose de jonc de mer");
                categories.add("Autres");}
                break;
        }



        ListView listView =findViewById(R.id.lv);

        CategoryQuestionAdapter adapter =new CategoryQuestionAdapter(CategoryQuestionActivity.this, R.layout.category_under,categories);

        listView.setAdapter(adapter);

    }
}
