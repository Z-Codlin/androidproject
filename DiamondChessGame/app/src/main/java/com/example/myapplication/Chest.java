package com.example.myapplication;

import android.graphics.PointF;

import java.util.ArrayList;

/**
 * Created by 任昊 on 2018/4/8.
 */

public class Chest {
    public ArrayList<PointF> pos = new ArrayList<PointF>(){
    };
    public ArrayList<PointF> posRemove = new ArrayList<PointF>();

    public  Chest(){
        posRemove.add(new PointF(540,1000));
        pos.add( new PointF(540-140,1000));
        pos.add( new PointF(540-280,1000));
        pos.add( new PointF(540-420,1000));
        pos.add(new PointF(540+140,1000));
        pos.add(new PointF(540+280,1000));
        pos.add(new PointF(540+420,1000));
        pos.add(new PointF(540,1000+140));
        pos.add( new PointF(540-140,1000+140));
        pos.add( new PointF(540-280,1000+140));
        pos.add( new PointF(540-420,1000+140));
        pos.add(new PointF(540+140,1000+140));
        pos.add(new PointF(540+280,1000+140));
        pos.add(new PointF(540+420,1000+140));
        pos.add(new PointF(540,1000-140));
        pos.add( new PointF(540-140,1000-140));
        pos.add( new PointF(540-280,1000-140));
        pos.add( new PointF(540-420,1000-140));
        pos.add(new PointF(540+140,1000-140));
        pos.add(new PointF(540+280,1000-140));
        pos.add(new PointF(540+420,1000-140));
        pos.add(new PointF(540,1000+280));
        pos.add(new PointF(540+140,1000+280));
        pos.add(new PointF(540-140,1000+280));
        pos.add(new PointF(540,1000+420));
        pos.add(new PointF(540+140,1000+420));
        pos.add(new PointF(540-140,1000+420));
        pos.add(new PointF(540,1000-280));
        pos.add(new PointF(540+140,1000-280));
        pos.add(new PointF(540-140,1000-280));
        pos.add(new PointF(540,1000-420));
        pos.add(new PointF(540+140,1000-420));
        pos.add(new PointF(540-140,1000-420));
    }
}
