package ir.mjahanbazi.screenoverlay;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.view.Gravity;
import android.view.WindowManager;

import androidx.annotation.Nullable;


public class AppService extends Service {
    private WindowManager windowManager;
    private AppView appView;
    private WindowManager.LayoutParams param;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        final int width = AppUtils.dpi2Pixel(200, this);
        final int height = AppUtils.dpi2Pixel(300, this);
        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        AppUtils.windowManager = windowManager;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            param = new WindowManager.LayoutParams(
                    width,
                    height,
                    WindowManager.LayoutParams.TYPE_PHONE,
                    WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                            | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT) {{
                gravity = Gravity.CENTER;
            }};
        } else {
            param = new WindowManager.LayoutParams(
                    width,
                    height,
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                            | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT) {{
                gravity = Gravity.CENTER;
            }};
        }
        appView = new AppView(this);
        AppUtils.mainWindow = appView;
        AppUtils.mainWindowParam = param;
        windowManager.addView(appView, param);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
