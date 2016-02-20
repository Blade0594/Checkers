package com.mygdx.game;

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
            if(mas_pawn[current_i-1][current_j-1] == 1 && mas_pawn[current_i-2][current_j-2] == 0) //^<
                return true;
        }
        if(current_i >= 2 && current_j <= 5)
        {
            if(mas_pawn[current_i-1][current_j+1] == 1 && mas_pawn[current_i-2][current_j+2] == 0) //>^
                return true;
        }
        if(current_i <= 5 && current_j <= 5)
        {
            if(mas_pawn[current_i+1][current_j+1] == 1 && mas_pawn[current_i+2][current_j+2] == 0) //>\/
                return true;
        }
        if(current_i <= 5 && current_j >= 2)
        {
            if(mas_pawn[current_i+1][current_j-1] == 1 && mas_pawn[current_i+2][current_j-2] == 0) //<\/
                return true;
        }
        return false;
    }
    public Boolean check_hit(int[][] mas_pawn)
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
                            return true;
                        }
                    }
                    if(i >= 2 && j <= 5)
                    {
                        if(mas_pawn[i-1][j+1] == 1 && mas_pawn[i-2][j+2] == 0) //>^
                        {
                            return true;
                        }
                    }
                   if(i <= 5 && j <= 5)
                   {
                       if(mas_pawn[i+1][j+1] == 1 && mas_pawn[i+2][j+2] == 0) //>\/
                       {
                           return true;
                       }
                   }
                   if(i <= 5 && j >= 2)
                   {
                       if(mas_pawn[i+1][j-1] == 1 && mas_pawn[i+2][j-2] == 0) //<\/
                       {
                           return true;
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
                        if (mas_pawn[i + 1][j + 1] == 1 && mas_pawn[i + 2][j + 2] == 0) //hit in direction >\/
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
                        if (mas_pawn[i - 1][j + 1] == 1 && mas_pawn[i - 2][j + 2] == 0) //hit ^>
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
                        if (mas_pawn[i + 1][j - 1] == 1 && mas_pawn[i + 2][j - 2] == 0)  //<\/
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
                        if (mas_pawn[i - 1][j - 1] == 1 && mas_pawn[i - 2][j - 2] == 0) //<^
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
            }
        }
        return coordinate_move;
    }
    public Boolean move(int[][] mas_pawn, int[] coordinate_move)
    {
        //0 = true 1 = false
        //изменить координаты на новые
        if(check_hit(mas_pawn) == true)
        {
            hit(mas_pawn, coordinate_move);
            if(check_next_hit(mas_pawn) == true) //Если после перемещения у компьютера будет возможность сделать новый удар
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
