package com.rxjava_operators_java;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.rxjava_operators_java.databinding.ActivityMainBinding;
import com.rxjava_operators_java.model.User;
import com.rxjava_operators_java.utility.Utilites;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btSubscribe.setOnClickListener(v -> {
            //createObservable();
            //createStringArrayObservable();
            createUsersObservable();
        });

    }

    private void createUsersObservable() {
        
        Observable.fromArray(Utilites.getUsers())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<User>>() {
                    @Override
                    public void onSubscribe( Disposable d) {
                        Log.i(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext( List<User> users) {
                        Log.i(TAG, "onNext: ");
                        for (User user : users)
                            Log.i(TAG, "onNext: "+user.toString());
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
        
    }

    private void createStringArrayObservable() {
        
        Observable.fromArray(Utilites.getStringArray())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe( Disposable d) {
                        Log.i(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext( String s) {
                        Log.i(TAG, "onNext: "+s);
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
    }

    private void createObservable() {

        Observable.fromArray(1, 2, 3, 4, 5, 6)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.i(TAG, "onNext: "+integer);
                    }

                    @Override
                    public void onError(Throwable e){
                        Log.i(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: ");
                    }
                });

    }
}