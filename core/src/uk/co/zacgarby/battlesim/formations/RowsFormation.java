package uk.co.zacgarby.battlesim.formations;

import com.badlogic.gdx.math.Vector2;

public class RowsFormation extends Formation {
    private int numRanks;
    private float rankSpacing, columnSpacing;

    public RowsFormation(Vector2 location, float heading, int numRanks, int numUnits) {
        this.location = location;
        this.heading = heading;
        this.numRanks = numRanks;
        this.numUnits = numUnits;
        this.rankSpacing = 1f;
        this.columnSpacing = 1f;
    }

    @Override
    public Vector2 layout(int index) {
        int rank = index % numRanks;
        int column = index / numRanks;
        float width = columnSpacing * (float) numUnits / (float) numRanks;

        Vector2 right = new Vector2(1, 0).rotateDeg(heading);
        Vector2 back = new Vector2(0, -1).rotateDeg(heading);
        Vector2 topLeft = location.cpy().sub(right.cpy().scl(width / 2f));

        // this.location is the position of the front centre unit.
        return topLeft.add(right.scl(column)).add(back.scl(rank));
    }

    public void setRanks(int ranks) {
        this.numRanks = ranks;
        this.setDirty(true);
    }

    public int getNumRanks() {
        return numRanks;
    }
}
