package club.lazy.common.http.dao;

import java.util.Map;

/**
 * can not use lombok
 */
public class JsonRpcRequest {

    private String method;

    private Map<String, Object> params;

    public JsonRpcRequest() {
    }

    public JsonRpcRequest(String method, Map<String, Object> params) {
        this.method = method;
        this.params = params;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "JsonRpcRequest{" +
                "method='" + method + '\'' +
                ", params=" + params +
                '}';
    }
}
