package uk.co.zacgarby.battlesim.physics;

import com.badlogic.gdx.math.Vector2;

public class IntersectionResult {
    public boolean intersect;
    public Vector2 toEdge;

    public IntersectionResult(boolean intersect, Vector2 toEdge) {
        this.intersect = intersect;
        this.toEdge = toEdge;
    }
}
