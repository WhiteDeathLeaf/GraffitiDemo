package com.galaxy_light.gzh.graffitidemo.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.galaxy_light.gzh.graffitidemo.style.BaseStyle;
import com.galaxy_light.gzh.graffitidemo.style.BlockStyle;
import com.galaxy_light.gzh.graffitidemo.style.CurveStyle;
import com.galaxy_light.gzh.graffitidemo.style.LineStyle;
import com.galaxy_light.gzh.graffitidemo.style.PaneStyle;
import com.galaxy_light.gzh.graffitidemo.style.PointStyle;
import com.galaxy_light.gzh.graffitidemo.style.RingStyle;
import com.galaxy_light.gzh.graffitidemo.style.RoundStyle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 涂鸦控件
 * Created by WhiteDeathLeaf on 2017/8/19.
 */

public class GraffitiView extends SurfaceView implements SurfaceHolder.Callback {
    private Bitmap bitmap;
    private BaseStyle currentStyle;//当前画笔样式
    private List<BaseStyle> baseStyles;
    private StyleType styleType = StyleType.Curve;//默认曲线样式
    private int currentColor = Color.BLACK;//默认画笔颜色
    private int currentSize = 5;//默认画笔宽度
    private SurfaceHolder surfaceHolder = getHolder();
    private Paint paint = new Paint(Color.WHITE);

    public GraffitiView(Context context) {
        super(context);
        init();
    }

    public GraffitiView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GraffitiView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Canvas canvas = surfaceHolder.lockCanvas();
        canvas.drawColor(Color.WHITE);
        surfaceHolder.unlockCanvasAndPost(canvas);
        baseStyles = new ArrayList<>();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_CANCEL) return false;
        float x = event.getX();
        float y = event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                setCurrentStyle(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                Canvas canvas = surfaceHolder.lockCanvas();
                canvas.drawColor(Color.WHITE);
                for (BaseStyle baseStyle : baseStyles) {
                    baseStyle.draw(canvas);
                }
                currentStyle.move(x, y);
                currentStyle.draw(canvas);
                surfaceHolder.unlockCanvasAndPost(canvas);
                break;
            case MotionEvent.ACTION_UP:
                baseStyles.add(currentStyle);
                currentStyle = null;
                break;
        }
        return true;
    }

    /**
     * 设置当前样式
     *
     * @param x x轴坐标
     * @param y y轴坐标
     */
    private void setCurrentStyle(float x, float y) {
        switch (styleType) {
            case Block://矩形
                currentStyle = new BlockStyle(x, y, currentSize, currentColor);
                break;
            case Curve://曲线
                currentStyle = new CurveStyle(x, y, currentSize, currentColor);
                break;
            case Line://直线
                currentStyle = new LineStyle(x, y, currentSize, currentColor);
                break;
            case Pane://方框
                currentStyle = new PaneStyle(x, y, currentSize, currentColor);
                break;
            case Point://点
                currentStyle = new PointStyle(x, y, currentColor);
                break;
            case Ring://圆环
                currentStyle = new RingStyle(x, y, currentSize, currentColor);
                break;
            case Round://圆
                currentStyle = new RoundStyle(x, y, currentSize, currentColor);
                break;
        }
    }

    /**
     * 初始化
     */
    public void init() {
        surfaceHolder.addCallback(this);
        setFocusable(true);
        paint.setStrokeWidth(currentSize);
    }

    /**
     * 设置画笔颜色
     *
     * @param color 要改变的画笔颜色
     */
    public void setColor(int color) {
        currentColor = color;
    }

    /**
     * 设置画笔宽度
     *
     * @param size 要改变的画笔宽度
     */
    public void setSize(int size) {
        currentSize = size;
    }

    /**
     * 设置画笔样式
     *
     * @param type 要改变的画笔样式
     */
    public void setType(StyleType type) {
        styleType = type;
    }

    /**
     * 将涂鸦转换成Bitmap
     *
     * @return 生成的Bitmap图片
     */
    public Bitmap convertBitmap() {
        bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.TRANSPARENT);
        for (BaseStyle baseStyle : baseStyles) {
            baseStyle.draw(canvas);
        }
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return bitmap;
    }

    /**
     * 保存图片
     *
     * @param graffitiView 涂鸦控件
     * @return 是否保存成功
     */
    public boolean saveBitmap(GraffitiView graffitiView) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "graffiti" + File.separator + System.currentTimeMillis() + ".png";
            File file = new File(path);
            if (!file.exists()) {
                file.getParentFile().mkdir();
            }
            saveBitmapToAppointPath(graffitiView.convertBitmap(), path);
            return true;
        }
        return false;
    }

    /**
     * 保存Bitmap
     *
     * @param bitmap 转换成的bitmap
     * @param path   保存路径
     */
    private void saveBitmapToAppointPath(Bitmap bitmap, String path) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 撤销
     *
     * @return 是否撤销成功
     */
    public boolean revoke() {
        if (baseStyles != null && baseStyles.size() > 0) {
            baseStyles.remove(baseStyles.size() - 1);
            Canvas canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.WHITE);
            for (BaseStyle baseStyle : baseStyles) {
                baseStyle.draw(canvas);
            }
            surfaceHolder.unlockCanvasAndPost(canvas);
            return true;
        }
        return false;
    }

    /**
     * 重绘
     */
    public void reset() {
        if (baseStyles != null && baseStyles.size() > 0) {
            baseStyles.clear();
            Canvas canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.WHITE);
            for (BaseStyle baseStyle : baseStyles) {
                baseStyle.draw(canvas);
            }
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    /**
     * 画笔样式
     */
    private enum StyleType {
        Block, Curve, Line, Pane, Point, Ring, Round
    }
}
