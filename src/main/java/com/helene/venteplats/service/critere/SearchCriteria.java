package com.helene.venteplats.service.critere;

import java.time.LocalDateTime;

public class SearchCriteria {
    private String key;
    private String operation;
    private String stringValue;
    private float prixValue;
    private LocalDateTime dateValue;

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

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public float getPrixValue() {
        return prixValue;
    }

    public void setPrixValue(float prixValue) {
        this.prixValue = prixValue;
    }

    public LocalDateTime getDateValue() {
        return dateValue;
    }

    public void setDateValue(LocalDateTime dateValue) {
        this.dateValue = dateValue;
    }
}
