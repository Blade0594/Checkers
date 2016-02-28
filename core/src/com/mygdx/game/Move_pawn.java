package com.mygdx.game;

import com.badlogic.gdx.Gdx;

/**
 * Created by Dmitry on 18.02.2016.
 */
public class Move_pawn {
    private int current_i = -100;
    private int current_j = -100;
    private int hit_i = -10;
    private int hit_j = -10;
    private Boolean have_to_hit(int[][] mas_pawn, int i_finish, int j_finish, int type_pawn)
    {
        if(type_pawn == 1)
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
        }
        //for pawn king
        Boolean[] Direction = new Boolean[]{false, false, false, false};
        if(type_pawn == 3)
        {
            //define the direction from where we came
           /* int di = i_finish - current_i;
            int dj = j_finish - current_j;
            if(di >= 0 && dj >= 0)
            {
                Direction[0] = true;
            }
            if(di >= 0 && dj <= 0)
            {
                Direction[1] = true;
            }
            if(di <= 0 && dj <= 0)
            {
                Direction[2] = true;
            }
            if(di <= 0 && dj >= 0)
            {
                Direction[3] = true;
            }*/
            int next_i = current_i;
            int next_j = current_j;

            if(Direction[0] == false)
            {
                while(true)
                {
                    next_i = next_i - 1;
                    next_j = next_j - 1;
                    if(next_i >= 1 && next_j >= 1) //array boundaries
                    {
                        if(mas_pawn[next_i][next_j] == 2 && mas_pawn[next_i-1][next_j-1] == 0)
                        {
                            return true;
                        }
                        if(mas_pawn[next_i][next_j] == 2 && mas_pawn[next_i-1][next_j-1] == 2)
                        {
                            return false;
                        }
                    } else break;
                }
            }
             next_i = current_i;
             next_j = current_j;
            if(Direction[1] == false)
            {
                while(true)
                {
                    next_i = next_i - 1;
                    next_j = next_j + 1;
                    if(next_i >= 1 && next_j <= 6)
                    {
                        if(mas_pawn[next_i][next_j] == 2 && mas_pawn[next_i-1][next_j+1] == 0)
                        {
                            return true;
                        }
                        if(mas_pawn[next_i][next_j] == 2 && mas_pawn[next_i-1][next_j+1] == 2)
                        {
                            return false;
                        }
                    } else break;
                }
            }
             next_i = current_i;
             next_j = current_j;
            if(Direction[2] == false)
            {

                while(true)
                {
                    next_i = next_i + 1;
                    next_j = next_j + 1;
                    if(next_i <= 6 && next_j <= 6 && next_i >= 0 && next_j >= 0)
                    {
                        if(mas_pawn[next_i][next_j] == 2 && mas_pawn[next_i+1][next_j+1] == 0)
                        {
                            return true; //player have to hit
                        }
                        if(mas_pawn[next_i][next_j] == 2 && mas_pawn[next_i+1][next_j+1] == 2)
                        {
                            return false; //player have to hit
                        }
                    } else break;
                }
            }
             next_i = current_i;
             next_j = current_j;
            if(Direction[3] == false)
            {
                while(true)
                {
                    next_i = next_i + 1;
                    next_j = next_j - 1;
                    if(next_i <= 6 && next_j >= 1)
                    {
                        if(mas_pawn[next_i][next_j] == 2 && mas_pawn[next_i+1][next_j-1] == 0)
                        {
                            return true;
                        }
                        if(mas_pawn[next_i][next_j] == 2 && mas_pawn[next_i-1][next_j+1] == 2)
                        {
                            return false;
                        }
                    } else break;
                }
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
    private int move_hit_king(int[][] mas_pawn, int i_start, int j_start, int i_finish, int j_finish, int type_pawn, Boolean hit)
    {
        mas_pawn[i_start][j_start] = 0;
        if(hit == true)
        mas_pawn[hit_i][hit_j] = 0;
        mas_pawn[i_finish][j_finish] = type_pawn;  ///move to finish
        //ход сделан - нужно переставить координаты, так как следующее сравнение не имеет смысла
        current_i = i_finish;
        current_j = j_finish;
        hit_i = -10;
        hit_j = -10;
        if(have_to_hit(mas_pawn, i_finish, j_finish, type_pawn) == true) //player have to make move again
        {
            return 1;
        }
        if(have_to_hit(mas_pawn, i_finish, j_finish, type_pawn) == false)
        {
            reset_current_i_j();
            return 0; //move of AI
        }
        return 7;
    }
    private int move_or_Hit(int[][] mas_pawn, int i_start, int j_start, int i_finish, int j_finish, int d1, int d2, int type_pawn)
    {
        mas_pawn[i_start][j_start] = 0;
        mas_pawn[i_start+d1][j_start+d2] = 0; //delete the pawn!!! different in all cases
        mas_pawn[i_finish][j_finish] = type_pawn;  ///move to finish
        if(i_finish == 0)  //create pawn king
        {
            mas_pawn[i_finish][j_finish] = 3;
        }
        //ход сделан - нужно переставить координаты, так как следующее сравнение не имеет смысла
        current_i = i_finish;
        current_j = j_finish;
        if(have_to_hit(mas_pawn, i_finish, j_finish, type_pawn) == true) //player have to make move again
        {
            return 1;
        }
        if(have_to_hit(mas_pawn, i_finish, j_finish, type_pawn) == false)
        {
            reset_current_i_j();
            return 0; //move of AI
        }
        return 7;
    }
   private Boolean check(int[][] mas_pawn, int i_start, int j_start, int i_finish, int j_finish,  int current_pawn_type)
   {
       if(mas_pawn[i_finish][j_finish] != 0) return false;
       if((i_finish == i_start-1) && (j_finish == j_start-1) ) return true;
       if((i_finish == i_start-1) && (j_finish == j_start+1) ) return true;
       //For pawn king
       if(current_pawn_type == 3)
       {
           //Special checks for pawn king
            int next_i = i_start;
            int next_j = j_start;
            int count_enemy = 0;
           if(Math.abs(i_finish-i_start) == Math.abs(j_finish-j_start)) //на одной диагонали
           {
               return true;
            /*   while(true)
               {
                   next_i++; //diagonal - next cell
                   next_j++;
                   if(mas_pawn[next_i][next_j] == 2)
                   {
                       count_enemy++;
                   }
                   if(count_enemy == 2) return false;
                   if(next_i == i_finish && next_j == j_finish) return true;

               }*/
           }
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
        //Log.getLog("dfgdfg", "dsfdfg", 0);


      //  Log.d(MyActivity.LOG_TAG,"Application started");
        if(current_i == -100)
        {
            current_i = i_start;
            current_j = j_start;
        }
        if(have_to_hit(mas_pawn, i_finish, j_finish, current_pawn_type) == false)
        {
            if (check(mas_pawn, i_start, j_start, i_finish, j_finish, current_pawn_type) == true)
            {
                //0, 0 - just move
                move_or_Hit(mas_pawn, i_start, j_start, i_finish, j_finish, 0, 0, current_pawn_type);
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
        if(have_to_hit(mas_pawn, i_finish, j_finish, current_pawn_type) == true)
        {
            //Right now - if player have to hit, he can not to hit
            if(current_pawn_type == 3)  //pawn king
            {
                //Determine the count of enemies pawn
                int count_enemies = count_element(mas_pawn);
                //We have to find out what pawn of enemy we are going to delete
                int c_i = i_start;
                int c_j = j_start;
                //find out the direction where we are moving
                int di = i_finish - i_start;
                int dj = j_finish - j_start;
                di = di / Math.abs(di); //get the sign
                dj = dj / Math.abs(dj);
                Boolean check_pawn_king = false;
                while(true)
                {
                    c_i = c_i + di;
                    c_j = c_j + dj;

                //    Gdx.app.log("c_i", Integer.toString(c_i));
                //    Gdx.app.log("c_j", Integer.toString(c_j));
                    if(mas_pawn[c_i][c_j] == 2) //remember the position of enemies
                    {
                        hit_i = c_i;
                        hit_j = c_j;
                        check_pawn_king = true;
                    }
                    if(check_pawn_king == true)
                    {
                        if(c_i == i_finish && c_j == j_finish) break;
                    }
                    if(c_i == 0 || c_i == 7 || c_j == 0 || c_j == 7)
                    {
                        //put in previous position
                        mas_pawn[i_start][j_start] = 3;
                        return 1;  //player have to make move again
                    }
                   /* else
                    {
                        //put in previous position
                        mas_pawn[i_start][j_start] = 3;
                        return 1;
                    }*/
                }
                if(move_hit_king(mas_pawn, i_start, j_start, i_finish, j_finish, 3, true) == 1)
                {
                    return 1;
                }
                else return 0;
            }
            if(current_pawn_type == 1)
            {
                //Determine the count of enemies pawn
                int count_enemies = count_element(mas_pawn);
                if(j_start >= 1 && i_start >= 1) //array boundaries
                {
                    if(mas_pawn[i_start-1][j_start-1] == 2 && i_finish == (i_start-2) && j_finish == (j_start-2)) //<^
                    {
                        //Check the condition - From which way did we come ?
                        //нужно еще знать куда мы походили
                        if(move_or_Hit(mas_pawn, i_start, j_start, i_finish, j_finish, -1, -1, 1) == 1)
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
                        if(move_or_Hit(mas_pawn, i_start, j_start, i_finish, j_finish, -1, 1, 1) == 1)
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
                        if(move_or_Hit(mas_pawn, i_start, j_start, i_finish, j_finish, 1, 1, 1) == 1)
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
                        if (move_or_Hit(mas_pawn, i_start, j_start, i_finish, j_finish, 1, -1, 1) == 1)
                        {
                            return 1;
                        }
                        else return 0;
                    }
                }
                if(count_element(mas_pawn) != count_enemies-1)
                {
                    //put in previous position
                    mas_pawn[i_start][j_start] = 1;
                    return 1;
                }
            }
        }
        return 5;
    }
    private int count_element(int[][] mas_pawn)
    {
        int count = 0;
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                if(mas_pawn[i][j] == 2)
                {
                    count++;
                }
            }
        }
        return count;
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