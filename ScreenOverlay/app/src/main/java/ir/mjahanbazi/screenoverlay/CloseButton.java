package ir.mjahanbazi.screenoverlay;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;


public class CloseButton extends AppCompatImageButton implements View.OnClickListener {
    private Context context;

    public CloseButton(@NonNull Context context) {
        super(context);
        init(context);
    }

    public CloseButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CloseButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        setBackground(createSelector());
        int widthHeight = AppUtils.dpi2Pixel(35);
        setMaxWidth(widthHeight);
        setMinimumWidth(widthHeight);
        setMaxHeight(widthHeight);
        setMinimumHeight(widthHeight);
        setScaleType(ScaleType.FIT_CENTER);
        setOnClickListener(this);
    }

    private StateListDrawable createSelector() {
        StateListDrawable selector = new StateListDrawable();
        selector.setExitFadeDuration(200);
        selector.addState(new int[]{android.R.attr.state_pressed},
                getResources().getDrawable(R.drawable.bg_close_pressed, null));
        selector.addState(new int[]{},
                getResources().getDrawable(R.drawable.bg_close_default, null));
        return selector;
    }

    @Override
    public void onClick(View view) {
        AppUtils.windowManager.removeView(AppUtils.mainWindow);
        context.stopService(new Intent(context, AppService.class));
    }

}
