package com.medjay.employment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.karan.churi.PermissionManager.PermissionManager;
import com.medjay.employment.fragments.SignInFragment;
import com.medjay.employment.fragments.SignUpFragment;

public class ConnxionActivity extends AppCompatActivity {

    PermissionManager permissionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connxion);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new SignUpFragment()).commit();

    }

    @Override
    protected void onStart() {
        super.onStart();

        permissionManager=new PermissionManager() {
            @Override
            public void ifCancelledAndCanRequest(Activity activity) {

                super.ifCancelledAndCanRequest(activity);
            }

            @Override
            public void ifCancelledAndCannotRequest(Activity activity) {
                super.ifCancelledAndCannotRequest(activity);
            }
        };

        permissionManager.checkAndRequestPermissions(this);

        if (permissionManager.getStatus().get(0).granted.size()==2){

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, final @NonNull String[] permissions, @NonNull int[] grantResults) {

        permissionManager.checkResult(requestCode, permissions, grantResults);


    }
}
