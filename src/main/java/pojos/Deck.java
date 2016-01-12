package pojos;

import pojos.Card;

import java.util.List;

public class Deck {
    protected long id;
    protected long ownerId;
    protected List<Long> mainDeckIds;
    protected List<Long> buildableIds;

    protected List<Card> mainDeckCards;
    protected List<Card> buildableCards;

    protected Deck(long id, long ownerId, List<Long> mainDeckIds, List<Long> buildableIds) {
        this.id = id;
        this.ownerId = ownerId;
        this.mainDeckIds = mainDeckIds;
        this.buildableIds = buildableIds;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public List<Long> getMainDeckIds() {
        return mainDeckIds;
    }

    public void setMainDeckIds(List<Long> mainDeckIds) {
        this.mainDeckIds = mainDeckIds;
    }

    public List<Long> getBuildableIds() {
        return buildableIds;
    }

    public void setBuildableIds(List<Long> buildableIds) {
        this.buildableIds = buildableIds;
    }

    public List<Card> getMainDeckCards() {
        return mainDeckCards;
    }

    public void setMainDeckCards(List<Card> mainDeckCards) {
        this.mainDeckCards = mainDeckCards;
    }

    public List<Card> getBuildableCards() {
        return buildableCards;
    }

    public void setBuildableCards(List<Card> buildableCards) {
        this.buildableCards = buildableCards;
    }
}
