package com.three_stack.maximum_alpha.database_client.pojos;

import java.util.List;
import java.util.Map;

public class DBAbility {
    protected Map<String, Integer> cost;
    protected boolean tap;
    protected String text;
    protected List<DBEffect> effects;

    public DBAbility(Map<String, Integer> cost, boolean tap, String text, List<DBEffect> effects) {
        this.cost = cost;
        this.tap = tap;
        this.text = text;
        this.effects = effects;
    }

    public Map<String, Integer> getCost() {
        return cost;
    }

    public void setCost(Map<String, Integer> cost) {
        this.cost = cost;
    }

    public boolean isTap() {
        return tap;
    }

    public void setTap(boolean tap) {
        this.tap = tap;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<DBEffect> getEffects() {
        return effects;
    }

    public void setEffects(List<DBEffect> effects) {
        this.effects = effects;
    }
}
