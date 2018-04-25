package com.example.bikeshmaharjan.modelpuzzle.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bikeshmaharjan.modelpuzzle.Model.Board;
import com.example.bikeshmaharjan.modelpuzzle.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button[] buttons = new Button[9];
    TextView message;
    Button button;
    Board board = new Board();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttons[0] = (Button) findViewById(R.id.button1);
        buttons[1] = (Button) findViewById(R.id.button2);
        buttons[2] = (Button) findViewById(R.id.button3);
        buttons[3] = (Button) findViewById(R.id.button4);
        buttons[4] = (Button) findViewById(R.id.button5);
        buttons[5] = (Button) findViewById(R.id.button6);
        buttons[6] = (Button) findViewById(R.id.button7);
        buttons[7] = (Button) findViewById(R.id.button8);
        buttons[8] = (Button) findViewById(R.id.button9);

//        initialize();
        message = (TextView) findViewById(R.id.textView_message);
        button = (Button) findViewById(R.id.button_newGame);
    }

//    public void initialize(){
//        for(int i = 0;i<9;i++){
//            buttons[i].setClickable(true);
//        }
//    }

    public void newGameSelected(View view){
        Log.e("NewGame","Button");

        List<Integer> tiles = board.createListOfRandomTilePositions();
        for(int i =0;i<9;i++) {
            buttons[i].setText(String.valueOf(tiles.get(i)));
            if(tiles.get(i)==0){
                buttons[i].setVisibility(View.INVISIBLE);
            } else {
                buttons[i].setVisibility(View.VISIBLE);
            }
        }

//        board.createListOfRandomTilePositions();
    }

    public void tileClicked(View view){

        // identify which tile was clicked
        for (int i = 0; i < 9; i++) {
            if (view.getId() == buttons[i].getId()) {
                List<Integer> tiles = board.tryToMoveTile(i);
                for(int j =0;j<9;j++) {
                    buttons[j].setText(String.valueOf(tiles.get(j)));
                    if(tiles.get(j)==0){
                        buttons[j].setVisibility(View.INVISIBLE);
                    } else {
                        buttons[j].setVisibility(View.VISIBLE);
                    }
                }

                // Id's matched - tile i was clicked
            break;
            }
        }
        if(board.checkForWin()){

            message.setText(R.string.playAgainMessage);
        }

    }
}
