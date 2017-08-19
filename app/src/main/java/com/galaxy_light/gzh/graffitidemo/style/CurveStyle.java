package com.galaxy_light.gzh.graffitidemo.style;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * 曲线——样式
 * Created by WhiteDeathLeaf on 2017/8/19.
 */

public class CurveStyle extends BaseStyle {
    private Path path;
    private int size;

    public CurveStyle() {
        path = new Path();
        size = 1;
    }

    public CurveStyle(float x, float y, int size, int color) {
        super(color);
        path = new Path();
        this.size = size;
        path.moveTo(x, y);
        path.lineTo(x, y);
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStrokeWidth(size);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPath(path, paint);
    }

    @Override
    public void move(float x, float y) {
        path.lineTo(x, y);
    }
}
