package com.gao.yingjian.mobiledevelopmentassignmentone.Models;

public class SurveyDetailItem {
    public enum InputType
    {
        Text,
        CheckBox,
        RadioButton
    }

    private int itemId;
    private String title;
    private InputType type;
    private String[] values;

    public SurveyDetailItem() {
    }

    public SurveyDetailItem(int itemId, String title, InputType type, String[] values) {
        this.itemId = itemId;
        this.title = title;
        this.type = type;
        this.values = values;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setType(InputType type) {
        this.type = type;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public int getItemId() {
        return itemId;
    }

    public String[] getValues() {
        return values;
    }

    public InputType getType() {
        return type;
    }
}
