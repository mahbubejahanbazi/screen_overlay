package ir.mjahanbazi.screenoverlay;

import android.app.Activity;
import android.view.WindowManager;


public class AppUtils {
    private static float density = -1;
    public static Activity activity;
    public static WindowManager windowManager;
    public static AppView mainWindow;
    public static WindowManager.LayoutParams mainWindowParam;
    ;

    public static int dpi2Pixel(int dpi) {
        return (int) (dpi * getDensity());
    }

    private static float getDensity() {
        if (density == -1) {
            density = activity.getResources().getDisplayMetrics().density;
        }
        return density;
    }
}
