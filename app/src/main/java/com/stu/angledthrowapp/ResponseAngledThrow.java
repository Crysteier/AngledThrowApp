
package com.stu.angledthrowapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "coords",
    "distanceTraveled",
    "finishTime",
    "stepCount",
    "tds",
    "highestPoint"
})
public class ResponseAngledThrow {

    @JsonProperty("coords")
    private List<Coord> coords = null;
    @JsonProperty("distanceTraveled")
    private Double distanceTraveled;
    @JsonProperty("finishTime")
    private Double finishTime;
    @JsonProperty("stepCount")
    private Double stepCount;
    @JsonProperty("tds")
    private Double tds;
    @JsonProperty("highestPoint")
    private Double highestPoint;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("coords")
    public List<Coord> getCoords() {
        return coords;
    }

    @JsonProperty("coords")
    public void setCoords(List<Coord> coords) {
        this.coords = coords;
    }

    @JsonProperty("distanceTraveled")
    public Double getDistanceTraveled() {
        return distanceTraveled;
    }

    @JsonProperty("distanceTraveled")
    public void setDistanceTraveled(Double distanceTraveled) {
        this.distanceTraveled = distanceTraveled;
    }

    @JsonProperty("finishTime")
    public Double getFinishTime() {
        return finishTime;
    }

    @JsonProperty("finishTime")
    public void setFinishTime(Double finishTime) {
        this.finishTime = finishTime;
    }

    @JsonProperty("stepCount")
    public Double getStepCount() {
        return stepCount;
    }

    @JsonProperty("stepCount")
    public void setStepCount(Double stepCount) {
        this.stepCount = stepCount;
    }

    @JsonProperty("tds")
    public Double getTds() {
        return tds;
    }

    @JsonProperty("tds")
    public void setTds(Double tds) {
        this.tds = tds;
    }

    @JsonProperty("highestPoint")
    public Double getHighestPoint() {
        return highestPoint;
    }

    @JsonProperty("highestPoint")
    public void setHighestPoint(Double highestPoint) {
        this.highestPoint = highestPoint;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
