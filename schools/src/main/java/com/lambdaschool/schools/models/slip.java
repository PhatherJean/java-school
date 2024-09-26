package com.lambdaschool.schools.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonIgnoreProperties(ignoreUnknown = true)
public class slip
{
    private int id;
    private String advice;

    public int getId()
    {
        return id;
    }

    public String getAdvice()
    {
        return advice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }
}
