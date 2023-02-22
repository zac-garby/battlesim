package uk.co.zacgarby.battlesim.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.math.Vector2;
import uk.co.zacgarby.battlesim.components.GoalComponent;
import uk.co.zacgarby.battlesim.components.MovementComponent;
import uk.co.zacgarby.battlesim.components.PositionComponent;

public class FollowingSystem extends FamilySystem {
    public FollowingSystem() {
        super(Family.all(PositionComponent.class, MovementComponent.class, GoalComponent.class).get());
    }

    @Override
    public void updateEntity(Entity entity, float dt) {
        GoalComponent goal = GoalComponent.mapper.get(entity);
        MovementComponent movement = MovementComponent.mapper.get(entity);
        PositionComponent position = PositionComponent.mapper.get(entity);

        Vector2 diff = goal.pos.cpy().sub(position.pos);

        if (diff.len2() > (goal.range * goal.range)) {
            movement.velocity = diff.nor().scl(movement.speed);
        }
    }
}
