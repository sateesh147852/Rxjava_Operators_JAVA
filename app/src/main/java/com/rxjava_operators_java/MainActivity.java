package com.rxjava_operators_java;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.rxjava_operators_java.databinding.ActivityMainBinding;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

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

        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}