package uk.co.zacgarby.battlesim.physics;

import com.badlogic.gdx.math.Vector2;

public class Circle extends Shape {
    public float radius;

    public Circle(float radius) {
        this.radius = radius;
    }

    @Override
    public IntersectionResult intersect(Vector2 thisPos, Vector2 otherPos, Shape other) {
        if (other instanceof Circle) {
            Circle circle = (Circle) other;
            Vector2 diff = thisPos.cpy().sub(otherPos);
            float dist = circle.radius + radius;
            float len = diff.len();

            if (diff.len() <= dist) {
                return new IntersectionResult(true, diff.nor().scl(dist - len));
            } else {
                return new IntersectionResult(false, null);
            }
        }

        return null;
    }
}
