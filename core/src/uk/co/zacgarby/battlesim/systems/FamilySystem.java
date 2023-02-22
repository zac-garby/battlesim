package uk.co.zacgarby.battlesim.systems;

import com.badlogic.ashley.core.*;
import uk.co.zacgarby.battlesim.components.MovementComponent;
import uk.co.zacgarby.battlesim.components.PositionComponent;

import java.util.HashSet;
import java.util.Set;

public abstract class FamilySystem extends EntitySystem implements EntityListener {
    private Family family;
    protected final Set<Entity> entities = new HashSet<>();

    public FamilySystem(Family family) {
        this.family = family;
    }

    @Override
    public void addedToEngine(Engine engine) {
        engine.addEntityListener(family, this);

        for (Entity e : engine.getEntitiesFor(family)) {
            entities.add(e);
        }
    }

    @Override
    public void removedFromEngine(Engine engine) {
        engine.removeEntityListener(this);
    }

    public abstract void updateEntity(Entity entity, float dt);

    @Override
    public void update(float dt) {
        for (Entity e : entities) {
            updateEntity(e, dt);
        }
    }

    @Override
    public void entityAdded(Entity entity) {
        entities.add(entity);
    }

    @Override
    public void entityRemoved(Entity entity) {
        entities.remove(entity);
    }
}
