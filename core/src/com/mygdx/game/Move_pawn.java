package com.mygdx.game;

/**
 * Created by Dmitry on 18.02.2016.
 */
public class Move_pawn {
    public Boolean check_hit(int[][] mas_pawn) //if player have to hit
    {
        int a ; //i
        int b ; //j
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                a = i; b = j;

                if(mas_pawn[i][j] == 1)
                {
                    if(a >= 2 && b <= 5)
                    {
                        if(mas_pawn[a-1][b+1] == 2 && mas_pawn[a-2][b+2] == 0)
                        {
                            return true; //the player have to strike
                        }
                    }
                    if(a >= 2 && b >= 2)
                    {
                        if(mas_pawn[a-1][b-1] == 2 && mas_pawn[a-2][b-2] == 0)
                        {
                            return true; //the player have to strike
                        }
                    }

                }
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
    public Boolean move(int[][] mas_pawn, int i_start, int j_start, int i_finish, int j_finish)
    {
        //Check if player have to strike instead of move
       // if(check_hit(mas_pawn) == true) return false;
        if(mas_pawn[i_finish][j_finish] != 0) return false;
        if((i_finish == i_start-1) && (j_finish == j_start-1) ) return true;
        if((i_finish == i_start-1) && (j_finish == j_start+1) ) return true;
        if(j_start >= 1)
        {
            if(mas_pawn[i_start-1][j_start-1] == 2 && mas_pawn[i_finish][j_finish] == 0)
            {
                mas_pawn[i_start][j_start] = 0;
                mas_pawn[i_start-1][j_start-1] = 0;
                return true;
            }
        }
      if(j_start <= 6)
      {
          if(mas_pawn[i_start-1][j_start+1] == 2 && mas_pawn[i_finish][j_finish] == 0)
          {
              mas_pawn[i_start][j_start] = 0;
              mas_pawn[i_start-1][j_start+1] = 0;
              return true;
          }
      }
        return false;
    }
}
