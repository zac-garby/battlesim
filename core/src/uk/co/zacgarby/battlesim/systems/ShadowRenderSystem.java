package uk.co.zacgarby.battlesim.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import uk.co.zacgarby.battlesim.Battlesim;
import uk.co.zacgarby.battlesim.components.PositionComponent;
import uk.co.zacgarby.battlesim.components.ShadowComponent;
import uk.co.zacgarby.battlesim.components.SpriteComponent;
import uk.co.zacgarby.battlesim.components.TeamMaskComponent;

import java.util.Arrays;
import java.util.Comparator;

public class ShadowRenderSystem extends FamilySystem {
    public static TextureRegion texture;
    public SpriteBatch batch;

    public ShadowRenderSystem(SpriteBatch batch) {
        super(Family.all(SpriteComponent.class, PositionComponent.class, ShadowComponent.class).get());
        this.batch = batch;
        this.priority = 0;

        if (texture == null) {
            texture = new TextureRegion(new Texture(Gdx.files.internal("shadow.png")));
        }
    }

    @Override
    public void updateEntity(Entity entity, float dt) {
        SpriteComponent sprite = SpriteComponent.mapper.get(entity);
        ShadowComponent shadow = ShadowComponent.mapper.get(entity);
        PositionComponent position = PositionComponent.mapper.get(entity);

        float scale = sprite.scaleX * 0.65f;
        float height = scale * (float) texture.getRegionHeight() / (float) texture.getRegionWidth();

        batch.setColor(shadow.colour);
        batch.draw(texture,
                position.pos.x - scale/2f, position.pos.y + height / 2,
                scale/2f, 0,
                scale, height,
                1f, 1f,
                Battlesim.perspectiveRotation);
        batch.setColor(255, 255, 255, 255);
    }
}
