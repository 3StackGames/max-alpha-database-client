package com.three_stack.maximum_alpha.database_client.pojos;

import org.bson.types.ObjectId;

import java.util.List;

public class DBDeck {
    protected ObjectId id;
    protected ObjectId ownerId;
    protected List<ObjectId> mainCardIds;
    protected List<ObjectId> structureIds;

    /**
     * @Warning: The card pointers aren't guaranteed to point to unique instances of DBCard
     */
    protected List<DBCard> mainCards;
    /**
     * @Warning: The card pointers aren't guaranteed to point to unique instances of DBCard
     */
    protected List<DBCard> structureCards;

    public DBDeck(ObjectId id, ObjectId ownerId, List<ObjectId> mainCardIds, List<ObjectId> structureIds) {
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

    public List<DBCard> getMainCards() {
        return mainCards;
    }

    public void setMainCards(List<DBCard> mainCards) {
        this.mainCards = mainCards;
    }

    public List<DBCard> getStructureCards() {
        return structureCards;
    }

    public void setStructureCards(List<DBCard> structureCards) {
        this.structureCards = structureCards;
    }
}
