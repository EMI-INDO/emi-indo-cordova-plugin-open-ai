package emi.indo.cordova.plugin.open.ai;

import androidx.annotation.NonNull;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



public class emiOpenAiPlugin extends CordovaPlugin {

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }


    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        switch (action) {

            case "completions": {

                final String apiKey = args.optString(0);
                long timeOut = args.optLong(1);
                final String prompt = args.optString(2);
                final int max_tokens = args.optInt(3);
                final int temperature = args.optInt(4);

                OkHttpClient client = new OkHttpClient.Builder()
                        .readTimeout(timeOut, TimeUnit.SECONDS)
                        .build();

                JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("model", "text-davinci-003");
                    jsonBody.put("prompt", prompt);
                    jsonBody.put("max_tokens", max_tokens);
                    jsonBody.put("temperature", temperature);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestBody body = RequestBody.create(jsonBody.toString(), JSON);
                Request request = new Request.Builder()
                        .url("https://api.openai.com/v1/completions")
                        .header("Authorization", "Bearer " + apiKey)
                        .post(body)
                        .build();

                client.newCall(request).enqueue(new Callback() {

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        JSONObject jsonObject;
                        try {
                            jsonObject = new JSONObject(Objects.requireNonNull(response.body()).string());
                            JSONArray jsonArray = jsonObject.getJSONArray("choices");
                            String result = jsonArray.getJSONObject(0).getString("text");
                            callbackContext.success(result.trim());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        callbackContext.error("Failed to load response due to " + e.getMessage());

                    }
                });


                return true;
            }

            case "chat": {

                final String apiKey = args.optString(0);
                long timeOut = args.optLong(1);
                final String role = args.optString(2);
                final String content = args.optString(3);

                OkHttpClient client = new OkHttpClient.Builder()
                        .readTimeout(timeOut, TimeUnit.SECONDS)
                        .build();

                JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("model", "gpt-3.5-turbo");
                    JSONArray messageArr = new JSONArray();
                    JSONObject obj = new JSONObject();
                    obj.put("role", role);
                    obj.put("content", content);
                    messageArr.put(obj);

                    jsonBody.put("messages", messageArr);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RequestBody body = RequestBody.create(jsonBody.toString(), JSON);
                Request request = new Request.Builder()
                        .url("\n" +
                                "https://api.openai.com/v1/chat/completions")
                        .header("Authorization", "Bearer " + apiKey)
                        .post(body)
                        .build();

                client.newCall(request).enqueue(new Callback() {


                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        JSONObject jsonObject;
                        try {
                            jsonObject = new JSONObject(Objects.requireNonNull(response.body()).string());
                            JSONArray jsonArray = jsonObject.getJSONArray("choices");
                            String result = jsonArray.getJSONObject(0)
                                    .getJSONObject("message")
                                    .getString("content");
                            callbackContext.success(result.trim());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        callbackContext.error("Failed to load response due to " + e.getMessage());

                    }
                });


                return true;
            }

        } 

        return false;
    }

}