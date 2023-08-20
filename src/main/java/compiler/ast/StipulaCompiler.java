package compiler.ast;

import compiler.parser.StipulaBaseVisitor;
import compiler.parser.StipulaParser;
import lcp.lib.datastructures.Pair;
import lcp.lib.datastructures.Triple;
import lcp.lib.dfa.states.DfaState;
import lcp.lib.dfa.states.FinalStates;
import lcp.lib.dfa.transitions.ContractCallByEvent;
import lcp.lib.dfa.transitions.ContractCallByParty;
import lcp.lib.dfa.transitions.TransitionData;
import lcp.lib.models.assets.Asset;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public class StipulaCompiler extends StipulaBaseVisitor {
    private final ArrayList<String> parties;
    private final Map<Pair<String, Integer>, Type> globalVariables;
    private final Map<String, ArrayList<String>> functionTypes;
    private final ArrayList<String> obligationFunctions;
    private String finalBytecode;
    @Getter
    private DfaState initialState;
    private final HashSet<DfaState> acceptanceStates;
    private final HashSet<DfaState> failingStates;
    @Getter
    private final ArrayList<Triple<DfaState, DfaState, TransitionData>> transitions;
    //private final AssetsStorage assetsStorage;

    public StipulaCompiler(
            Map<Pair<String, Integer>, Type> globalVariables,
            Map<String, ArrayList<String>> functionTypes/*,
            AssetsStorage assetsStorage*/
    ) {
        //this.assetsStorage = assetsStorage;
        this.parties = new ArrayList<>();
        this.globalVariables = globalVariables;
        this.functionTypes = functionTypes;
        this.obligationFunctions = new ArrayList<>();
        this.acceptanceStates = new HashSet<>();
        this.failingStates = new HashSet<>();
        this.transitions = new ArrayList<>();
    }

    public FinalStates getFinalStates() {
        return new FinalStates(acceptanceStates, failingStates);
    }

    @Override
    public String visitProg(StipulaParser.ProgContext context) {
        if (context.agreement() != null) {
            initialState = new DfaState(context.init_state.getText());
            finalBytecode = fullVisitAgreement(context.agreement(), context.init_state.getText());
        }

        for (StipulaParser.FunContext functionContext : context.fun()) {
            StipulaContract cnt = visitFun(functionContext);
        }

        for (String obligationFunction : obligationFunctions) {
            finalBytecode += obligationFunction;
        }

        return finalBytecode;
    }

    public String fullVisitAgreement(StipulaParser.AgreementContext context, String initialState) {
        StringBuilder methodSignature = new StringBuilder("fn agreement ");

        for (StipulaParser.PartyContext party : context.party()) {
            methodSignature.append(party.getText()).append(",");
        }
        methodSignature = new StringBuilder(methodSignature.substring(0, methodSignature.length() - 1) + " ");
        methodSignature.append(initialState).append(" ");

        for (int i = 0; i < context.vardec().size(); i++) {
            String v = context.vardec().get(i).getText();

            for (Pair<String, Integer> globalVariable : globalVariables.keySet()) {
                if (globalVariable.getFirst().equals(v)) {
                    if (!globalVariables.get(globalVariable).getTypeName().equals("bool") &&
                            !globalVariables.get(globalVariable).getTypeName().equals("int") &&
                            !globalVariables.get(globalVariable).getTypeName().equals("party") &&
                            !globalVariables.get(globalVariable).getTypeName().equals("real") &&
                            !globalVariables.get(globalVariable).getTypeName().equals("str") &&
                            !globalVariables.get(globalVariable).getTypeName().equals("time") &&
                            !globalVariables.get(globalVariable).getTypeName().equals("asset")) {
                        methodSignature.append("*,");
                    } else {
                        methodSignature.append(globalVariables.get(globalVariable).getTypeName()).append(",");
                    }
                }
            }
        }

        methodSignature = new StringBuilder(methodSignature.substring(0, methodSignature.length() - 1));
        return methodSignature + "\n" + visitAgreement(context);
    }

    @Override
    public String visitAgreement(StipulaParser.AgreementContext context) {
        StringBuilder body = new StringBuilder("global:\n");
        ArrayList<String> fields = new ArrayList<>();

        for (StipulaParser.PartyContext n : context.party()) {
            parties.add(n.getText());
        }

        for (StipulaParser.VardecContext n : context.vardec()) {
            fields.add(n.getText());
        }

        // Instantiate parties
        for (String party : parties) {
            body.append("GINST party ").append(party).append("\n");
        }

        // Instantiate global variables
        for (Pair<String, Integer> globalVariable : globalVariables.keySet()) {
            if (globalVariables.get(globalVariable).getTypeName().equals("asset")) {
                AssetType assetType = (AssetType) globalVariables.get(globalVariable);

                Asset asset;
                int numberOfDecimals = 2;
                // FIXME
                /*try {
                    asset = this.assetsStorage.getAsset(assetType.getAssetId());
                    numberOfDecimals = asset.asset().getDecimals();
                } catch (IOException | AssetNotFoundException ex) {

                }*/

                body.append("GINST ").append(assetType.getTypeName()).append(" ").append(globalVariable.getFirst()).append(" ").append(numberOfDecimals).append(" ").append(assetType.getAssetId()).append("\n");
            } else if (globalVariables.get(globalVariable).getTypeName().equals("real")) {
                body.append("GINST ").append(globalVariables.get(globalVariable).getTypeName()).append(" ").append(globalVariable.getFirst()).append(" 2\n");
            } else if (!globalVariables.get(globalVariable).getTypeName().equals("bool") &&
                    !globalVariables.get(globalVariable).getTypeName().equals("int") &&
                    !globalVariables.get(globalVariable).getTypeName().equals("party") &&
                    !globalVariables.get(globalVariable).getTypeName().equals("str") &&
                    !globalVariables.get(globalVariable).getTypeName().equals("time")) {
                body.append("GINST * ").append(globalVariable.getFirst()).append("\n");
            } else {
                body.append("GINST ").append(globalVariables.get(globalVariable).getTypeName()).append(" ").append(globalVariable.getFirst()).append("\n");
            }
        }

        // Set up parties arguments
        body.append("args:" + "\n");
        for (String party : parties) {
            body.append("PUSH party :").append(party).append("\n");
            body.append("GSTORE ").append(party).append("\n");
        }

        // Set up fields arguments
        for (String field : fields) {
            for (Pair<String, Integer> globalVariable : globalVariables.keySet()) {
                if (globalVariable.getFirst().equals(field)) {
                    body.append("PUSH ").append(globalVariables.get(globalVariable).getTypeName()).append(" :").append(field).append("\n");
                    body.append("GSTORE ").append(field).append("\n");
                    break;
                }
            }
        }

        body.append("start:\nend:\nHALT\n");
        return body.toString();
    }

    @Override
    public ArrayList<Pair<Party, ArrayList<Field>>> visitAssign(StipulaParser.AssignContext context) {
        ArrayList<Pair<Party, ArrayList<Field>>> toRet = new ArrayList<>();
        Pair<Party, ArrayList<Field>> pair;
        ArrayList<Field> fields = new ArrayList<>();

        for (StipulaParser.VardecContext d : context.vardec()) {
            Field tmp = new Field(d.getText());
            fields.add(tmp);
        }

        for (StipulaParser.PartyContext d : context.party()) {
            Party nd = new Party(d.getText());
            pair = new Pair<>(nd, fields);
            toRet.add(pair);
        }

        return toRet;
    }

    @Override
    public ArrayList<AssetEntity> visitAssetdecl(StipulaParser.AssetdeclContext context) {
        ArrayList<AssetEntity> retAssetEntities = new ArrayList<AssetEntity>();
        for (int i = 0; i < context.idAsset.size(); i++) {
            AssetEntity tmpAssetEntity = new AssetEntity(context.idAsset.get(i).getText());
            retAssetEntities.add(tmpAssetEntity);
        }
        return retAssetEntities;
    }

    @Override
    public ArrayList<Field> visitFielddecl(StipulaParser.FielddeclContext context) {
        ArrayList<Field> retFields = new ArrayList<Field>();
        for (int i = 0; i < context.idField.size(); i++) {
            Field tmpField = new Field(context.idField.get(i).getText());
            retFields.add(tmpField);
        }
        return retFields;
    }

    @Override
    public StipulaContract visitFun(StipulaParser.FunContext context) {
        StringBuilder bytecode = new StringBuilder("fn ");

        // Get the source states and the destination state for the current function
        ArrayList<String> sourceStates = new ArrayList<>();
        String destinationState = "";

        if (context.state() != null) {
            for (int i = 0; i < context.state().size(); i++) {
                String state = context.state().get(i).getText();

                if (i == context.state().size() - 1) {
                    destinationState = state;
                } else {
                    sourceStates.add(state);
                }
            }
            bytecode.append(sourceStates.get(0)).append(" "); // FIXME
        }

        ArrayList<String> parties = new ArrayList<>();
        for (StipulaParser.PartyContext party : context.party()) {
            parties.add(party.getText());
        }
        bytecode.append(parties.get(0)).append(" "); // FIXME

        String functionName = context.funId.getText();
        bytecode.append(functionName).append(" ").append(destinationState).append(" ");

        ArrayList<String> currentFunctionTypes = new ArrayList<>();
        for (String functionType : functionTypes.get(functionName)) {
            if (!functionType.equals("bool") && !functionType.equals("int") &&
                    !functionType.equals("party") && !functionType.equals("real") &&
                    !functionType.equals("str") && !functionType.equals("time") &&
                    !functionType.equals("asset")) {
                currentFunctionTypes.add("*");
            } else {
                currentFunctionTypes.add(functionType);
            }
        }

        // Add transition for state machine
        transitions.add(
                new Triple<>(
                        new DfaState(sourceStates.get(0)),
                        new DfaState(destinationState),
                        new ContractCallByParty(functionName, parties.get(0), currentFunctionTypes)
                )
        );

        for (String type : currentFunctionTypes) {
            bytecode.append(type).append(",");
        }
        bytecode = new StringBuilder(bytecode.substring(0, bytecode.length() - 1) + "\n");

        ArrayList<String> arguments = new ArrayList<>();

        if (context.vardec() != null) {
            for (StipulaParser.VardecContext n : context.vardec()) {
                arguments.add(n.getText());
            }
        }

        if (context.assetdec() != null) {
            for (StipulaParser.AssetdecContext n : context.assetdec()) {
                arguments.add(n.getText());
            }
        }

        if (!currentFunctionTypes.isEmpty()) {
            bytecode.append("args:\n");

            for (int i = 0; i < currentFunctionTypes.size(); i++) {
                bytecode.append("PUSH ").append(currentFunctionTypes.get(i)).append(" :").append(arguments.get(i)).append("\n");

                switch (currentFunctionTypes.get(i)) {
                    case "asset", "*" ->
                            bytecode.append("AINST ").append(currentFunctionTypes.get(i)).append(" :").append(arguments.get(i)).append("\n");
                    case "real" ->
                        //bytecode += "AINST " + currentFunctionTypes.get(i) + " " + arguments.get(i) + " 2\n";
                            bytecode.append("AINST ").append(currentFunctionTypes.get(i)).append(" :").append(arguments.get(i)).append("\n");
                    default ->
                            bytecode.append("AINST ").append(currentFunctionTypes.get(i)).append(" ").append(arguments.get(i)).append("\n");
                }

                bytecode.append("ASTORE ").append(arguments.get(i)).append("\n");
            }
        }

        bytecode.append("start:\n");

        if (context.prec() != null) {
            Expression conds = visitPrec(context.prec());

            Entity left = conds.getLeft();
            Entity right = conds.getRight();

            if (left != null && right != null) {
                boolean found = false;

                for (Pair<String, Integer> globalVariable : globalVariables.keySet()) {
                    if (globalVariable.getFirst().equals(left.name)) {
                        found = true;
                        bytecode.append("GLOAD ").append(left.name).append("\n");
                    }
                }

                if (!found) {
                    bytecode.append("ALOAD ").append(left.name).append("\n");
                }

                found = false;
                for (Pair<String, Integer> globalVariable : globalVariables.keySet()) {
                    if (globalVariable.getFirst().equals(right.name)) {
                        found = true;
                        bytecode.append("GLOAD ").append(right.name).append("\n");
                    }
                }

                if (!found) {
                    bytecode.append("ALOAD ").append(right.name).append("\n");
                }

                bytecode.append(getBytecodeOperand(conds.getOperator())).append("\nJMPIF if_branch\nRAISE AMOUNT_NOT_EQUAL\nJMP end\nif_branch:\n");
            }
        }

        for (StipulaParser.StatContext sc : context.stat()) {
            ArrayList<Pair<Expression, ArrayList<Statement>>> ret = visitStat(sc);

            if (ret != null) {
                for (Pair<Expression, ArrayList<Statement>> pair : ret) {
                    if (pair.getFirst() == null) {
                        for (Statement statement : pair.getSecond()) {
                            if (!statement.getOperator().equals("FIELDUP")) {
                                String left = statement.getLeftExpression().getId();
                                String right = statement.getRightExpression().getId();

                                String rightTermType = "";
                                boolean found = false;
                                boolean isLeftVariableGlobal = false;
                                boolean isRightVariableGlobal = false;

                                if (statement.isFractExpressionNull()) {
                                    for (int i = 0; i < arguments.size(); i++) {
                                        if (arguments.get(i).equals(left)) {
                                            found = true;
                                            rightTermType = currentFunctionTypes.get(i);
                                            break;
                                        }
                                    }

                                    if (!found) {
                                        for (Pair<String, Integer> globalVariable : globalVariables.keySet()) {
                                            if (globalVariable.getFirst().equals(left)) {
                                                found = true;
                                                isLeftVariableGlobal = true;
                                                rightTermType = globalVariables.get(globalVariable).getTypeName();
                                                break;
                                            }
                                        }
                                    }

                                    if (!found) {
                                        // TODO
                                        // throw new Exception("Impossible to find the type for '" + right + "' variable");
                                    }
                                } else {
                                    // TODO
                                }

                                found = false;
                                for (int i = 0; i < arguments.size(); i++) {
                                    if (arguments.get(i).equals(right)) {
                                        found = true;
                                        rightTermType = currentFunctionTypes.get(i);
                                        break;
                                    }
                                }

                                if (!found) {
                                    for (Pair<String, Integer> globalVariable : globalVariables.keySet()) {
                                        if (globalVariable.getFirst().equals(right)) {
                                            found = true;
                                            isRightVariableGlobal = true;
                                            rightTermType = globalVariables.get(globalVariable).getTypeName();
                                            break;
                                        }
                                    }
                                }

                                if (!found) {
                                    for (String party : this.parties) {
                                        if (party.equals(right)) {
                                            found = true;
                                            isRightVariableGlobal = true;
                                            rightTermType = "party";
                                            break;
                                        }
                                    }
                                }

                                if (!found) {
                                    // TODO
                                    // throw new Exception("Impossible to find the type for '" + right + "' variable");
                                }

                                // Deposit
                                if (rightTermType.equals("asset")) {
                                    if (statement.isFractExpressionNull()) {
                                        if (isLeftVariableGlobal) {
                                            bytecode.append("GLOAD ");
                                        } else {
                                            bytecode.append("ALOAD ");
                                        }
                                        bytecode.append(left).append("\n");
                                    } else {
                                        // TODO
                                    }

                                    if (isRightVariableGlobal) {
                                        bytecode.append("GLOAD ");
                                    } else {
                                        bytecode.append("ALOAD ");
                                    }
                                    bytecode.append(right).append("\n");
                                    bytecode.append("DEPOSIT ").append(right).append("\n");
                                } else {
                                    // Withdraw

                                    // i.e. wallet -o Borrower
                                    if (statement.isFractExpressionNull()) {
                                        bytecode.append("PUSH real 100 2\n");

                                        if (isLeftVariableGlobal) {
                                            bytecode.append("GLOAD ");
                                        } else {
                                            bytecode.append("ALOAD ");
                                        }
                                        bytecode.append(left).append("\n");

                                        if (isRightVariableGlobal) {
                                            bytecode.append("GLOAD ");
                                        } else {
                                            bytecode.append("ALOAD ");
                                        }

                                        bytecode.append(right).append("\n");
                                        bytecode.append("WITHDRAW ").append(left).append("\n");
                                    } else {
                                        // i.e. (y*wallet) -o wallet, Lender

                                    }
                                }

                            } else {
                                // FIELDUP
                                String left = statement.getLeftExpression().getId();
                                String right = statement.getRightExpression().getId();

                                String rightTermType = "";
                                boolean found = false;
                                boolean isLeftVariableGlobal = false;
                                boolean isRightVariableGlobal = false;

                                if (statement.isFractExpressionNull()) {
                                    for (int i = 0; i < arguments.size(); i++) {
                                        if (arguments.get(i).equals(left)) {
                                            found = true;
                                            rightTermType = currentFunctionTypes.get(i);
                                            break;
                                        }
                                    }

                                    if (!found) {
                                        for (Pair<String, Integer> globalVariable : globalVariables.keySet()) {
                                            if (globalVariable.getFirst().equals(left)) {
                                                found = true;
                                                isLeftVariableGlobal = true;
                                                rightTermType = globalVariables.get(globalVariable).getTypeName();
                                                break;
                                            }
                                        }
                                    }

                                    if (!found) {
                                        // TODO
                                        // throw new Exception("Impossible to find the type for '" + right + "' variable");
                                    }
                                } else {
                                    // TODO
                                }

                                found = false;
                                for (int i = 0; i < arguments.size(); i++) {
                                    if (arguments.get(i).equals(right)) {
                                        found = true;
                                        rightTermType = currentFunctionTypes.get(i);
                                        break;
                                    }
                                }

                                if (!found) {
                                    for (Pair<String, Integer> globalVariable : globalVariables.keySet()) {
                                        if (globalVariable.getFirst().equals(right)) {
                                            found = true;
                                            isRightVariableGlobal = true;
                                            rightTermType = globalVariables.get(globalVariable).getTypeName();
                                            break;
                                        }
                                    }
                                }

                                if (!found) {
                                    for (String party : this.parties) {
                                        if (party.equals(right)) {
                                            found = true;
                                            isRightVariableGlobal = true;
                                            rightTermType = "party";
                                            break;
                                        }
                                    }
                                }

                                if (!found) {
                                    // TODO
                                    // throw new Exception("Impossible to find the type for '" + right + "' variable");
                                }

                                if (!rightTermType.equals("party")) {
                                    if (statement.isFractExpressionNull()) {
                                        if (isLeftVariableGlobal) {
                                            bytecode.append("GLOAD ");
                                        } else {
                                            bytecode.append("ALOAD ");
                                        }
                                        bytecode.append(left).append("\n");
                                    } else {
                                        // TODO
                                    }

                                    if (isRightVariableGlobal) {
                                        bytecode.append("GSTORE ");
                                    } else {
                                        bytecode.append("ASTORE ");
                                    }
                                    bytecode.append(right).append("\n");
                                }
                            }
                        }
                    } else {
                        // newContract.addIfThenElse(ret);
                    }
                }
            }
        }

        if (context.events() != null) {
            int k = 0;
            for (StipulaParser.EventsContext evn : context.events()) {
                Event event = visitEvents(evn);

                if (event != null) {
                    String left = null;
                    String right = null;
                    String operator;
                    Expression leftComplexExpression;
                    Expression rightComplexExpression;

                    if (event.getExpression().getLeft() != null) {
                        left = event.getExpression().getLeft().getId();
                    }

                    if (event.getExpression().getRight() != null) {
                        right = event.getExpression().getRight().getId();
                    }

                    if (left == null && right == null) {
                        leftComplexExpression = event.getExpression().getLeftComplexExpression();
                        rightComplexExpression = event.getExpression().getRightComplexExpression();
                        left = leftComplexExpression.getLeft().getId();
                        right = rightComplexExpression.getLeft().getId();
                    }

                    operator = getBytecodeOperand(event.getExpression().getOperator());

                    boolean found = false;
                    boolean isLeftVariableGlobal = false;
                    boolean isRightVariableGlobal = false;

                    for (int i = 0; i < arguments.size(); i++) {
                        if (arguments.get(i).equals(left)) {
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        for (Pair<String, Integer> globalVariable : globalVariables.keySet()) {
                            if (globalVariable.getFirst().equals(left)) {
                                found = true;
                                isLeftVariableGlobal = true;
                                break;
                            }
                        }
                    }

                    if (!found) {
                        // TODO
                        // throw new Exception("Impossible to find the type for '" + right + "' variable");
                    }

                    found = false;
                    for (int i = 0; i < arguments.size(); i++) {
                        if (arguments.get(i).equals(right)) {
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        for (Pair<String, Integer> globalVariable : globalVariables.keySet()) {
                            if (globalVariable.getFirst().equals(right)) {
                                found = true;
                                isRightVariableGlobal = true;
                                break;
                            }
                        }
                    }

                    assert right != null;
                    if (right.equals("now")) {
                        bytecode.append("PUSH time ").append(right).append("\n");
                    } else {
                        if (isRightVariableGlobal) {
                            bytecode.append("GLOAD ");
                        } else {
                            bytecode.append("ALOAD ");
                        }
                        bytecode.append(right).append("\n");
                    }

                    assert left != null;
                    if (left.equals("now")) {
                        bytecode.append("PUSH time ").append(left).append("\n");
                    } else {
                        if (isLeftVariableGlobal) {
                            bytecode.append("GLOAD ");
                        } else {
                            bytecode.append("ALOAD ");
                        }
                        bytecode.append(left).append("\n");
                    }

                    bytecode.append(operator).append("\n");
                    String obligationFunctionName = "obligation_" + (k + 1);
                    bytecode.append("TRIGGER ").append(obligationFunctionName).append("\n");

                    String sourceState = event.getInitState();
                    String destState = event.getEndState();

                    // Add transition for state machine
                    transitions.add(
                            new Triple<>(
                                    new DfaState(sourceState),
                                    new DfaState(destState),
                                    new ContractCallByEvent(obligationFunctionName)
                            )
                    );

                    StringBuilder obligationFunction = new StringBuilder("obligation " + sourceState + " " + obligationFunctionName + " " + destState + "\nstart:\n");

                    for (Pair<Expression, ArrayList<Statement>> pair : event.getStatements()) {
                        if (pair.getFirst() == null) {
                            for (Statement statement : pair.getSecond()) {
                                // newContract.addStatement(stm);

                                if (!statement.getOperator().equals("FIELDUP")) {
                                    left = statement.getLeftExpression().getId();
                                    right = statement.getRightExpression().getId();

                                    String rightTermType = "";
                                    found = false;
                                    isLeftVariableGlobal = false;
                                    isRightVariableGlobal = false;

                                    if (statement.isFractExpressionNull()) {
                                        for (int i = 0; i < arguments.size(); i++) {
                                            if (arguments.get(i).equals(left)) {
                                                found = true;
                                                rightTermType = currentFunctionTypes.get(i);
                                                break;
                                            }
                                        }

                                        if (!found) {
                                            for (Pair<String, Integer> globalVariable : globalVariables.keySet()) {
                                                if (globalVariable.getFirst().equals(left)) {
                                                    found = true;
                                                    isLeftVariableGlobal = true;
                                                    rightTermType = globalVariables.get(globalVariable).getTypeName();
                                                    break;
                                                }
                                            }
                                        }

                                        if (!found) {
                                            // TODO
                                            // throw new Exception("Impossible to find the type for '" + right + "' variable");
                                        }
                                    } else {
                                        // TODO
                                    }

                                    found = false;
                                    for (int i = 0; i < arguments.size(); i++) {
                                        if (arguments.get(i).equals(right)) {
                                            found = true;
                                            rightTermType = currentFunctionTypes.get(i);
                                            break;
                                        }
                                    }

                                    if (!found) {
                                        for (Pair<String, Integer> globalVariable : globalVariables.keySet()) {
                                            if (globalVariable.getFirst().equals(right)) {
                                                found = true;
                                                isRightVariableGlobal = true;
                                                rightTermType = globalVariables.get(globalVariable).getTypeName();
                                                break;
                                            }
                                        }
                                    }

                                    if (!found) {
                                        for (String party : this.parties) {
                                            if (party.equals(right)) {
                                                found = true;
                                                isRightVariableGlobal = true;
                                                rightTermType = "party";
                                                break;
                                            }
                                        }
                                    }

                                    if (!found) {
                                        // TODO
                                        // throw new Exception("Impossible to find the type for '" + right + "' variable");
                                    }

                                    // Deposit
                                    if (rightTermType.equals("asset")) {
                                        if (statement.isFractExpressionNull()) {
                                            if (isLeftVariableGlobal) {
                                                obligationFunction.append("GLOAD ");
                                            } else {
                                                obligationFunction.append("ALOAD ");
                                            }
                                            obligationFunction.append(left).append("\n");
                                        } else {
                                            // TODO
                                        }

                                        if (isRightVariableGlobal) {
                                            obligationFunction.append("GLOAD ");
                                        } else {
                                            obligationFunction.append("ALOAD ");
                                        }
                                        obligationFunction.append(right).append("\n");
                                        obligationFunction.append("DEPOSIT ").append(right).append("\n");
                                    } else {
                                        // Withdraw

                                        // i.e. wallet -o Borrower
                                        if (statement.isFractExpressionNull()) {
                                            obligationFunction.append("PUSH real 100 2\n");

                                            if (isLeftVariableGlobal) {
                                                obligationFunction.append("GLOAD ");
                                            } else {
                                                obligationFunction.append("ALOAD ");
                                            }
                                            obligationFunction.append(left).append("\n");

                                            if (isRightVariableGlobal) {
                                                obligationFunction.append("GLOAD ");
                                            } else {
                                                obligationFunction.append("ALOAD ");
                                            }

                                            obligationFunction.append(right).append("\n");
                                            obligationFunction.append("WITHDRAW ").append(left).append("\n");
                                        } else {
                                            // i.e. (y*wallet) -o wallet, Lender

                                        }
                                    }
                                }
                            }
                        } else {
                            // newContract.addIfThenElse(ret);
                        }
                    }
                    obligationFunction.append("end:\nHALT\n");
                    obligationFunctions.add(obligationFunction.toString());
                    k++;
                }
            }
        }
        finalBytecode += bytecode + "end:\nHALT\n";
        return null;
    }

    private String getBytecodeOperand(String operator) {
        return switch (operator) {
            case "==" -> "ISEQ";
            case "!=" -> "ISEQ\nNOT";
            case ">" -> "ISLE\nNOT";
            case ">=" -> "ISLT\nNOT";
            case "<=" -> "ISLE";
            case "<" -> "ISLT";
            case "+" -> "ADD";
            case "-" -> "SUB";
            case "*" -> "MUL";
            case "/" -> "DIV";
            default -> null;
        };
    }

    @Override
    public Event visitEvents(StipulaParser.EventsContext context) {
        if (context.EMPTY() == null) {
            String init = context.ID(0).toString();
            String end = context.ID(context.ID().size() - 1).toString();
            ArrayList<Pair<Expression, ArrayList<Statement>>> eventStat = new ArrayList<>();

            for (StipulaParser.StatContext stm : context.stat()) {
                eventStat.addAll(visitStat(stm));
            }

            Expression expr = visitExpr(context.expr());
            return new Event(init, end, eventStat, expr);
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<Pair<Expression, ArrayList<Statement>>> visitStat(StipulaParser.StatContext context) {
        ArrayList<Pair<Expression, ArrayList<Statement>>> ret = null;

        if (context.ASSETUP() != null) {
            AssetEntity left;
            AssetEntity right;

            if (context.COMMA() != null) {
                if (context.left.expr() != null) {
                    Expression expr = visitExpr(context.left.expr());
                    double fract = 0;
                    Entity fractExpr = null;

                    if (expr.getRight() != null) {
                        left = new AssetEntity(expr.getRight().getId());
                        right = new AssetEntity(context.right.getText());

                        try {
                            fract = Double.parseDouble(expr.getLeft().getId());
                        } catch (NumberFormatException e) {
                            fractExpr = new Entity(expr.getLeft().getId());
                        }
                    } else {
                        left = new AssetEntity(expr.getRightComplexExpression().getLeft().getId());
                        right = new AssetEntity(context.rightPlus.getText());

                        try {
                            fract = Double.parseDouble(expr.getLeftComplexExpression().getLeft().getId());
                        } catch (NumberFormatException e) {
                            fractExpr = new Entity((expr.getLeftComplexExpression().getLeft().getId()));
                        }
                    }

                    ArrayList<Statement> tmpArray = new ArrayList<Statement>();
                    if (fractExpr == null) {
                        tmpArray.add(new Statement(left, right, "ASSETUP", fract));
                    } else {
                        tmpArray.add(new Statement(left, right, "ASSETUP", fractExpr));
                    }

                    Pair<Expression, ArrayList<Statement>> tmpPair = new Pair<>(null, tmpArray);
                    ret = new ArrayList<>();
                    ret.add(tmpPair);
                } else {
                    left = new AssetEntity(context.right.getText());
                    right = new AssetEntity(context.rightPlus.getText());

                    ArrayList<Statement> tmpArray = new ArrayList<>();
                    tmpArray.add(new Statement(left, right, "ASSETUP"));

                    Pair<Expression, ArrayList<Statement>> tmpPair = new Pair<>(null, tmpArray);
                    ret = new ArrayList<>();
                    ret.add(tmpPair);
                }
            } else if (context.left.expr() != null) {
                Expression expr = visitExpr(context.left.expr());
                double fract = 0;
                Entity fractExpr = null;

                if (expr.getRight() != null) {
                    left = new AssetEntity(expr.getRight().getId());
                    right = new AssetEntity(context.right.getText());

                    try {
                        fract = Double.parseDouble(expr.getLeft().getId());
                    } catch (NumberFormatException e) {
                        fractExpr = new Entity(expr.getLeft().getId());
                    }
                } else {
                    left = new AssetEntity(expr.getRightComplexExpression().getLeft().getId());
                    right = new AssetEntity(context.right.getText());

                    try {
                        fract = Double.parseDouble(expr.getLeftComplexExpression().getLeft().getId());
                    } catch (NumberFormatException e) {
                        fractExpr = new Entity((expr.getLeftComplexExpression().getLeft().getId()));
                    }
                }

                ArrayList<Statement> tmpArray = new ArrayList<>();
                if (fractExpr == null) {
                    tmpArray.add(new Statement(left, right, "ASSETUP", fract));
                } else {
                    tmpArray.add(new Statement(left, right, "ASSETUP", fractExpr));
                }

                Pair<Expression, ArrayList<Statement>> tmpPair = new Pair<>(null, tmpArray);
                ret = new ArrayList<>();
                ret.add(tmpPair);
            } else {
                left = new AssetEntity(context.left.getText());
                right = new AssetEntity(context.right.getText());

                ArrayList<Statement> tmpArray = new ArrayList<>();
                tmpArray.add(new Statement(left, right, "ASSETUP"));

                Pair<Expression, ArrayList<Statement>> tmpPair = new Pair<>(null, tmpArray);
                ret = new ArrayList<>();
                ret.add(tmpPair);
            }
        } else if (context.FIELDUP() != null) {
            Field left = new Field(context.left.getText());
            Field right = new Field(context.right.getText());
            ArrayList<Statement> tmpArray = new ArrayList<>();
            tmpArray.add(new Statement(left, right, "FIELDUP"));
            Pair<Expression, ArrayList<Statement>> tmpPair = new Pair<>(null, tmpArray);
            ret = new ArrayList<>();
            ret.add(tmpPair);
        } else if (context.ifelse() != null) {
            ret = visitIfelse(context.ifelse());
        }

        return ret;
    }

    @Override
    public ArrayList<Pair<Expression, ArrayList<Statement>>> visitIfelse(StipulaParser.IfelseContext context) {
        Expression condIf = visitExpr(context.cond);
        ArrayList<Pair<Expression, ArrayList<Statement>>> toRet = new ArrayList<Pair<Expression, ArrayList<Statement>>>();
        ArrayList<Statement> tmpStat = new ArrayList<Statement>();
        int start = 0;
        boolean flag = false;

        for (int i = start; i < context.ifBranch.size() && !flag; i++) {
            if (context.ifBranch.get(i).getText().equals("_")) {
                flag = true;
                start = i + 1;
            } else {
                ArrayList<Pair<Expression, ArrayList<Statement>>> tmpRet = visitStat(context.ifBranch.get(i));
                for (Pair<Expression, ArrayList<Statement>> pair : tmpRet) {
                    if (pair.getFirst() == null) {
                        tmpStat.addAll(pair.getSecond());
                    }
                }
            }
        }

        Pair<Expression, ArrayList<Statement>> tmp = new Pair<Expression, ArrayList<Statement>>(condIf, tmpStat);
        toRet.add(tmp);

        if (context.condElseIf != null) {
            flag = false;
            tmpStat = new ArrayList<Statement>();

            for (StipulaParser.ExprContext expr : context.condElseIf) {
                for (int i = start; i < context.elseIfBranch.size() && !flag; i++) {
                    if (context.elseIfBranch.get(i).getText().equals("_")) {
                        flag = true;
                        start = i + 1;
                    } else {
                        ArrayList<Pair<Expression, ArrayList<Statement>>> tmpRet = visitStat(context.elseIfBranch.get(i));

                        for (Pair<Expression, ArrayList<Statement>> pair : tmpRet) {
                            if (pair.getFirst() == null) {
                                tmpStat.addAll(pair.getSecond());
                            }
                        }
                    }
                }
                tmp = new Pair<>(visitExpr(expr), tmpStat);
                toRet.add(tmp);
            }
        }

        if (context.elseBranch != null) {
            tmpStat = new ArrayList<Statement>();

            for (StipulaParser.StatContext stm : context.elseBranch) {
                ArrayList<Pair<Expression, ArrayList<Statement>>> tmpRet = visitStat(stm);

                for (Pair<Expression, ArrayList<Statement>> pair : tmpRet) {
                    if (pair.getFirst() == null) {
                        tmpStat.addAll(pair.getSecond());
                    }
                }
            }
            tmp = new Pair<Expression, ArrayList<Statement>>(new Expression(new Entity("_")), tmpStat);
            toRet.add(tmp);
        }
        return toRet;
    }

    @Override
    public Expression visitPrec(StipulaParser.PrecContext context) {
        return visitExpr(context.expr());
    }

    @Override
    public Expression visitExpr(StipulaParser.ExprContext context) {
        if (context.right == null) {
            if (context.left.right == null) {
                if (context.left.left.right == null) {
                    return visitValue(context.left.left.left);
                } else {
                    Entity left = visitValue(context.left.left.left).getLeft();
                    Entity right = visitValue(context.left.left.right).getLeft();
                    String operator = context.left.left.operator.getText();
                    return new Expression(left, right, operator);
                }
            } else {
                Expression leftExpression = visitFactor(context.left.left);
                Expression rightExpression = visitTerm(context.left.right);
                String operator = context.left.operator.getText();
                return new Expression(leftExpression, rightExpression, operator);
            }
        } else {
            Expression leftExpression = visitTerm(context.left);
            Expression rightExpression = visitExpr(context.right);
            String operator = context.operator.getText();
            return new Expression(leftExpression, rightExpression, operator);
        }
    }

    @Override
    public Expression visitTerm(StipulaParser.TermContext context) {
        if (context.right != null) {
            Entity left = (Entity) visit(context.left);
            Entity right = (Entity) visit(context.right);
            return new Expression(left, right, context.operator.getText());
        } else {
            return visitFactor(context.left);
        }
    }

    @Override
    public Expression visitFactor(StipulaParser.FactorContext context) {
        if (context.right != null) {
            Entity left = visitValue(context.left).getLeft();
            Entity right = visitValue(context.right).getLeft();
            return new Expression(left, right, context.operator.getText());
        } else if (context.operator != null) {
            return new Expression(visitValue(context.left).getLeft(), context.operator.getText());
        } else {
            return new Expression(visitValue(context.left).getLeft());
        }
    }

    @Override
    public Expression visitValue(StipulaParser.ValueContext context) {
        Expression ret = null;

        if (context.NOW() != null) {
            ret = new Expression(new Entity(context.NOW().getText()), null);
        } else if (context.TRUE() != null) {
            ret = new Expression(new Entity(context.TRUE().getText()), null);
        } else if (context.FALSE() != null) {
            ret = new Expression(new Entity(context.FALSE().getText()), null);
        } else if (context.EMPTY() != null) {
            ret = new Expression(new Entity(""), null);
        } else if (context.RAWSTRING() != null) {
            ret = new Expression(new Entity(context.RAWSTRING().getText()), null);
        } else if (context.ID() != null) {
            ret = new Expression(new Entity(context.ID().getText()), null);
        } else if (context.number() != null) {
            ret = new Expression(new Entity(context.number().getText()), null);
        } else if (context.expr() != null) {
            ret = visitExpr(context.expr());
        }

        return ret;
    }
}
