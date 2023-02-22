package uk.co.zacgarby.battlesim.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TeamMaskComponent implements Component {
    public Color colour;
    public static ComponentMapper<TeamMaskComponent> mapper = ComponentMapper.getFor(TeamMaskComponent.class);
    public TextureRegion texture;

    public TeamMaskComponent(Texture texture, Color colour) {
        this.colour = colour;
        this.texture = new TextureRegion(texture);
    }

    public float getWidth() {
        return texture.getRegionWidth();
    }

    public float getHeight() {
        return texture.getRegionHeight();
    }
}
