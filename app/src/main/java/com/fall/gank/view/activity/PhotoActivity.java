package com.fall.gank.view.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.example.rxpermisson.PermissionAppCompatActivity;
import com.fall.gank.R;
import com.fall.gank.Utils.FrescoUtils;
import com.fall.gank.Utils.MDStatusBarCompat;

import me.relex.photodraweeview.PhotoDraweeView;
import rx.Subscription;

/**
 * Created by qqq34 on 2016/12/12.
 */

public class PhotoActivity extends PermissionAppCompatActivity {
    private static final String EXTRA_URL = "PhotoActivity.url";
    private Subscription mSubscription;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        String url = getIntent().getStringExtra(EXTRA_URL);
        MDStatusBarCompat.setImageTranslucent(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("查看图片");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> {
            finish();
        });
        PhotoDraweeView photoDraweeView = (PhotoDraweeView) findViewById(R.id.photo_drawee_view);
        photoDraweeView.setPhotoUri(Uri.parse(url));
        toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.save_photo:
                    mSubscription = checkPermission(R.string.base_permission, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            .subscribe(aBoolean -> {
                                if (aBoolean) {
                                    FrescoUtils.savePicture(url, PhotoActivity.this);
                                    Snackbar.make(toolbar, "保存成功！路径：" + FrescoUtils.IMAGE_PIC_CACHE_DIR, Snackbar.LENGTH_LONG).show();
                                }else {
                                    Snackbar.make(toolbar, "保存失败，app没有相应的权限", Snackbar.LENGTH_LONG).show();

                                }
                            }, throwable -> {
                            });


                    break;
            }
            return true;
        });
    }

    public static void newIntent(Context context, String url) {
        Intent intent = new Intent(context, PhotoActivity.class);
        intent.putExtra(EXTRA_URL, url);
        context.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_photo, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }
}
