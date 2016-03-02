package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class MyGdxGame extends ApplicationAdapter implements InputProcessor {
	OrthographicCamera camera;
	SpriteBatch batch;
	AI ai;
	int[][] mas_pawn;
	int b_x = 100;
	int b_y = 400;
	Boolean choose_pawn_king = false;
	Boolean choose_pawn_king_ai = false;
	Boolean ai_move_boolean = false;
	Boolean possible_move = false;
	int mouse_down_i ;
	int mouse_down_j ;
	int mouse_up_i;
	int[] coordinate_move;
	int mouse_up_j;
	Convert convert;
	int current_pawn_type = -5;
	float x = 0; float y = 0; // for animation when computer's pawn is moving
	Texture img_background;
	Texture img_board;
	Texture img_pawn_human;
	Texture img_pawn_computer;
	Texture img_pawn_computer_king;
	Texture img_pawn_human_king;
	Rectangle pawn;
	Rectangle pawn_ai;
	//private int time = 0;
	Move_pawn human ;
	private BitmapFont font;
	Texture player ;
	Vector3 touchPos ;
	Vector3 mousemovePos ;
	float direction_x = 0.0f;
	float direction_y = 0.0f;
	Vector3 touchuppos ;
	Boolean animation = false;
	Boolean move_pawn = false;
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();
		touchPos = new Vector3();

		//Движение компьютера, 0 - запуск анимации(value = 1), 1-4 - координаты перемещения, 5-6 удалить пешку игрока,
		// 7 - direction, 8 - type_pawn - king pawn of just pawn
		coordinate_move = new int[] {0, -5,-5,-5,-5, -5, -5, -5, -5 };
		mousemovePos = new Vector3();
		touchuppos = new Vector3();
		img_background = new Texture("background1.jpg");
		img_board = new Texture("board.jpg");
		human = new Move_pawn();
		convert = new Convert();
		player = new Texture("player1.png");
		img_pawn_human = new Texture("pawn_human.png");
		img_pawn_computer = new Texture("pawn_computer.png");
		img_pawn_computer_king = new Texture("pawn_computer_king2.png");
		img_pawn_human_king = new Texture("pawn_human_king.png");
		font = new BitmapFont();
		ai = new AI();
		mas_pawn = new int[][]{{0, 2, 0, 2, 0, 2, 0, 2},
				{2, 0, 2, 0, 2, 0, 2, 0},
				{0, 2, 0, 2, 0, 2, 0, 2},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{1, 0, 1, 0, 1, 0, 1, 0},
				{0, 1, 0, 1, 0, 1, 0, 1},
				{1, 0, 1, 0, 1, 0, 1, 0}
		};
		pawn = new Rectangle();
		pawn.x = -100;
		pawn.y = 50;
		pawn.width = 50;
		pawn.height = 50;
		pawn_ai = new Rectangle();
		pawn_ai.x = -100;
		pawn_ai.y = 50;
		pawn_ai.width = 50;
		pawn_ai.height = 50;
		Gdx.input.setInputProcessor(this);
	}
	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(img_background, 0, 0, 800, 480);
		batch.draw(img_board, 100, 50); //board.width, board.height
		b_y = 400;
		for(int i = 0; i < 8; i++)
		{
			b_x = 100;
			for(int j = 0; j < 8; j++)
			{
				if(mas_pawn[i][j] == 2) {
					batch.draw(img_pawn_computer, b_x, b_y);
				}
				if(mas_pawn[i][j] == 1) {
					batch.draw(img_pawn_human, b_x, b_y);
				}
				if(mas_pawn[i][j] == 3) {
					batch.draw(img_pawn_human_king, b_x, b_y);
				}
				if(mas_pawn[i][j] == 4) {
					batch.draw(img_pawn_computer_king, b_x, b_y);
				}
				b_x += 50;
			}
			b_y -= 50;
		}
		//It depends on a type of pawn
		//if(mas_pawn[human.get_i(touchPos.y)][human.get_j(touchPos.x)] == 1
		if(move_pawn == true &&  choose_pawn_king == false)
		{
			batch.draw(img_pawn_human, pawn.x, pawn.y); //when player move the pawn
		}
		if(move_pawn == true &&  choose_pawn_king == true)
		{
			batch.draw(img_pawn_human_king, pawn.x, pawn.y); //when player move the pawn
		}
		if(animation == false)
		{
			if(coordinate_move[0] == 1) //launch animation
			{
				animation = true;
				if(mas_pawn[coordinate_move[1]][coordinate_move[2]] == 4)
				{
					choose_pawn_king_ai = true;
				}
				else choose_pawn_king_ai = false;
				mas_pawn[coordinate_move[1]][coordinate_move[2]] = 0; //delete from start position

				x = convert.get_x(coordinate_move[2]);
				y = convert.get_y(coordinate_move[1]);
				if(coordinate_move[7] == 3)
				{
					direction_x = 1f;
					direction_y = -1f;
				}
				if(coordinate_move[7] == 4)
				{
					direction_x = -1f;
					direction_y = -1f;
				}
				//new directions
				if(coordinate_move[7] == 1)
				{
					direction_x = -1f;
					direction_y = 1f;
				}
				if(coordinate_move[7] == 2)
				{
					direction_x = 1f;
					direction_y = 1f;
				}
			}
		}
		if(animation == true)
		{
			//Direction
			x += direction_x;
			y += direction_y;
		}
		if(choose_pawn_king_ai == true)
		batch.draw(img_pawn_computer_king, x, y);
		else
			batch.draw(img_pawn_computer, x, y);
		if(x == convert.get_x(coordinate_move[4]) && y == convert.get_y(coordinate_move[3]))
		{
			if(coordinate_move[3] == 7)
			mas_pawn[coordinate_move[3]][coordinate_move[4]] = 4; //king pawn
			else  mas_pawn[coordinate_move[3]][coordinate_move[4]] = coordinate_move[8];
			coordinate_move[0] = 0;
			//delete the hit player's pawn
			if(coordinate_move[5] != -5)
			mas_pawn[coordinate_move[5]][coordinate_move[6]] = 0;
			//mas_pawn[coordinate_move[3]][coordinate_move[4]] =
			x = -100;
			y = -100;
			animation = false;
			if(ai_move_boolean == true)
			{
				ai_move_boolean = ai.move(mas_pawn, coordinate_move);
			}
		}
		//Анимация движения пешек компьютера
		//if(coordinate_move[0] != -5)
		///{
			batch.draw(img_pawn_computer, pawn_ai.x, pawn_ai.y);
		//}
			font.draw(batch, String.valueOf(mouse_down_i) + " " +
			String.valueOf(mouse_down_j) + " v: " + mas_pawn[mouse_down_i][mouse_down_j]  +
					" __ " + String.valueOf(mouse_up_i) + " " + String.valueOf(mouse_up_j) +
					" __ " + String.valueOf(possible_move) +
					" Current_i_j: " + String.valueOf(human.get_current_i()) + "  " + String.valueOf(human.get_current_j()),50, 30);
		  //  font.draw(batch, "ai_move_boolean: " +  String.valueOf(ai_move_boolean),20,420);
		batch.end();
	}
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		touchPos.set(screenX, screenY, 0);
		camera.unproject(touchPos);
		b_y = 400;
		for(int i = 0; i < 8; i++)
		{
			b_x = 100;
			for(int j = 0; j < 8; j++)
			{
				if((mas_pawn[i][j] == 1 || mas_pawn[i][j] == 3)
						&& (mas_pawn[human.get_i(touchPos.y)][human.get_j(touchPos.x)] == 1
						||  mas_pawn[human.get_i(touchPos.y)][human.get_j(touchPos.x)] == 3)) //player's pawn
				{
				   if(human.get_i(touchPos.y) == i && human.get_j(touchPos.x) == j) //choose the cell, not the pawn
				   {
					   mouse_down_i = human.get_i(touchPos.y); //remember the i_start and j_start
					   mouse_down_j = human.get_j(touchPos.x);
					   if(mas_pawn[mouse_down_i][mouse_down_j] == 3) choose_pawn_king = true;
					   else choose_pawn_king = false;
					   mouse_up_i = mouse_down_i; //чтоб можно было поставить пешку обратно
					   mouse_up_j = mouse_down_j;
					    current_pawn_type = mas_pawn[i][j];
					   mas_pawn[i][j] = 0;
					   pawn.x = touchPos.x - 25;
					   pawn.y = touchPos.y - 25;

				   }
				}
				b_x += 50;
			}
			b_y -= 50;
		}
		if((Math.pow((touchPos.x - (pawn.x+25)),2)  + Math.pow((touchPos.y - (pawn.y+25)),2)) <= 25*25  )
		{
			move_pawn = true;
		}
		return true;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		touchuppos.set(screenX, screenY, 0);
		camera.unproject(touchuppos);
		if(move_pawn == true)
		{
			pawn.x = -1000;
			//new coordinates of pawn
			mouse_up_i = human.get_i(touchuppos.y);
			mouse_up_j = human.get_j(touchuppos.x);
			if(human.move(mas_pawn, mouse_down_i, mouse_down_j, mouse_up_i, mouse_up_j, current_pawn_type) == 0)
			{
				ai_move_boolean = ai.move(mas_pawn, coordinate_move);
			}
			//new
			move_pawn = false;
		}
		return false;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		mousemovePos.set(screenX, screenY, 0);
		camera.unproject(mousemovePos);
		if(move_pawn == true)
		{
			pawn.x = mousemovePos.x-25;
			pawn.y = mousemovePos.y-25;
		}
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
	public void dispose() {
		super.dispose();
		img_board.dispose();
		batch.dispose();
	}
}
