package lv.tsi.javaweb.seabattle.model;

public class Game {
    private Player player1;
    private Player player2;
    private boolean player1Move = true;
    private boolean finished;
    private boolean canceled;

    public Player getCurrentPlayer() {
        if (player1Move) {
            return player1;
        } else {
            return player2;
        }
    }

    public Player getOppositePlayer() {
/*        if (player1Move) {
            return player2;
        } else {
            return player1;
        } */
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
        Field oppositeMyField = getOppositePlayer().getMyField();
        CellContent c = oppositeMyField.getCell(addr);
        if (c == CellContent.SHIP) {
            oppositeMyField.setCell(addr, CellContent.HIT);
            getCurrentPlayer().getEnemyField().setCell(addr, CellContent.HIT);
            if (!oppositeMyField.hasMoreShips()) {
                finished = true;
                getCurrentPlayer().setWinner(true);
            }


            return;
        }
        if (c == CellContent.EMPTY) {
            oppositeMyField.setCell(addr, CellContent.MISS);
            getCurrentPlayer().getEnemyField().setCell(addr, CellContent.MISS);
        }
        player1Move = !player1Move;
    }

    public boolean isFinished() {
        return finished;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }
}
