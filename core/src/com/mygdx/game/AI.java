package com.mygdx.game;
import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

/**
 * Created by Dmitry on 18.02.2016.
 */
public class AI {
    private int current_i = -100;
    private int current_j = -100;
    ArrayList<Choose_ai> ai_next_move = new ArrayList<Choose_ai>();
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
       // }

        return false;
    }
    public Boolean check_hit(int[][] mas_pawn) //just return true or false
    {
        for(int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if(mas_pawn[i][j] == 2) // for simple pawn
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
                    int c_i ;
                    int c_j ;
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
                                   Gdx.app.log("CHECK", Integer.toString(c_i+di) + " " + Integer.toString(c_j+dj));
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
            //Add the possibility AI to make a choose
            int index_choose = choose_best_move(mas_pawn);
            int i = ai_next_move.get(index_choose).pawn_i;
            int j = ai_next_move.get(index_choose).pawn_j;
            if(mas_pawn[i][j] == 4) //special move for pawn king
            {
                move_pawn_king(coordinate_move, index_choose);
            }
                        if(i <= 6 && j <= 6)
                        {

                            if(ai_next_move.get(index_choose).way_move == 2) //empty place on the border
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
                            if(ai_next_move.get(index_choose).way_move == 1) //empty place on the border
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
        return false;
    }
    private int[][] get_copy_array(int[][] arr)
    {
        int[][] mas_pawn_copy = new int[8][8];
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                mas_pawn_copy[i][j] = arr[i][j];

            }
        }
        return  mas_pawn_copy;
    }
    private int choose_best_move(int[][] mas_pawn)
    {
        ai_next_move.clear();
        int[][] mas_pawn_copy = get_copy_array(mas_pawn);
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (mas_pawn[i][j] == 2) { //for simple pawn
                        if(i <= 6 && j >= 1) {  //ckheck borders
                            if (mas_pawn[i + 1][j - 1] == 0){   //move to left
                                mas_pawn_copy[i][j] = 0; //delete from previous position
                                mas_pawn_copy[i + 1][j - 1] = 2; //move to new position
                                Choose_ai ai = new Choose_ai(i, j, 1);
                                ai.evaluation += player_move(mas_pawn_copy);
                                if((i+1) == 7) ai.evaluation += 7;
                                ai_next_move.add(ai);
                            }
                            mas_pawn_copy = get_copy_array(mas_pawn);
                        }
                        if(i <= 6 && j <= 6){
                            if (mas_pawn[i + 1][j + 1] == 0) { //move to right
                                mas_pawn_copy[i][j] = 0; //delete from previous position
                                mas_pawn_copy[i + 1][j + 1] = 2; //new AI move
                                Choose_ai ai = new Choose_ai(i, j, 2); //move left
                                ai.evaluation += player_move(mas_pawn_copy);
                                if((i+1) == 7) ai.evaluation += 7;
                                ai_next_move.add(ai);
                            }
                            mas_pawn_copy = get_copy_array(mas_pawn);
                        }
                }
            }
        }
        mas_pawn_copy = get_copy_array(mas_pawn); //restore the souce array with figures
        //Add the evaluation for pawn-king
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                if(mas_pawn[i][j] == 4) {
                    //We are going to check this moves in all directions
                    int c_i = i;
                    int c_j = j;
                    int di = 0;
                    int dj = 0;
                    for (int k = 1; k < 5; k++) {
                        if (k == 1) {
                            di = -1;
                            dj = -1; //<^
                        }
                        if (k == 2) {
                            di = -1;
                            dj = 1; //^>
                        }
                        if (k == 3) {
                            di = 1;
                            dj = 1; //\/>
                        }
                        if (k == 4) {
                            di = 1;
                            dj = -1; //<\/
                        }
                        c_i = i;
                        c_j = j;
                    while (true) {
                        c_i = c_i + di;
                        c_j = c_j + dj;
                        if (c_i == -1 || c_i == 8 || c_j == -1 || c_j == 8) {
                            Gdx.app.log("Exit from cycle", "-1 8 -1 8");
                            break;
                        }
                        if (c_i >= 1 && c_j >= 1 && c_i <= 6 && c_j <= 6) {
                            if (mas_pawn[c_i][c_j] == 0)  //empty place on board
                            {
                                mas_pawn_copy[i][j] = 0; //delete from previous position
                                mas_pawn_copy[c_i][c_j] = 4;
                                Choose_ai ai = new Choose_ai(i, j);
                                ai.evaluation += player_move(mas_pawn_copy);
                                ai.evaluation += 2; //cause it is a pawn king
                                ai.set_end(c_i, c_j);  //new coordinates pawn king
                                ai.direction = k;
                                ai_next_move.add(ai);
                            }
                            mas_pawn_copy = get_copy_array(mas_pawn);
                        }
                    }
                 }
                }
            }
        }
        int index_max_evaluation = 0;
        for(int i = 0; i < ai_next_move.size(); i++)
        {
            Gdx.app.log("Moves----", Integer.toString(ai_next_move.get(i).pawn_i)
                    + " " + Integer.toString(ai_next_move.get(i).pawn_j)
                    + " " + Integer.toString(ai_next_move.get(i).way_move)
                    + " " + Integer.toString(ai_next_move.get(i).end_i)
                    + " " + Integer.toString(ai_next_move.get(i).end_j)
                    + " " + Integer.toString(ai_next_move.get(i).evaluation)
                    + " " + Integer.toString(ai_next_move.get(i).direction));
        }
        int max_exaluation = ai_next_move.get(0).evaluation;
        for(int i = 1; i < ai_next_move.size(); i++)
        {
            if(ai_next_move.get(i).evaluation >= max_exaluation)
            {
                max_exaluation = ai_next_move.get(i).evaluation;
                index_max_evaluation = i;
            }
        }
        Gdx.app.log("index_max_evaluation",Integer.toString(index_max_evaluation));
        return index_max_evaluation;
    }
    private int[] move_pawn_king(int[] coordinate_move, int index_pawn)
    {
        coordinate_move[0] = 1; //launch animation
        coordinate_move[1] = ai_next_move.get(index_pawn).pawn_i;
        coordinate_move[2] = ai_next_move.get(index_pawn).pawn_j;
        coordinate_move[3] = ai_next_move.get(index_pawn).end_i;
        coordinate_move[4] = ai_next_move.get(index_pawn).end_j;
        coordinate_move[7] = ai_next_move.get(index_pawn).direction;
        coordinate_move[8] = 4;
        return coordinate_move;
    }
    private int player_move(int[][] mas_pawn_copy)
    {
        /*  for(int i = 0; i < 8; i++)
        {
            Gdx.app.log("", Integer.toString(mas_pawn_copy[i][0]) + Integer.toString(mas_pawn_copy[i][1]) + Integer.toString(mas_pawn_copy[i][2]) +
                    Integer.toString(mas_pawn_copy[i][3]) + Integer.toString(mas_pawn_copy[i][4]) + Integer.toString(mas_pawn_copy[i][5]) +
                    Integer.toString(mas_pawn_copy[i][6]) + Integer.toString(mas_pawn_copy[i][7]));
        }*/
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                if(mas_pawn_copy[i][j] == 1 || mas_pawn_copy[i][j] == 3) //check if player have to hit
                {
                    //Check in all directions for simple pawn
                    if(i >= 2 && j >= 2)
                    {
                        if((mas_pawn_copy[i-1][j-1] == 2 || mas_pawn_copy[i-1][j-1] == 4)  && mas_pawn_copy[i-2][j-2] == 0)
                        {
                            return 1;
                        }
                    }
                   if(i >= 2 && j <= 5)
                   {
                       if((mas_pawn_copy[i-1][j+1] == 2 || mas_pawn_copy[i-1][j+1] == 4) && mas_pawn_copy[i-2][j+2] == 0)
                       {
                           return 1;
                       }
                   }
                   if(i <= 5 && j <= 5)
                   {
                       if((mas_pawn_copy[i+1][j+1] == 2 || mas_pawn_copy[i+1][j+1] == 4) && mas_pawn_copy[i+2][j+2] == 0)
                       {
                           return 1;
                       }
                   }
                   if(i <= 5 && j >= 2)
                   {
                       if((mas_pawn_copy[i+1][j-1] == 2 || mas_pawn_copy[i+1][j-1] == 4) && mas_pawn_copy[i+2][j-2] == 0)
                       {
                           return 1;
                       }
                   }
                }
            }
        }
        return 4;
    }
}
