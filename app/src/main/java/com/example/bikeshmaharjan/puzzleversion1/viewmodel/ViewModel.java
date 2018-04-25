package com.example.bikeshmaharjan.puzzleversion1.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayMap;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.bikeshmaharjan.puzzleversion1.BR;
import com.example.bikeshmaharjan.puzzleversion1.model.Board;

import java.util.List;

/**
 * Created by bikeshmaharjan on 4/16/18.
 */

public class ViewModel extends BaseObservable{


    public Board board;
    public ObservableArrayMap<String,String> tiles = new ObservableArrayMap<>();
    public ObservableField<String> winmessage = new ObservableField<>();
    public ObservableField<String> win = new ObservableField<>();

    public ViewModel() {
        board = new Board();
        win.set("false");
        winmessage.set("Click New game");
        List<Integer> previousTiles = board.returnTilePosition();
        for (int i=0;i<9;i++){
            tiles.put(String.valueOf(i),String.valueOf(previousTiles.get(i)));
        }
        notifyPropertyChanged(BR._all);

    }

    public String getMessage() {
        return winmessage.get();
    }

    public void setMessage(String message) {
        this.winmessage.set(message);
        notifyPropertyChanged(BR._all);
    }

    public void newGameSelected(){
        Log.e("NewGame","Button");
        win.set("false");
        winmessage.set("Move the tiles");
        List<Integer> previousTiles = board.createListOfRandomTilePositions();
            for (int i=0;i<9;i++) {
                tiles.put(String.valueOf(i), String.valueOf(previousTiles.get(i)));
            }
        notifyPropertyChanged(BR._all);
    }

    public void tileClicked(View view){
        Log.e("Tile","Button");
        // identify which tile was clicked
        for (int i = 0; i < 9; i++) {
            if( ((Button)view).getText().toString() == tiles.get(String.valueOf(i))) {
                List<Integer> previousTiles = board.tryToMoveTile(i);
                for(int j =0;j<9;j++) {
                    tiles.put(String.valueOf(j),String.valueOf(previousTiles.get(j)));
                }

                // Id's matched - tile i was clicked
                break;
            }
        }
        if(board.checkForWin()){

            winmessage.set("Congratulations!! You've won. click new game again");
            win.set("true");

            notifyPropertyChanged(BR._all);
        }

    }
}
