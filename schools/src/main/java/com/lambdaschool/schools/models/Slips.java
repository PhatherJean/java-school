package com.lambdaschool.schools.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Slips
{
    private List<SlipsElement> slips;

    public List<SlipsElement> getSlips() {
        return slips;
    }

    public void setSlips(List<SlipsElement> slips) {
        this.slips = slips;
    }
}