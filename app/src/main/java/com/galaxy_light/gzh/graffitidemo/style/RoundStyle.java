package com.galaxy_light.gzh.graffitidemo.style;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * 圆形——样式
 * Created by WhiteDeathLeaf on 2017/8/19.
 */

public class RoundStyle extends BaseStyle {
    private float startX;
    private float startY;
    private float stopX;
    private float stopY;
    private float radius;
    private int size;

    public RoundStyle(float x, float y, int size, int color) {
        super(color);
        this.startX = x;
        this.startY = y;
        this.stopX = x;
        this.stopY = y;
        this.radius = 0;
        this.size = size;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint=new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(size);
        canvas.drawCircle((startX + stopX) / 2, (startX + stopY) / 2, radius, paint);
    }

    @Override
    public void move(float x, float y) {
        stopX = x;
        stopY = y;
        radius = (float) ((Math.sqrt((x - startX) * (x - startX) + (y - startY) * (y - startY))) / 2);
    }
}
