
package com.challenge.larionbabych.codingchallenge.model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "open_now",
    "weekday_text"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpeningHours {

    @JsonProperty("open_now")
    private Boolean openNow;
    @JsonProperty("weekday_text")
    private List<Object> weekdayText = null;

    @JsonProperty("open_now")
    public Boolean getOpenNow() {
        return openNow;
    }

    @JsonProperty("open_now")
    public void setOpenNow(Boolean openNow) {
        this.openNow = openNow;
    }

    @JsonProperty("weekday_text")
    public List<Object> getWeekdayText() {
        return weekdayText;
    }

    @JsonProperty("weekday_text")
    public void setWeekdayText(List<Object> weekdayText) {
        this.weekdayText = weekdayText;
    }

}
