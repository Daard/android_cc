package com.challenge.larionbabych.codingchallenge.a_details;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.challenge.larionbabych.codingchallenge.R;
import com.challenge.larionbabych.codingchallenge.di.AppInjector;
import com.challenge.larionbabych.codingchallenge.model.Place;
import javax.inject.Inject;
import static android.widget.Toast.makeText;

public class DetailFragment extends Fragment implements AppInjector.Injectable {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    public static final String ARG_ITEM_ID = "item_id";
    private DetailViewModel viewModel;
    private String placeId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            placeId = getArguments().getString(ARG_ITEM_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel.class);
        View rootView = inflater.inflate(R.layout.place_detail, container, false);
        viewModel.requestById(placeId);
        viewModel.getData().observe(this, placeResponse -> {
            if (placeResponse != null) {
                Place place = placeResponse.getResult();
                Activity activity = this.getActivity();
                CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);
                if (appBarLayout != null) {
                    appBarLayout.setTitle(placeResponse.getResult().getName());
                }
                ((TextView) rootView.findViewById(R.id.place_detail)).setText(place.getScope());
            } else {
                makeText(getActivity(), "error: check connection", Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }

}
