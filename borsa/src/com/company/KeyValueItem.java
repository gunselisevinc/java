package com.company;

public class KeyValueItem {
    public  KeyValueItem(String key,String value){
        this.key = key;
        this.value = value;
    }

    public KeyValueItem(){

    }

    @Override
    public String toString(){
        return this.value;
    }

    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
