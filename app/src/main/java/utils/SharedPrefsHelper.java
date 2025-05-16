package utils;
import android.content.Context;
import android.content.SharedPreferences;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import com.example.artemka.ConversionHistory;
import java.util.Date;
public class SharedPrefsHelper {
    private static final String PREFS_NAME = "ArtemKaPrefs";
    private static final String HISTORY_KEY = "conversion_history";
    private static final String THEME_KEY = "app_theme";

    public static void saveHistory(Context context, List<ConversionHistory> history) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        try {
            JSONArray jsonArray = new JSONArray();
            for (ConversionHistory item : history) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("fromCurrency", item.getFromCurrency());
                jsonObject.put("toCurrency", item.getToCurrency());
                jsonObject.put("amount", item.getAmount());
                jsonObject.put("result", item.getResult());
                jsonObject.put("date", item.getDate().getTime());
                jsonArray.put(jsonObject);
            }
            editor.putString(HISTORY_KEY, jsonArray.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        editor.apply();
    }

    public static List<ConversionHistory> loadHistory(Context context) {
        List<ConversionHistory> history = new ArrayList<>();
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String jsonString = prefs.getString(HISTORY_KEY, null);

        if (jsonString != null) {
            try {
                JSONArray jsonArray = new JSONArray(jsonString);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    ConversionHistory item = new ConversionHistory(
                            jsonObject.getString("fromCurrency"),
                            jsonObject.getString("toCurrency"),
                            jsonObject.getDouble("amount"),
                            jsonObject.getDouble("result"),
                            new Date(jsonObject.getLong("date"))
                    );
                    history.add(item);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return history;
    }

    // Методы для темы остаются без изменений
    public static void saveTheme(Context context, boolean isDarkTheme) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putBoolean(THEME_KEY, isDarkTheme).apply();
    }

    public static boolean loadTheme(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getBoolean(THEME_KEY, false);
    }
}