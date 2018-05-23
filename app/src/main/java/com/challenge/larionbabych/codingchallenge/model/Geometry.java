
package com.challenge.larionbabych.codingchallenge.model;

import android.annotation.SuppressLint;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "location",
    "viewport"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Geometry {

    @JsonProperty("location")
    private Location location;
    @JsonProperty("viewport")
    private Viewport viewport;

    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(Location location) {
        this.location = location;
    }

    @JsonProperty("viewport")
    public Viewport getViewport() {
        return viewport;
    }

    @JsonProperty("viewport")
    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "lat",
        "lng"
    })
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Location {

        @JsonProperty("lat")
        private Double lat;
        @JsonProperty("lng")
        private Double lng;

        @JsonProperty("lat")
        public Double getLat() {
            return lat;
        }

        @JsonProperty("lat")
        public void setLat(Double lat) {
            this.lat = lat;
        }

        @JsonProperty("lng")
        public Double getLng() {
            return lng;
        }

        @JsonProperty("lng")
        public void setLng(Double lng) {
            this.lng = lng;
        }

        @SuppressLint("DefaultLocale")
        @Override public String toString() {
            return String.format("%.7f,%.7f", lat, lng);
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "northeast",
        "southwest"
    })
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Viewport {

        @JsonProperty("northeast")
        private Location northeast;
        @JsonProperty("southwest")
        private Location southwest;

        @JsonProperty("northeast")
        public Location getNortheast() {
            return northeast;
        }

        @JsonProperty("northeast")
        public void setNortheast(Location northeast) {
            this.northeast = northeast;
        }

        @JsonProperty("southwest")
        public Location getSouthwest() {
            return southwest;
        }

        @JsonProperty("southwest")
        public void setSouthwest(Location southwest) {
            this.southwest = southwest;
        }

    }
}
