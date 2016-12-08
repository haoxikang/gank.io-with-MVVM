package com.fall.gank.database;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by qqq34 on 2016/12/8.
 */

public class Collection extends SugarRecord {
    private String classificationName;
    private String dimension;
    private String year;
    private String monthAndDay;
    @Unique
    private String url;

    public Collection() {

    }

    public Collection(String classificationName, String url, String monthAndDay, String year, String dimension) {
        this.classificationName = classificationName;
        this.url = url;
        this.monthAndDay = monthAndDay;
        this.year = year;
        this.dimension = dimension;
    }

    public String getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(String classificationName) {
        this.classificationName = classificationName;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonthAndDay() {
        return monthAndDay;
    }

    public void setMonthAndDay(String monthAndDay) {
        this.monthAndDay = monthAndDay;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
