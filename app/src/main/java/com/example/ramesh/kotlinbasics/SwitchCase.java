package com.example.ramesh.kotlinbasics;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hp on 9/1/2017.
 */

public class SwitchCase extends android.support.v4.app.Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.switch_case, container, false);
        ((MainActivity)getActivity()).setActionBarTitle("Java vs Kotlin");
        return v;
    }
}
