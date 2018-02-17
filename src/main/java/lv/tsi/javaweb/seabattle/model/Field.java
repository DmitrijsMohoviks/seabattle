package lv.tsi.javaweb.seabattle.model;

import java.util.*;

/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
public class Field {
    private Map<String, CellContent> content = new HashMap<>();
    private boolean invalid;

    public boolean hasMoreShips() {
        return content.containsValue(CellContent.SHIP);
    }

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
        Map<String, Ship> ships = new HashMap<>();
        for (int row = 1; row <= 10; row++) {
            for (char col = 'A'; col <= 'J'; col++) {
                String addr = "" + col + row;
                if (!hasShip(addr)) {
                    continue;
                }
                String leftAddr = "" + (char) (col - 1) + row;
                Ship ship = ships.get(leftAddr);
                if (ship == null) {
                    String topAddr = "" + col + (row - 1);
                    ship = ships.get(topAddr);
                }
                if (ship == null) {
                    ship = new Ship();
                }
                ship.add(addr);
                ships.put(addr, ship);
            }
        }
        return new HashSet<>(ships.values());
    }

    public boolean isInvalid() {
        return invalid;
    }

    public void setCell(String addr, CellContent value) {
        content.put(addr, value);
    }

    private class Ship {
        Set<String> addresses = new HashSet<>();
        boolean invalid;

        void add(String addr) {
            addresses.add(addr);
        }

        int size() {
            return addresses.size();
        }

        void markInvalid() {
            for (String addr : addresses) {
                content.put(addr, CellContent.HIT);
            }
        }

        boolean isInvalid() {
            return invalid;
        }

        void validate() {
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
    }
}
