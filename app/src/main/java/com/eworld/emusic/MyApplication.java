package com.eworld.emusic;

import android.app.Application;
import android.content.Context;

import com.bilibili.magicasakura.utils.ThemeUtils;
import com.eworld.emusic.utils.ThemeHelper;


/**
 * Created by EWorld
 */
public class MyApplication extends Application implements ThemeUtils.switchColor {

    @Override
    public void onCreate() {
        super.onCreate();
        //init
        ThemeUtils.setSwitchColor(this);
    }

    /**
     * @param context
     * @param colorId
     * @return
     */
    @Override
    public int replaceColorById(Context context, int colorId) {
        /*if(ThemeHelper.getThemeId(context) == "blue") {
            switch (colorId) {
                // define in Step 1
                case R.color.theme_color_primary:
                    return R.color.blue;
            }
        }*/
        return 0;
    }

    /**
     * @param context
     * @param color
     * @return
     */
    @Override
    public int replaceColor(Context context, int color) {
        /*if (ThemeHelper.isDefaultTheme(context)) {
            return originColor;
        }*/
        return 0;
    }
}
