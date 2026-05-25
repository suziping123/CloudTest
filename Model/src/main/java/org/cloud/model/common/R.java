package org.cloud.model.common;

import lombok.Data;

/**
 * @author SuZiPing
 * @version 1.0
 */
@Data
public class R {
    private Integer code;
    private String message;
    private Object data;

    public static R ok(Object data) {
        R r = new R();
        r.setCode(200);
        r.setMessage("success");
        r.setData(data);
        return r;
    }

    public static R ok(String message, Object data) {
        R r = new R();
        r.setCode(200);
        r.setMessage(message);
        r.setData(data);
        return r;
    }

    public static R error(String message, Object data, Integer code) {
        R r = new R();
        r.setCode(code);
        r.setMessage(message);
        r.setData(data);
        return r;
    }
    public static R error() {
        R r = new R();
        r.setCode(500);
        r.setMessage("error");
        r.setData(null);
        return r;
    }
}
