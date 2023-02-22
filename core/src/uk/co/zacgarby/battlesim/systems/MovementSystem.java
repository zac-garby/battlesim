package uk.co.zacgarby.battlesim.systems;

import com.badlogic.ashley.core.*;
import uk.co.zacgarby.battlesim.components.CollisionComponent;
import uk.co.zacgarby.battlesim.components.MovementComponent;
import uk.co.zacgarby.battlesim.components.PositionComponent;
import uk.co.zacgarby.battlesim.physics.IntersectionResult;

public class MovementSystem extends FamilySystem {
    public MovementSystem() {
        super(Family.all(PositionComponent.class, MovementComponent.class).get());
    }

    @Override
    public void updateEntity(Entity entity, float dt) {
        MovementComponent movement = MovementComponent.mapper.get(entity);
        PositionComponent position = PositionComponent.mapper.get(entity);

        position.pos.add(movement.velocity.cpy().scl(dt));

        if (CollisionComponent.mapper.has(entity)) {
            CollisionComponent thisCollision = CollisionComponent.mapper.get(entity);

            for (Entity other : entities) {
                if (other != entity && CollisionComponent.mapper.has(other)) {
                    CollisionComponent otherCollision = CollisionComponent.mapper.get(other);
                    PositionComponent otherPosition = PositionComponent.mapper.get(other);

                    IntersectionResult intersection = thisCollision.shape.intersect(
                            position.pos,
                            otherPosition.pos,
                            otherCollision.shape);

                    if (intersection.intersect) {
                        position.pos.add(intersection.toEdge.scl(1.1f));
                    }
                }
            }
        }

        movement.velocity.x = 0;
        movement.velocity.y = 0;
    }
}
