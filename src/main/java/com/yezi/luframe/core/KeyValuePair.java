package com.yezi.luframe.core;

/**
 * created by yezi on 2018/7/30
 */
public class KeyValuePair<TKey, TValue> {

    private TKey key;
    private TValue value;

    public KeyValuePair(){

    }

    public KeyValuePair(TKey key, TValue value){
        this.key = key;
        this.value = value;
    }

    public TKey getKey() {
        return key;
    }

    public void setKey(TKey key) {
        this.key = key;
    }

    public TValue getValue() {
        return value;
    }

    public void setValue(TValue value) {
        this.value = value;
    }
}
