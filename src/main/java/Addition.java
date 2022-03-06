public class Addition extends Function{
    private Variable left;
    private Variable right;

    public Addition() {
        this.left = new Variable(0);
        this.right = new Variable(0);
    }

    public Addition(int _left, int _right) {
        this.left = new Variable(_left);
        this.right = new Variable(_right);
    }

    public boolean isConfluent() {
        return true;
    }

    public void swapValues() {
        String tempLabel = this.left.getLabel();
        double tempConstant = this.left.getConstant();
        double tempExponent = this.left.getExponent();

        this.left.setLabel(this.right.getLabel());
        this.left.setConstant(this.right.getConstant());
        this.left.setExponent(this.right.getExponent());

        this.right.setLabel(tempLabel);
        this.right.setConstant(tempConstant);
        this.right.setExponent(tempExponent);
    }

    public String toString() {
        return this.left + " + " + this.right;
    }

    @Override
    public void print() {
        System.out.println(this.toString());
    }


}
