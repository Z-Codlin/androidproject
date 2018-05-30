package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.util.Log;

public class ChessBoardView extends View {

    private int startX = 0;
    private int startY = 0;
    private int GRID_WIDTH = -1;
    private int GRID_MOD = -1;
    private int GRID_NUM = 7;//有问题
    private static final int
    private Paint paint = null;

    private int[][] mChessPieceState = new int[GRID_NUM][GRID_NUM];
    private static final int INVALID_POS_STATE = -1;
    private static final int EXIST_PIECE_STATE = 1;
    private static final int EMPTY_PIECE_STATE = 0;
    private static int mSelectedPieceX = -1;
    private static int mSelectedPieceY = -1;

    private int CHESS_BLACK = 1;//表示棋子的颜色，1代表黑色，2代表白色，0达标没有棋子
    private int CHESS_WHITE = 2;
    private int chess_flag = 0;//用于记录上一次下的棋子的颜色，1为黑色，2为白色，0是刚开始下棋,上一次没下棋子

    public ChessBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initChessPieceState();

        DisplayMetrics dm = getResources().getDisplayMetrics();
        float width = dm.density * 300 + 0.5f;

        GRID_WIDTH = (int) width / 7;
        GRID_MOD = (int) width % 7;
        Log.e("zcl","width:"+width+",dm.heightPixels:"+dm.heightPixels+",GRID_WIDTH:"+GRID_WIDTH+",GRID_MOD:"+GRID_MOD);

        paint = new Paint();//实例化一个画笔
        paint.setAntiAlias(true);//设置画笔去锯齿，没有此语句，画的线或图片周围不圆滑

        // 为了不让棋盘的边界与屏幕的边界完全重合，需要让棋盘的边界离屏幕边界一定距离。
        if(GRID_MOD == 0){
            startX = GRID_WIDTH/2;
            startY = GRID_WIDTH/2;
            GRID_NUM--;
        }
        else{
            startX = GRID_MOD/2;
            startY = GRID_MOD/2;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(0xFF8B4726);//背景色
        paint.setColor(Color.BLACK);//画笔颜色
        for(int i=0;i<GRID_NUM;i++)
        {
            if (i < 2 || i >5) {
                canvas.drawLine(startX + 2 * GRID_WIDTH, startY + i * GRID_WIDTH, startX + (GRID_NUM - 3) * GRID_WIDTH, startY + i * GRID_WIDTH, paint);//短横线
                canvas.drawLine(startX + i * GRID_WIDTH, startY + 2 * GRID_WIDTH, startX + i * GRID_WIDTH, startY + (GRID_NUM - 3) * GRID_WIDTH, paint);//短竖线

            } else {
                canvas.drawLine(startX, startY + i * GRID_WIDTH, startX + (GRID_NUM - 1) * GRID_WIDTH, startY + i * GRID_WIDTH, paint);//横线
                canvas.drawLine(startX + i * GRID_WIDTH, startY, startX + i * GRID_WIDTH, startY + (GRID_NUM - 1) * GRID_WIDTH, paint);//竖线
            }
        }
        //使用maskFilter实现棋子的滤镜效果，使之看起来更有立体感。
        float[] dire = new float[]{1,1,1};  //光线方向
        float light = 0.5f;   //光线强度
        float spe = 6;
        float blur = 3.5f;
        EmbossMaskFilter emboss=new EmbossMaskFilter(dire,light,spe,blur);
        paint.setMaskFilter(emboss);

        //绘制棋子
        for(int i=0;i<GRID_NUM;i++)
        {
            for(int j=0;j<GRID_NUM;j++)
            {
/*                if(isValidCoordinate(i,j) && !isCenterPos(i,j))
                {
                    paint.setColor(Color.BLACK);//画笔，画棋子
                    canvas.drawCircle(startX+i*GRID_WIDTH + GRID_WIDTH/2,startY+j*GRID_WIDTH+GRID_WIDTH/2 ,GRID_WIDTH/2-3, paint);
                    Log.e("zhanchaolin-W",",Circle-X:"+(startX+i*GRID_WIDTH)+",Circle-Y:"+(startY+j*GRID_WIDTH));
                } */
                if (mChessPieceState[i][j] == EXIST_PIECE_STATE) {
                    paint.setColor(Color.BLACK);//画笔，画棋子
                    if (isSelectedPiece(i,j)) {
                        paint.setColor(Color.BULE);//画笔，画棋子
                    }
                    canvas.drawCircle(startX+i*GRID_WIDTH + GRID_WIDTH/2,startY+j*GRID_WIDTH+GRID_WIDTH/2 ,GRID_WIDTH/2-3, paint);
                    Log.e("zhanchaolin-W",",Circle-X:"+(startX+i*GRID_WIDTH)+",Circle-Y:"+(startY+j*GRID_WIDTH));
                }
            }
        }
    }
    private boolean isValidCoordinate(int corX, int corY) {
        if ((corX < 2 && corY <2)
                || (corX < 2 && corY > 4)
                || (corX > 4 && corY < 2)
                || (corX > 4 && corY > 4)) {
            return false;
        }
        return true;
    }
    private  boolean isCenterPos(int corX, int corY) {
        if (corX == 3 && corY == 3) {
            return true;
        }
        return false;
    }

