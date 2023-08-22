package compiler.module.ast;

import lombok.Getter;

public class Statement {
    @Getter
    private double fract = 1;
    @Getter
    private final Entity leftExpression;
    @Getter
    private final Entity rightExpression;
    private Entity fractExpression;
    @Getter
    private final String operator;

    public Statement(Entity leftExpression, Entity rightExpression, String operator) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
        this.operator = operator;
    }

    public Statement(Entity leftExpression, Entity rightExpression, String operator, double f) {
        this.leftExpression = leftExpression;
        fract = f;
        this.rightExpression = rightExpression;
        this.operator = operator;
    }

    public Statement(Entity leftExpression, Entity rightExpression, String operator, Entity f) {
        this.leftExpression = leftExpression;
        fractExpression = f;
        this.rightExpression = rightExpression;
        this.operator = operator;
        fract = 0;
    }

    public String getFractExpression() {
        return fractExpression.getId();
    }

    public boolean isFractExpressionNull() {
        return fractExpression == null;
    }

    public void printStatement() {
        if (operator.equals("FIELDUP")) {
            System.out.println(leftExpression.getId() + " -> " + rightExpression.getId());
        } else {
            if (fract != 1) {
                System.out.println(fract + "*" + leftExpression.getId() + " -○ " + rightExpression.getId());
            } else {
                System.out.println(leftExpression.getId() + " -○ " + rightExpression.getId());
            }
        }
    }

    public String getTextStatement() {
        String ret = "";
        if (operator.equals("FIELDUP")) {
            ret = ret + leftExpression.getId() + " -> " + rightExpression.getId();
        } else {
            if (fract != 1) {
                ret = ret + fract + "*" + leftExpression.getId() + " -○ " + rightExpression.getId();
            } else {
                ret = ret + leftExpression.getId() + " -○ " + rightExpression.getId();
            }
        }
        return ret;
    }

    @Override
    public String toString() {
        return "Statement{" +
                "fract=" + fract +
                ", leftExpression=" + leftExpression +
                ", rightExpression=" + rightExpression +
                ", fractExpression=" + fractExpression +
                ", operator='" + operator + '\'' +
                '}';
    }
}
