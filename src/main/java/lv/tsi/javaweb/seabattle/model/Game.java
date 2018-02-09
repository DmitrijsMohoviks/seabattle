package lv.tsi.javaweb.seabattle.model;

/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
public class Game {
    private Player player1;
    private Player player2;
    private boolean player1Move = true;

    public Player getCurrentPlayer() {
        if (player1Move) {
            return player1;
        } else {
            return player2;
        }
    }

    public Player getOppositePlayer() {
        return player1Move ? player2 : player1;
    }

    public boolean isComplete() {
        return player1 != null && player2 != null;
    }

    public boolean isReadyToStart() {
        return isComplete() && player1.isReady() && player2.isReady();
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void fire(String addr) {
        CellContent c = getOppositePlayer().getMyField().getCell(addr);
        if (c == CellContent.SHIP) {
            getOppositePlayer().getMyField().setCell(addr, CellContent.HIT);
            getCurrentPlayer().getEnemyField().setCell(addr, CellContent.HIT);
            return;
        }
        if (c == CellContent.EMPTY) {
            getOppositePlayer().getMyField().setCell(addr, CellContent.MISS);
            getCurrentPlayer().getEnemyField().setCell(addr, CellContent.MISS);
        }
        player1Move = !player1Move;
    }
}
