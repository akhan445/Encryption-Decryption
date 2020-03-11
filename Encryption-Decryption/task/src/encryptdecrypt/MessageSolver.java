package encryptdecrypt;

public class MessageSolver {

    private SolvingMethod method;

    public void setMethod(SolvingMethod method) {
        this.method = method;
    }

    public String solve(int mode, String message, int key) {
        return this.method.solve(mode, message, key);
    }
}
