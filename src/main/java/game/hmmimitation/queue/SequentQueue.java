package game.hmmimitation.queue;

import game.hmmimitation.Unit;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Евгений
 */
public class SequentQueue implements Queue{

    private class UnitStep implements Comparable<UnitStep>{
        Unit unit;
        boolean isWent = false;

        public UnitStep(Unit unit) {
            this.unit = unit;
        }

        @Override
        public int compareTo(UnitStep o) {
            UnitStep o1 = this, o2 = o;
            if (o1.isWent && o2.isWent) {
                return 0;
            }
            if (o1.isWent && !o2.isWent) {
                return -1;
            }
            if (!o1.isWent && o2.isWent) {
                return 1;
            }
//            int difference = o1.unit.getInitiative() - o2.unit.getInitiative();
//            if (difference != 0) {
//                return difference;
//            }
//            if (o1.unit.isAttacker() != o2.unit.isAttacker()) {
//                return o1.unit.isAttacker() ? 1 : -1;
//            }
            return 0;
        }
        
    }
    
    private List<UnitStep> queue;
    
    @Override
    public void addUnit(Unit unit) {
        UnitStep unitStep = new UnitStep(unit);
        queue.add(unitStep);
    }

    @Override
    public Unit getCurrentUnit() {
        boolean haveAllStep = queue.stream()
                .anyMatch(step -> !step.isWent);
        if (haveAllStep) {
            queue.stream().forEach((step) -> {
                step.isWent = false;
            });
        }
        Collections.sort(queue);
        UnitStep first = queue.get(0);
        first.isWent = true;
        return first.unit;
    }

    @Override
    public void deleteUnit(Unit unit) {
        for (UnitStep step : queue) {
            if (unit.equals(step.unit)) {
                queue.remove(step);
                break;
            }
        }
    }
    
}
