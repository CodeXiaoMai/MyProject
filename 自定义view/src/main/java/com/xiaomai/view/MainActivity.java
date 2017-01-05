
package com.xiaomai.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xiaomai.view.ajibenxingzhuang.DrawArcView;
import com.xiaomai.view.ajibenxingzhuang.DrawCircleView;
import com.xiaomai.view.ajibenxingzhuang.DrawColorView;
import com.xiaomai.view.ajibenxingzhuang.DrawLineView;
import com.xiaomai.view.ajibenxingzhuang.DrawOvalView;
import com.xiaomai.view.ajibenxingzhuang.DrawPointView;
import com.xiaomai.view.ajibenxingzhuang.DrawRectView;
import com.xiaomai.view.ajibenxingzhuang.PaintTest;
import com.xiaomai.view.ajibenxingzhuang.PieChartView;
import com.xiaomai.view.bCanvasDemo.CanvasRotateView;
import com.xiaomai.view.bCanvasDemo.CanvasScaleView;
import com.xiaomai.view.bCanvasDemo.CanvasSkewView;
import com.xiaomai.view.bCanvasDemo.CanvasTranslateView;
import com.xiaomai.view.javabean.PieData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DrawColorView drawColorView = new DrawColorView(this);
        DrawPointView drawPointView = new DrawPointView(this);
        DrawLineView drawLineView = new DrawLineView(this);
        DrawRectView drawRectView = new DrawRectView(this);
        DrawOvalView drawOvalView = new DrawOvalView(this);
        DrawCircleView drawCircleView = new DrawCircleView(this);
        DrawArcView drawArcView = new DrawArcView(this);
        PaintTest paintTest = new PaintTest(this);

        PieChartView pieChartView = new PieChartView(this);
        List<PieData> datas = new ArrayList<>();
        datas.add(new PieData("", 20, 0xff00ffff));
        datas.add(new PieData("", 40, 0xffffff00));
        datas.add(new PieData("", 80, 0xffffffff));
        datas.add(new PieData("", 220, 0xff00ffff));
        datas.add(new PieData("", 24, 0xffff00ff));
        pieChartView.setData(datas);

        CanvasTranslateView canvasTranslate = new CanvasTranslateView(this);
        CanvasScaleView canvasScaleView = new CanvasScaleView(this);
        CanvasRotateView canvasRotateView = new CanvasRotateView(this);
        CanvasSkewView canvasSkewView = new CanvasSkewView(this);
        setContentView(canvasSkewView);

    }
}
