package uk.co.zacgarby.battlesim.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import uk.co.zacgarby.battlesim.Battlesim;
import uk.co.zacgarby.battlesim.components.PositionComponent;
import uk.co.zacgarby.battlesim.components.SpriteComponent;
import uk.co.zacgarby.battlesim.components.TeamMaskComponent;

import java.util.Arrays;
import java.util.Comparator;

public class SpriteRenderSystem extends FamilySystem {
    public SpriteBatch batch;

    public SpriteRenderSystem(SpriteBatch batch) {
        super(Family.all(SpriteComponent.class, PositionComponent.class).get());
        this.batch = batch;
        this.priority = 1;
    }

    @Override
    public void updateEntity(Entity entity, float dt) {
        SpriteComponent sprite = SpriteComponent.mapper.get(entity);
        PositionComponent position = PositionComponent.mapper.get(entity);

        batch.draw(sprite.texture,
                position.pos.x - sprite.scaleX/2f, position.pos.y + sprite.scaleX/2f,
                sprite.scaleX/2f, 0.0f,
                sprite.scaleX, sprite.scaleX * sprite.getAspectRatio(),
                1f, 1f,
                Battlesim.perspectiveRotation);

        if (TeamMaskComponent.mapper.has(entity)) {
            TeamMaskComponent teamMask = TeamMaskComponent.mapper.get(entity);

            batch.setColor(teamMask.colour);
            batch.draw(teamMask.texture,
                    position.pos.x - sprite.scaleX/2f, position.pos.y + sprite.scaleX/2f,
                    sprite.scaleX/2f, 0.0f,
                    sprite.scaleX, sprite.scaleX * sprite.getAspectRatio(),
                    1f, 1f,
                    Battlesim.perspectiveRotation);
            batch.setColor(255, 255, 255, 255);
        }
    }

    @Override
    public void update(float dt) {
        Object[] entities = this.entities.toArray();

        Arrays.sort(entities, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                PositionComponent s1 = PositionComponent.mapper.get((Entity) o1);
                PositionComponent s2 = PositionComponent.mapper.get((Entity) o2);
                return (int) Math.signum(s2.pos.y - s1.pos.y);
            }
        });

        for (Object e : entities) {
            updateEntity((Entity) e, dt);
        }
    }
}
