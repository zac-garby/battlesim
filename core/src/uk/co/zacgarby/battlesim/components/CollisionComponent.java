package uk.co.zacgarby.battlesim.components;

import com.badlogic.ashley.core.Component;
import uk.co.zacgarby.battlesim.physics.Shape;

public class CollisionComponent implements Component {
    public Shape shape;

    public CollisionComponent(Shape shape) {
        this.shape = shape;
    }
}
