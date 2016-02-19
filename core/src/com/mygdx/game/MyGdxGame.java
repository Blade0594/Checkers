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
	Boolean possible_move = false;
	int mouse_down_i ;
	int mouse_down_j ;
	int mouse_up_i;
	int mouse_up_j;
	Texture img_background;
	Texture img_board;
	Texture img_pawn_human;
	Texture img_pawn_computer;
	Rectangle pawn;
	Move_pawn human ;
	private BitmapFont font;
	Texture player ;
	Vector3 touchPos ;
	Vector3 mousemovePos ;
	Vector3 touchuppos ;
	Boolean move_pawn = false;
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();
		touchPos = new Vector3();
		mousemovePos = new Vector3();
		touchuppos = new Vector3();
		img_background = new Texture("background1.jpg");
		img_board = new Texture("board.jpg");
		human = new Move_pawn();
		player = new Texture("player1.png");
		img_pawn_human = new Texture("pawn_human.png");
		img_pawn_computer = new Texture("pawn_computer.png");
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
		batch.draw(img_pawn_human, pawn.x, pawn.y); //normal texture
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
				b_x += 50;
			}
			b_y -= 50;

		}
			font.draw(batch, String.valueOf(mouse_down_i) + " " +
			String.valueOf(mouse_down_j) + " v: " + mas_pawn[mouse_down_i][mouse_down_j]  +
					" __ " + String.valueOf(mouse_up_i) + " " + String.valueOf(mouse_up_j) +
					" __ " + String.valueOf(possible_move),50, 30);

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
				if(mas_pawn[i][j] == 1) //player's pawn
				{
					if((Math.pow((touchPos.x - (b_x+25)),2)  + Math.pow((touchPos.y - (b_y+25)),2)) <= 25*25  )
					{
						mouse_down_i = human.get_i(touchPos.y);
						mouse_down_j = human.get_j(touchPos.x);
						mouse_up_i = mouse_down_i; //чтоб можно было поставить пешку обратно
						mouse_up_j = mouse_down_j;
						mas_pawn[i][j] = 0;
						pawn.x = touchPos.x-25;
						pawn.y = touchPos.y-25;
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
			if(human.move(mas_pawn, mouse_down_i, mouse_down_j, mouse_up_i, mouse_up_j) == 0)
			{
				ai.move(mas_pawn); //allow to make move AI
			}
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
