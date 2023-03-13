package com.example.mvvmkg.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.mvvmkg.databinding.ActivityMainBinding;
import com.example.mvvmkg.viewmodel.SettingsViewModel;
import com.example.mvvmkg.viewmodel.TestViewModel;

public class MainActivity extends AppCompatActivity {
    private SettingsViewModel settingsViewModel;
    private TestViewModel testViewModel;

    private ActivityMainBinding binding;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding =ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        settingsViewModel = new ViewModelProvider(MainActivity.this)
                .get(SettingsViewModel.class);
        settingsViewModel.setupSettings();

        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingsViewModel.changeSettings(
                        binding.port.getText().toString(),
                        binding.url.getText().toString()

                );
            }
        });

        settingsViewModel.getObservedSettings().observe(com.example.mvvmkg.view.MainActivity.this, s -> {
            //teraz każda zmiana w livedata będzie się odwzorowywać na pole txt
            binding.tv.setText(s.getUrl() + ":" + s.getPort());
        });


//        ZADANIE 3


    }

}