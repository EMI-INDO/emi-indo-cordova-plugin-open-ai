package emi.indo.cordova.plugin.open.ai;

import androidx.annotation.NonNull;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

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
    OkHttpClient client = new OkHttpClient();


    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        if (action.equals("completions")) {
           final String message = args.optString(0);
           final String apiKey = args.optString(1);

            _completions(message, apiKey, callbackContext);

            return true;
        }
        return false;
    }

    private void _completions(String message, String apiKey, CallbackContext callbackContext) {

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("model", "text-davinci-003");
            jsonBody.put("prompt", message);
            jsonBody.put("max_tokens", 4000);
            jsonBody.put("temperature", 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(jsonBody.toString(), JSON);
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/completions")
                .header("Authorization", "Bearer "+ apiKey)
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


    }


}
