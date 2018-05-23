package com.challenge.larionbabych.codingchallenge.a_main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.challenge.larionbabych.codingchallenge.R;
import com.challenge.larionbabych.codingchallenge.a_details.DetailActivity;
import com.challenge.larionbabych.codingchallenge.a_details.DetailFragment;
import com.challenge.larionbabych.codingchallenge.api.Api;
import com.challenge.larionbabych.codingchallenge.di.AppInjector;
import com.challenge.larionbabych.codingchallenge.model.Geometry;
import com.challenge.larionbabych.codingchallenge.model.Place;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import static android.widget.Toast.makeText;

public class PlacesFragment extends Fragment implements AppInjector.Injectable {

    private PlacesAdapter adapter = new PlacesAdapter();
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private PlacesViewModel viewModel;
    private Location location;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PlacesViewModel.class);
        View rootView = inflater.inflate(R.layout.fragment_places, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.list_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        viewModel.getData().observe(this, placesResponse -> {
            if (placesResponse != null) {
                adapter.replaceData(placesResponse.getResults(), location);
            } else {
                makeText(getActivity(), "error: check connection", Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }

    public PlacesViewModel viewModel() {
        return viewModel;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public static class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.ViewHolder> {

        private List<Place> places = new ArrayList<>();
        private Location location;

        private final View.OnClickListener mOnClickListener = view -> {
            String placeId = (String) view.getTag();
            Context context = view.getContext();
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(DetailFragment.ARG_ITEM_ID, placeId);
            context.startActivity(intent);
        };

        void replaceData(List<Place> places, Location location) {
            this.places = places;
            this.location = location;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_place, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            Place place = places.get(position);
            holder.setItem(place);
            holder.itemView.setOnClickListener(mOnClickListener);
            holder.itemView.setTag(place.getPlaceId());
        }

        private double distance (Geometry.Location location){
            Location locationA = new Location("point A");
            locationA.setLatitude(location.getLat());
            locationA.setLongitude(location.getLng());
            Location locationB = this.location;
            return locationA.distanceTo(locationB) ;
        }

        @Override
        public int getItemCount() {
            return places.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView nameView;
            TextView ratingView;
            TextView distanceView;
            TextView addressView;
            ImageView imageView;

            ViewHolder(View view) {
                super(view);
                nameView = view.findViewById(R.id.name);
                ratingView = view.findViewById(R.id.rating);
                distanceView = view.findViewById(R.id.distance);
                addressView = view.findViewById(R.id.address);
                imageView = view.findViewById(R.id.image);
            }

            void setItem(Place place) {
                Resources resources = itemView.getResources();
                nameView.setText(place.getName());
                ratingView.setText(resources.getString(R.string.item_rating, place.getRating()));
                double distance = distance(place.getGeometry().getLocation());
                distanceView.setText(resources.getString(R.string.item_distance, distance/1000));
                addressView.setText(place.getVicinity());
                DisplayMetrics metrics = resources.getDisplayMetrics();
                int px = 100 * metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT;
                String url;
                if (place.getPhotos() == null || place.getPhotos().size() == 0) {
                    url = place.getIcon();
                } else {
                    url = Api.BASE_URL + "photo?maxwidth=" + px + "&photoreference=" +
                            place.getPhotos().get(0).getPhotoReference() + "&key=" + Api.KEY;
                }
                Picasso.with(itemView.getContext()).load(url).
                        fit().centerCrop().into(imageView);
            }

        }
    }

}
