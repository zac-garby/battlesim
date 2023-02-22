package uk.co.zacgarby.battlesim.formations;

import com.badlogic.gdx.math.Vector2;

public abstract class Formation {
    protected Vector2 location;
    protected float heading;
    protected int numUnits;
    protected boolean isDirty;

    public abstract Vector2 layout(int index);

    public void setLocation(Vector2 location) {
        setLocation(location.x, location.y);
    }

    public void setLocation(float x, float y) {
        this.location.x = x;
        this.location.y = y;
        this.isDirty = true;
    }

    public void setHeading(float heading) {
        this.heading = heading;
        this.isDirty = true;
    }

    public Vector2 getLocation() {
        return location;
    }

    public float getHeading() {
        return heading;
    }

    public boolean isDirty() {
        return isDirty;
    }

    public void setDirty(boolean isDirty) {
        this.isDirty = isDirty;
    }
}
