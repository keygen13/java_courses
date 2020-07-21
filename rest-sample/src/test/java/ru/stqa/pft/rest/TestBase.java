package ru.stqa.pft.rest;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;

public class TestBase {

    protected void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }

    protected boolean isIssueOpen(int issueId) throws IOException {
        String url = String.format("https://bugify.stqa.ru/api/issues/%s.json", issueId);
        String json = getExecutor()
                .execute(Request.Get(url))
                .returnContent()
                .asString();
        JsonElement parsed = new JsonParser().parse(json);
        String state = parsed
                .getAsJsonObject()
                .get("issues")
                .getAsJsonArray()
                .get(0)
                .getAsJsonObject()
                .get("state_name")
                .getAsString();

        return !(state.equals("Resolved") || state.equals("Closed"));
    }
}
