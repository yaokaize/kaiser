package club.lazy.common.http.dao;

import club.lazy.common.contant.CommonContants;
import club.lazy.common.contant.ErrorCode;

public class JsonRpcResponse<R> {

    private String id;

    private ErrorCode errorCode;

    private String message;

    private R result;

    public JsonRpcResponse(ErrorCode code, String message) {
        this.id = "1";
        this.result = (R) CommonContants.FAIL;
        this.errorCode = code;
        this.message = message;
    }

    public JsonRpcResponse(R result) {
        this.id = "1";
        this.result = result;
        this.errorCode = ErrorCode.NO_ERROR;
        this.message = CommonContants.SUCCESS;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public R getResult() {
        return result;
    }

    public void setResult(R result) {
        this.result = result;
    }
}
