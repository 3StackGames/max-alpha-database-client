package com.three_stack.maximum_alpha.database_client.factories;

import com.three_stack.maximum_alpha.database_client.pojos.DBUser;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;

public class UserFactory {

    @SuppressWarnings("unchecked")
    public static DBUser create(Document userDocument) {
        DBUser user = new DBUser();

        user.setId(userDocument.getObjectId("_id"));
        user.setUsername(userDocument.getString("username"));
        user.setCardIds((List<ObjectId>) userDocument.get("cards"));
        return user;
    }
}
