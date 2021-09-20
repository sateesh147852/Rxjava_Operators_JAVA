package com.rxjava_operators_java;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.rxjava_operators_java.databinding.ActivityMainBinding;
import com.rxjava_operators_java.model.User;
import com.rxjava_operators_java.utility.Utilites;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
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
            //createUserObservable();
            createIntegerObservable();
        });

    }

    private void createIntegerObservable() {

        Observable.range(1,10)
                .subscribeOn(Schedulers.io())
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        Thread.sleep(1000);
                        return String.valueOf(integer);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                        Log.i(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(String s) {
                        Log.i(TAG, "onNext: "+s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: ");
                    }
                });

    }

    private void createUserObservable() {

        Observable.fromIterable(Utilites.getUsers())
                .subscribeOn(Schedulers.io())
                .map(new Function<User, User>() {
                    @Override
                    public User apply(User user) throws Exception {
                        user.setName(user.getName().toUpperCase());
                        Thread.sleep(1000);
                        return user;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe: ");
                        disposable = d;
                    }

                    @Override
                    public void onNext(User user) {
                        Log.i(TAG, "onNext: "+user.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: ");
                    }
                });
        
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}