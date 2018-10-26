package com.example.ramesh.kotlinbasics;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by hp on 9/1/2017.
 */

public class AndroidPrograms extends Fragment {

    Button activity, fragmen, text_edit, alert_dialog, onClickListener, switchActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.android_programs, container, false);
        ((MainActivity)getActivity()).setActionBarTitle("Android Programs");
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        activity = (Button)view.findViewById(R.id.activity);
        fragmen = (Button)view.findViewById(R.id.fragments);
        text_edit = (Button)view.findViewById(R.id.text_edit);
        alert_dialog = (Button)view.findViewById(R.id.alert_dialog);
        onClickListener = (Button)view.findViewById(R.id.on_click_listener);
        switchActivity = (Button)view.findViewById(R.id.switch_activity);

        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                Activity activity = new Activity();
                transaction.replace(R.id.fragment_container, activity).addToBackStack("").commit();
            }
        });

        fragmen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                Fragments fragments = new Fragments();
                transaction.replace(R.id.fragment_container, fragments).addToBackStack("").commit();
            }
        });

        text_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                TextEdit textEdit = new TextEdit();
                transaction.replace(R.id.fragment_container, textEdit).addToBackStack("").commit();
            }
        });

        alert_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                AlertDialog alertDialog = new AlertDialog();
                transaction.replace(R.id.fragment_container, alertDialog).addToBackStack("").commit();
            }
        });

        onClickListener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                On_Click_Listener on_click_listener = new On_Click_Listener();
                transaction.replace(R.id.fragment_container, on_click_listener).addToBackStack("").commit();
            }
        });

        switchActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                SwitchActivity switchActivity = new SwitchActivity();
                transaction.replace(R.id.fragment_container, switchActivity).addToBackStack("").commit();
            }
        });

    }
}
