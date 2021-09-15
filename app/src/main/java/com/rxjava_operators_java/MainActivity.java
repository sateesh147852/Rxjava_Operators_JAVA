package com.rxjava_operators_java;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.rxjava_operators_java.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btSubscribe.setOnClickListener(v -> {

        });

    }
}