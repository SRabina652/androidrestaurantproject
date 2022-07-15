package com.cs50.userregistration.model;

import com.cs50.userregistration.model.DataHolder;

import java.util.List;

public class ParentModel {
    private String parentTitle;
    List<DataHolder> childmodel;

    public ParentModel(String parentTitle, List<DataHolder> childmodel) {
        this.parentTitle = parentTitle;
        this.childmodel = childmodel;
    }

    public String getParentTitle() {
        return parentTitle;
    }

    public void setParentTitle(String parentTitle) {
        this.parentTitle = parentTitle;
    }

    public List<DataHolder> getChildmodel() {
        return childmodel;
    }

    public void setChildmodel(List<DataHolder> childmodel) {
        this.childmodel = childmodel;
    }
}
