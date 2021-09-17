package com.rxjava_operators_java;

import android.widget.TextView;

import io.reactivex.Observable;

public class RxTextView {


    public static Observable<CharSequence> textChanges(TextView textView){
        return Observable.create(new TextViewObservable(textView));
    }


}
