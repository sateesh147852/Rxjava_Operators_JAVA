package com.rxjava_operators_java;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.rxjava_operators_java.databinding.ActivityMainBinding;
import com.rxjava_operators_java.model.User;
import com.rxjava_operators_java.utility.Utilites;

import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
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
            //createObservable();
            createUserObservable();
        });

    }

    private void createUserObservable() {

        Observable.fromIterable(Utilites.getUsers())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .reduce(new BiFunction<User, User, User>() {
                    
                    @Override
                    public User apply( User user,  User user2) throws Exception {
                        String name = user.getName() + " "+ user2.getName();
                        user.setName(name);
                        return user;
                    }
                })
                .subscribe(new MaybeObserver<User>() {
                    @Override
                    public void onSubscribe( Disposable d) {
                        disposable = d;
                        Log.i(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onSuccess( User user) {
                        Log.i(TAG, "onSuccess: "+user.getName());
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

        Observable.range(1,5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer input, Integer result) throws Exception {
                        return input + result;
                    }
                })
                .subscribe(new MaybeObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                        Log.i(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onSuccess(Integer integer) {
                        Log.i(TAG, "onSuccess: "+integer);
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