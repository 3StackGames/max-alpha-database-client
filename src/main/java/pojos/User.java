package pojos;

import org.bson.types.ObjectId;

import java.util.List;

public class User {
    protected ObjectId id;
    protected String username;
    protected List<ObjectId> cardIds;
    protected List<Card> cards;

    public User() {

    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ObjectId> getCardIds() {
        return cardIds;
    }

    public void setCardIds(List<ObjectId> cardIds) {
        this.cardIds = cardIds;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
