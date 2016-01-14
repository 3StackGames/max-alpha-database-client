package com.three_stack.maximum_alpha.database_client.factories;

import org.bson.Document;
import org.bson.types.ObjectId;
import com.three_stack.maximum_alpha.database_client.pojos.DBDeck;

import java.util.List;

public class DeckFactory {

    @SuppressWarnings("unchecked")
    public static DBDeck create(Document deckDocument) {
        ObjectId id = deckDocument.getObjectId("_id");
        ObjectId ownerId = deckDocument.getObjectId("owner");
        List<ObjectId> mainDeckIds = (List<ObjectId>) deckDocument.get("mainCards");
        List<ObjectId> buildableIds = (List<ObjectId>) deckDocument.get("structures");
        return new DBDeck(id, ownerId, mainDeckIds, buildableIds);
    }
}
