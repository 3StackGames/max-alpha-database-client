package com.three_stack.maximum_alpha.database_client;

import com.three_stack.maximum_alpha.database_client.pojos.DBCard;
import com.three_stack.maximum_alpha.database_client.pojos.DBDeck;
import com.three_stack.maximum_alpha.database_client.pojos.DBEffect;
import com.three_stack.maximum_alpha.database_client.pojos.DBUser;
import org.apache.commons.lang.Validate;
import org.bson.types.ObjectId;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class DatabaseClientTests {
    protected DatabaseClient client;
    protected ObjectId userId;
    protected ObjectId deckId;
    protected ObjectId creatureId;
    protected ObjectId spellId;
    protected ObjectId structureId;

    @org.junit.Before
    public void setUp() throws Exception {
//        client = new DatabaseClient("localhost", 27017, "test", "password", "admin", "max-alpha");
        client = new DatabaseClient("107.170.204.106", 27017, "3sd", "apples", "admin", "max-alpha-2");
        userId = new ObjectId("5692c8785874ab801b000001");
        deckId = new ObjectId("568ec4b9bbdcf16c2c000003");
        creatureId = new ObjectId("56bcc8958b7edb940a000001");
        spellId = new ObjectId("56c3774c01a0e11103000002");
        structureId = new ObjectId("568ec276bbdcf16c2c000002");
    }

    @Test
    public void test_getUser_shouldReturnUser() throws Exception {
        DBUser user = client.getUser(userId);
        Validate.notNull(user);
        Validate.notNull(user.getId());
    }

    @Test
    public void test_getUserWithCards_shouldReturnUserWithCards() throws Exception {
        DBUser user = client.getUserWithCards(userId);
        List<DBCard> cards = user.getCards();
        Validate.notEmpty(cards);
        DBCard card = cards.get(0);
        Validate.notNull(card);
        Validate.notNull(card.getId());
    }

    @Test
    public void test_getDeck_shouldReturnDeck() throws Exception {
        DBDeck deck = client.getDeck(deckId);
        Validate.notNull(deck);
        Validate.notNull(deck.getId());
    }

    @Test
    public void test_getDeckWithCards_shouldReturnDeckWithCards() throws Exception {
        DBDeck deck = client.getDeckWithCards(deckId);
        List<DBCard> mainCards = deck.getMainCards();
        List<DBCard> structures = deck.getStructureCards();
        Validate.notEmpty(mainCards);
        Validate.notEmpty(structures);

        DBCard mainDeckCard = mainCards.get(0);
        DBCard structureCard = structures.get(0);

        Validate.notNull(mainDeckCard);
        Validate.notNull(mainDeckCard.getId());
        Validate.notNull(structureCard);
        Validate.notNull(structureCard.getId());
    }

    @Test
    public void test_getCard_shouldReturnCreature() throws Exception {
        DBCard creatureCard = client.getCard(creatureId);
        Validate.notNull(creatureCard);
        Validate.notNull(creatureCard.getId());
        Map<String, List<DBEffect>> triggerEffects = creatureCard.getTriggerEffects();
    }

    @Test
    public void test_getCard_shouldReturnSpell() throws Exception {
        DBCard spellCard = client.getCard(spellId);
        Validate.notNull(spellCard);
        Validate.notNull(spellCard.getId());
        List<DBEffect> effects = spellCard.getEffects();

        Validate.notEmpty(effects);

        DBEffect firstEffect = effects.get(0);
        Validate.notNull(firstEffect);
    }

    @Test
    public void test_getCard_shouldReturnStructure() throws Exception {
        DBCard structureCard = client.getCard(structureId);
        Validate.notNull(structureCard);
        Validate.notNull(structureCard.getId());
    }
}
