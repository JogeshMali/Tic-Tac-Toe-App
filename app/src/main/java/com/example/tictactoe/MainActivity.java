package com.example.tictactoe;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    boolean gameactive = true;
    int activeplayer = 0;
    int[] gamestate={2,2,2,2,2,2,2,2,2};
    int[][] winposition={{0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}};

    public void playerTap(View view){
        ImageView img = (ImageView)view;
        int tappedimage = Integer.parseInt(img.getTag().toString());
        if (!gameactive){
            gamereset(view);
            return;
        }
        if (gamestate[tappedimage]==2){
            gamestate[tappedimage]=activeplayer;
            img.setTranslationY(-1000f);
            if (activeplayer == 0){
                img.setImageResource(R.drawable.x2);
                activeplayer=1;
                TextView status = findViewById(R.id.status);
                status.setText("Player-2 Turns : 0");
            }
            else {
                img.setImageResource(R.drawable.o);
                activeplayer=0;
                TextView status = findViewById(R.id.status);
                status.setText("Player-1 Turns : X");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }

        for (int[] winpositions : winposition){
            if (gamestate[winpositions[0]]==gamestate[winpositions[1]]&&
                    gamestate[winpositions[1]]==gamestate[winpositions[2]]
                    &&gamestate[winpositions[0]]!=2){
                gameactive=false;
                String winstring ;
                if (gamestate[winpositions[0]]==0){
                    winstring="Player-1 has won :X";
                }
                else {
                    winstring="Player-2 has won :0";
                }
                TextView status = findViewById(R.id.status);
                status.setText(winstring);
            }
        }
    }
    public void gamereset(View view){
    gameactive=true;
    activeplayer=0;
    Arrays.fill(gamestate, 2);
    ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
    ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
    ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
    ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
    ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
    ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
    ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
    ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
    ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
    TextView status = findViewById(R.id.status);
    status.setText("Player-1 Turns : X");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}