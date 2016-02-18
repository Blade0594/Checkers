package com.mygdx.game;

/**
 * Created by Dmitry on 18.02.2016.
 */
public class Move_pawn {
    public int get_i(float y)
    {
        if(y >= 50 && y <= 100) return 7;
        if(y >= 100 && y <= 150) return 6;
        if(y >= 150 && y <= 200) return 5;
        if(y >= 200 && y <= 250) return 4;
        if(y >= 250 && y <= 300) return 3;
        if(y >= 300 && y <= 350) return 2;
        if(y >= 350 && y <= 400) return 1;
        if(y >= 400 && y <= 450) return 0;
        return 100;
    }
    public int get_j(float x)
    {
        if(x >= 100 && x <= 150) return 0;
        if(x >= 150 && x <= 200) return 1;
        if(x >= 200 && x <= 250) return 2;
        if(x >= 250 && x <= 300) return 3;
        if(x >= 300 && x <= 350) return 4;
        if(x >= 350 && x <= 400) return 5;
        if(x >= 400 && x <= 450) return 6;
        if(x >= 450 && x <= 500) return 7;
        return 100;
    }
    public Boolean move(int[][] mas_pawn, int i_start, int j_start, int i_finish, int j_finish)
    {
        if((i_finish == i_start-1) && (j_finish == j_start-1) ) return true;
        if((i_finish == i_start-1) && (j_finish == j_start+1) ) return true;
        return false;
    }
}
