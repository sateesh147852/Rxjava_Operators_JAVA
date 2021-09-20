package com.rxjava_operators_java;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.rxjava_operators_java.databinding.ActivityMainBinding;
import com.rxjava_operators_java.model.User;
import com.rxjava_operators_java.utility.Utilites;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
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
            //createUserObservable();
            //createStringObservable();
            createIntegerObservable();
        });

    }

    private void createIntegerObservable() {
        
        Observable.merge(getFirstNumbers(),getSecondNumbers())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                        Log.i(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.i(TAG, "onNext: "+integer);
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
    
    private Observable<Integer> getFirstNumbers(){
        return Observable.range(1,10);
    }

    private Observable<Integer> getSecondNumbers(){
        return Observable.range(11,20);
    }

    private void createStringObservable() {
        
        Observable.merge(getFirstNames(),getLastNames())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe: ");
                        disposable = d;
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
    
    private Observable<String> getFirstNames(){
        return Observable.just("sateesh","akash","Ramesh","kiran");
    }

    private Observable<String> getLastNames(){
        return Observable.just("chikkalagi","patil","rao","bilal");
    }

    private void createUserObservable() {
        
        Observable.merge(getMaleObservable(),getFemaleObservable())
                .subscribeOn(Schedulers.io())
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


    private Observable<User> getMaleObservable() {


        return Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(ObservableEmitter<User> emitter) throws Exception {

                if (!emitter.isDisposed()) {
                    for (User user : Utilites.getUsers()) {
                        Thread.sleep(1000);
                        emitter.onNext(user);
                    }
                    emitter.onComplete();
                }

            }
        });

    }


    private Observable<User> getFemaleObservable() {

        List<User> users = new ArrayList<>();

        users.add(new User("11", "Geeta"));
        users.add(new User("12", "keerti"));
        users.add(new User("13", "Aasha"));
        users.add(new User("14", "Pooja"));
        users.add(new User("15", "aarati"));

        return Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(ObservableEmitter<User> emitter) throws Exception {

                if (!emitter.isDisposed()) {
                    for (User user : users) {
                        Thread.sleep(1000);
                        emitter.onNext(user);
                    }
                    emitter.onComplete();
                }
                
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}