package com.example.ramesh.kotlinbasics;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class KotlinBasics extends Fragment {

    String[] introduction = {"WHAT IS KOTLIN?", "KOTLIN FOR SERVER SIDE", "KOTLIN FOR ANDROID"};
    String[] gettingStarted = {"BASIC SYNTAX", "IDIOMS", "CODING CONVENTIONS"};
    String[] basics = {"BASIC TYPES", "LOOPS & IF CONDITION", "RETURNS AND JUMPS"};
    String[] classes = {"CLASSES", "INTERFACE", "NESTED CLASSES"};
    String[] functions = {"FUNCTIONS", "LAMBDAS", "INLINE FUNCTIONS"};
    String[] others = {"COLLECTIONS", "RANGES", "EQUALITY"};
    ArrayList<String> empty = new ArrayList<>();
    ArrayAdapter<String> adapter_empty;
    ListView list_intro, list_getting_started, list_basics, list_classes, list_functions, list_other;
    Button button_intro, button_getting_started, button_basics, button_classes, button_functions, button_other;

    boolean isIntroVisible, isFunctionsVisible, isOthersVisible;
    boolean isGettingStartedVisible, isBasicsVisible, isClassesVisible;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.kotlin_basics, container, false);
        ((MainActivity)getActivity()).setActionBarTitle("Kotlin Basics");
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        button_intro = (Button)view.findViewById(R.id.introduction);
        button_getting_started = (Button)view.findViewById(R.id.getting_started);
        button_basics = (Button)view.findViewById(R.id.basics);
        button_classes = (Button)view.findViewById(R.id.classes_and_objects);
        button_functions = (Button)view.findViewById(R.id.functions);
        button_other = (Button)view.findViewById(R.id.other);
        list_intro = (ListView)view.findViewById(R.id.list_intro);
        list_getting_started = (ListView)view.findViewById(R.id.list_getting_started);
        list_basics = (ListView)view.findViewById(R.id.list_basics);
        list_classes = (ListView)view.findViewById(R.id.list_classes_and_objects);
        list_functions = (ListView)view.findViewById(R.id.list_functions);
        list_other = (ListView)view.findViewById(R.id.list_other);

        adapter_empty =  new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, empty);
        isIntroVisible=false;
        isGettingStartedVisible=false;
        isBasicsVisible=false;
        isClassesVisible=false;
        isFunctionsVisible=false;
        isOthersVisible=false;

        button_intro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isIntroVisible){
                    isIntroVisible = false;
                    list_intro.setAdapter(adapter_empty);
                } else {
                    isIntroVisible = true;
                    //list_intro.setAdapter(adapter_intro);
                    list_intro.setAdapter(new SetAdapter(introduction));
                    list_intro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if(position==0){
                                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                WhatIsKotlin kotlin = new WhatIsKotlin();
                                fragmentTransaction.replace(R.id.fragment_container, kotlin).addToBackStack("").commit();
                            } else if(position==1){
                                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                KotlinForServerSide kotlin = new KotlinForServerSide();
                                fragmentTransaction.replace(R.id.fragment_container, kotlin).addToBackStack("").commit();
                            } else if(position==2){
                                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                KotlinForAndroid kotlin = new KotlinForAndroid();
                                fragmentTransaction.replace(R.id.fragment_container, kotlin).addToBackStack("").commit();
                            }
                        }
                    });
                }
            }
        });
        button_getting_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isGettingStartedVisible){
                    isGettingStartedVisible = false;
                    list_getting_started.setAdapter(adapter_empty);
                } else {
                    isGettingStartedVisible = true;
                    list_getting_started.setAdapter(new SetAdapter(gettingStarted));
                    list_getting_started.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if(position==0){
                                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                BasicSyntax basicSyntax = new BasicSyntax();
                                fragmentTransaction.replace(R.id.fragment_container, basicSyntax).addToBackStack("").commit();
                            } else if(position==1){
                                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                Idioms idioms = new Idioms();
                                fragmentTransaction.replace(R.id.fragment_container, idioms).addToBackStack("").commit();
                            } else if(position==2){
                                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                CodingConventions codingConventions = new CodingConventions();
                                fragmentTransaction.replace(R.id.fragment_container, codingConventions).addToBackStack("").commit();
                            }
                        }
                    });
                }
            }
        });

        button_basics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBasicsVisible){
                    isBasicsVisible = false;
                    list_basics.setAdapter(adapter_empty);
                }else{
                    isBasicsVisible = true;
                    list_basics.setAdapter(new SetAdapter(basics));
                    list_basics.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if(position==0){
                                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                BasicTypes basicTypes = new BasicTypes();
                                fragmentTransaction.replace(R.id.fragment_container, basicTypes).addToBackStack("").commit();
                            } else if(position==1){
                                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                LoopIf loopIf = new LoopIf();
                                fragmentTransaction.replace(R.id.fragment_container, loopIf).addToBackStack("").commit();
                            } else if(position==2){
                                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                ReturnJump returnJump = new ReturnJump();
                                fragmentTransaction.replace(R.id.fragment_container, returnJump).addToBackStack("").commit();
                            }
                        }
                    });
                }
            }
        });

        button_classes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isClassesVisible){
                    isClassesVisible = false;
                    list_classes.setAdapter(adapter_empty);
                }else{
                    isClassesVisible = true;
                    list_classes.setAdapter(new SetAdapter(classes));
                    list_classes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if(position==0){
                                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                Classes classes = new Classes();
                                fragmentTransaction.replace(R.id.fragment_container, classes).addToBackStack("").commit();
                            } else if(position==1){
                                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                Interfaces interfaces = new Interfaces();
                                fragmentTransaction.replace(R.id.fragment_container, interfaces).addToBackStack("").commit();
                            } else if(position==2){
                                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                NestedClasses nestedClasses = new NestedClasses();
                                fragmentTransaction.replace(R.id.fragment_container, nestedClasses).addToBackStack("").commit();
                            }
                        }
                    });
                }
            }
        });

        button_functions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFunctionsVisible){
                    isFunctionsVisible = false;
                    list_functions.setAdapter(adapter_empty);
                }else{
                    isFunctionsVisible = true;
                    list_functions.setAdapter(new SetAdapter(functions));
                    list_functions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if(position==0){
                                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                Functions functions = new Functions();
                                fragmentTransaction.replace(R.id.fragment_container, functions).addToBackStack("").commit();
                            } else if(position==1){
                                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                Lambdas lambdas = new Lambdas();
                                fragmentTransaction.replace(R.id.fragment_container, lambdas).addToBackStack("").commit();
                            } else if(position==2){
                                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                InlineFunctions inlineFunctions = new InlineFunctions();
                                fragmentTransaction.replace(R.id.fragment_container, inlineFunctions).addToBackStack("").commit();
                            }
                        }
                    });
                }
            }
        });

        button_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOthersVisible){
                    isOthersVisible = false;
                    list_other.setAdapter(adapter_empty);
                }else{
                    isOthersVisible = true;
                    list_other.setAdapter(new SetAdapter(others));
                    list_other.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if(position==0){
                                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                Collections collections = new Collections();
                                fragmentTransaction.replace(R.id.fragment_container, collections).addToBackStack("").commit();
                            } else if(position==1){
                                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                Ranges ranges = new Ranges();
                                fragmentTransaction.replace(R.id.fragment_container, ranges).addToBackStack("").commit();
                            } else if(position==2){
                                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                Equality equality = new Equality();
                                fragmentTransaction.replace(R.id.fragment_container, equality).addToBackStack("").commit();
                            }
                        }
                    });
                }
            }
        });

    }

    public class SetAdapter extends BaseAdapter {

        String[] menu;
        SetAdapter(String[] menu){
            this.menu = menu;
        }

        @Override
        public int getCount() {
            return menu.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflate = getActivity().getLayoutInflater();
            convertView = inflate.inflate(R.layout.submenu_layout, null);
            TextView item = (TextView)convertView.findViewById(R.id.submenu_item);
            item.setText(menu[position]);
            return convertView;
        }
    }

}
