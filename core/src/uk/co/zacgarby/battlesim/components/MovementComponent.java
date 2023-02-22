package uk.co.zacgarby.battlesim.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.math.Vector2;

public class MovementComponent implements Component {
    public static ComponentMapper<MovementComponent> mapper = ComponentMapper.getFor(MovementComponent.class);

    public MovementComponent(float speed) {
        this.speed = speed;
        this.velocity = new Vector2();
    }

    // metres per second
    public float speed;

    public Vector2 velocity;
}
