package com.example.connect3game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int[] arr={-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int[][] winning={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean game_active=true;
    int turn=0;

    public void dropIn(View view){
        ImageView counter=(ImageView) view;
        //Log.i("Tag",counter.getTag().toString());
        int number=Integer.parseInt(counter.getTag().toString());
        if(arr[number]!=-1 || !game_active)
            return;
        arr[number]=turn;
        counter.setTranslationY(-1500);
        if(turn==0) {
            counter.setImageResource(R.drawable.blue);
            turn=1;
        }
        else {
            counter.setImageResource(R.drawable.red);
            turn=0;
        }
        counter.animate().translationYBy(1500).setDuration(200);
        for(int i=0;i<8;i++){
            if(arr[winning[i][0]]==arr[winning[i][1]] && arr[winning[i][1]]==arr[winning[i][2]] && arr[winning[i][0]] != -1) {
                String ans;
                if (arr[number] == 1)
                    ans = "Red";
                else
                    ans = "Blue";
                game_active=false;
                Button play = (Button)findViewById(R.id.playButton);
                TextView text = (TextView)findViewById(R.id.winningTextView);
                text.setText(ans+" has won!");
                text.setVisibility(View.VISIBLE);
                play.setVisibility(View.VISIBLE);
                return;
            }
        }
        int i;
        for(i=0;i<9;i++)
            if(arr[i]==-1)
                break;
        if(i==9){
            Button play = (Button)findViewById(R.id.playButton);
            TextView text = (TextView)findViewById(R.id.winningTextView);
            text.setText("Game has drawn. Play again...");
            text.setVisibility(View.VISIBLE);
            play.setVisibility(View.VISIBLE);
        }
    }

    public void playAgain(View view){
        Log.i("Info","Button pressed");
        Button play = (Button)findViewById(R.id.playButton);
        TextView text = (TextView)findViewById(R.id.winningTextView);
        play.setVisibility(View.INVISIBLE);
        text.setVisibility(View.INVISIBLE);

        GridLayout grid=(GridLayout)findViewById(R.id.gridLayout);
        for(int k=0;k<grid.getChildCount();k++){
            ImageView counter=(ImageView)grid.getChildAt(k);
            counter.setImageDrawable(null);
        }
        for(int j=0;j<9;j++)
            arr[j] = -1;
        turn=0;
        game_active=true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView bart=(ImageView)findViewById(R.id.bart);
        bart.animate().rotationYBy(1440).scaleX(0.5f).scaleY(0.5f).setDuration(2000);
        bart.animate().alpha(0);
        //bart.animate().translationXBy(10000).setDuration(1000);
    }
}
