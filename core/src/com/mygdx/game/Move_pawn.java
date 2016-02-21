package com.mygdx.game;

/**
 * Created by Dmitry on 18.02.2016.
 */
public class Move_pawn {
    private int current_i = -100;
    private int current_j = -100;
    private Boolean check_collision(int[][] mas_pawn)
    {
        if(current_i >= 2 && current_j >= 2)
        {
            if(mas_pawn[current_i-1][current_j-1] == 2 && mas_pawn[current_i-2][current_j-2] == 0) //<^
            {
                return true;
            }
        }
        if(current_i >= 2 && current_j <= 5) //+
        {
            if(mas_pawn[current_i-1][current_j+1] == 2 && mas_pawn[current_i-2][current_j+2] == 0) //>^
            {
                return true;
            }
        }
        if(current_i <= 5 && current_j <= 5) //+
        {
            if(mas_pawn[current_i+1][current_j+1] == 2 && mas_pawn[current_i+2][current_j+2] == 0) //>\/
            {
                return true;
            }
        }
        if(current_i <= 5 && current_j >= 2)
        {
            if(mas_pawn[current_i+1][current_j-1] == 2 && mas_pawn[current_i+2][current_j-2] == 0) //<\/
            {
                return true;
            }
        }
        return false;
    }
    private int Correct_move(int[][] mas_pawn, int i_finish, int j_finish)
    {
        if(current_i >= 2 && current_j >= 2)
        {
            if(mas_pawn[current_i-1][current_j-1] == 2 && mas_pawn[current_i-2][current_j-2] == 0
                    && i_finish != current_i-2 && j_finish !=  current_j-2 ) //<^
            {
                return 1;
            }
        }
        if(current_i >= 2 && current_j <= 5) //+
        {
            if(mas_pawn[current_i-1][current_j+1] == 2 && mas_pawn[current_i-2][current_j+2] == 0
                    && i_finish != current_i-2 && j_finish != current_j+2) //>^
            {
                return 1;
            }
        }
        if(current_i <= 5 && current_j <= 5) //+
        {
            if(mas_pawn[current_i+1][current_j+1] == 2 && mas_pawn[current_i+2][current_j+2] == 0
                    && i_finish != current_i+2 && j_finish != current_j+2) //>\/
            {
                return 1;
            }
        }
        if(current_i <= 5 && current_j >= 2)
        {
            if(mas_pawn[current_i+1][current_j-1] == 2 && mas_pawn[current_i+2][current_j-2] == 0
                    && i_finish != current_i+2 && j_finish != current_j-2) //<\/
            {
                return 1;
            }
        }
        return 0; //everything is alright
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
    private void reset_current_i_j()
    {
        current_i = -100;
        current_j = -100;
    }
    private int moveToHit(int[][] mas_pawn, int i_start, int j_start, int i_finish, int j_finish, int d1, int d2, int type_pawn)
    {
        mas_pawn[i_start][j_start] = 0;
        mas_pawn[i_start+d1][j_start+d2] = 0; //delete the pawn!!! different in all cases
        mas_pawn[i_finish][j_finish] = type_pawn;  ///move to finish
        if(i_finish == 0)
        {
            mas_pawn[i_finish][j_finish] = 3;
        }
        //ход сделан - нужно переставить координаты, так как следующее сравнение не имеет смысла
        current_i = i_finish;
        current_j = j_finish;
        if(check_collision(mas_pawn) == true)
        {
            return 1;
        }
        if(check_collision(mas_pawn) == false)
        {
            reset_current_i_j();
            return 0; //move of AI
        }
        return 7;
    }
   // private boolean scanning_for_pawn_king()
   private Boolean check(int[][] mas_pawn, int i_start, int j_start, int i_finish, int j_finish,  int current_pawn_type)
   {
       if(mas_pawn[i_finish][j_finish] != 0) return false;
       if((i_finish == i_start-1) && (j_finish == j_start-1) ) return true;
       if((i_finish == i_start-1) && (j_finish == j_start+1) ) return true;
       //For pawn king
       if(current_pawn_type == 3)
       {
           //Special checks for pawn king

            if(Math.abs(i_finish-i_start) == Math.abs(j_finish-j_start)) return true;
         //  return true;
       }
       return false;
   }
    public int move(int[][] mas_pawn, int i_start, int j_start, int i_finish, int j_finish,  int current_pawn_type)
    {
        //If the player put the pawn in the same position
        if(i_start == i_finish && j_start == j_finish)
        {
            mas_pawn[i_start][j_start] = current_pawn_type;
            return 1; //the move was not done
        }
        if(current_i == -100)
        {
            current_i = i_start;
            current_j = j_start;
        }
        if(check_collision(mas_pawn) == false)
        {
            if(check(mas_pawn, i_start, j_start, i_finish, j_finish, current_pawn_type) == true)
            {
                moveToHit(mas_pawn, i_start, j_start, i_finish, j_finish, 0, 0, current_pawn_type);
                if(i_finish == 0)
                {
                    mas_pawn[i_finish][j_finish] = 3;
                }
                reset_current_i_j();
                return 0;
            }
            else  //put in previous position
            {
                mas_pawn[i_start][j_start] = current_pawn_type;
                return 5;
            }
        }
        //Check if we choose wrong move
     /*   if(check_collision(mas_pawn) == true)
        {
            if(Correct_move(mas_pawn, i_finish, j_finish) == 1)
            {
                mas_pawn[i_start][j_start] = 1;
                return 1;
            }
        }*/
        if(check_collision(mas_pawn) == true) //if(have_to_hit(mas_pawn)  23:52
        {
            if(j_start >= 1 && i_start >= 1) //array boundaries
            {
                if(mas_pawn[i_start-1][j_start-1] == 2 && i_finish == (i_start-2) && j_finish == (j_start-2)) //<^
                {
                    //Check the condition - From which way did we come ?
                    //нужно еще знать куда мы походили
                    if(moveToHit(mas_pawn, i_start, j_start, i_finish, j_finish, -1, -1, 1) == 1)
                    {
                        return 1;
                    }
                    else return 0;
                }
            }
            if(j_start <= 6 && i_start >= 1)
            {
                if(mas_pawn[i_start-1][j_start+1] == 2 && i_finish == (i_start-2) && j_finish == (j_start+2)) //>^
                {
                    if(moveToHit(mas_pawn, i_start, j_start, i_finish, j_finish, -1, 1, 1) == 1)
                    {
                        return 1;
                    }
                    else return 0;
                }
            }
            if(j_start <= 5 && i_start <=5) //correct later the boundaries
            {
                if(mas_pawn[i_start+1][j_start+1] == 2 && i_finish == (i_start+2) && j_finish == (j_start+2)) //>\/
                {
                    if(moveToHit(mas_pawn, i_start, j_start, i_finish, j_finish, 1, 1, 1) == 1)
                    {
                        return 1;
                    }
                    else return 0;
                }
            }
            if(i_start <= 5 && j_start >= 2)
            {
                if (mas_pawn[i_start + 1][j_start - 1] == 2 && i_finish == (i_start + 2) && j_finish == (j_start - 2)) //<\/
                {
                    if(moveToHit(mas_pawn, i_start, j_start, i_finish, j_finish, 1, -1, 1) == 1)
                    {
                        return 1;
                    }
                    else return 0;
                }
            }
        }
        return 5;
    }
    public int get_current_i()
    {
        return current_i;
    }
    public int get_current_j()
    {
        return current_j;
    }

}