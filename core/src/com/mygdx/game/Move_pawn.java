package com.mygdx.game;

/**
 * Created by Dmitry on 18.02.2016.
 */
public class Move_pawn {
    public Boolean have_to_hit(int[][] mas_pawn) //Общее сканирование
    {
        for(int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if(mas_pawn[i][j] == 1)
                {
                    if(i >= 2 && j >= 2)
                    {
                        if(mas_pawn[i-1][j-1] == 2 && mas_pawn[i-2][j-2] == 0) //<^
                        {
                            return true;
                        }
                    }
                    if(i >= 2 && j <= 5)
                    {
                        if(mas_pawn[i-1][j+1] == 2 && mas_pawn[i-2][j+2] == 0) //>^
                        {
                            return true;
                        }
                    }
                    if(i <= 5 && j <= 5)
                    {
                        if(mas_pawn[i+1][j+1] == 2 && mas_pawn[i+2][j+2] == 0) //>\/
                        {
                            return true;
                        }
                    }
                    if(i <= 5 && j >= 2)
                    {
                        if(mas_pawn[i+1][j-1] == 2 && mas_pawn[i+2][j-2] == 0) //<\/
                        {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    private Boolean check_collision(int[][] mas_pawn, int i_start, int j_start)
    {
        if(i_start >= 2 && j_start >= 2)
        {
            if(mas_pawn[i_start-1][j_start-1] == 2 && mas_pawn[i_start-2][j_start-2] == 0) //<^
            {
                return true;
            }
        }
        if(i_start >= 2 && j_start <= 5)
        {
            if(mas_pawn[i_start-1][j_start+1] == 2 && mas_pawn[i_start-2][j_start+2] == 0) //>^
            {
                return true;
            }
        }
        if(i_start <= 5 && j_start <= 5)
        {
            if(mas_pawn[i_start+1][j_start+1] == 2 && mas_pawn[i_start+2][j_start+2] == 0) //>\/
            {
                return true;
            }
        }
        if(i_start <= 5 && j_start >= 2)
        {
            if(mas_pawn[i_start+1][j_start-1] == 2 && mas_pawn[i_start+2][j_start-2] == 0) //<\/
            {
                return true;
            }
        }
        return false;
    }
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
   private Boolean check(int[][] mas_pawn, int i_start, int j_start, int i_finish, int j_finish)
   {
       if(mas_pawn[i_finish][j_finish] != 0) return false;
       if((i_finish == i_start-1) && (j_finish == j_start-1) ) return true;
       if((i_finish == i_start-1) && (j_finish == j_start+1) ) return true;
       return false;
   }
    public int move(int[][] mas_pawn, int i_start, int j_start, int i_finish, int j_finish)
    {
        if(check_collision(mas_pawn, i_start, j_start) == false)
        {
            if(check(mas_pawn, i_start, j_start, i_finish, j_finish) == true)
            {
                mas_pawn[i_finish][j_finish] = 1;
                mas_pawn[i_start][j_start] = 0;
                return 0;
            }
            else  //put in previous position
            {
                mas_pawn[i_start][j_start] = 1;
                return 5;
            }
        }
        if(check_collision(mas_pawn, i_start, j_start) == true) //if(have_to_hit(mas_pawn)  23:52
        {
            if(j_start >= 1 && i_start >= 1)
            {
                if(mas_pawn[i_start-1][j_start-1] == 2 ) //&& mas_pawn[i_finish][j_finish] == 0
                {
                    mas_pawn[i_start][j_start] = 0;
                   // mas_pawn[i_start-1][j_start-1] = 0;
                    mas_pawn[i_finish+1][j_finish+1] = 0;  //1:27
                    mas_pawn[i_finish][j_finish] = 1;  ///move to finish
                    if(have_to_hit(mas_pawn) == true) return 1;
                    if(have_to_hit(mas_pawn) == false) return 0; //move of AI
                }
            }
            if(j_start <= 6 && i_start >= 1)
            {
                if(mas_pawn[i_start-1][j_start+1] == 2 ) //&& mas_pawn[i_finish][j_finish] == 0
                {
                    mas_pawn[i_start][j_start] = 0;
                 //   mas_pawn[i_start-1][j_start+1] = 0;
                    mas_pawn[i_finish+1][j_finish-1] = 0; // 1:21 night
                    mas_pawn[i_finish][j_finish] = 1;
                    if(have_to_hit(mas_pawn) == true) return 1;
                    if(have_to_hit(mas_pawn) == false) return 0; //move of AI
                }
            }
            if(j_start <= 6) //correct later the boundaries
            {
                if(mas_pawn[i_start+1][j_start+1] == 2)  // && mas_pawn[i_finish][j_finish] == 0
                {
                    mas_pawn[i_start][j_start] = 0;
                   // mas_pawn[i_start+1][j_start+1] = 0;
                    mas_pawn[i_finish-1][j_finish-1] = 0;  //1:27
                    mas_pawn[i_finish][j_finish] = 1;
                    if(have_to_hit(mas_pawn) == true) return 1;
                    if(have_to_hit(mas_pawn) == false) return 0; //move of AI
                }
            }
            //left and down - move player
           // if(j_start >= 1)
           // {          // && i_finish == i_start+2 && j_finish == j_start-2
                if(mas_pawn[i_start+1][j_start-1] == 2)  // mas_pawn[i_finish][j_finish] == 0
                {
                    mas_pawn[i_start][j_start] = 0; //move from this place
                  //  mas_pawn[i_start+1][j_start-1] = 0; //kill the enemy
                    mas_pawn[i_finish-1][j_finish+1] = 0; //new 0-50
                    mas_pawn[i_finish][j_finish] = 1; //finishing moving
                    if(have_to_hit(mas_pawn) == true) return 1;
                    if(have_to_hit(mas_pawn) == false) return 0; //move of AI
                }
            //}
        }
        return 5;

    }

}