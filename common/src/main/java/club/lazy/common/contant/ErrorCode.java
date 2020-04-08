package club.lazy.common.contant;

public enum ErrorCode {

    RUNTIME_ERROR(9100),
    METHOD_NOT_FOUND(9101),
    MISSING_PARAM(9102),
    NO_ERROR(0)
    ;
    private int code;

    ErrorCode(int code) {
        this.code = code;
    }
}
