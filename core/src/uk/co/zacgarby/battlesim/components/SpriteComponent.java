package uk.co.zacgarby.battlesim.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteComponent implements Component {
    public static ComponentMapper<SpriteComponent> mapper = ComponentMapper.getFor(SpriteComponent.class);

    public float scaleX = 1.0f;
    public TextureRegion texture;

    public SpriteComponent(Texture texture) {
        this.texture = new TextureRegion(texture);
    }

    public float getWidth() {
        return texture.getRegionWidth();
    }

    public float getHeight() {
        return texture.getRegionHeight();
    }

    public float getAspectRatio() {
        return getHeight() / getWidth();
    }
}
