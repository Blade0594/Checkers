package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class MyGdxGame extends ApplicationAdapter implements InputProcessor {
	OrthographicCamera camera;
	SpriteBatch batch;
	int[][] mas_pawn;
	int b_x = 100;
	int b_y = 400;
	Texture img;
	Texture img_board;
	Texture img_pawn_human;
	Texture img_pawn_computer;
	Rectangle pawn;
	Move_pawn object1 ;
	private BitmapFont font;
	Texture player ;
	Boolean show_m = false;
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
		img = new Texture("background1.jpg");
		img_board = new Texture("board.jpg");
		object1 = new Move_pawn();
		player = new Texture("player1.png");
		img_pawn_human = new Texture("pawn_human.png");
		img_pawn_computer = new Texture("pawn_computer.png");
		font = new BitmapFont();
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
		batch.draw(img, 0, 0, 800, 480);
		batch.draw(img_board, 100, 50); //board.width, board.height
		batch.draw(img_pawn_human, pawn.x, pawn.y); //normal texture
		//batch.draw(player, 300, 200);
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
		//if(show_m == true)
		//font.draw(batch, String.valueOf(touchPos.x) + " " + String.valueOf(touchPos.y), touchPos.x, touchPos.y);
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
						mas_pawn[i][j] = 0;
						pawn.x = touchPos.x-25;
						pawn.y = touchPos.y-25;
						show_m = true;
					//	move_pawn = true;
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
		//pawn.x = touchPos.x-25;
		//pawn.y = touchPos.y-25;
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		touchuppos.set(screenX, screenY, 0);
		camera.unproject(touchuppos);

		show_m = false;
		if(move_pawn == true)
		{

			//if(touchuppos.x >= 250 && touchuppos.x <= 300 && touchuppos.y >= 200 && touchuppos.y <= 250)
		//	{
				show_m = true;
				pawn.x = -1000;
				mas_pawn[object1.get_i(touchuppos.y)][object1.get_j(touchuppos.x)] = 1;
		//	}
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
