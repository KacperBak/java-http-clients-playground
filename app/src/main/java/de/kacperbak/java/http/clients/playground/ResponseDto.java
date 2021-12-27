package de.kacperbak.java.http.clients.playground;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class ResponseDto {

    private List<String> args;
    private String url;

    public ResponseDto(List<String> args, String url) {
        this.args = args;
        this.url = url;
    }

    public List<String> getArgs() {
        return args;
    }

    public String getUrl() {
        return url;
    }

    public static Optional<ResponseDto> parseFromBody(String body) {
        var jsonObject = new JSONObject(body);
        var args = extractArgs(jsonObject);
        var url = jsonObject.getString("url");
        return (!args.isEmpty() && url != null) ? Optional.of(new ResponseDto(args, url)) : Optional.empty();
    }

    private static List<String> extractArgs(JSONObject jo) {
        return ((HashMap) jo.toMap().get("args")).values().stream().toList();
    }
}
