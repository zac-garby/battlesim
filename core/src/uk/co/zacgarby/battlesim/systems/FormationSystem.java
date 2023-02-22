package uk.co.zacgarby.battlesim.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import uk.co.zacgarby.battlesim.Families;
import uk.co.zacgarby.battlesim.components.FormationComponent;
import uk.co.zacgarby.battlesim.components.GoalComponent;
import uk.co.zacgarby.battlesim.formations.Formation;

import java.util.ArrayDeque;
import java.util.Queue;

public class FormationSystem extends FamilySystem implements EntityListener {
    private final Queue<Formation> appliedFormations = new ArrayDeque<>();

    public FormationSystem() {
        super(Families.formation);
    }

    @Override
    public void updateEntity(Entity entity, float dt) {
        FormationComponent formation = FormationComponent.mapper.get(entity);

        if (formation.formation.isDirty()) {
            GoalComponent goal = GoalComponent.mapper.get(entity);
            goal.pos = formation.formation.layout(formation.index);
        }
    }

    @Override
    public void update(float dt) {
        for (Entity e : entities) {
            updateEntity(e, dt);
        }

        while (!appliedFormations.isEmpty()) {
            Formation formation = appliedFormations.remove();
            formation.setDirty(false);
        }
    }

    @Override
    public void entityAdded(Entity entity) {
        super.entityAdded(entity);

        GoalComponent goal = GoalComponent.mapper.get(entity);
        FormationComponent formation = FormationComponent.mapper.get(entity);

        goal.pos = formation.formation.layout(formation.index);
    }
}