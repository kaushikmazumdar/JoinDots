package in.teamkrishna.joindots;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class JoinDots extends ApplicationAdapter implements InputProcessor{
	SpriteBatch batch;
	Pixmap pixmap;
	Texture texture;
	Sprite sprite;
	OrthographicCamera camera;

	int iDragX, iDragY;
	int iTouchX, iTouchY;
	int iTouchUpX, iTouchUpY;
	int iPrevDragX, iPrevDragY;
	int iWidth, iHeight;
	int iAlphabetLine[];

	boolean bTouchUp=false;
	boolean bTouchDown=false;

	@Override
	public void create () {
		Gdx.graphics.setContinuousRendering(false);

		iWidth = Gdx.graphics.getWidth();
		iHeight = Gdx.graphics.getHeight();
		camera = new OrthographicCamera();
		camera.setToOrtho(false,iWidth,iHeight);

		batch = new SpriteBatch();

		pixmap = new Pixmap(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.RED);
		pixmap.fillCircle(30,30, 5);
		pixmap.fillCircle(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 5);

		texture = new Texture(pixmap);
		//sprite = new Sprite(texture);

		iDragX= iDragY=0;
		iTouchX = iTouchY=0;
		iTouchUpX = iTouchUpY=0;
		iPrevDragX = iPrevDragY =0;
		//iAlphabetLine = new Integer[5];

		Gdx.input.setInputProcessor(this);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		if(bTouchUp) {
			pixmap.setColor(Color.YELLOW);
			pixmap.drawLine(iTouchX, iTouchY, iTouchUpX, iTouchUpY);
			texture.draw(pixmap, 0, 0);
			bTouchUp=false;
		} else
		{
			pixmap.setColor(Color.BLUE);

			if(bTouchDown==false) {

				pixmap.drawLine(iPrevDragX, iPrevDragY, iDragX, iDragY);
				iPrevDragX = iDragX;
				iPrevDragY = iDragY;

				// this is where magic happened
				//pixmap.drawLine(iTouchX,iTouchY,iDragX,iDragY);
				texture.draw(pixmap, 0, 0);
			}
		}

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
		iTouchX=screenX;
		iTouchY=screenY;

		iPrevDragX=screenX;
		iPrevDragY=screenY;

		bTouchDown=true;
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		System.out.println("touch up");
		bTouchUp=true;
		iTouchUpX=screenX;
		iTouchUpY=screenY;
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {

//		if(bTouchDown==false) {
//			iPrevDragX = iDragX;
//			iPrevDragY = iDragY;
//		}
//		else
			bTouchDown = false;

		iDragX=screenX;
		iDragY=screenY;

		System.out.println("x|y : " + screenX + "," + screenY);
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
