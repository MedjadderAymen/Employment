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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.medjay.employment.HomeActivity;
import com.medjay.employment.R;
import com.medjay.employment.models.AccessToken;
import com.medjay.employment.models.TokenManager;
import com.medjay.employment.network.RetrofitBuilder;
import com.medjay.employment.network.WebService;
import com.medjay.employment.utils.FileUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class SignUpFragment extends Fragment {

    private ConstraintLayout c1,c2,c3;
    private ImageView _avatar;
    private Uri _avatar_imageURI;
    private static String LOG="ActivityCall";
    private TokenManager tokenManager;

    WebService service;
    Call<AccessToken>call;

    EditText _first_name, _last_name, _email, _password,
            _birth_date, _card_number, _address, _phone_number, _postal_code;

    TextView _toSignIn1, _toSignIn2, _toSignIn3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        tokenManager=TokenManager.getInstance(getActivity().getSharedPreferences("prefs",MODE_PRIVATE));

        c1= view.findViewById(R.id.step1);
        c2= view.findViewById(R.id.step2);
        c3= view.findViewById(R.id.step3);

        _first_name= view.findViewById(R.id.first_name);
        _last_name= view.findViewById(R.id.last_name);
        _email= view.findViewById(R.id.email);
        _password= view.findViewById(R.id.password);

        _birth_date= view.findViewById(R.id.birth_date);
        _card_number= view.findViewById(R.id.card_number);
        _address= view.findViewById(R.id.address);
        _phone_number= view.findViewById(R.id.phone_number);
        _postal_code= view.findViewById(R.id.postal_code);

        _toSignIn1= view.findViewById(R.id.toSignIn1);
        _toSignIn2= view.findViewById(R.id.toSignIn2);
        _toSignIn3= view.findViewById(R.id.toSignIn3);

        _toSignIn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goSignIn();
            }
        });

        _toSignIn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goSignIn();
            }
        });

        _toSignIn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goSignIn();
            }
        });

        Button _proceed= view.findViewById(R.id.proceed);
        _proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (_first_name.getText().toString().isEmpty() || _last_name.getText().toString().isEmpty() ||
                        _email.getText().toString().isEmpty() || _password.getText().toString().isEmpty()){

                    Snackbar.make(view,"Remplir les données",Snackbar.LENGTH_SHORT).show();

                }else {

                    c1.setVisibility(View.GONE);
                    c2.setVisibility(View.VISIBLE);

                }

            }
        });

        Button _next= view.findViewById(R.id.next);
        _next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (_birth_date.getText().toString().isEmpty() || _card_number.getText().toString().isEmpty() ||
                        _address.getText().toString().isEmpty() || _phone_number.getText().toString().isEmpty()
                        || _postal_code.getText().toString().isEmpty()){

                    Snackbar.make(view,"Remplir les données",Snackbar.LENGTH_SHORT).show();

                }else {

                    c2.setVisibility(View.GONE);
                    c3.setVisibility(View.VISIBLE);

                }
            }
        });

        Button _finish= view.findViewById(R.id.finish);
        _finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (_avatar_imageURI==null){

                    Snackbar.make(view,"Séléctionner une image",Snackbar.LENGTH_SHORT).show();

                }else {

                    SignUp();

                }
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

    private void goSignIn() {

        Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new SignInFragment()).commit();

    }

    public void getAvatar(){

        Intent gal = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gal, 123);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==123 && resultCode==RESULT_OK){

            _avatar_imageURI=data.getData();
            _avatar.setImageURI(_avatar_imageURI);


        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (call!=null){
            call.cancel();
            call=null;
        }
    }

    public void SignUp() {

        HashMap<String, String> map = new HashMap<>();
        map.put("last_name", _last_name.getText().toString());
        map.put("first_name", _first_name.getText().toString());
        map.put("email", _email.getText().toString());
        map.put("password", _password.getText().toString());
        map.put("birth_date", _birth_date.getText().toString());
        map.put("card_number", _card_number.getText().toString());
        map.put("phone_number", _phone_number.getText().toString());
        map.put("address", _address.getText().toString());
        map.put("postal_code", _postal_code.getText().toString());

        File fileOriginalName= FileUtils.getFile(getActivity(),_avatar_imageURI);
        RequestBody filePart=RequestBody.create(MediaType.parse("multipart/form-data"),fileOriginalName);
        MultipartBody.Part file=MultipartBody.Part.createFormData("avatar",fileOriginalName.getName(),filePart);

        service= RetrofitBuilder.getRetrofitInstance().create(WebService.class);
        call=service.register(map,file);

        call.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {

                if (response.code()==200){

                    tokenManager.saveToken(response.body());
                    startActivity(new Intent(getContext(), HomeActivity.class));

                }else if(response.code()== 401){
                    Toasty.warning(getContext(),"email ou password erroné,"+response.message(), Toast.LENGTH_SHORT).show();

                }else {
                    Toasty.error(getContext(),"Erreur d'enregistrement,"+response.message(),Toast.LENGTH_SHORT).show();
                }

                Log.i(LOG,response.body()+""+response.code()+" "+response.errorBody()+" "+response.message());
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                Toasty.error(getContext(),t.getMessage()+"",Toasty.LENGTH_LONG).show();
                Log.e(LOG,t.getMessage());
            }
        });

    }

}
