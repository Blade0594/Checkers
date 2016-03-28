package com.mygdx.game;

/**
 * Created by Dmitry on 27.03.2016.
 * In this class we will store all moves which computer can do
 * and after comparison we will choose the best solution
 */
public class Choose_ai {
    int pawn_i = 0;
    int pawn_j = 0;
    int way_move ; //1 - left, 2 - right
    int evaluation  = 0;
    int end_i = 0;
    int end_j = 0;
    int direction = -5;
    Choose_ai(int i, int j, int way_move1)
    {
        pawn_i = i;
        pawn_j = j;
        way_move = way_move1;
    }
    Choose_ai(int i, int j)
    {
        pawn_i = i;
        pawn_j = j;
    }
    public void set_end(int i_next, int j_next)
    {
        end_i = i_next;
        end_j = j_next;
    }
}
