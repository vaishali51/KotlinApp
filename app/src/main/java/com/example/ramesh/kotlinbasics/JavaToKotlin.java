package com.example.ramesh.kotlinbasics;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.lang.*;
import java.lang.Exception;

/**
 * Created by hp on 9/1/2017.
 */

public class JavaToKotlin extends Fragment{

    Button data_types, for_loop, switch_case, exception, constructor, interfac;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.java_to_kotlin, container, false);
        ((MainActivity)getActivity()).setActionBarTitle("Java to Kotlin");
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        data_types = (Button)view.findViewById(R.id.data_types);
        for_loop = (Button)view.findViewById(R.id.for_loop);
        switch_case = (Button)view.findViewById(R.id.switch_case);
        exception = (Button)view.findViewById(R.id.exception);
        constructor = (Button)view.findViewById(R.id.constructor);
        interfac = (Button)view.findViewById(R.id.interfac);

        data_types.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                DataTypes dataTypes = new DataTypes();
                transaction.replace(R.id.fragment_container, dataTypes).addToBackStack("").commit();
            }
        });

        for_loop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                ForLoop forLoop = new ForLoop();
                transaction.replace(R.id.fragment_container, forLoop).addToBackStack("").commit();
            }
        });

        switch_case.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                SwitchCase switchCase = new SwitchCase();
                transaction.replace(R.id.fragment_container, switchCase).addToBackStack("").commit();
            }
        });

        exception.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                Exceptio exception = new Exceptio();
                transaction.replace(R.id.fragment_container, exception).addToBackStack("").commit();
            }
        });

        constructor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                Constructor constructor = new Constructor();
                transaction.replace(R.id.fragment_container, constructor).addToBackStack("").commit();
            }
        });

        interfac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                Interfac interfac = new Interfac();
                transaction.replace(R.id.fragment_container, interfac).addToBackStack("").commit();
            }
        });
    }
}
