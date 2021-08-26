package com.example.fragmenttransfer;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Path;
import android.os.Bundle;
public class MainActivity extends AppCompatActivity implements FragmentA.FragmentAListner,FragmentB.FragmentBListner{
FragmentA fragmentA;
FragmentB fragmentB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentA=new FragmentA();
        fragmentB=new FragmentB();
        getSupportFragmentManager().beginTransaction().replace(R.id.containerframe1,fragmentA).replace(R.id.containerframe2,fragmentB).commit();
    }

    @Override
    public void oninputa(Path path) {
        fragmentB.Update(path);
    }

    @Override
    public void oninputb(Path path) {
        fragmentA.Update(path);

    }
}