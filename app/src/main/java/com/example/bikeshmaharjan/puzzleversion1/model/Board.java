package com.example.bikeshmaharjan.puzzleversion1.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by bikeshmaharjan on 4/17/18.
 */

public class Board {

    private static final int NUM_TILE = 3;
    private int size = NUM_TILE*NUM_TILE;
    private int buttonClickedIndex = 0;
    private int emptyLocationIndex = 0;
    private boolean gameOver = false;
    List<Integer> tiles = new ArrayList<Integer>();


    public Board() {
        for(int i=0;i<size;i++){
            if(i == 8){
                tiles.add(i, 0);
            }
            else {
                tiles.add(i, i + 1);
            }


        }
    }
    public List<Integer> returnTilePosition(){
        return tiles;
    }

    public List<Integer> createListOfRandomTilePositions()
    {
        Collections.shuffle(tiles);

        return tiles;

    }

    public List<Integer> tryToMoveTile(int pos)
    {

        checkIfTileCanMove(pos);

        buttonClickedIndex=pos;
        // If an empty space is available (the tile can move!)
        if (emptyLocationIndex != -1)
        {
            swapTwoTiles();

        }
        return tiles;

    }

    private int checkIfTileCanMove(int pos)
    {
        // identify potential locations to move to
        int[] locationsToCheck = new int[4];
        locationsToCheck[0] = pos - 3; // above
        locationsToCheck[1] = pos + 3; // below
        locationsToCheck[2] = pos - 1; // to left
        locationsToCheck[3] = pos + 1; // to right

        if (pos == 2 || pos == 5) {
            // these buttons are special cases where buttonClickedIndex + 1 can actually be a free space
            locationsToCheck[3] = -1; // negative index is automatically invalid
        }

        // check the valid locations specified above
        for (int i = 0; i < 4; i++)
        {
            // can only check valid indexes (>=0)
            if (locationsToCheck[i] >= 0 && locationsToCheck[i] < 9)
            {
                if (tiles.get(locationsToCheck[i])== 0)
                {
                    // button occupying location is invisible, e.g. empty space
                    emptyLocationIndex = locationsToCheck[i];
                    return locationsToCheck[i];
                }
            }
        }
        emptyLocationIndex = -1;
        Log.e("emptylocation",String.valueOf(emptyLocationIndex));

        return -1; // -1: flag for no empty locations found
    }

    public void swapTwoTiles(){
        // swap the tile positions in memory
        Log.e("Button",String.valueOf(tiles.get(emptyLocationIndex))+String.valueOf(tiles.get(buttonClickedIndex)));
        Collections.swap(tiles,emptyLocationIndex,buttonClickedIndex);
        Log.e("Button",String.valueOf(tiles.get(emptyLocationIndex))+String.valueOf(tiles.get(buttonClickedIndex)));

    }

    public boolean checkForWin() {
        // assume game is won
        boolean win = true;

        // check the order of the tiles:
        for (int i = 0; i < size -1; i++) {
            // if any tile is out of order, game is NOT won
            if (tiles.get(i) != i + 1) {
                win = false;
                break;
            }
        }

        // evaluate result of check
        if (win) {
            gameOver = true;
        }

        return gameOver;
    }
}
