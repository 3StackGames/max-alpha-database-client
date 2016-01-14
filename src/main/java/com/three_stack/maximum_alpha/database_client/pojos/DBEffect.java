package com.three_stack.maximum_alpha.database_client.pojos;

import java.util.List;

public class DBEffect {
    protected List<String> checks;
    protected List<String> results;
    protected List<Object> values;

    public DBEffect(List<String> checks, List<String> results, List<Object> values) {
        this.checks = checks;
        this.results = results;
        this.values = values;
    }

    public List<String> getChecks() {
        return checks;
    }

    public void setChecks(List<String> checks) {
        this.checks = checks;
    }

    public List<String> getResults() {
        return results;
    }

    public void setResults(List<String> results) {
        this.results = results;
    }

    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }
}
