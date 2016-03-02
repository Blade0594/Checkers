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
      //  Gdx.app.log("drgret", "check_next_hit1");
       // if(mas_pawn[current_i][current_j] == 2)
        //{
        //    Gdx.app.log("drgret", "check_next_hit2");
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
       // }

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
                        c_i = i;
                        c_j = j;
                       while(true)
                       {
                           c_i = c_i + di;
                           c_j = c_j + dj;
                           Gdx.app.log("c_i_ai", Integer.toString(c_i));
                           Gdx.app.log("c_j_aj", Integer.toString(c_j));
                           //change place
                           if(c_i == -1 || c_i == 8 || c_j == -1 || c_j == 8)
                           {
                               break;
                           }
                           if(c_i >= 1 && c_j >= 1 && c_i <= 6 && c_j <= 6)
                           {
                               if(mas_pawn[c_i][c_j] == 2 || mas_pawn[c_i][c_j] == 4) //between player's pawn and computer's pawn is other pawn
                               {
                                   return false;
                               }
                               if((mas_pawn[c_i][c_j] == 1 || mas_pawn[c_i][c_j] == 3) && mas_pawn[c_i+di][c_j+dj] == 0)
                               {
                                   return true;
                               }

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
                          //  mas_pawn[i + 1][j + 1] = 0;
                          //  mas_pawn[i + 2][j + 2] = 2;
                            coordinate_move[0] = 1; //launch animation
                            coordinate_move[1] = i;
                            coordinate_move[2] = j;
                            coordinate_move[3] = i+2;
                            coordinate_move[4] = j+2;
                            coordinate_move[5] = i + 1;
                            coordinate_move[6] = j + 1;
                            current_i = i+2;
                            current_j = j+2;
                            coordinate_move[7] = 3; //direction
                            coordinate_move[8] = 2; //type pawn
                            return coordinate_move;
                        }
                    }
                    if(i >= 2 && j <= 5)
                    {
                        if ((mas_pawn[i - 1][j + 1] == 1 || mas_pawn[i - 1][j + 1] == 3) && mas_pawn[i - 2][j + 2] == 0) //hit ^>
                        {
                           // mas_pawn[i][j] = 0;
                          //  mas_pawn[i - 1][j + 1] = 0;
                           // mas_pawn[i - 2][j + 2] = 2;
                            coordinate_move[0] = 1; //launch animation
                            coordinate_move[1] = i;
                            coordinate_move[2] = j;
                            coordinate_move[3] = i-2;
                            coordinate_move[4] = j+2;
                            coordinate_move[5] = i - 1;
                            coordinate_move[6] = j + 1;
                            current_i = i-2;
                            current_j = j+2;
                            coordinate_move[7] = 2; //direction
                            coordinate_move[8] = 2; //type pawn
                            return coordinate_move;
                        }
                    }
                    if(j >= 2 && i <= 5) //hit to left
                    {
                        if ((mas_pawn[i + 1][j - 1] == 1 || mas_pawn[i + 1][j - 1] == 3) && mas_pawn[i + 2][j - 2] == 0)  //<\/
                        {
                           // mas_pawn[i][j] = 0;
                         //   mas_pawn[i + 1][j - 1] = 0; //remove the pawn of player
                          //  mas_pawn[i + 2][j - 2] = 2;
                            coordinate_move[0] = 1; //launch animation
                            coordinate_move[1] = i;
                            coordinate_move[2] = j;
                            coordinate_move[3] = i+2;
                            coordinate_move[4] = j-2;
                            coordinate_move[5] = i + 1;
                            coordinate_move[6] = j - 1;
                            current_i = i+2;
                            current_j = j-2;
                            coordinate_move[7] = 4; //direction
                            coordinate_move[8] = 2; //type pawn
                           // return coordinate_move;
                        }
                    }
                    if(i >= 2 && j >= 2)
                    {
                        if ((mas_pawn[i - 1][j - 1] == 1 || mas_pawn[i - 1][j - 1] == 3) && mas_pawn[i - 2][j - 2] == 0) //<^
                        {
                          //  mas_pawn[i - 1][j - 1] = 0; //remove pawn of computer
                            coordinate_move[0] = 1; //launch animation
                            coordinate_move[1] = i;
                            coordinate_move[2] = j;
                            coordinate_move[3] = i-2;
                            coordinate_move[4] = j-2;
                            coordinate_move[5] = i - 1;
                            coordinate_move[6] = j - 1;
                             current_i = i-2;
                             current_j = j-2;
                            coordinate_move[7] = 1; //direction
                            coordinate_move[8] = 2; //type pawn
                            return coordinate_move;
                        }
                    }
                }
                if(mas_pawn[i][j] == 4)
                {
                    //check in all direction
                    int c_i = i;
                    int c_j = j;
                    int di = 0; int dj = 0;
                    for(int k = 1; k < 5; k++) //k - direction
                    {
                        if(k == 1)
                        {
                            di = -1;  dj = -1;
                        }
                        if(k == 2)
                        {
                            di = -1;  dj = 1;
                        }
                        if(k == 3)
                        {
                            di = 1;   dj = 1;
                        }
                        if(k == 4)
                        {
                            di = 1;   dj = -1;
                        }
                        //update c_i
                        c_i = i;
                        c_j = j;
                        while(true)
                        {
                            c_i = c_i + di;
                            c_j = c_j + dj;
                            if(c_i >= 1 && c_j >= 1 && c_i <= 6 && c_j <= 6) //border was added
                            {
                                if((mas_pawn[c_i][c_j] == 1 || mas_pawn[c_i][c_j] == 3) && mas_pawn[c_i+di][c_j+dj] == 0)
                                {
                                    //mas_pawn[i][j] = 0;
                                    coordinate_move[0] = 1; //launch animation
                                    coordinate_move[1] = i;  //start
                                    coordinate_move[2] = j;
                                    coordinate_move[3] = c_i+di; //finish
                                    coordinate_move[4] = c_j+dj;
                                    coordinate_move[5] = c_i; //delete player's pawn
                                    coordinate_move[6] = c_j;
                                    current_i = c_i+di;
                                    current_j = c_j+dj;
                                    coordinate_move[7] = k; //direction depends on k
                                    coordinate_move[8] = 4; //type pawn
                                    return coordinate_move;
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
        if(check_hit(mas_pawn) == false) //just move pawn
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
                                coordinate_move[5] = -5;
                                coordinate_move[6] = -5;
                                coordinate_move[7] = 3;
                                coordinate_move[8] = 2;
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
                                coordinate_move[5] = -5;
                                coordinate_move[6] = -5;
                                coordinate_move[7] = 4;
                                coordinate_move[8] = 2;
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
