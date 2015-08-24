package br.com.tricoli.shopping.list.producer;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.net.UnknownHostException;

/**
 *
 * It is used to generate the a {@link org.springframework.data.mongodb.core.MongoOperations} that
 * will be injected by CDI.
 *
 * To produce a valid {@link org.springframework.data.mongodb.core.MongoOperations} this class
 * needs to handle the {@link com.mongodb.MongoClient} and its configurations.
 *
 * @author  Luigi Tricoli
 * @version 1.0
 *
 */
@ApplicationScoped
public class MongoTemplateProducer {

    MongoClient client;

    /**
     *
     * The default constructor. It creates a {@link com.mongodb.MongoClient} with a pool of
     * 30 connections.
     *
     */
    public MongoTemplateProducer() {
        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        builder = builder.connectionsPerHost(10);

        //client = new MongoClient("localhost", builder.build());
        client = new MongoClient(new MongoClientURI("mongodb://shopping:shopping@ds059712.mongolab.com:59712/lists", builder));
    }

    @Produces
    public MongoOperations createMongoTemplate() throws UnknownHostException, MongoException {
        MongoDbFactory factory = new SimpleMongoDbFactory(client, "lists");
        return new MongoTemplate(factory);
    }
}