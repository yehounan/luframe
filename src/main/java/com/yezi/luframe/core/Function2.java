package com.yezi.luframe.core;

/**
 * Created by yezi on 2018/1/9.
 */
@FunctionalInterface
public interface Function2<T1, T2, TResult> {

    TResult apply(T1 item1, T2 item2);
}
