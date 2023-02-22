package uk.co.zacgarby.battlesim.physics;

import com.badlogic.gdx.math.Vector2;

public abstract class Shape {
    public abstract IntersectionResult intersect(Vector2 thisPos, Vector2 otherPos, Shape other);
}
