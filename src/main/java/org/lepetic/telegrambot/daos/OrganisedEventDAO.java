package org.lepetic.telegrambot.daos;

import com.mongodb.MongoClient;
import org.lepetic.telegrambot.entities.OrganisedEvent;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

public class OrganisedEventDAO extends BasicDAO<OrganisedEvent, String> {

    public OrganisedEventDAO(MongoClient mongoClient, Morphia morphia, String dbName) {
        super(mongoClient, morphia, dbName);
    }
}
