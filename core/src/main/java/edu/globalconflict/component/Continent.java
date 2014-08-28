package edu.globalconflict.component;

import edu.globalconflict.entity.Component;

/**
 * @author mateusz
 * @since 28.08.14
 */
public final class Continent implements Component {
    public final int bonus;
    public final String[] territories;

    public Continent(int bonus, String[] territories) {
        this.bonus = bonus;
        this.territories = territories;
    }
}
