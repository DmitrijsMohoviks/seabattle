package lv.tsi.javaweb.seabattle.model;

import java.util.*;

/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
public class Field {
    private final Map<String, CellContent> content = new HashMap<>();
    private final Map<String, Ship> contentShips = new HashMap<>();
    private boolean invalid;

    public boolean hasMoreShips() {
        return content.containsValue(CellContent.SHIP);
    }

    public Ship getShip(String addr) {
        return contentShips.get(addr);
    }

    public CellContent getCell(String addr) {
        return content.getOrDefault(addr, CellContent.EMPTY);
    }

    public void clear() {
        content.clear();
        contentShips.clear();
        invalid = false;
    }

    public void validate() {
        if (content.size() != 20) {
            invalid = true;
        }
        Set<Ship> ships = collectConnectedCells();
        List<Integer> sizes = new ArrayList<>(Arrays.asList(4, 3, 3, 2, 2, 2, 1, 1, 1, 1));
        for (Ship ship : ships) {
            boolean sizeIsAllowed = sizes.remove((Integer) ship.size());
            ship.validate();
            if (!sizeIsAllowed || ship.isInvalid()) {
                ship.markInvalid();
                invalid = true;
            }
        }
        if (!sizes.isEmpty()) {
            invalid = true;
        }
    }

    private Set<Ship> collectConnectedCells() {
        contentShips.clear();
        for (int row = 1; row <= 10; row++) {
            for (char col = 'A'; col <= 'J'; col++) {
                String addr = "" + col + row;
                if (getCell(addr) != CellContent.SHIP) {
                    continue;
                }
                String leftAddr = "" + (char) (col - 1) + row;
                Ship ship = contentShips.get(leftAddr);
                if (ship == null) {
                    String topAddr = "" + col + (row - 1);
                    ship = contentShips.get(topAddr);
                }
                if (ship == null) {
                    ship = new Ship();
                }
                ship.add(addr);
                contentShips.put(addr, ship);
            }
        }
        return new HashSet<>(contentShips.values());
    }

    public boolean isInvalid() {
        return invalid;
    }

    public void setCell(String addr, CellContent value) {
        content.put(addr, value);
    }

    public class Ship {
        private Set<String> addresses = new HashSet<>();
        private int hitCount;
        private boolean invalid;
        private boolean killed;

        private void add(String addr) {
            addresses.add(addr);
        }

        private int size() {
            return addresses.size();
        }

        private void markInvalid() {
            for (String addr : addresses) {
                content.put(addr, CellContent.HIT);
            }
        }

        private boolean isInvalid() {
            return invalid;
        }

        public boolean isKilled() {
            return killed;
        }

        private void validate() {
            // check diagonals
            for (String addr : addresses) {
                char col = addr.charAt(0);
                int row = Integer.parseInt(addr.substring(1));
                String topLeftAddr = "" + (char) (col - 1) + (row - 1);
                String topRightAddr = "" + (char) (col + 1) + (row - 1);
                String bottomLeftAddr = "" + (char) (col - 1) + (row + 1);
                String bottomRightAddr = "" + (char) (col + 1) + (row + 1);
                if (getCell(topLeftAddr) != CellContent.EMPTY
                        || getCell(topRightAddr) != CellContent.EMPTY
                        || getCell(bottomLeftAddr) != CellContent.EMPTY
                        || getCell(bottomRightAddr) != CellContent.EMPTY) {
                    invalid = true;
                    return;
                }
            }
        }

        public void hit(String addr) {
            if (!addresses.contains(addr) || getCell(addr) != CellContent.SHIP) {
                return;
            }
            setCell(addr, CellContent.HIT);
            hitCount++;
            if (hitCount == addresses.size()) {
                killed = true;
                markKill(Field.this);
            }
        }

        public void markKill(Field field) {
            for (String addr : addresses) {
                field.setCell(addr, CellContent.KILL);
            }
        }
    }
}
