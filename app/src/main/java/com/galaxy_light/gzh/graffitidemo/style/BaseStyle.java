package com.galaxy_light.gzh.graffitidemo.style;

import android.graphics.Canvas;
import android.graphics.Color;

/**
 * 画笔样式基类
 * Created by WhiteDeathLeaf on 2017/8/19.
 */

public abstract class BaseStyle {
    public int color;//画笔颜色
    public BaseStyle() {
        this.color = Color.BLACK;//默认黑色
    }

    public BaseStyle(int color) {
        this.color = color;
    }

    /**
     * 绘制图形
     * @param canvas 画布
     */
    public abstract void draw(Canvas canvas);

    /**
     * 画笔移动
     * @param x x轴坐标
     * @param y y轴坐标
     */
    public abstract void move(float x,float y);
}
