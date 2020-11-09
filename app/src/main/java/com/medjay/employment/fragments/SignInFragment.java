package com.medjay.employment.fragments;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.medjay.employment.R;
import com.medjay.employment.models.AccessToken;
import com.medjay.employment.network.RetrofitBuilder;
import com.medjay.employment.network.WebService;


public class SignInFragment extends Fragment {

    Call<AccessToken> call;
    WebService webService;

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        webService= RetrofitBuilder.getRetrofitInstance().create(WebService.class);
        call=webService.login("aymen@gmail.com","password");

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        call.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {

                Toasty.success(getActivity(),response.body().getToken(),Toasty.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {

                Toasty.error(getActivity(),t.getMessage()+"hello world",Toasty.LENGTH_LONG).show();

            }
        });
    }
}
