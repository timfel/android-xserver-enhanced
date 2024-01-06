package au.com.darkside.x11server;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.os.Build;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

public class XServerFrameLayout extends FrameLayout {
  private static final String LOG_TAG = "XServerFrameLayout";

  private boolean isNavBarHidden = false;
  private boolean isTogglingNavBar = false;

  private ScaleGestureDetector mScaleDetector;
  private ScaleGestureListenerImpl mScaleListener;

  public XServerFrameLayout(Context context) {
    super(context);
    init(context);
  }

  public XServerFrameLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public XServerFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent event) {
    super.onInterceptTouchEvent(event);
    Toast.makeText(getContext(), "Intercepted!", Toast.LENGTH_SHORT).show();

    Log.d(LOG_TAG, "Pointer count: " + event.getPointerCount());
    
    if (event.getPointerCount() == 3) {
      Log.d(LOG_TAG, "onTouch: Three finger touch detected");
      toggleNavigationBar();
      return true;
    }

    mScaleDetector.onTouchEvent(event);
    if (mScaleListener.scaleInProgress == true) {
      Toast.makeText(getContext(), "mScaleListener.scaleInProgress is true", Toast.LENGTH_SHORT)
          .show();
      Log.d(LOG_TAG, "mScaleListener.scaleInProgress is true");
      return true;
    }

    return false;
  }

  private void toggleNavigationBar() {
        if (isTogglingNavBar) {
            return;
        }

        isTogglingNavBar = true;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (!isNavBarHidden) {
                setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                isNavBarHidden = true;
            } else {
                setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                isNavBarHidden = false;
            }
        }

        // Reset the flag after a delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isTogglingNavBar = false;
            }
        }, 500); // 500ms delay
    }

  private void init(Context context) {
    mScaleListener = new ScaleGestureListenerImpl(this);
    mScaleDetector = new ScaleGestureDetector(context, mScaleListener);
  }
}