package com.galaxy_light.gzh.graffitidemo.style;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * 矩形——样式
 * Created by WhiteDeathLeaf on 2017/8/19.
 */

public class BlockStyle extends BaseStyle {
    private float startX;
    private float startY;
    private float stopX;
    private float stopY;
    private int size;

    public BlockStyle() {
        startX = 0;
        startY = 0;
        stopX = 0;
        stopY = 0;
    }

    public BlockStyle(float x, float y, int size, int color) {
        super(color);
        startX = x;
        startY = y;
        stopX = x;
        stopY = y;
        this.size = size;
    }

    @Override
    public void draw(Canvas canvas) {
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(size);
        canvas.drawRect(startX, startY, stopX, stopY, paint);
    }

    @Override
    public void move(float x, float y) {
        stopX = x;
        stopY = y;
    }
}
