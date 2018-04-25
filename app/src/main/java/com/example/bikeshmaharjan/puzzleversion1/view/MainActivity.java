package com.example.bikeshmaharjan.puzzleversion1.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bikeshmaharjan.puzzleversion1.R;
import com.example.bikeshmaharjan.puzzleversion1.viewmodel.ViewModel;
import com.example.bikeshmaharjan.puzzleversion1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    ViewModel viewModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel=new ViewModel();
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setGameViewModel(viewModel);
        activityMainBinding.setHandler(viewModel);
    }
}






