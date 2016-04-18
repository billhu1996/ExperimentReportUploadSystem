package External;
import java.lang.Error;
/**
 * Created by bill on 16/4/18.
 */
class ErrorWithCode extends Error {
    public ErrorWithCode(String message) {
        super(message);
    }
}
