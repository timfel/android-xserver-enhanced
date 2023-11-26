package au.com.darkside.xserver;

// import au.com.darkside.xserver.OnScaleGestureListener;
import au.com.darkside.xserver.ScreenView;

import android.view.ScaleGestureDetector;
import android.os.Handler;

public class ScaleGestureListenerImpl implements ScaleGestureDetector.OnScaleGestureListener {
    public Boolean scaleInProgress = false;
    private ScreenView _screenView;

    public ScaleGestureListenerImpl(ScreenView screenView) {
        this._screenView = screenView;
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        float scaleFactor = detector.getScaleFactor();
        scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f));
        _screenView.setScaleX(scaleFactor);
        _screenView.setScaleY(scaleFactor);
        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        scaleInProgress = true;
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                scaleInProgress = false;
            }
        }, 500);
    }

}