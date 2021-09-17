package ir.mjahanbazi.screenoverlay;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AppView extends RelativeLayout implements View.OnTouchListener {
    private int initialTouchX = 0;
    private int initialTouchY = 0;
    private int initialX = 0;
    private int initialY = 0;
    private boolean flagDrag = false;

    public AppView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public AppView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AppView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOnTouchListener(this);
        LinearLayout buttonPanel = new LinearLayout(context);
        buttonPanel.setId(View.generateViewId());
        addView(buttonPanel, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT) {{
            addRule(RelativeLayout.ALIGN_PARENT_TOP);
            addRule(RelativeLayout.ALIGN_PARENT_END);
        }});
        buttonPanel.addView(new CloseButton(context), new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT) {{
            int margin = AppUtils.dpi2Pixel(3, getContext());
            setMargins(margin, margin, margin, margin);
        }});
        setBackgroundResource(R.drawable.bg_app_view);
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        WindowManager.LayoutParams customViewParam = AppUtils.mainWindowParam;
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            initialTouchX = (int) motionEvent.getRawX();
            initialTouchY = (int) motionEvent.getRawY();
            initialX = customViewParam.x;
            initialY = customViewParam.y;
            flagDrag = true;
            return true;
        }
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            flagDrag = false;
            return true;
        }
        if (flagDrag) {
            int newx = (int) motionEvent.getRawX();
            int newy = (int) motionEvent.getRawY();
            customViewParam.y = initialY + newy - initialTouchY;
            customViewParam.x = initialX + newx - initialTouchX;
            AppUtils.windowManager.updateViewLayout(this, customViewParam);
        }
        return false;
    }


}
