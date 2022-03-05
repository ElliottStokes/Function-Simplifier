public class Addition extends Function{
    private int left;
    private int right;

    public Addition() {
        this.left = 0;
        this.right = 0;
    }

    public Addition(int _left, int _right) {
        this.left = _left;
        this.right = _right;
    }

    @Override
    public void print() {
        System.out.println(this.left + " + " + this.right);
    }
}
