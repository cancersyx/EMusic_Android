package com.eworld.emusic.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * Created by EWorld
 * 2021/12/23
 * 权限管理类
 */
public class PermissionHelper {
    private static final String TAG = "PermissionHelper";
    public static final int REQUEST_CODE_READ_PHONE = 100;
    public static final int REQUEST_CODE_EXTERNAL_STORAGE = 101;
    private Activity mActivity;

    /**
     * 权限数组
     */
    private PermissionModel[] mPermissionModels = new PermissionModel[]{
            new PermissionModel("电话", Manifest.permission.READ_PHONE_STATE, "我们需要读取手机信息的权限来标识您的身份", REQUEST_CODE_READ_PHONE),
            new PermissionModel("存储", Manifest.permission.WRITE_EXTERNAL_STORAGE, "我们需要您允许我们读写你的存储卡，以方便我们临时保存一些数据", REQUEST_CODE_EXTERNAL_STORAGE),

    };

    public PermissionHelper(Activity activity) {
        this.mActivity = activity;
    }

    /**
     * 遍历申请权限
     */
    public void applyPermissions() {
        for (PermissionModel model : mPermissionModels) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(mActivity, model.permission)) {
                //申请权限permission
                ActivityCompat.requestPermissions(mActivity, new String[]{model.permission}, model.requestCode);
                return;
            }
        }

        if (mApplyPermissionListener != null) {
            mApplyPermissionListener.onAfterApplyPermission();
        }
    }

    /**
     * 是否申请了所有权限
     * @return
     */
    public boolean isApplyAllPermission() {
        for (PermissionModel model : mPermissionModels) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(mActivity, model.permission)) {
                //有权限未申请
                return false;
            }
        }
        return true;
    }

    /**
     * @param requestCode  请求码
     * @param permissions  权限
     * @param grantResults 授予的权限
     */
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_READ_PHONE:
            case REQUEST_CODE_EXTERNAL_STORAGE:
                if (PackageManager.PERMISSION_GRANTED != grantResults[0]) {
                    //用户不允许权限
                    if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, permissions[0])) {
                        LUtil.d(TAG, ">>>>>>>> onRequestPermissionResult() 用户禁止授予该权限 = " + permissions[0]);
                    }
                } else {
                    //去打开设置
                    // TODO: 2021/12/23
                }

                break;

        }
    }


    class PermissionModel {
        /**
         * 权限名称
         */
        public String name;

        /**
         * 请求的权限
         */
        public String permission;

        /**
         * 权限解释
         */
        public String explain;

        /**
         * 请求code
         */
        public int requestCode;

        public PermissionModel(String name, String permission, String explain, int requestCode) {
            this.name = name;
            this.permission = permission;
            this.explain = explain;
            this.requestCode = requestCode;
        }
    }

    //------------------------------------------------------------------------
    private OnApplyPermissionListener mApplyPermissionListener;

    public void setApplyPermissionListener(OnApplyPermissionListener permissionListener) {
        mApplyPermissionListener = permissionListener;
    }

    public interface OnApplyPermissionListener {
        void onAfterApplyPermission();//申请所有权限后的回调
    }
}
