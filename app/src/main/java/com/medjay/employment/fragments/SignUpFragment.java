package com.medjay.employment.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.medjay.employment.HomeActivity;
import com.medjay.employment.R;
import com.medjay.employment.models.AccessToken;
import com.medjay.employment.network.RetrofitBuilder;
import com.medjay.employment.network.WebService;
import com.medjay.employment.utils.FileUtils;

import java.io.File;
import java.util.HashMap;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import static android.app.Activity.RESULT_OK;

public class SignUpFragment extends Fragment {

    View view;
    ConstraintLayout c1,c2,c3;
    ImageView _avatar;
    Uri _avatar_imageURI;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_sign_up, container, false);

        c1=view.findViewById(R.id.step1);
        c2=view.findViewById(R.id.step2);
        c3=view.findViewById(R.id.step3);

        Button _proceed=view.findViewById(R.id.proceed);
        _proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                c1.setVisibility(View.GONE);
                c2.setVisibility(View.VISIBLE);

                //get data

            }
        });

        Button _next=view.findViewById(R.id.next);
        _next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                c2.setVisibility(View.GONE);
                c3.setVisibility(View.VISIBLE);

                //get data

            }
        });

        Button _finish=view.findViewById(R.id.finish);
        _finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get data  and register

                //startActivity(new Intent(getActivity(), HomeActivity.class));

                HashMap<String, String> map = new HashMap<>();
                map.put("last_name", "lsli");
                map.put("first_name", "hou");
                map.put("email", "housem@gmail.com");
                map.put("password", "password");
                map.put("birth_date", "2020-02-13");
                map.put("card_number", "7658");
                map.put("phone_number", "6546218213");
                map.put("address", "fi darna");
                map.put("postal_code", "25026");

                File fileOriginalName= FileUtils.getFile(getActivity(),_avatar_imageURI);
                RequestBody filePart=RequestBody.create(MediaType.parse("multipart/form-data"),fileOriginalName);
                MultipartBody.Part file=MultipartBody.Part.createFormData("avatar",fileOriginalName.getName(),filePart);

                WebService service= RetrofitBuilder.getRetrofitInstance().create(WebService.class);
                Call<JsonObject>call=service.register(map,file);

                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        try {

                            JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                            Toasty.success(getContext(),jsonObject.getString("email"),Toasty.LENGTH_LONG).show();
                            Log.d("zaki",jsonObject.getString("message"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Toasty.error(getContext(),t.getMessage()+"",Toasty.LENGTH_LONG).show();
                    }
                });

            }
        });

        _avatar = (ImageView) view.findViewById(R.id.avatar);

        _avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAvatar();
            }
        });


        return view;
    }

    public void getAvatar(){

        Intent gal = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gal, 123);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==123 && resultCode==RESULT_OK){
           /* Uri imageUri = data.getData();*/

            _avatar_imageURI=data.getData();
            _avatar.setImageURI(_avatar_imageURI);


        }
    }


}
