package com.three_stack.maximum_alpha.database_client.pojos;

import org.bson.types.ObjectId;

import java.util.List;

public class DBUser {
    protected ObjectId id;
    protected String username;
    protected List<ObjectId> cardIds;
    protected List<DBCard> cards;

    public DBUser() {

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

    public List<DBCard> getCards() {
        return cards;
    }

    public void setCards(List<DBCard> cards) {
        this.cards = cards;
    }
}
