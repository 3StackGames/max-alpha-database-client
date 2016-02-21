package com.three_stack.maximum_alpha.database_client.pojos;

import java.util.List;
import java.util.Map;

public class DBEffect {
    protected List<Map<String, Object>> checks;
    protected List<DBResult> results;

    public DBEffect(List<Map<String, Object>> checks, List<DBResult> results) {
        this.checks = checks;
        this.results = results;
    }

    public List<Map<String, Object>> getChecks() {
        return checks;
    }

    public void setChecks(List<Map<String, Object>> checks) {
        this.checks = checks;
    }

    public List<DBResult> getResults() {
        return results;
    }

    public void setResults(List<DBResult> results) {
        this.results = results;
    }
}
