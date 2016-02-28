package com.mygdx.game;
import com.badlogic.gdx.Gdx;
/**
 * Created by Dmitry on 18.02.2016.
 */
public class AI {
    private int current_i = -100;
    private int current_j = -100;
    private boolean check_next_hit(int[][] mas_pawn)
    {
        if(current_i >=2 && current_j >=2)
        {
            if((mas_pawn[current_i-1][current_j-1] == 1 || mas_pawn[current_i-1][current_j-1] == 3) && mas_pawn[current_i-2][current_j-2] == 0) //^<
                return true;
        }
        if(current_i >= 2 && current_j <= 5)
        {
            if((mas_pawn[current_i-1][current_j+1] == 1 || mas_pawn[current_i-1][current_j+1] == 3) && mas_pawn[current_i-2][current_j+2] == 0) //>^
                return true;
        }
        if(current_i <= 5 && current_j <= 5)
        {
            if((mas_pawn[current_i+1][current_j+1] == 1 || mas_pawn[current_i+1][current_j+1] == 3) && mas_pawn[current_i+2][current_j+2] == 0) //>\/
                return true;
        }
        if(current_i <= 5 && current_j >= 2)
        {
            if((mas_pawn[current_i+1][current_j-1] == 1 || mas_pawn[current_i+1][current_j-1] == 3) && mas_pawn[current_i+2][current_j-2] == 0) //<\/
                return true;
        }
        return false;
    }
    public Boolean check_hit(int[][] mas_pawn) //just return true or false
    {
        for(int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if(mas_pawn[i][j] == 2)
                {
                    if(i >= 2 && j >=2)
                    {
                        if((mas_pawn[i-1][j-1] == 1 || mas_pawn[i-1][j-1] == 3) && mas_pawn[i-2][j-2] == 0) //<^
                        {
                            return true;
                        }
                    }
                    if(i >= 2 && j <= 5)
                    {
                        if((mas_pawn[i-1][j+1] == 1 || mas_pawn[i-1][j+1] == 3) && mas_pawn[i-2][j+2] == 0) //>^
                        {
                            return true;
                        }
                    }
                   if(i <= 5 && j <= 5)
                   {
                       if((mas_pawn[i+1][j+1] == 1 || mas_pawn[i+1][j+1] == 3) && mas_pawn[i+2][j+2] == 0) //>\/
                       {
                           return true;
                       }
                   }
                   if(i <= 5 && j >= 2)
                   {
                       if((mas_pawn[i+1][j-1] == 1 || mas_pawn[i+1][j-1] == 3) && mas_pawn[i+2][j-2] == 0) //<\/
                       {
                           return true;
                       }
                   }
                }
                if(mas_pawn[i][j] == 4)
                {
                    //check in all direction
                    //direction 1
                    int c_i = i;
                    int c_j = j;
                    int di = 0; int dj = 0;
                   for(int k = 0; k < 4; k++)
                   {
                       if(k == 0)
                       {
                           di = -1;  dj = -1;
                       }
                       if(k == 1)
                       {
                           di = -1;  dj = 1;
                       }
                       if(k == 2)
                       {
                           di = 1;   dj = 1;
                       }
                       if(k == 3)
                       {
                           di = 1;   dj = -1;
                       }
                       while(true)
                       {
                           c_i = c_i + di;
                           c_j = c_j + dj;
                           Gdx.app.log("c_i_ai", Integer.toString(c_i));
                           Gdx.app.log("c_j_ai", Integer.toString(c_j));
                           if(c_i >= 1 && c_j >= 1) //add later check of boundaries
                           {
                               if((mas_pawn[c_i][c_j] == 1 || mas_pawn[c_i][c_j] == 3) && mas_pawn[c_i-1][c_j-1] == 0)
                               {
                                   return true;
                               }
                           }
                           if(c_i == 0 || c_i == 7 || c_j == 0 || c_j == 7)
                           {
                               break;
                           }
                       }
                   }
                }
            }
        }
        return false;
    }
    public int[] hit(int[][] mas_pawn, int[] coordinate_move)
    {
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                if(mas_pawn[i][j] == 2)
                {
                    if(j <= 5 && i <= 5) //hit to right
                    {
                        if ((mas_pawn[i + 1][j + 1] == 1 || mas_pawn[i + 1][j + 1] == 3) && mas_pawn[i + 2][j + 2] == 0) //hit in direction >\/
                        {
                           // mas_pawn[i][j] = 0;
                            mas_pawn[i + 1][j + 1] = 0;
                          //  mas_pawn[i + 2][j + 2] = 2;
                            coordinate_move[0] = 1; //launch animation
                            coordinate_move[1] = i;
                            coordinate_move[2] = j;
                            coordinate_move[3] = i+2;
                            coordinate_move[4] = j+2;
                            current_i = i+2;
                            current_j = j+2;
                            coordinate_move[7] = 3; //direction
                            return coordinate_move;
                        }
                    }
                    if(i >= 2 && j <= 5)
                    {
                        if ((mas_pawn[i - 1][j + 1] == 1 || mas_pawn[i - 1][j + 1] == 3) && mas_pawn[i - 2][j + 2] == 0) //hit ^>
                        {
                           // mas_pawn[i][j] = 0;
                            mas_pawn[i - 1][j + 1] = 0;
                           // mas_pawn[i - 2][j + 2] = 2;
                            coordinate_move[0] = 1; //launch animation
                            coordinate_move[1] = i;
                            coordinate_move[2] = j;
                            coordinate_move[3] = i-2;
                            coordinate_move[4] = j+2;
                            current_i = i-2;
                            current_j = j+2;
                            coordinate_move[7] = 2; //direction
                            return coordinate_move;
                        }
                    }
                    if(j >= 2 && i <= 5) //hit to left
                    {
                        if ((mas_pawn[i + 1][j - 1] == 1 || mas_pawn[i + 1][j - 1] == 3) && mas_pawn[i + 2][j - 2] == 0)  //<\/
                        {
                           // mas_pawn[i][j] = 0;
                            mas_pawn[i + 1][j - 1] = 0; //remove the pawn of player
                          //  mas_pawn[i + 2][j - 2] = 2;
                            coordinate_move[0] = 1; //launch animation
                            coordinate_move[1] = i;
                            coordinate_move[2] = j;
                            coordinate_move[3] = i+2;
                            coordinate_move[4] = j-2;
                            current_i = i+2;
                            current_j = j-2;
                            coordinate_move[7] = 4; //direction
                           // return coordinate_move;
                        }
                    }
                    if(i >= 2 && j >= 2)
                    {
                        if ((mas_pawn[i - 1][j - 1] == 1 || mas_pawn[i - 1][j - 1] == 3) && mas_pawn[i - 2][j - 2] == 0) //<^
                        {
                           // mas_pawn[i][j] = 0; //remove enemy from previous place
                            mas_pawn[i - 1][j - 1] = 0; //remove pawn of computer
                           // mas_pawn[i - 2][j - 2] = 2; //move pawn of enemy
                            coordinate_move[0] = 1; //launch animation
                            coordinate_move[1] = i;
                            coordinate_move[2] = j;
                            coordinate_move[3] = i-2;
                            coordinate_move[4] = j-2;
                             current_i = i-2;
                             current_j = j-2;
                            coordinate_move[7] = 1; //direction
                            return coordinate_move;
                        }
                    }
                }
                if(mas_pawn[i][j] == 4)
                {

                }
            }
        }
        return coordinate_move;
    }
    public Boolean move(int[][] mas_pawn, int[] coordinate_move)
    {
        if(check_hit(mas_pawn))
        {
            hit(mas_pawn, coordinate_move);
            if(check_next_hit(mas_pawn)) //Если после перемещения у компьютера будет возможность сделать новый удар
            {
                return true;  //ai have to make move again, this is needed to make animation on every hit
            }
            else return false; //player can make move

        }
        if(check_hit(mas_pawn) == false)
        {
            for(int i = 0; i < 8; i++)
            {
                for(int j = 0; j < 8; j++)
                {
                    if(mas_pawn[i][j] == 2)
                    {
                        if(i <= 6 && j <= 6)
                        {
                            if(mas_pawn[i+1][j+1] == 0) //empty place on the border
                            {
                               // mas_pawn[i][j] = 0; //delete pawn from previous position
                              //  mas_pawn[i+1][j+1] = 2;

                                coordinate_move[0] = 1; //launch animation
                                coordinate_move[1] = i;
                                coordinate_move[2] = j;
                                coordinate_move[3] = i+1;
                                coordinate_move[4] = j+1;
                                coordinate_move[7] = 3;
                                return false; //player have to make a move
                            }
                        }
                        if(j >= 1 && i <= 6) //move left
                        {
                            if(mas_pawn[i+1][j-1] == 0) //empty place on the border
                            {
                                //mas_pawn[i][j] = 0; //delete pawn from previous position
                               // mas_pawn[i+1][j-1] = 2;
                                coordinate_move[0] = 1; //launch animation
                                coordinate_move[1] = i;  //moving enemy's pawn
                                coordinate_move[2] = j;
                                coordinate_move[3] = i+1;
                                coordinate_move[4] = j-1;
                                coordinate_move[7] = 4;
                                return false; //player have to make a move
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
