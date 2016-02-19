package com.mygdx.game;

/**
 * Created by Dmitry on 18.02.2016.
 */
public class AI {

    private Boolean simple_move = true; //if there is at least 1 possible to hit - just hit without moving
    private Boolean check_hit(int[][] mas_pawn)
    {
        for(int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if(mas_pawn[i][j] == 2)
                {
                    if(i >= 2 && j >=2)
                    {
                        if(mas_pawn[i-1][j-1] == 1 && mas_pawn[i-2][j-2] == 0) //<^
                        {
                            simple_move = false;
                            return true;
                        }
                    }
                    if(i >= 2 && j <= 5)
                    {
                        if(mas_pawn[i-1][j+1] == 1 && mas_pawn[i-2][j+2] == 0) //>^
                        {
                            simple_move = false;
                            return true;
                        }
                    }
                   if(i <= 5 && j <= 5)
                   {
                       if(mas_pawn[i+1][j+1] == 1 && mas_pawn[i+2][j+2] == 0) //>\/
                       {
                           simple_move = false;
                           return true;
                       }
                   }
                   if(i <= 5 && j >= 2)
                   {
                       if(mas_pawn[i+1][j-1] == 1 && mas_pawn[i+2][j-2] == 0) //<\/
                       {
                           simple_move = false;
                           return true;
                       }
                   }
                }
            }
        }
        return false;
    }
    public int[][] hit(int[][] mas_pawn)
    {
        int a ; //i
        int b ; //j
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                a = i; b = j;
                if(mas_pawn[i][j] == 2)
                {
                    if(b <= 5 && a <= 5) //hit to right
                    {
                        if (mas_pawn[a + 1][b + 1] == 1 && mas_pawn[a + 2][b + 2] == 0) //hit in direction >\/
                        {
                            mas_pawn[a][b] = 0;
                            mas_pawn[a + 1][b + 1] = 0;
                            mas_pawn[a + 2][b + 2] = 2;
                            return mas_pawn;
                        }
                    }
                    if(a >= 2 && b <= 5)
                    {
                        if (mas_pawn[a - 1][b + 1] == 1 && mas_pawn[a - 2][b + 2] == 0) //hit ^>
                        {
                            mas_pawn[a][b] = 0;
                            mas_pawn[a - 1][b + 1] = 0;
                            mas_pawn[a - 2][b + 2] = 2;

                            return mas_pawn;
                        }
                    }
                    if(b >= 2 && a <= 5) //hit to left
                    {
                        if (mas_pawn[a + 1][b - 1] == 1 && mas_pawn[a + 2][b - 2] == 0)  //<\/
                        {
                            mas_pawn[a][b] = 0;
                            mas_pawn[a + 1][b - 1] = 0; //remove the pawn of player
                            mas_pawn[a + 2][b - 2] = 2;
                            return mas_pawn;
                        }
                    }
                    if(a >= 2 && b >= 2)
                    {
                        if (mas_pawn[a - 1][b - 1] == 1 && mas_pawn[a - 2][b - 2] == 0) //<^
                        {
                            mas_pawn[a][b] = 0; //remove enemy from previous place
                            mas_pawn[a - 1][b - 1] = 0; //remove pawn of computer
                            mas_pawn[a - 2][b - 2] = 2; //move pawn of enemy
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
        simple_move = true;
        for(int k = 0; k < 12; k++)
        {
            if(check_hit(mas_pawn) == true)
                hit(mas_pawn);
            else break;
        }

        if(simple_move == true)
        {
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
                        if(b >= 1 && a <= 6) //move left
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
        }
        return mas_pawn;
    }
}
