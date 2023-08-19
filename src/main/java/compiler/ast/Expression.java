package compiler.ast;

public class Expression {
    private Entity leftExpression = null;
    private Entity rightExpression = null;
    private Expression leftComplexExpression = null;
    private Expression rightComplexExpression = null;
    private final String operator;

    public Expression(Entity leftExpression, Entity rightExpression, String operator) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
        this.operator = operator;
    }

    public Expression(Entity leftExpression, String operator) {
        this.leftExpression = leftExpression;
        this.operator = operator;
    }

    public Expression(Entity leftExpression) {
        this.leftExpression = leftExpression;
        operator = null;
    }

    public Expression(Expression leftComplexExpression, Expression rightComplexExpression, String operator) {
        this.leftComplexExpression = leftComplexExpression;
        this.rightComplexExpression = rightComplexExpression;
        this.operator = operator;
    }

    public boolean isValid(Entity left, Entity right, String op) {
        switch (op) {
            case "==":
                return left.getValue() == right.getValue();
            case "!=":
                return left.getValue() != right.getValue();
            case ">":
                return left.getValue() > right.getValue();
            case ">=":
                return left.getValue() >= right.getValue();
            case "<=":
                return left.getValue() <= right.getValue();
            case "<":
                return left.getValue() < right.getValue();
        }
        return false;
    }

    public boolean isValidExpr(Expression expression) {
        if (expression.getLeftComplexExpression() != null && expression.getRightComplexExpression() != null) {
            boolean leftExpression = isValid(expression.getLeftComplexExpression().getLeft(), expression.getLeftComplexExpression().getRight(), expression.getLeftComplexExpression().getOperator());
            boolean rightExpression = isValid(expression.getRightComplexExpression().getLeft(), expression.getRightComplexExpression().getRight(), expression.getRightComplexExpression().getOperator());

            if (expression.getOperator().equals("||")) {
                return leftExpression || rightExpression;
            } else {
                return leftExpression && rightExpression;
            }
        } else {
            return isValid(expression.getLeft(), expression.getRight(), expression.getOperator());
        }
    }

    public void printExpression() {
        if (leftComplexExpression != null) {
            if (rightComplexExpression != null) {
                leftComplexExpression.printExpression();
                System.out.print(" " + operator + " ");
                rightComplexExpression.printExpression();
            } else {
                leftComplexExpression.printExpression();

            }
        } else if (rightExpression != null && operator != null) {
            System.out.print(leftExpression.getId() + " " + operator + " " + rightExpression.getId());
        } else if (operator != null) {
            System.out.print(leftExpression.getId() + " " + operator);
        } else {
            System.out.print(leftExpression.getId());
        }
    }

    public String getTextExpression() {
        String ret = "";

        if (leftComplexExpression != null) {
            if (rightComplexExpression != null) {
                ret = ret + leftComplexExpression.getTextExpression();
                ret = ret + (" " + operator + " ");
                ret = ret + rightComplexExpression.getTextExpression();
            } else {
                ret = ret + leftComplexExpression.getTextExpression();
            }
        } else if (rightExpression != null && operator != null) {
            ret = ret + leftExpression.getId() + " " + operator + " " + rightExpression.getId();
        } else if (operator != null) {
            ret = ret + leftExpression.getId() + " " + operator;
        } else {
            ret = ret + leftExpression.getId();
        }
        return ret;
    }

    public Entity getLeft() {
        return leftExpression;
    }

    public Entity getRight() {
        return rightExpression;
    }

    public String getOperator() {
        return operator;
    }

    public Expression getLeftComplexExpression() {
        return leftComplexExpression;
    }

    public Expression getRightComplexExpression() {
        return rightComplexExpression;
    }

    @Override
    public String toString() {
        return "Expression{" +
                "leftExpression=" + leftExpression +
                ", rightExpression=" + rightExpression +
                ", leftComplexExpression=" + leftComplexExpression +
                ", rightComplexExpression=" + rightComplexExpression +
                ", operator='" + operator + '\'' +
                '}';
    }
}
