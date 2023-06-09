package com.example.kginsta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.kginsta.databinding.ActivityMainBinding;
import com.example.kginsta.model.User;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View bindingView = binding.getRoot();
        setContentView(bindingView);
        User user = new User("","");
        binding.setUserToLogin(user);

        binding.submit.setOnClickListener(view -> {
            Toast.makeText(bindingView.getContext(), user.getLogin(), Toast.LENGTH_SHORT).show();
        });
    }
}

//https://instaapp-production.up.railway.app/api/photos