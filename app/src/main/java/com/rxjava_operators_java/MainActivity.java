package com.rxjava_operators_java;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.rxjava_operators_java.databinding.ActivityMainBinding;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private String TAG = "MainActivity";
    private Disposable disposable;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btSubscribe.setOnClickListener(v -> {

            RxTextView.textChanges(binding.etSearch)
                    .debounce(3, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<CharSequence>() {
                        @Override
                        public void onSubscribe( Disposable d) {
                            disposable = d;
                            Log.i(TAG, "onSubscribe: ");
                        }

                        @Override
                        public void onNext( CharSequence charSequence) {
                            Log.i(TAG, "onNext: "+charSequence.toString());
                        }

                        @Override
                        public void onError( Throwable e) {
                            Log.i(TAG, "onError: "+e.getMessage());
                        }

                        @Override
                        public void onComplete() {
                            Log.i(TAG, "onComplete: ");
                        }
                    });
            
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}