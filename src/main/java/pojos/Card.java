package pojos;

import org.bson.types.ObjectId;

import java.util.List;
import java.util.Map;

public class Card {
    protected ObjectId id;
    protected String name;
    protected String type;
    protected Map<String, Integer> cost;
    protected String text;
    protected String flavorText;
    protected int health;
    protected int attack;
    protected Map<String, List<Effect>> effects;


    public Card(ObjectId id, String name, String type, Map<String, Integer> cost, String text, String flavorText, Map<String, List<Effect>> effects) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.cost = cost;
        this.text = text;
        this.flavorText = flavorText;
        this.effects = effects;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Integer> getCost() {
        return cost;
    }

    public void setCost(Map<String, Integer> cost) {
        this.cost = cost;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFlavorText() {
        return flavorText;
    }

    public void setFlavorText(String flavorText) {
        this.flavorText = flavorText;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public Map<String, List<Effect>> getEffects() {
        return effects;
    }

    public void setEffects(Map<String, List<Effect>> effects) {
        this.effects = effects;
    }
}
