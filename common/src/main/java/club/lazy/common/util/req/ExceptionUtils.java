package club.lazy.common.util.req;

import java.lang.reflect.InvocationTargetException;

public class ExceptionUtils {

    public static Exception getTargetException(InvocationTargetException e){
        while(e.getTargetException() instanceof InvocationTargetException){
            e = (InvocationTargetException)e.getTargetException();
        }
        return (Exception)e.getTargetException();
    }

    public static Exception getTargetException(Exception e){
        if(e instanceof InvocationTargetException)
            e = getTargetException((InvocationTargetException)e);

        return e;
    }
}