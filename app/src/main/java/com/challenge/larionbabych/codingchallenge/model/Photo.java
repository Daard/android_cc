
package com.challenge.larionbabych.codingchallenge.model;

import java.util.HashMap;
import java.util.List;
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
    "height",
    "html_attributions",
    "photo_reference",
    "width"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Photo {

    @JsonProperty("height")
    private Integer height;
    @JsonProperty("html_attributions")
    private List<String> htmlAttributions = null;
    @JsonProperty("photo_reference")
    private String photoReference;
    @JsonProperty("width")
    private Integer width;

    @JsonProperty("height")
    public Integer getHeight() {
        return height;
    }

    @JsonProperty("height")
    public void setHeight(Integer height) {
        this.height = height;
    }

    @JsonProperty("html_attributions")
    public List<String> getHtmlAttributions() {
        return htmlAttributions;
    }

    @JsonProperty("html_attributions")
    public void setHtmlAttributions(List<String> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    @JsonProperty("photo_reference")
    public String getPhotoReference() {
        return photoReference;
    }

    @JsonProperty("photo_reference")
    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }

    @JsonProperty("width")
    public Integer getWidth() {
        return width;
    }

    @JsonProperty("width")
    public void setWidth(Integer width) {
        this.width = width;
    }

}
