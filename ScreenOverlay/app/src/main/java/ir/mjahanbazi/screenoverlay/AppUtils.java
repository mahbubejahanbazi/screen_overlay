package ir.mjahanbazi.screenoverlay;

import android.content.Context;
import android.view.WindowManager;


public class AppUtils {
    public static WindowManager windowManager;
    public static AppView mainWindow;
    public static WindowManager.LayoutParams mainWindowParam;

    public static int dpi2Pixel(int dpi, Context context) {
        return (int) (dpi * context.getResources().getDisplayMetrics().density);
    }
}
