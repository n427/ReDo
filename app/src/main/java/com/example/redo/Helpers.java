package com.example.redo;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class Helpers {
    private static final String BASE_URL = "https://api.sheety.co/431e78c323ce8d7e2868e0d3a5ee3c6e/reDo/";

    public static void post(final String sheet, final JSONObject json) {
        final StringBuilder sb = new StringBuilder();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                    OkHttpClient client = new OkHttpClient();

                    okhttp3.RequestBody body = RequestBody.create(JSON, json.toString());
                    okhttp3.Request request = new okhttp3.Request.Builder()
                            .url(BASE_URL + sheet)
                            .post(body)
                            .build();

                    okhttp3.Response response = client.newCall(request).execute();

                    String networkResp = response.body().string();
                    if (!networkResp.isEmpty()) {
                        sb.append(parseJSONStringToJSONObject(networkResp).toString());
                    }
                } catch (Exception ex) {
                    String err = String.format("{\"result\":\"false\",\"error\":\"%s\"}", ex.getMessage());
                    sb.append(parseJSONStringToJSONObject(err));
                }
            }
        });

        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject parseJSONStringToJSONObject(final String str) {
        JSONObject response = null;
        try {
            response = new JSONObject(str);
        } catch (Exception ex) {
            try {
                response = new JSONObject();
                response.put("result", "failed");
                response.put("data", str);
                response.put("error", ex.getMessage());
            } catch (JSONException ignored) {
            }
        }
        return response;
    }
}
