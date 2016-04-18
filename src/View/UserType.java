package View;
/**
 * Created by bill on 16/4/18.
 */
public class UserType {
    private static final int Student = 1;
    private static final int Teacher = 2;
    private int index;
    public UserType(int index) {
        this.index = index;
    }
    public int getIndex() {
        return index;
    }
}
