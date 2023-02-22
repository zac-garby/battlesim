package uk.co.zacgarby.battlesim;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import uk.co.zacgarby.battlesim.components.*;
import uk.co.zacgarby.battlesim.formations.Formation;
import uk.co.zacgarby.battlesim.formations.RowsFormation;
import uk.co.zacgarby.battlesim.systems.*;

public class Battlesim extends ApplicationAdapter {
	public Engine engine;

	private SpriteBatch batch;
	private OrthographicCamera camera;
	private OrthographicCamera uiCamera;
	private BitmapFont font;

	RowsFormation formation = new RowsFormation(new Vector2(0f, -10f), 0f, 5, 100);

	public static final float perspectiveRotation = 5f;

	@Override
	public void create () {
		batch = new SpriteBatch();

		engine = new Engine();

		FollowingSystem followingSystem = new FollowingSystem();
		engine.addEntityListener(
				Family.all(PositionComponent.class, MovementComponent.class, GoalComponent.class).get(),
				followingSystem);
		engine.addSystem(followingSystem);

		engine.addSystem(new MovementSystem());
		engine.addSystem(new SpriteRenderSystem(batch));
		engine.addSystem(new ShadowRenderSystem(batch));
		engine.addSystem(new FormationSystem());

		camera = new OrthographicCamera(75, 75 * ((float) Gdx.graphics.getHeight() / Gdx.graphics.getWidth()));
		uiCamera = new OrthographicCamera((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);

		camera.rotate(-perspectiveRotation);
		camera.translate(0, 2f);

		Texture kite = new Texture(Gdx.files.internal("kite.png"));
		Texture round = new Texture(Gdx.files.internal("round.png"));
		Texture archer = new Texture(Gdx.files.internal("archer.png"));
		Texture flag = new Texture(Gdx.files.internal("flag.png"));

		Texture kiteMask = new Texture(Gdx.files.internal("kite-mask.png"));
		Texture roundMask = new Texture(Gdx.files.internal("round-mask.png"));
		Texture archerMask = new Texture(Gdx.files.internal("archer-mask.png"));
		Texture flagMask = new Texture(Gdx.files.internal("flag-mask.png"));

		font = new BitmapFont();

		int i = 0;
		for (int x = -10; x < 10; x++) {
			for (int y = -10; y > -15; y--) {
				Entity entity = new Entity();

				float rx = (float) (Math.random() - 0.5) * 0.2f;
				float ry = (float) (Math.random() - 0.5) * 0.2f;
				float rs = (float) (Math.random() - 0.5) * 0.05f + 1f;

				entity.add(new SpriteComponent(kite));
				entity.add(new TeamMaskComponent(kiteMask, new Color(150f / 255f, 179f / 255f, 219f / 255f, 1f)));
				entity.add(new ShadowComponent(0.1f));

				entity.add(new MovementComponent(rs));
				entity.add(new PositionComponent(x + rx, y + ry));

				entity.add(new GoalComponent(x + rx, y + ry + 4, 0.1f));
				entity.add(new FormationComponent(formation, i++));

				engine.addEntity(entity);
			}
		}

		for (int y = 13; y < 15; y++) {
			for (int x = -30; x < 30; x++) {
				Entity entity = new Entity();

				float rx = (float) (Math.random() - 0.5) * 0.2f;
				float ry = (float) (Math.random() - 0.5) * 0.2f;
				float rs = (float) (Math.random() - 0.5) * 0.05f + 1f;

				entity.add(new SpriteComponent(archer));
				entity.add(new TeamMaskComponent(archerMask, new Color(239f / 255f, 212f / 255f, 53f / 255f, 1f)));
				entity.add(new ShadowComponent(0.1f));

				entity.add(new PositionComponent(x + rx, y + ry));
				entity.add(new MovementComponent(rs));

				engine.addEntity(entity);
			}
		}

		for (int y = 6; y < 10; y++) {
			for (int x = -30; x < 30; x++) {
				Entity entity = new Entity();

				float rx = (float) (Math.random() - 0.5) * 0.2f;
				float ry = (float) (Math.random() - 0.5) * 0.2f;
				float rs = (float) (Math.random() - 0.5) * 0.05f + 1f;

				entity.add(new SpriteComponent(round));
				entity.add(new TeamMaskComponent(roundMask, new Color(239f / 255f, 212f / 255f, 53f / 255f, 1f)));
				entity.add(new PositionComponent(x + rx, y + ry));
				entity.add(new MovementComponent(rs));
				entity.add(new ShadowComponent(0.1f));

				engine.addEntity(entity);
			}
		}

		for (int x = -30; x < 30; x += 5) {
			Entity entity = new Entity();

			float rx = (float) (Math.random() - 0.5) * 0.35f;
			float ry = (float) (Math.random() - 0.5) * 0.35f;
			float rs = (float) (Math.random() - 0.5) * 0.05f + 1f;

			entity.add(new SpriteComponent(flag));
			entity.add(new TeamMaskComponent(flagMask, new Color(239f / 255f, 212f / 255f, 53f / 255f, 1f)));
			entity.add(new PositionComponent(x + rx, 11 + ry));
			entity.add(new MovementComponent(rs));
			entity.add(new ShadowComponent(0.1f));

			engine.addEntity(entity);
		}
	}

	@Override
	public void render () {
		ScreenUtils.clear(150f / 255f, 137f / 255f, 111f / 255f, 1);

		batch.setProjectionMatrix(uiCamera.combined);
		batch.begin();
		font.draw(batch, "fps: " + Gdx.graphics.getFramesPerSecond(), -390, 390);
		batch.end();

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		update();
		batch.end();
	}

	public void update() {
		float dt = Gdx.graphics.getDeltaTime();
		float camSpeed = 10f;

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			camera.translate(-camSpeed * dt, 0, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			camera.translate(camSpeed * dt, 0, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			camera.translate(0, -camSpeed * dt, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			camera.translate(0, camSpeed * dt, 0);
		}

		formation.setHeading(formation.getHeading() + 10f * dt);

		engine.update(dt);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
