package com.example.myapplication;

import android.graphics.PointF;

import java.util.ArrayList;

/**
 * Created by 任昊 on 2018/5/16.
 */

public class check {
    public static boolean checkpos(ArrayList<PointF> pos){
        for (PointF p:
                pos) {
            for (PointF p1:
                    pos) {
                if((p.x == p1.x &&p.y -p1.y==-140)
                        ||(p.x == p1.x &&p.y -p1.y==140)
                        ||(p.y == p1.y &&p.x -p1.x==-140)
                        ||(p.y == p1.y &&p.x -p1.x==-140)){
                    return true;
                }
            }
        }
        return false;
    }
}
