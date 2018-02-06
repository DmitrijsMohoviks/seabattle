package lv.tsi.javaweb.seabattle.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
public class Field {
    private Map<String, CellContent> content = new HashMap<>();
    private boolean invalid;

    public void setShip(String addr) {
        content.put(addr, CellContent.SHIP);
    }

    public boolean hasShip(String addr) {
        return content.get(addr) == CellContent.SHIP;
    }

    public CellContent getCell(String addr) {
        return content.getOrDefault(addr, CellContent.EMPTY);
    }

    public void clear() {
        content.clear();
    }

    public void validate() {
        this.invalid = content.size() != 20;
    }

    public boolean isInvalid() {
        return invalid;
    }

}
