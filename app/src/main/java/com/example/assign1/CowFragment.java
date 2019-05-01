package com.example.assign1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CowFragment extends Fragment {


    public CowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cow, container, false);
        Bundle args = getArguments();
        int cowtagid = args.getInt("cow", 0);
        TextView tv1 = (TextView)view.findViewById((R.id.cowtitle));
        tv1.setText((MainActivity.pageNames[cowtagid-1]));
        return view;
    }


}
