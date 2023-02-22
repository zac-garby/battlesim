package uk.co.zacgarby.battlesim.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import uk.co.zacgarby.battlesim.physics.Shape;

public class CollisionComponent implements Component {
    public static ComponentMapper<CollisionComponent> mapper = ComponentMapper.getFor(CollisionComponent.class);

    public Shape shape;

    public CollisionComponent(Shape shape) {
        this.shape = shape;
    }
}
