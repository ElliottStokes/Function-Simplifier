import java.util.List;

public class Function {
    private String functionString;

    public Function() {
        this.functionString = "";
    }
    public Function(String funcStr) {
        this.functionString = funcStr;
    }

    public void print() {
        // Print the formatted function
        System.out.println(this.functionString);
    }
}
