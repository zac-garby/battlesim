package uk.co.zacgarby.battlesim.systems;

import com.badlogic.ashley.core.*;
import uk.co.zacgarby.battlesim.components.MovementComponent;
import uk.co.zacgarby.battlesim.components.PositionComponent;

public class MovementSystem extends FamilySystem {
    public MovementSystem() {
        super(Family.all(PositionComponent.class, MovementComponent.class).get());
    }

    @Override
    public void updateEntity(Entity entity, float dt) {
        MovementComponent movement = MovementComponent.mapper.get(entity);
        PositionComponent position = PositionComponent.mapper.get(entity);

        position.pos.add(movement.velocity.cpy().scl(dt));

        movement.velocity.x = 0;
        movement.velocity.y = 0;
    }
}
