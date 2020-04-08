package club.lazy.common.http.exception;

public class CustomException extends RuntimeException {

    private Integer errorCode;

    public CustomException(String message){
        super(message);
        this.errorCode = 9001;
    }

    public CustomException(Integer errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}
