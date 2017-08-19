package com.galaxy_light.gzh.graffitidemo.style;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * 点——样式
 * Created by WhiteDeathLeaf on 2017/8/19.
 */

public class PointStyle extends BaseStyle{
    private float x;
    private float y;

    public PointStyle(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public PointStyle(float x, float y,int color) {
        super(color);
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint=new Paint();
        paint.setColor(color);
        canvas.drawPoint(x,y,paint);
    }

    @Override
    public void move(float x, float y) {

    }
}
