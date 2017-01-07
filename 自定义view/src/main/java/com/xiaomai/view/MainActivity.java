
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
import com.xiaomai.view.cCanvasDrawPictureAndText.CanvasDrawPictureView;
import com.xiaomai.view.cCanvasDrawPictureAndText.CanvasDrawPosTextView;
import com.xiaomai.view.cCanvasDrawPictureAndText.CanvasDrawTextView;
import com.xiaomai.view.dCanvasDrawPathView.PathViewA;
import com.xiaomai.view.dCanvasDrawPathView.PathViewB;
import com.xiaomai.view.dCanvasDrawPathView.PathViewC;
import com.xiaomai.view.dCanvasDrawPathView.PathViewD;
import com.xiaomai.view.dCanvasDrawPathView.RadarView;
import com.xiaomai.view.javabean.PieData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
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

        CanvasDrawPictureView canvasDrawPictureView = new CanvasDrawPictureView(this);

//        final CheckView checkView = (CheckView) findViewById(R.id.checkView);
//        // setContentView(checkView);
//        findViewById(R.id.check).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                checkView.check();
//            }
//        });
//        findViewById(R.id.uncheck).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                checkView.unCheck();
//            }
//        });
//

        CanvasDrawTextView canvasDrawTextView = new CanvasDrawTextView(this);
        CanvasDrawPosTextView canvasDrawPosTextView = new CanvasDrawPosTextView(this);
        PathViewA pathViewA = new PathViewA(this);
        PathViewB pathViewB = new PathViewB(this);
        PathViewC pathViewC = new PathViewC(this);
        PathViewD pathViewD = new PathViewD(this);
        RadarView radarView = new RadarView(this);
        setContentView(radarView);
    }
}
