package game.hmmimitation.queue;

import game.hmmimitation.Unit;

/**
 *
 * @author Евгений
 */
public interface Queue {
    void addUnit(Unit unit);
    
    Unit getCurrentUnit();
    
    void deleteUnit(Unit unit);
}
