package com.medjay.employment.fragments;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.medjay.employment.HomeActivity;
import com.medjay.employment.R;
import com.medjay.employment.models.AccessToken;
import com.medjay.employment.models.TokenManager;
import com.medjay.employment.network.RetrofitBuilder;
import com.medjay.employment.network.WebService;

import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;


public class SignInFragment extends Fragment {

    Call<AccessToken> call;
    WebService webService;
    TokenManager tokenManager;

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        tokenManager=TokenManager.getInstance(getActivity().getSharedPreferences("prefs",MODE_PRIVATE));

        Button _loginButton = (Button) view.findViewById(R.id.login);
        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Login();

            }
        });

        TextView _toSignUp=view.findViewById(R.id.toSignUp);
        _toSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new SignUpFragment()).commit();

            }
        });


        return view;
    }

    private void Login() {

        webService= RetrofitBuilder.getRetrofitInstance().create(WebService.class);
        call=webService.login("aymen@gmail.com","password");

        call.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {

                if (response.code()==200){

                    tokenManager.saveToken(response.body());
                    startActivity(new Intent(getContext(), HomeActivity.class));

                }else if(response.code()== 401){
                    Toasty.warning(getContext(),"Email ou password erroné,"+response.message(), Toast.LENGTH_SHORT).show();

                }else {
                    Toasty.error(getContext(),"Erreur d'enregistrement,"+response.message(),Toast.LENGTH_SHORT).show();
                }

                Log.i("LOG",response.body()+""+response.code()+" "+response.errorBody()+" "+response.message());
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                Toasty.error(getContext(),t.getMessage()+"",Toasty.LENGTH_LONG).show();
                Log.e("LOG",t.getMessage());
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (call!=null){
            call.cancel();
            call=null;
        }
    }

}
