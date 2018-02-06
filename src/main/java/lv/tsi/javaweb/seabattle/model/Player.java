package lv.tsi.javaweb.seabattle.model;

<<<<<<< HEAD
public class Player {
    private String name;
=======
/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
public class Player {
    private String name;
    private Field myField = new Field();
    private Field enemyField = new Field();
    private boolean ready;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Field getMyField() {
        return myField;
    }

    public void setMyField(Field myField) {
        this.myField = myField;
    }

    public Field getEnemyField() {
        return enemyField;
    }

    public void setEnemyField(Field enemyField) {
        this.enemyField = enemyField;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public boolean isReady() {
        return ready;
    }
}
