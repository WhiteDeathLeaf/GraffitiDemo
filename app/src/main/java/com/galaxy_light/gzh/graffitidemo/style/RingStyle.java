package com.galaxy_light.gzh.graffitidemo.style;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * 圆环——样式
 * Created by WhiteDeathLeaf on 2017/8/19.
 */

public class RingStyle extends BaseStyle {
    private float startX;
    private float startY;
    private float stopX;
    private float stopY;
    private float radius;
    private int size;

    public RingStyle() {
        startX = 0;
        stopX = 0;
        startY = 0;
        stopY = 0;
        radius = 0;
    }

    public RingStyle(float x, float y, int size, int color) {
        super(color);
        startX = x;
        stopX = x;
        startY = y;
        stopY = y;
        radius = 0;
        this.size = size;
    }

    @Override
    public void draw(Canvas canvas) {
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(size);
        canvas.drawCircle((startX + stopX) / 2, (startY + stopY) / 2, radius, paint);
    }

    @Override
    public void move(float x, float y) {
        stopX = x;
        stopY = y;
        radius = (float) (Math.sqrt((x - startX) * (x - startX) + (y - startY) * (y - startY)) / 2);
    }
}
