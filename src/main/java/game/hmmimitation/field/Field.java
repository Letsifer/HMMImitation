package game.hmmimitation.field;

import game.hmmimitation.Unit;

/**
 *
 * @author gea
 */
public interface Field {
    public Unit getUnitAtCell(int i, int j);
    
    public void initUnits();
}
