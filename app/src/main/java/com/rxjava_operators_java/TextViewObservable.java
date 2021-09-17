package com.rxjava_operators_java;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class TextViewObservable implements ObservableOnSubscribe<CharSequence> {

    private TextView textView;

    public TextViewObservable(TextView textView){
        this.textView = textView;
    }

    @Override
    public void subscribe(ObservableEmitter<CharSequence> emitter) throws Exception {

        final TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!emitter.isDisposed()){
                    emitter.onNext(s.toString());
                }

            }
        };

        textView.addTextChangedListener(textWatcher);
        textView.setText(textView.getText());

    }
}
