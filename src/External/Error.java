package External;

/**
 * Created by bill on 16/4/18.
 */
public class Error extends ErrorWithCode {
    public Integer code = 0;
    public Error(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
