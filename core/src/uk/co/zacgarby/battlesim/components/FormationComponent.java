package uk.co.zacgarby.battlesim.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import uk.co.zacgarby.battlesim.formations.Formation;

public class FormationComponent implements Component {
    public static ComponentMapper<FormationComponent> mapper = ComponentMapper.getFor(FormationComponent.class);

    public int index;
    public Formation formation;

    public FormationComponent(Formation formation, int index) {
        this.formation = formation;
        this.index = index;
    }
}
