package controllers.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSerializer;

import java.util.List;

import play.mvc.Controller;
import play.mvc.Util;
import play.mvc.results.RenderJson;

/**
 * Add JSONP support : render callback function if "callback" parameter provided.
 */
public class JsonpController extends Controller {

    public static final String CALLBACK_FORMAT = "%s(%s)";

    @Util
    protected static <T> void renderJSON(Class<T> type, T o, JsonSerializer<T> adapter) {
        String callback = getCallbackParameter();
        if (callback != null) {
            throw new RenderJson(String.format(CALLBACK_FORMAT, callback, createGson(type, adapter).toJson(o)));
        }
        throw new RenderJson(createGson(type, adapter).toJson(o));
    }

    @Util
    protected static <T> void renderJSON(Class<T> type, List<T> o, JsonSerializer<T> adapter) {
        String callback = getCallbackParameter();
        if (callback != null) {
            throw new RenderJson(String.format(CALLBACK_FORMAT, callback, createGson(type, adapter).toJson(o)));
        }
        throw new RenderJson(createGson(type, adapter).toJson(o));
    }

    private static String getCallbackParameter() {
        if (request.params._contains("callback")) {
            return request.params.get("callback");
        }
        return null;
    }

    private static <T> Gson createGson(Class<?> myclass, JsonSerializer<T> adapter) {
        GsonBuilder gson = new GsonBuilder();
        gson.addSerializationExclusionStrategy(new NoExposeExclusionStrategy()).create();
        gson.registerTypeAdapter(myclass, adapter);
        return gson.create();
    }
}
