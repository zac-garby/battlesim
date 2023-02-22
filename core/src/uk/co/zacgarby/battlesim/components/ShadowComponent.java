package uk.co.zacgarby.battlesim.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.Color;

public class ShadowComponent implements Component {
    public static ComponentMapper<ShadowComponent> mapper = ComponentMapper.getFor(ShadowComponent.class);

    public Color colour;
    public float scale;

    public ShadowComponent(float transparency) {
        this.colour = new Color(0f, 0f, 0f, transparency);
        this.scale = 0.65f;
    }
}
