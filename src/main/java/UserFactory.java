import org.bson.Document;

import java.util.List;

public class UserFactory {

    public static User create(Document userDocument) {
        User user = new User();

        user.setId(userDocument.getLong("id"));
        user.setUsername(userDocument.getString("username"));
        user.setCardIds((List<Integer>) userDocument.get("cards"));
        return user;
    }
}
