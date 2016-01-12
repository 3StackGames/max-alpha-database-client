package factories;

import org.bson.Document;
import org.bson.types.ObjectId;
import pojos.User;

import java.util.List;

public class UserFactory {

    @SuppressWarnings("unchecked")
    public static User create(Document userDocument) {
        User user = new User();

        user.setId(userDocument.getObjectId("_id"));
        user.setUsername(userDocument.getString("username"));
        user.setCardIds((List<ObjectId>) userDocument.get("cards"));
        return user;
    }
}
