package org.cmad.blog.data;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoServiceFactory {
    
    
    private static ThreadLocal<Datastore> mongoTL = new ThreadLocal<Datastore>();
    
    /**
     * Method to retrieve a mongo database client from the thread local storage
     * @return
     */
    public static Datastore getMongoDB(){
       // String address = "localhost";
        String address = "146.148.45.81";
        
        if(mongoTL.get()==null){
            MongoClientURI connectionString = new MongoClientURI("mongodb://"+address+":27017");
            MongoClient mongoClient = new MongoClient(connectionString);    
            Morphia morphia = new Morphia();
            morphia.mapPackage("org.cmad.blog.api");
            Datastore datastore = morphia.createDatastore(mongoClient, "blog");
            datastore.ensureIndexes();
            mongoTL.set(datastore);
            return datastore;
        }
        return mongoTL.get();
    }
    
}
