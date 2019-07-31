package org.lepetic.telegrambot.entities;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

public class OrganisedEvent {

    @Id
    private ObjectId id;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
