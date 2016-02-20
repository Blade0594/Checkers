package com.mygdx.game;

/**
 * Created by Dmitry on 20.02.2016.
 */
public class Convert {
    public int get_x(int j)
    {
        if(j == 0) return 100;
        if(j == 1) return 150;
        if(j == 2) return 200;
        if(j == 3) return 250;
        if(j == 4) return 300;
        if(j == 5) return 350;
        if(j == 6) return 400;
        if(j == 7) return 450;
        return 9000;
    }
    public int get_y(int i)
    {
        if(i == 7) return 50;
        if(i == 6) return 100;
        if(i == 5) return 150;
        if(i == 4) return 200;
        if(i == 3) return 250;
        if(i == 2) return 300;
        if(i == 1) return 350;
        if(i == 0) return 400;
        return 9000;
    }

}
