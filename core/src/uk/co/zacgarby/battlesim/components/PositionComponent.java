package uk.co.zacgarby.battlesim.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.math.Vector2;

public class PositionComponent implements Component {
    public static ComponentMapper<PositionComponent> mapper = ComponentMapper.getFor(PositionComponent.class);

    public Vector2 pos;

    public PositionComponent(float x, float y) {
        this.pos = new Vector2(x, y);
    }
}
