package org.sjf.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.util.HashMap;

/**
 * json相关工具
 * Created by SJF on 2016/9/7.
 */
public class JsonUtil {

    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    private static String toJson(Object data) { return gson.toJson(data); }

    public static<T> T toObject(String json, Class<T> clazz) throws JsonSyntaxException {
        return gson.fromJson(json, clazz);
    }

    /**
     * 将输入参数整合为json格式
     * @param errorCode
     * @param errorMsg
     * @param param
     * @param <T>
     * @return
     */
    public static <T> String statusResponse(int errorCode, Object errorMsg, T param) {
        HashMap<String, Object> statusResponse = new HashMap<>();
        statusResponse.put("errorCode", errorCode);
        statusResponse.put("errorMsg", errorMsg);
        statusResponse.put("param", param);
        System.out.println(gson.toJson(statusResponse));
        return gson.toJson(statusResponse);
    }
}
