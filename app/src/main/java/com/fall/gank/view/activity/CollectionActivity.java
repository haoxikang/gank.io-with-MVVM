package com.fall.gank.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.fall.gank.R;
import com.fall.gank.Utils.MDStatusBarCompat;
import com.fall.gank.view.fragment.ClassificationFragment;

/**
 * Created by qqq34 on 2016/12/12.
 */

public class CollectionActivity extends AppCompatActivity {
    private ClassificationFragment mClassificationFragment;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        MDStatusBarCompat.setOrdinaryToolBar(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("收藏");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> {
            finish();
        });
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (mClassificationFragment==null){
            mClassificationFragment = ClassificationFragment.newInstance("collection");
        }
        transaction.replace(R.id.container, mClassificationFragment);
        transaction.commit();
    }
}
