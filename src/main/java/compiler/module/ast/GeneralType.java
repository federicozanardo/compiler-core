package compiler.module.ast;

public class GeneralType extends Type {
    private int val = 0;

    public GeneralType() {
        type = "Type";
    }

    public GeneralType(int n) {
        val = n;
        type = "Type" + n;
    }

    public int getNumberType() {
        return val;
    }
}
