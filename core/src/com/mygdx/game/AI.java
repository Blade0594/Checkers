package com.mygdx.game;

/**
 * Created by Dmitry on 18.02.2016.
 */
public class AI {
    private Boolean hit = false;
    public int[][] check_hit(int[][] mas_pawn)
    {
        int a ; //i
        int b ; //j
        int tepm_a;
        int temp_b;
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                a = i; b = j;
                if(mas_pawn[i][j] == 2)
                {
                    if(b <= 5 && a <= 5) //hit to right
                    {
                        if(mas_pawn[a+1][b+1] == 1 && mas_pawn[a+2][b+2] == 0)
                        {
                            mas_pawn[a+1][b+1] = 0;
                            mas_pawn[a+2][b+2] = 2;
                            hit = true;
                            return mas_pawn;
                        }
                    }
                    if(b >= 2 && a <= 5) //hit to left
                    {
                        if(mas_pawn[a+1][b-1] == 1 && mas_pawn[a+2][b-2] == 0)
                        {
                            mas_pawn[a+1][b-1] = 0; //remove the pawn of player
                            mas_pawn[a+2][b-2] = 2;
                            return mas_pawn;
                        }
                    }
                }

            }
        }
        return mas_pawn;
    }
    public int[][]  move(int[][] mas_pawn)
    {
        hit = false;
        check_hit(mas_pawn);
        if(hit == true)
        return mas_pawn;
        int a ; // i
        int b ; // j
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                if(mas_pawn[i][j] == 2)
                {
                    a = i; b = j; //array bounds
                    if(a <= 6 && b <= 6)
                    {
                        if(mas_pawn[a+1][b+1] == 0) //empty place on the border
                        {
                            mas_pawn[a][b] = 0; //delete pawn from previous position
                            mas_pawn[a+1][b+1] = 2;
                            return mas_pawn;
                        }
                    }
                    if(b >= 1) //move left
                    {
                        if(mas_pawn[a+1][b-1] == 0) //empty place on the border
                        {
                            mas_pawn[a][b] = 0; //delete pawn from previous position
                            mas_pawn[a+1][b-1] = 2;
                            return mas_pawn;
                        }
                    }
                }
            }
        }
        return mas_pawn;
    }
}
