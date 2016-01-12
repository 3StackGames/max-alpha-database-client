import org.apache.commons.lang.Validate;
import org.bson.types.ObjectId;
import org.junit.Test;
import pojos.Card;
import pojos.Deck;
import pojos.Effect;
import pojos.User;

import java.util.List;
import java.util.Map;

public class DatabaseClientTests {
    protected DatabaseClient client;
    protected ObjectId userId;
    protected ObjectId deckId;
    protected ObjectId cardId;

    @org.junit.Before
    public void setUp() throws Exception {
        client = new DatabaseClient("localhost", 27017, "test", "password", "admin", "max-alpha");
        userId = new ObjectId("5692c8785874ab801b000001");
        deckId = new ObjectId("568ec4b9bbdcf16c2c000003");
        cardId = new ObjectId("568f777bccd62a580e000002");
    }

    @Test
    public void test_getUser_shouldReturnUser() throws Exception {
        User user = client.getUser(userId);
        Validate.notNull(user);
        Validate.notNull(user.getId());
    }

    @Test
    public void test_getUserWithCards_shouldReturnUserWithCards() throws Exception {
        User user = client.getUserWithCards(userId);
        List<Card> cards = user.getCards();
        Validate.notEmpty(cards);
        Card card = cards.get(0);
        Validate.notNull(card);
        Validate.notNull(card.getId());
    }

    @Test
    public void test_getDeck_shouldReturnDeck() throws Exception {
        Deck deck = client.getDeck(deckId);
        Validate.notNull(deck);
        Validate.notNull(deck.getId());
    }

    @Test
    public void test_getDeckWithCards_shouldReturnDeckWithCards() throws Exception {
        Deck deck = client.getDeckWithCards(deckId);
        List<Card> mainCards = deck.getMainCards();
        List<Card> structures = deck.getStructureCards();
        Validate.notEmpty(mainCards);
        Validate.notEmpty(structures);

        Card mainDeckCard = mainCards.get(0);
        Card structureCard = structures.get(0);

        Validate.notNull(mainDeckCard);
        Validate.notNull(mainDeckCard.getId());
        Validate.notNull(structureCard);
        Validate.notNull(structureCard.getId());
    }

    @Test
    public void test_getCard_shouldReturnCard() throws Exception {
        Card card = client.getCard(cardId);
        Validate.notNull(card);
        Validate.notNull(card.getId());
        Map<String, List<Effect>> effects = card.getEffects();

        Validate.notEmpty(effects);

        List<Effect> firstEffects = effects.values().iterator().next();
        Validate.notEmpty(firstEffects);
        Validate.notNull(firstEffects.get(0));
    }
}
