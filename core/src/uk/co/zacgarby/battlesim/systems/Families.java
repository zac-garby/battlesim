package uk.co.zacgarby.battlesim.systems;

import com.badlogic.ashley.core.Family;
import uk.co.zacgarby.battlesim.components.FormationComponent;
import uk.co.zacgarby.battlesim.components.GoalComponent;
import uk.co.zacgarby.battlesim.components.PositionComponent;
import uk.co.zacgarby.battlesim.components.SpriteComponent;

public abstract class Families {
    public static final Family renderable = Family.all(SpriteComponent.class, PositionComponent.class).get();
    public static final Family formation = Family.all(FormationComponent.class, GoalComponent.class).get();
}
