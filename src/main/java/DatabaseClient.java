import com.mongodb.Function;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import factories.CardFactory;
import factories.DeckFactory;
import factories.UserFactory;
import org.bson.Document;
import pojos.Card;
import pojos.Deck;
import pojos.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class DatabaseClient {
    private MongoClient client;
    private MongoDatabase database;
    private MongoCollection<Document> usersCollection;
    private MongoCollection<Document> decksCollection;
    private MongoCollection<Document> cardsCollection;

    public DatabaseClient(String address, int port, String username, String password, String authDatabase, String contentDatabase) {
        client = getClient(address, port, username, password, authDatabase);
        database = client.getDatabase(contentDatabase);
    }

    public User getUser(int id) {
        Document userDocument = findUserDocumentById(id);
        return UserFactory.create(userDocument);
    }

    public User getUserWithCards(int id) {
        Document userDocument = findUserDocumentById(id);
        User user = UserFactory.create(userDocument);

        List<Card> cards = findAndConvertList(user.getCardIds(), getCardCollection(), CardFactory::create);
        user.setCards(cards);

        return user;
    }

    public Deck getDeck(int id) {
        Document deck = findDeckDocumentById(id);
        return DeckFactory.create(deck);
    }

    public Deck getDeckWithCards(int id) {
        Document deckDocument = findDeckDocumentById(id);
        Deck deck = DeckFactory.create(deckDocument);


        MongoCollection<Document> cardCollection = getCardCollection();

        List<Card> mainCards = findAndConvertList(deck.getMainDeckIds(), cardCollection, CardFactory::create);
        List<Card> buildableCards = findAndConvertList(deck.getBuildableIds(), cardCollection, CardFactory::create);

        deck.setMainDeckCards(mainCards);
        deck.setBuildableCards(buildableCards);

        return deck;
    }

    public Card getCard(int id) {
        MongoCollection<Document> cards = getCardCollection();
        Document card =  cards.find(new Document("id", id)).first();
        return CardFactory.create(card);
    }

    private <T> List<T> findAndConvertList(List<Long> ids, MongoCollection<Document> collection, Function<Document, T> factoryFunction) {
        Document query = generateIdListQuery(ids);
        List<T> objects = new ArrayList<>();
        collection.find(query).map(factoryFunction).forEach((Consumer<? super T>) object -> objects.add(object));
        return objects;
    }

    private Document generateIdListQuery(List<Long> ids) {
        Document query = new Document();

        List<Document> orList = ids.stream()
                .map(id -> new Document("id", id))
                .collect(Collectors.toList());
        query.append("$or", orList);

        return query;
    }

    private Document findDeckDocumentById(int id) {
        MongoCollection<Document> decks = getDeckCollection();
        return decks.find(new Document("id", id)).first();
    }

    private Document findUserDocumentById(int id) {
        MongoCollection<Document> users = getUserCollection();
        return users.find(new Document("id", id)).first();
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
            usersCollection = database.getCollection("user");
            return usersCollection;
        }
    }
}
