package com.eworld.emusic;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.eworld.emusic.utils.LUtil;
import com.eworld.emusic.utils.PermissionHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by EWorld
 * 2021/12/23
 */
public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";
    private PermissionHelper mPermissionHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //移除标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mPermissionHelper = new PermissionHelper(this);
            mPermissionHelper.setApplyPermissionListener(new PermissionHelper.OnApplyPermissionListener() {
                @Override
                public void onAfterApplyPermission() {
                    openMain();
                }
            });
            if (mPermissionHelper.isApplyAllPermission()) {
                LUtil.d(TAG,">>>>>>> 999");
                openMain();
            } else {
                // TODO: 2021/12/23
                mPermissionHelper.applyPermissions();
            }

        } else {
            openMain();

        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mPermissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void openMain() {
        MainActivity.startActivity(this);

    }


}
