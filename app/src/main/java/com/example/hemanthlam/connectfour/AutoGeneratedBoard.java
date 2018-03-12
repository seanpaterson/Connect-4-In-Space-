package com.example.hemanthlam.connectfour;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import java.util.List;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

/**
 * Created by sonam on 3/1/18.
 */

public class AutoGeneratedBoard extends GameActivity {
    RelativeLayout relativeLayoutChild;FrameLayout frameLayout;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_generated_board);
        relativeLayout = findViewById(R.id.GAME_1_RELATIVE_LAYOUT);
        relativeLayout.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener()
        {
            @Override
            public void onGlobalLayout()
            {
                // gets called after layout has been done but before display.

                relativeLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                int width = relativeLayout.getWidth();
                int height = relativeLayout.getHeight();

                createBoard(width,height);
                // set click listeners for all the discs on board
                setClickListener();
                // process winner and highlight player turn
                // initialize game end screen
                winnerProcessing(width);
            }
        });
    }

    public void winnerProcessing(int width){
        generatePlayerNamesAndIcons(this.p1Name, this.p1Color, 1, (RelativeLayout) findViewById(R.id.GAME_1_RELATIVE_LAYOUT),getwidthOfDisc(width));
        generatePlayerNamesAndIcons(this.p2Name, this.p2Color, 2, (RelativeLayout) findViewById(R.id.GAME_1_RELATIVE_LAYOUT),getwidthOfDisc(width));

        // Required to draw the edge around player 1's icon when the game starts
        if (this.p1HighlightView != null)
            this.p1HighlightView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) { thisActivity.drawCircleEdges((ImageView)view, thisActivity.p1Color); }
            });

        // Initialize Game End Screen
        thisActivity.initializeGameEndScreen();
        //thisActivity.drawCircleEdges((ImageView)view, thisActivity.p1Color);
    }

    public void setClickListener(){
        box = findViewById(R.id.GAME_1_INNER_RELATIVE);
        gameBoard = new Board(getIntent().getStringExtra("Board"));
        for(int i = 0; i < box.getChildCount();++i){
            final int I = i;
            box.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    placeDisc(I);
                }
            });
        }
    }

    public int getwidthOfDisc(int width){
        int boardCols= getIntent().getIntExtra("width",7);
        int margin = width/boardCols - 5;
        return margin;
    }

    /**
     * creates an auto generated board for all the board sizes namely 10*8, 7*6, 8*7
     * @param width
     * @param height
     */
    public void createBoard(int width,int height){
        frameLayout = new FrameLayout(this);
        frameLayout.setId(R.id.frameLayout);
        LinearLayout.LayoutParams linLayoutParam1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        frameLayout.setLayoutParams(linLayoutParam1);

        relativeLayoutChild = new RelativeLayout(this);
        relativeLayoutChild.setLayoutParams(linLayoutParam1);

        int boardCols = getIntent().getIntExtra("width",7);
        int boardRows = getIntent().getIntExtra("height",6);
        int color = getIntent().getIntExtra("Color",Color.CYAN);
        int margin = getwidthOfDisc(width);
        frameLayout.addView(relativeLayoutChild);
        LinearLayout layout;
        LinearLayout.LayoutParams linLayoutParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        ImageView disc;

        List<LinearLayout> arrCols = new ArrayList<>();
        for(int i=0;i<boardCols;i++){
            layout = new LinearLayout(this);
            layout.setBackgroundColor(color);
            layout.setOrientation(LinearLayout.VERTICAL);
            linLayoutParam.width = margin;
            linLayoutParam.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            int width1= linLayoutParam.width;
            margin = width1 ;
            linLayoutParam.setMarginStart(margin*i);
            layout.setLayoutParams(linLayoutParam);
            arrCols.add(layout);
            relativeLayoutChild.addView(layout);

            for(int j=0;j<boardRows;j++){
                disc = new ImageView(this);
                linLayoutParam.width =width1-5;
                linLayoutParam.height = width1-3;
                disc.setLayoutParams(linLayoutParam);
                linLayoutParam.setMarginStart(5);
                linLayoutParam.setMargins(5,0,5,3);
                disc.setBackgroundResource(R.drawable.white);
                layout.addView(disc);
            }
        }
        relativeLayout.addView(frameLayout);
        relativeLayoutChild.setId(R.id.GAME_1_INNER_RELATIVE);
        relativeLayoutChild.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        relativeLayoutChild.setGravity(Gravity.BOTTOM);
    }
}

