package game.hmmimitation;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Евгений
 */
public class UnitTest {

    public UnitTest() {
    }

    /**
     * Test of takeDamage method, of class Unit.
     */
    @Test
    public void testTakeDamage() {
        Unit test = new Unit("test", 10, true);
        test.setUnitsNumber(100);
        assertFalse(test.takeDamage(63));
        assertUnitsNumberAndHealth(test, 94, 7);
        assertFalse(test.takeDamage(126));
        assertUnitsNumberAndHealth(test, 82, 1);
        assertTrue(test.takeDamage(840));
        assertUnitsNumberAndHealth(test, 0, 0);

    }

    private void assertUnitsNumberAndHealth(Unit unit, int answerNumber, int answerHealth) {
        assertEquals(unit.getUnitsNumber(), answerNumber);
        assertEquals(unit.getCurrentHealthPoints(), answerHealth);
    }

    /**
     * Test of attackUnit method, of class Unit.
     */
    @Test
    public void testAttackUnit() {
        Unit archer = createFirstUnit(),
               spearman = createSecondUnit();
        assertFalse(archer.attackUnit(spearman));
        assertUnitsNumberAndHealth(spearman, 6, 5);
        assertFalse(spearman.attackUnit(archer));
        assertUnitsNumberAndHealth(archer, 8, 9);
        assertFalse(archer.attackUnit(spearman));
        assertUnitsNumberAndHealth(spearman, 5, 2);
    }
    
    private Unit createFirstUnit() {
        Unit unit = new Unit("archer", 10, 5, 2, 2, 2, 0, 0, 3, 10, true);
        return unit;
    }
    
    private Unit createSecondUnit() {
        Unit unit = new Unit("pikeman", 12, 6, 3, 3, 6, 0, 0, 0, 7, false);
        return unit;
    }

}
