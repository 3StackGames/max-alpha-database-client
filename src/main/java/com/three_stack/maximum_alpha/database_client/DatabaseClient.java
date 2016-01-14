package com.three_stack.maximum_alpha.database_client;

import com.mongodb.Function;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.three_stack.maximum_alpha.database_client.factories.CardFactory;
import com.three_stack.maximum_alpha.database_client.factories.DeckFactory;
import com.three_stack.maximum_alpha.database_client.factories.UserFactory;
import com.three_stack.maximum_alpha.database_client.pojos.DBUser;
import org.bson.Document;
import org.bson.types.ObjectId;
import com.three_stack.maximum_alpha.database_client.pojos.DBCard;
import com.three_stack.maximum_alpha.database_client.pojos.DBDeck;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class DatabaseClient {
    private MongoDatabase database;
    private MongoCollection<Document> usersCollection;
    private MongoCollection<Document> decksCollection;
    private MongoCollection<Document> cardsCollection;

    public DatabaseClient(String address, int port, String username, String password, String authDatabase, String contentDatabase) {
        MongoClient client = getClient(address, port, username, password, authDatabase);
        database = client.getDatabase(contentDatabase);
    }

    public DBUser getUser(String id) {
        return getUser(o(id));
    }

    public DBUser getUser(ObjectId id) {
        Document userDocument = findUserDocumentById(id);
        System.out.println(getUserCollection().count());
        return UserFactory.create(userDocument);
    }

    public DBUser getUserWithCards(String id) {
        return getUserWithCards(o(id));
    }

    public DBUser getUserWithCards(ObjectId id) {
        Document userDocument = findUserDocumentById(id);
        DBUser user = UserFactory.create(userDocument);

        List<DBCard> cards = findAndConvertList(user.getCardIds(), getCardCollection(), CardFactory::create);
        user.setCards(cards);

        return user;
    }

    public DBDeck getDeck(String id) {
        return getDeck(o(id));
    }

    public DBDeck getDeck(ObjectId id) {
        Document deck = findDeckDocumentById(id);
        return DeckFactory.create(deck);
    }

    public DBDeck getDeckWithCards(String id) {
        return getDeckWithCards(o(id));
    }

    public DBDeck getDeckWithCards(ObjectId id) {
        Document deckDocument = findDeckDocumentById(id);
        DBDeck deck = DeckFactory.create(deckDocument);


        MongoCollection<Document> cardCollection = getCardCollection();

        List<DBCard> mainCards = findAndConvertList(deck.getMainCardIds(), cardCollection, CardFactory::create);
        List<DBCard> buildableCards = findAndConvertList(deck.getStructureIds(), cardCollection, CardFactory::create);

        deck.setMainCards(mainCards);
        deck.setStructureCards(buildableCards);

        return deck;
    }

    public DBCard getCard(String id) {
        return getCard(o(id));
    }

    public DBCard getCard(ObjectId id) {
        MongoCollection<Document> cards = getCardCollection();
        Document card =  cards.find(new Document("_id", id)).first();
        return CardFactory.create(card);
    }

    private ObjectId o(String id) {
        return new ObjectId(id);
    }

    @SuppressWarnings("unchecked")
    private <T> List<T> findAndConvertList(List<ObjectId> ids, MongoCollection<Document> collection, Function<Document, T> factoryFunction) {
        Document query = generateIdListQuery(ids);
        List<T> objects = new ArrayList<>();
        collection.find(query).map(factoryFunction).forEach((Consumer<? super T>) objects::add);
        return objects;
    }

    private Document generateIdListQuery(List<ObjectId> ids) {
        Document query = new Document();

        List<Document> orList = ids.stream()
                .map(id -> new Document("_id", id))
                .collect(Collectors.toList());
        query.append("$or", orList);

        return query;
    }

    private Document findDeckDocumentById(ObjectId id) {
        MongoCollection<Document> decks = getDeckCollection();
        return decks.find(new Document("_id", id)).first();
    }

    private Document findUserDocumentById(ObjectId id) {
        MongoCollection<Document> userCollection = getUserCollection();
        FindIterable<Document> cursor = userCollection.find(new Document("_id", id));
        return cursor.first();
    }

    private com.mongodb.MongoClient getClient(String address, int port, String username, String password, String database) {

        MongoCredential mongoCredential = MongoCredential.createCredential(username, database, password.toCharArray());
        List<MongoCredential> credentials = new ArrayList<>();
        credentials.add(mongoCredential);

        ServerAddress serverAddress = new ServerAddress(address, port);
        return new com.mongodb.MongoClient(serverAddress, credentials);
    }

    private MongoCollection<Document> getCardCollection() {
        if(cardsCollection != null) {
            return cardsCollection;
        } else {
            cardsCollection = database.getCollection("cards");
            return cardsCollection;
        }
    }

    private MongoCollection<Document> getDeckCollection() {
        if(decksCollection != null) {
            return decksCollection;
        } else {
            decksCollection = database.getCollection("decks");
            return decksCollection;
        }
    }

    private MongoCollection<Document> getUserCollection() {
        if(usersCollection != null) {
            return usersCollection;
        } else {
            usersCollection = database.getCollection("users");
            return usersCollection;
        }
    }
}
