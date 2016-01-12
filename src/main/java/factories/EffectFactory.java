package factories;

import org.bson.Document;
import pojos.Effect;

import java.util.List;

public class EffectFactory {

    @SuppressWarnings("unchecked")
    public static Effect create(Document effectDocument) {
        if(effectDocument == null) {
            return null;
        }
        List<String> checks = (List<String>) effectDocument.get("checks");
        List<String> results = (List<String>) effectDocument.get("results");
        List<Object> values = (List<Object>) effectDocument.get("values");
        return new Effect(checks, results, values);
    }
}
