package game.hmmimitation;

import java.util.Objects;
import java.util.Random;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Евгений
 */
public class Unit {

    private static final Random RANDOMIZER = new Random();

    private final String name;

    @Getter
    private final int healthPoints;

    @Getter
    private int currentHealthPoints;

    @Getter
    @Setter
    private int attack;

    @Getter
    @Setter
    private int minDamage;

    @Getter
    @Setter
    private int maxDamage;

    @Getter
    @Setter
    private int defense;

    @Getter
    @Setter
    private int movementPoint;

    @Getter
    @Setter
    private int initiative;

    @Getter
    @Setter
    private int shotsNumber;

    @Getter
    private int remainingShots;

    @Getter
    @Setter
    private int unitsNumber;

    @Getter
    private final boolean isAttacker;

    //клетка, на которой расположен юнит (мб не надо)
    /**
     * Рассчитывает урон, который получил отряд. Урон уже высчитан с учетом
     * параметров нападения и защиты.
     *
     * @param damage наносимый отряду урон
     * @return true, если отряд уничтожен, false, если отряд уцелел
     */
    public boolean takeDamage(int damage) {
        int totalHealth = (unitsNumber - 1) * healthPoints + currentHealthPoints;
        if (totalHealth <= damage) {
            this.unitsNumber = 0;
            this.currentHealthPoints = 0;
            return true;
        }
        int killed = damage / healthPoints;
        unitsNumber -= killed;

        int remainDamage = damage - killed * healthPoints;
        if (remainDamage >= currentHealthPoints) {
            currentHealthPoints = healthPoints + currentHealthPoints - remainDamage;
            unitsNumber--;
        } else {
            currentHealthPoints -= remainDamage;
        }
        return false;
    }

    public boolean attackUnit(Unit another) {
        final double modifierCoefficient = 0.05;
        int baseDamage = getDamage();
        double modifier;
        int differenceInAttackAndDefense = this.attack - another.defense;
        if (differenceInAttackAndDefense >= 0) {
            modifier = 1 + differenceInAttackAndDefense * modifierCoefficient;
        } else {
            modifier = 1 / (1 - differenceInAttackAndDefense * modifierCoefficient);
        }
        int totalDamage = (int) (this.unitsNumber * baseDamage * modifier);
        return another.takeDamage(totalDamage);
    }

    /**
     * Получает случайное число урона в промежутке от минимального до
     * максимального.
     *
     * @return
     */
    public int getDamage() {
        return minDamage == maxDamage
                ? maxDamage
                : minDamage + RANDOMIZER.nextInt(maxDamage - minDamage);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Unit other = (Unit) obj;
        if (this.unitsNumber != other.unitsNumber) {
            return false;
        }
        if (this.isAttacker != other.isAttacker) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.name);
        hash = 73 * hash + this.unitsNumber;
        hash = 73 * hash + (this.isAttacker ? 1 : 0);
        return hash;
    }

    public Unit(String name, int healthPoints, boolean isAttacker) {
        this.name = name;
        this.isAttacker = isAttacker;
        this.healthPoints = healthPoints;
        this.currentHealthPoints = healthPoints;
    }

    public Unit(String name, int healthPoints, int attack, int minDamage, int maxDamage, int defense, int movementPoint, int initiative, int shotsNumber, int unitsNumber, boolean isAttacker) {
        this(name, healthPoints, isAttacker);
        this.attack = attack;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.defense = defense;
        this.movementPoint = movementPoint;
        this.initiative = initiative;
        this.shotsNumber = shotsNumber;
        this.remainingShots = shotsNumber;
        this.unitsNumber = unitsNumber;
    }

    private boolean isDead() {
        return unitsNumber == 1 && currentHealthPoints == 0;
    }
}
