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
    protected ObjectId maliciousWeaponsmithId;
    protected ObjectId lashTheCreatureId;

    @org.junit.Before
    public void setUp() throws Exception {
//        client = new DatabaseClient("localhost", 27017, "test", "password", "admin", "max-alpha");
        client = new DatabaseClient("107.170.204.106", 27017, "3sd", "apples", "admin", "max-alpha");
        userId = new ObjectId("5692c8785874ab801b000001");
        deckId = new ObjectId("568ec4b9bbdcf16c2c000003");
        maliciousWeaponsmithId = new ObjectId("568f777bccd62a580e000002");
        lashTheCreatureId = new ObjectId("56a8e4c192e2208c15000001");
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
        DBCard maliciousWeaponSmith = client.getCard(maliciousWeaponsmithId);
        Validate.notNull(maliciousWeaponSmith);
        Validate.notNull(maliciousWeaponSmith.getId());
        Map<String, List<DBEffect>> triggerEffects = maliciousWeaponSmith.getTriggerEffects();

        Validate.notEmpty(triggerEffects);

        List<DBEffect> firstEffects = triggerEffects.values().iterator().next();
        Validate.notEmpty(firstEffects);
        Validate.notNull(firstEffects.get(0));
    }

    @Test
    public void test_getCard_shouldReturnSpell() throws Exception {
        DBCard lashTheCreature = client.getCard(lashTheCreatureId);
        Validate.notNull(lashTheCreature);
        Validate.notNull(lashTheCreature.getId());
        List<DBEffect> effects = lashTheCreature.getEffects();

        Validate.notEmpty(effects);

        DBEffect firstEffect = effects.get(0);
        Validate.notNull(firstEffect);
    }
}
