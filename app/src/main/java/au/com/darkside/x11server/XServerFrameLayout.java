package au.com.darkside.x11server;

import android.widget.Toast;

import android.widget.FrameLayout;
import android.view.ScaleGestureDetector;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.content.Context;

import au.com.darkside.x11server.ScaleGestureListenerImpl;

public class XServerFrameLayout extends FrameLayout {
    private static final String LOG_TAG = "Berara";
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

    private void init(Context context) {
        mScaleListener = new ScaleGestureListenerImpl(this);
        mScaleDetector = new ScaleGestureDetector(context, mScaleListener);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(LOG_TAG, "Pointer count: " + event.getPointerCount());
        // Toast.makeText(getContext(), "Touch event detected", Toast.LENGTH_SHORT).show();
        Toast.makeText(getContext(), "Pointer count: " + event.getPointerCount(), Toast.LENGTH_SHORT).show();

        if (event.getPointerCount() == 3) {
                    Toast.makeText(getContext(), "onTouch: Three finger touch detected", Toast.LENGTH_SHORT).show();
                    // toggleNavigationBar();
                    return false;
        }

        mScaleDetector.onTouchEvent(event);
        if (mScaleListener.scaleInProgress == true) {
            Toast.makeText(getContext(), "mScaleListener.scaleInProgress is true", Toast.LENGTH_SHORT).show();
            // Log.d(LOG_TAG, "mScaleListener.scaleInProgress is true");
            return false;
        }

        return super.onTouchEvent(event);
    }
}