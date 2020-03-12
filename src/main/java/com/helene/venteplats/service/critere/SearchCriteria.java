package com.helene.venteplats.service.critere;

public class SearchCriteria {
    private String key;
    private String operation;
    private String value;

    public SearchCriteria() {}

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
