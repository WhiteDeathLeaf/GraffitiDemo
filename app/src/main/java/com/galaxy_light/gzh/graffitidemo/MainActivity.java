package com.galaxy_light.gzh.graffitidemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.galaxy_light.gzh.graffitidemo.custom.GraffitiView;
import com.galaxy_light.gzh.graffitidemo.style.BaseStyle;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.graffitiView)
    GraffitiView graffitiView;
    @BindView(R.id.tv_size)
    TextView tvSize;
    private BaseStyle baseStyle;
    private int currentSize = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        graffitiView.setSize(convertSize(currentSize));
    }

    public int convertSize(int size) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (size * density + 0.5f);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return graffitiView.onTouchEvent(event);
    }

    @OnClick({R.id.bt_revoke, R.id.bt_unRevoke, R.id.bt_reset, R.id.bt_save})
    public void omAction(View view) {
        switch (view.getId()) {
            case R.id.bt_revoke:
                baseStyle = graffitiView.revoke();
                break;
            case R.id.bt_unRevoke:
                graffitiView.unRevoke(baseStyle);
                baseStyle = null;
                break;
            case R.id.bt_reset:
                graffitiView.reset();
                break;
            case R.id.bt_save:
                String path = graffitiView.saveBitmap(graffitiView);
                if (path.equals("保存失败")) {
                    Toast.makeText(this, "图片保存失败", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "图片保存成功/"+path, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @OnClick({R.id.iv_1, R.id.iv_2, R.id.iv_3, R.id.iv_4, R.id.iv_5, R.id.iv_6, R.id.iv_7, R.id.iv_8, R.id.iv_9, R.id.iv_10, R.id.iv_11})
    public void onColorChange(View view) {
        switch (view.getId()) {
            case R.id.iv_1:
                graffitiView.setColor(Color.parseColor("#ff0000"));
                break;
            case R.id.iv_2:
                graffitiView.setColor(Color.parseColor("#ffaa00"));
                break;
            case R.id.iv_3:
                graffitiView.setColor(Color.parseColor("#b7ff00"));
                break;
            case R.id.iv_4:
                graffitiView.setColor(Color.parseColor("#00ff00"));
                break;
            case R.id.iv_5:
                graffitiView.setColor(Color.parseColor("#00ffae"));
                break;
            case R.id.iv_6:
                graffitiView.setColor(Color.parseColor("#00aaff"));
                break;
            case R.id.iv_7:
                graffitiView.setColor(Color.parseColor("#0004ff"));
                break;
            case R.id.iv_8:
                graffitiView.setColor(Color.parseColor("#aa00ff"));
                break;
            case R.id.iv_9:
                graffitiView.setColor(Color.parseColor("#ff00b2"));
                break;
            case R.id.iv_10:
                graffitiView.setColor(Color.parseColor("#ffffff"));
                break;
            case R.id.iv_11:
                graffitiView.setColor(Color.parseColor("#000000"));
                break;
        }
    }

    @OnClick({R.id.bt_block, R.id.bt_curve, R.id.bt_line, R.id.bt_pane, R.id.bt_ring, R.id.bt_round})
    public void onTypeChange(View view) {
        switch (view.getId()) {
            case R.id.bt_block:
                graffitiView.setType(GraffitiView.StyleType.Block);
                break;
            case R.id.bt_curve:
                graffitiView.setType(GraffitiView.StyleType.Curve);
                break;
            case R.id.bt_line:
                graffitiView.setType(GraffitiView.StyleType.Line);
                break;
            case R.id.bt_pane:
                graffitiView.setType(GraffitiView.StyleType.Pane);
                break;
            case R.id.bt_ring:
                graffitiView.setType(GraffitiView.StyleType.Ring);
                break;
            case R.id.bt_round:
                graffitiView.setType(GraffitiView.StyleType.Round);
                break;
        }
    }

    @OnClick({R.id.iv_up, R.id.iv_down})
    public void onSizeChange(View view) {
        switch (view.getId()) {
            case R.id.iv_up:
                currentSize++;
                tvSize.setText(String.valueOf(currentSize));
                graffitiView.setSize(convertSize(currentSize));
                break;
            case R.id.iv_down:
                currentSize--;
                if (currentSize==0){
                    currentSize=0;
                }
                tvSize.setText(String.valueOf(currentSize));
                graffitiView.setSize(convertSize(currentSize));
                break;
        }
    }
}
