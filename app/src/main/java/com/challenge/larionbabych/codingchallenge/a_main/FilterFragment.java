package com.challenge.larionbabych.codingchallenge.a_main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.challenge.larionbabych.codingchallenge.R;

public class FilterFragment extends Fragment {

    private View.OnClickListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof View.OnClickListener){
            listener = (View.OnClickListener)context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_filter, container, false);
        if(listener != null) {
            rootView.findViewById(R.id.rating).setOnClickListener(listener);
            rootView.findViewById(R.id.distance).setOnClickListener(listener);
        }
        return rootView;
    }

}
