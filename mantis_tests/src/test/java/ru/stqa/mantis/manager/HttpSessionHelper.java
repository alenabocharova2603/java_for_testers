package ru.stqa.mantis.manager;

import okhttp3.*;

import java.io.IOException;
import java.net.CookieManager;

public class HttpSessionHelper extends HelperBase{

    OkHttpClient client;
    public HttpSessionHelper(ApplicationManager manager) {
        super(manager);
        client = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(new CookieManager())).build();
    }

    public void login(String username, String password) {
        RequestBody formBody = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .build(); // формирует значение нужного типа и помещает его в переменную formBody
        Request request = new Request.Builder()
                .url(String.format("%s/login.php", manager.property("web.baseUrl")))
                .post(formBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful())
                throw new RuntimeException("Unexpected code " + response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isLoggedIn() {
        Request request = new Request.Builder()
                .url(manager.property("web.baseUrl"))
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
            String body = response.body().string();
            //далле нужно проверить наличие в этом тексте фрагмента, который свидетельствует о том, что логин действительно выполнился
         return body.contains("<span class=\"user-info\">"); //проверяем, что текст странички содержит фрагмент "<span class="user-info">"
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
