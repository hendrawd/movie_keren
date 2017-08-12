package keren.movie.moviekeren.db;

import io.realm.RealmObject;

/**
 * @author hendrawd on 8/12/17
 */

public class IntegerObject extends RealmObject {

    public Integer integer;

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }
}
