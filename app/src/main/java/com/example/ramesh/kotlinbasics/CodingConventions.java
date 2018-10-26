package com.example.ramesh.kotlinbasics;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by hp on 9/2/2017.
 */

public class CodingConventions extends Fragment {
    WebView webView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.coding_conventions, container, false);
        ((MainActivity)getActivity()).setActionBarTitle("Coding Conventions");
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        webView = (WebView)view.findViewById(R.id.webView);
        try {
            webView.loadUrl("file:///android_asset/coding_convention_kotlin.html");
            webView.getSettings().setJavaScriptEnabled(true);
        }catch (Exception e){
            Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
