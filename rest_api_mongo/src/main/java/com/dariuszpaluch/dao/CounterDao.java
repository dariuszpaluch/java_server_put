package com.dariuszpaluch.dao;

import com.dariuszpaluch.models.Counter;
import org.mongodb.morphia.Datastore;

public class CounterDao {
    private Datastore datastore = Context.getInstance().getDatastore();

    public int getSeq(String name) {
        Counter counter = this.datastore.findAndModify(this.datastore.createQuery(Counter.class).filter("_id", name), this.datastore.createUpdateOperations(Counter.class).inc("seq"));

        return counter.getSeq();
    }
}