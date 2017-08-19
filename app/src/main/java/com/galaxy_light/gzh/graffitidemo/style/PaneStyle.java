package com.galaxy_light.gzh.graffitidemo.style;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * 方框——样式
 * Created by WhiteDeathLeaf on 2017/8/19.
 */

public class PaneStyle extends BaseStyle {
    private float startX;
    private float stopX;
    private float startY;
    private float stopY;
    private int size;

    public PaneStyle() {
        startX = 0;
        stopX = 0;
        startY = 0;
        stopY = 0;
    }

    public PaneStyle(float x, float y, int size, int color) {
        super(color);
        startX = x;
        stopX = x;
        startY = y;
        stopY = y;
        this.size = size;
    }

    @Override
    public void draw(Canvas canvas) {
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(size);
        canvas.drawRect(startX, startY, stopX, stopY, paint);
    }

    @Override
    public void move(float x, float y) {
        stopX = x;
        stopY = y;
    }
}
