package com.medjay.employment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;
import com.luseen.luseenbottomnavigation.BottomNavigation.OnBottomNavigationItemClickListener;
import com.medjay.employment.fragments.DemandeFragment;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(getResources().getColor(R.color.white));

        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);

        BottomNavigationItem bottomNavigationItem = new BottomNavigationItem
                ("Demander", ContextCompat.getColor(this, R.color.white), R.drawable.ic_search_black_24dp);
        BottomNavigationItem bottomNavigationItem1 = new BottomNavigationItem
                ("Mes", ContextCompat.getColor(this, R.color.white), R.drawable.ic_event_note_black_24dp);
        BottomNavigationItem bottomNavigationItem2 = new BottomNavigationItem
                ("Découvrir", ContextCompat.getColor(this, R.color.white), R.drawable.ic_location_on_black_24dp);
        BottomNavigationItem bottomNavigationItem3 = new BottomNavigationItem
                ("Moi", ContextCompat.getColor(this, R.color.white), R.drawable.ic_person_black_24dp);

        bottomNavigationView.addTab(bottomNavigationItem);
        bottomNavigationView.addTab(bottomNavigationItem1);
        bottomNavigationView.addTab(bottomNavigationItem2);
        bottomNavigationView.addTab(bottomNavigationItem3);

        bottomNavigationView.isColoredBackground(false);
        bottomNavigationView.setItemActiveColorWithoutColoredBackground(R.color.colorPrimaryDark);
        bottomNavigationView.selectTab(0);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new DemandeFragment()).commit();

        bottomNavigationView.setOnBottomNavigationItemClickListener(new OnBottomNavigationItemClickListener() {
            @Override
            public void onNavigationItemClick(int index) {

                switch (index){

                    case 0:getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new DemandeFragment()).commit();
                        break;

                }

            }
        });
    }
}