    private boolean isSelectedPiece(int corX, int corY) {
        if (corX == mSelectedPieceX && corY == mSelectedPieceY) {
            return true;
        }
        return false;
    }

    private void setSelectedPiece(int corX, int corY) {
        mSelectedPieceX = corX;
        mSelectedPieceY = corY;
    }

    private boolean isExistPiece(int corX, int corY) {
        if (mChessPieceState[corX][corY] == EXIST_PIECE_STATE) {
            return true;
        }
        return false;
    }
    private boolean isEmptyPiece(int corX, int corY) {
        if (mChessPieceState[corX][corY] == EMPTY_PIECE_STATE) {
            return true;
        }
        return false;
    }
    private boolean isEndGame() {
        for(int i=0;i<GRID_NUM;i++) {
            for (int j = 0; j < GRID_NUM; j++) {
                if (isExistPiece(i, j)) {
                    if (i+2 < GRID_NUM && isExistPiece(i+1, j) && isEmptyPiece(i+2, j)){
                        return false;
                    }
                    if(i-2 >= 0 && isExistPiece(i-1, j) && isEmptyPiece(i-2, j)){
                        return false;
                    }
                    if(j+2 < GRID_NUM && isExistPiece(i, j+1) && isEmptyPiece(i, j+2)){
                        return false;
                    }
                    if(j-2 >= 0 && isExistPiece(i, j-1) && isEmptyPiece(i, j-2)){
                        return false;
                    }
                }
            }
        }
        return true;

    }

    private void initChessPieceState() {
        for(int i=0;i<GRID_NUM;i++) {
            for (int j = 0; j < GRID_NUM; j++) {
                if (isValidCoordinate(i,j)) {
                    if (isCenterPos(i,j)) {
                        mChessPieceState[i][j] = EMPTY_PIECE_STATE;
                    } else {
                        mChessPieceState[i][j] = EXIST_PIECE_STATE;
                    }
                } else {
                    mChessPieceState[i][j] = INVALID_POS_STATE;
                }
            }
        }
    }


    //重写View的监听触摸事件的方法
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float touchX = event.getX();
        float touchY = event.getY();

        if(touchX < startX || touchX>startX+(GRID_NUM-1)*GRID_WIDTH || touchY < startY || touchY>startY+(GRID_NUM-1)*GRID_WIDTH)
        {//点击到棋盘以外的位置

        }
        else
        {
            //根据点击的位置，从而获知在棋盘上的哪个位置，即是数组的脚标
            int index_x = (int)((touchX-startX)/GRID_WIDTH);
            int index_y = (int)((touchY-startY)/GRID_WIDTH);
            if (isExistPiece(index_x, index_y)) {
                setSelectedPiece(index_x, index_y); //设置选中棋子光标
            } else if (isEmptyPiece(index_x, index_y)) {
                if (index_x == mSelectedPieceX && Math.abs(mSelectedPieceY - index_y) == 2) {
                    int midcorY = mSelectedPieceY + (index_y - mSelectedPieceY)/2;
                    if (isExistPiece(index_x, midcorY)){ //进入此条件，棋子开始移动
                        mChessPieceState[index_x][midcorY] = EMPTY_PIECE_STATE; //下棋规则第一步，清除中间棋子
                        mChessPieceState[mSelectedPieceX][mSelectedPieceY] = EMPTY_PIECE_STATE; //移动棋子
                        mChessPieceState[index_x][index_y] = EXIST_PIECE_STATE;
                        setSelectedPiece(index_x, index_y); //重新设置选中棋子光标
                    }
                } else if (index_y == mSelectedPieceY && Math.abs(mSelectedPieceX - index_x) == 2){
                    int midcorX = mSelectedPieceX + (index_x - mSelectedPieceX)/2;
                    if (isExistPiece(midcorX, index_y)){ //进入此条件，棋子开始移动
                        mChessPieceState[index_x][midcorY] = EMPTY_PIECE_STATE; //下棋规则第一步，清除中间棋子
                        mChessPieceState[mSelectedPieceX][mSelectedPieceY] = EMPTY_PIECE_STATE; //移动棋子
                        mChessPieceState[index_x][index_y] = EXIST_PIECE_STATE;
                        setSelectedPiece(index_x, index_y); //重新设置选中棋子光标
                    }
                }
            }
            Log.e("zhanchaolin","index_x:"+index_x+",index_y:"+index_y+",touchX:"+touchX+",startX:"+startX+",GRID_WIDTH:"+GRID_WIDTH+",touchY:"+touchY+",startY:"+startY);

            if(chess_flag == 0)
            {//此句表示在最开始下棋的时候每次都是黑棋先下
                chess[index_x][index_y] = CHESS_BLACK;
                chess_flag = CHESS_BLACK;

            }else if( chess_flag == CHESS_BLACK && chess[index_x][index_y] == 0)
            {
                chess[index_x][index_y] = CHESS_WHITE;
                chess_flag = CHESS_WHITE;
            }else if(chess_flag == CHESS_WHITE && chess[index_x][index_y] == 0)
            {
                chess[index_x][index_y] = CHESS_BLACK;
                chess_flag = CHESS_BLACK;
            }
        }

        invalidate();//点击完成后，通知重绘即再次执行onDraw方法
        return super.onTouchEvent(event);
    }
}
