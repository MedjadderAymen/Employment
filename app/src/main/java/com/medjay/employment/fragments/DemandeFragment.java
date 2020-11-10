package com.medjay.employment.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import es.dmoral.toasty.Toasty;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.medjay.employment.CategoryActivity;
import com.medjay.employment.R;
import com.medjay.employment.adapters.CategoryGrid;
import com.medjay.employment.models.Category;

import java.util.ArrayList;
import java.util.List;

public class DemandeFragment extends Fragment {

    GridView gridView;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_demande, container, false);

        gridView=view.findViewById(R.id.grid_category);

        final List<Category> mCatregories = new ArrayList<>();

        mCatregories.add(new Category(R.drawable.logo,"Maison"));

        gridView.setAdapter(new CategoryGrid(getActivity(),getContext(),mCatregories));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                getActivity().startActivity(new Intent(getActivity(), CategoryActivity.class));

            }
        });

        return view;
    }

}
