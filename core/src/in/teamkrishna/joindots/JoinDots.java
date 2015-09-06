package in.teamkrishna.joindots;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class JoinDots extends ApplicationAdapter implements InputProcessor{
	SpriteBatch batch;
	ShapeRenderer shapeRenderer;
	Pixmap pixmap;
	Texture texture;
	Sprite sprite;

	int iDragX, iDragY;

	@Override
	public void create () {
		Gdx.graphics.setContinuousRendering(false);

		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();

		pixmap = new Pixmap(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
		//pixmap.setColor(Color.WHITE);
		//pixmap.fill();
		//pixmap.drawCircle(pixmap.getWidth() / 2, pixmap.getHeight() / 2, pixmap.getHeight() / 2 - 1);
		//pixmap.drawCircle(iDragX,iDragY,20);
		pixmap.setColor(Color.RED);
		pixmap.fillCircle(30,30, 20);
		pixmap.fillCircle(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 20);
		//pixmap.drawCircle(100,100,20);

		texture = new Texture(pixmap);
		//sprite = new Sprite(texture);

		Gdx.input.setInputProcessor(this);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		//batch.draw(img, 0, 0);

//		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//		shapeRenderer.circle(50, 50, 20);
//		shapeRenderer.circle(150,150,20);
//		shapeRenderer.end();

		//sprite.setPosition(100,100);
		//sprite.draw(batch);

		batch.draw(texture,0,0);

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
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {

		iDragX=screenX;
		iDragY=screenY;

		pixmap.setColor(Color.RED);
		pixmap.fillCircle(screenX,screenY, 20);
		texture.draw(pixmap,0,0);
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
