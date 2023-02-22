package uk.co.zacgarby.battlesim.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.math.Vector2;

public class GoalComponent implements Component {
    public static ComponentMapper<GoalComponent> mapper = ComponentMapper.getFor(GoalComponent.class);

    public Vector2 pos;
    public float range;

    public GoalComponent(float x, float y, float range) {
        this.pos = new Vector2(x, y);
        this.range = range;
    }
}
