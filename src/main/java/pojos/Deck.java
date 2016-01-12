package pojos;

import org.bson.types.ObjectId;

import java.util.List;

public class Deck {
    protected ObjectId id;
    protected ObjectId ownerId;
    protected List<ObjectId> mainCardIds;
    protected List<ObjectId> structureIds;

    protected List<Card> mainCards;
    protected List<Card> structureCards;

    public Deck(ObjectId id, ObjectId ownerId, List<ObjectId> mainCardIds, List<ObjectId> structureIds) {
        this.id = id;
        this.ownerId = ownerId;
        this.mainCardIds = mainCardIds;
        this.structureIds = structureIds;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(ObjectId ownerId) {
        this.ownerId = ownerId;
    }

    public List<ObjectId> getMainCardIds() {
        return mainCardIds;
    }

    public void setMainCardIds(List<ObjectId> mainCardIds) {
        this.mainCardIds = mainCardIds;
    }

    public List<ObjectId> getStructureIds() {
        return structureIds;
    }

    public void setStructureIds(List<ObjectId> structureIds) {
        this.structureIds = structureIds;
    }

    public List<Card> getMainCards() {
        return mainCards;
    }

    public void setMainCards(List<Card> mainCards) {
        this.mainCards = mainCards;
    }

    public List<Card> getStructureCards() {
        return structureCards;
    }

    public void setStructureCards(List<Card> structureCards) {
        this.structureCards = structureCards;
    }
}
