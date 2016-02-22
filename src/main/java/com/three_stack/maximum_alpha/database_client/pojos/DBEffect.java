package com.three_stack.maximum_alpha.database_client.pojos;

import java.util.List;

public class DBEffect {
    protected List<String> checks;
    protected List<DBResult> results;

    public DBEffect(List<String> checks, List<DBResult> results) {
        this.checks = checks;
        this.results = results;
    }

    public List<String> getChecks() {
        return checks;
    }

    public void setChecks(List<String> checks) {
        this.checks = checks;
    }

    public List<DBResult> getResults() {
        return results;
    }

    public void setResults(List<DBResult> results) {
        this.results = results;
    }
}
