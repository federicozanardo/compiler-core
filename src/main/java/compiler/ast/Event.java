package compiler.ast;

import lcp.lib.datastructures.Pair;

import java.util.ArrayList;
import java.util.Timer;

public class Event {
    private final int SECS = 60;
    private final int MINS = 60;
    private final String sourceState;
    private final String destinationState;
    private final ArrayList<Pair<Expression, ArrayList<Statement>>> statements;
    private final Expression expression;
    private Timer timer;
    private StipulaContract stipulaContract;

    public Event(String sourceState, String destinationState, ArrayList<Pair<Expression, ArrayList<Statement>>> statements, Expression expression) {
        this.sourceState = sourceState;
        this.destinationState = destinationState;
        this.statements = statements;
        this.expression = expression;
    }

    public StipulaContract getContract() {
        return stipulaContract;
    }

    public String getInitState() {
        return sourceState;
    }

    public String getEndState() {
        return destinationState;
    }

    public ArrayList<Pair<Expression, ArrayList<Statement>>> getStatements() {
        return statements;
    }

    public Expression getExpression() {
        return expression;
    }

    public String printEvent() {
        String ret = expression.getTextExpression() + " >> @" + sourceState + "{\n\t";
        for (Pair<Expression, ArrayList<Statement>> pair : statements) {
            if (pair.getFirst() != null) {
                ret = ret + pair.getFirst().getTextExpression();
            }
            for (Statement stm : pair.getSecond()) {
                ret = ret + stm.getTextStatement();
                ret = ret + "\n\t";
            }
        }
        ret = ret + " } ==> @" + destinationState;
        return ret;
    }

    @Override
    public String toString() {
        return "Event{" +
                "SECS=" + SECS +
                ", MINS=" + MINS +
                ", init='" + sourceState + '\'' +
                ", end='" + destinationState + '\'' +
                ", statements=" + statements +
                ", expr=" + expression +
                ", timer=" + timer +
                ", contract=" + stipulaContract +
                '}';
    }
}
