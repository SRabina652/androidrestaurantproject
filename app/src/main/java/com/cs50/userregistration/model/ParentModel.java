package com.cs50.userregistration.model;

import com.cs50.userregistration.model.DataHolder;

import java.util.List;

public class ParentModel {
    private String parentTitle;
    List<DataHolder> childmodellist;

    public ParentModel(String parentTitle, List<DataHolder> childmodel) {
        this.parentTitle = parentTitle;
        this.childmodellist = childmodel;
    }

    public String getParentTitle() {
        return parentTitle;
    }

    public void setParentTitle(String parentTitle) {
        this.parentTitle = parentTitle;
    }

    public List<DataHolder> getChildmodel() {
        return childmodellist;
    }

    public void setChildmodel(List<DataHolder> childmodel) {
        this.childmodellist = childmodel;
    }
}
