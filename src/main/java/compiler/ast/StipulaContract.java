package compiler.ast;

import lcp.lib.datastructures.Pair;

import java.util.ArrayList;

public class StipulaContract {
    private final ArrayList<Party> disputers;
    private final String id;
    private final ArrayList<Field> vars;
    private final ArrayList<AssetEntity> assetEntities;
    private final ArrayList<String> initState;
    private final String endState;
    private ArrayList<Field> globalVars = null;
    private ArrayList<AssetEntity> globalAssetEntities = null;
    private ArrayList<Statement> statements = null;
    private ArrayList<Expression> prec = null;
    private ArrayList<Pair<Expression, ArrayList<Statement>>> ifThenElse = null;
    private ArrayList<Party> globalParties = null;
    private Event events = null;
    private int index;

    public StipulaContract(String name, ArrayList<Field> f, ArrayList<AssetEntity> a, ArrayList<Party> d, ArrayList<String> s1, String s2, int i) {
        id = name;
        vars = f;
        assetEntities = a;
        disputers = d;
        initState = s1;
        endState = s2;
        index = i;
    }

    public Event getEvent() {
        return events;
    }

    public ArrayList<Party> getParty() {
        return disputers;
    }

    public String getId() {
        return id;
    }

    public int findVar(String expr, ArrayList<Field> array) {
        int indexRet = -1;
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getId().equals(expr)) {
                indexRet = i;
            }
        }
        return indexRet;
    }

    public int findParty(String expr) {
        int index = -1;
        for (int i = 0; i < globalParties.size(); i++) {
            if (globalParties.get(i).getId().equals(expr)) {
                index = i;
            }
        }
        return index;
    }

    public int findAsset(String expr, ArrayList<AssetEntity> array) {
        int indexRet = -1;
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getId().equals(expr)) {
                indexRet = i;
            }
        }
        return indexRet;
    }

    public void setValuesConditions(Entity left, Entity right) {
        if (left != null) {
            int indexLeft = findAsset(left.getId(), assetEntities);

            if (indexLeft != -1) {
                left.setValue((float) assetEntities.get(indexLeft).getValue());
            } else {
                indexLeft = findAsset(left.getId(), globalAssetEntities);

                if (indexLeft != -1) {
                    left.setValue((float) globalAssetEntities.get(indexLeft).getValue());
                } else {
                    indexLeft = findVar(left.getId(), vars);

                    if (indexLeft != -1) {
                        if (!(vars.get(indexLeft).getType() instanceof TimeType) || vars.get(indexLeft).getValueStr() == null) {
                            left.setValue((float) vars.get(indexLeft).getValue());
                        } else {
                            left.setValueStr(vars.get(indexLeft).getValueStr());
                        }
                    } else {
                        indexLeft = findVar(left.getId(), globalVars);

                        if (indexLeft != -1) {
                            if (!(globalVars.get(indexLeft).getType() instanceof TimeType) || globalVars.get(indexLeft).getValueStr() == null) {
                                left.setValue((float) globalVars.get(indexLeft).getValue());
                            } else {
                                left.setValueStr(globalVars.get(indexLeft).getValueStr());
                            }
                        } else {
                            try {
                                left.setValue((float) Double.parseDouble(left.getId()));
                            } catch (NumberFormatException e) {

                            }
                        }
                    }
                }
            }
        }
        if (right != null) {
            int indexRight = findAsset(right.getId(), assetEntities);

            if (indexRight != -1) {
                right.setValue((float) assetEntities.get(indexRight).getValue());
            } else {
                indexRight = findAsset(right.getId(), globalAssetEntities);

                if (indexRight != -1) {
                    right.setValue((float) globalAssetEntities.get(indexRight).getValue());
                } else {
                    indexRight = findVar(right.getId(), vars);

                    if (indexRight != -1) {
                        if (!(vars.get(indexRight).getType() instanceof TimeType) || vars.get(indexRight).getValueStr() == null) {
                            right.setValue((float) vars.get(indexRight).getValue());
                        } else {
                            right.setValueStr(vars.get(indexRight).getValueStr());
                        }
                    } else {
                        indexRight = findVar(right.getId(), globalVars);

                        if (indexRight != -1) {
                            if (!(globalVars.get(indexRight).getType() instanceof TimeType) || globalVars.get(indexRight).getValueStr() == null) {
                                right.setValue((float) globalVars.get(indexRight).getValue());
                            } else {
                                right.setValueStr(globalVars.get(indexRight).getValueStr());
                            }
                        } else {
                            try {
                                right.setValue((float) Double.parseDouble(right.getId()));
                            } catch (NumberFormatException e) {

                            }
                        }
                    }
                }
            }
        }
    }

    public boolean isComplexExpr(Entity expr) {
        if (expr.getId().contains("+") || expr.getId().contains("-") || expr.getId().contains("*") || expr.getId().contains("/")) {
            return true;
        }
        return false;
    }

    public ArrayList<Field> divideComplexExpr(Entity expr) {
        ArrayList<Field> toRet = new ArrayList<Field>();
        String[] split = null;
        String exprId = expr.getId().replaceAll("[()]", "");
        if (exprId.contains("+")) {
            split = exprId.split("\\+");
        } else if (exprId.contains("-")) {
            split = exprId.split("\\-");
        } else if (exprId.contains("*")) {
            split = exprId.split("\\*");
        } else {
            split = exprId.split("/");
        }
        for (int i = 0; i < split.length; i++) {
            Field toAdd = new Field(split[i]);
            toRet.add(toAdd);
        }
        return toRet;
    }

    public boolean runStatements(boolean valid, TypeInference tc, ArrayList<Statement> stms) {
        for (Statement s : stms) {
            if (s.getOperator().equals("FIELDUP")) {
                Field leftExpr = (Field) s.getLeftExpression();
                Field rightExpr = (Field) s.getRightExpression();
                Field leftExpr2 = null;
                boolean everyParty = false;

                if (rightExpr.getId().equals("_")) {
                    everyParty = true;
                } else if (isComplexExpr(leftExpr)) {
                    ArrayList<Field> entities = divideComplexExpr(leftExpr);

                    if (entities.size() == 2) {
                        leftExpr = entities.get(0);
                        leftExpr2 = entities.get(1);
                    }
                }

                int indexLeft = findVar(leftExpr.getId(), vars);
                int indexLeft2 = -1;

                if (leftExpr2 != null) {
                    indexLeft2 = findVar(leftExpr2.getId(), vars);
                }

                int indexRight = findVar(rightExpr.getId(), vars);
                boolean globalLeft = false;
                boolean globalLeft2 = false;
                boolean globalRight = false;
                boolean partyLeft = false;
                boolean partyLeft2 = false;
                boolean partyRight = false;
                boolean extLeft = false;
                boolean extLeft2 = false;
                boolean extRight = false;

                if (indexLeft == -1) {
                    indexLeft = findVar(leftExpr.getId(), globalVars);

                    if (indexLeft == -1) {
                        indexLeft = findParty(leftExpr.getId());

                        if (indexLeft == -1) {
                            extLeft = true;
                        } else {
                            partyLeft = true;
                        }
                    } else {
                        globalLeft = true;
                    }
                }

                if (leftExpr2 != null) {
                    if (indexLeft2 == -1) {
                        indexLeft2 = findVar(leftExpr2.getId(), globalVars);

                        if (indexLeft2 == -1) {
                            indexLeft2 = findParty(leftExpr2.getId());

                            if (indexLeft2 == -1) {
                                extLeft2 = true;
                            } else {
                                partyLeft2 = true;
                            }
                        } else {
                            globalLeft2 = true;
                        }
                    }
                }

                if (indexRight == -1) {
                    indexRight = findVar(rightExpr.getId(), globalVars);

                    if (indexRight == -1) {
                        indexRight = findParty(rightExpr.getId());

                        if (indexRight == -1) {
                            extRight = true;
                        } else {
                            partyRight = true;
                        }
                    } else {
                        if (!everyParty) {
                            globalRight = true;
                        }
                    }
                }

                if (leftExpr2 == null) {
                    if (extLeft && globalRight) {
                        try {
                            globalVars.get(indexRight).setValue((float) Double.parseDouble(leftExpr.getId()));
                        } catch (NumberFormatException e) {
                            globalVars.get(indexRight).setValueStr(String.format(leftExpr.getId()));
                        }
                    } else if (extLeft && partyRight) {
                        try {
                            globalParties.get(indexRight).setValue(((float) Double.parseDouble(leftExpr.getId())));
                        } catch (NumberFormatException e) {
                            globalParties.get(indexRight).setValueStr(String.format(leftExpr.getId()));
                        }
                    } else if (extLeft && !globalRight) {
                        try {
                            vars.get(indexRight).setValue((float) Double.parseDouble(leftExpr.getId()));
                        } catch (NumberFormatException e) {
                            vars.get(indexRight).setValueStr(String.format(leftExpr.getId()));
                        }
                    } else if (extLeft && everyParty) {
                        try {
                            for (Party p : globalParties) {
                                p.setValue(((float) Double.parseDouble(leftExpr.getId())));
                            }
                        } catch (NumberFormatException e) {
                            for (Party p : globalParties) {
                                p.setValueStr(String.format(leftExpr.getId()));
                            }
                        }
                    } else if (!partyLeft && partyRight && globalLeft) {
                        Type t1 = tc.getCorrectType(globalVars.get(indexLeft), index);

                        if (!(t1 instanceof StringType)) {
                            globalParties.get(indexRight).setValue(((float) (globalParties.get(indexRight).getValue() + (float) globalVars.get(indexLeft).getValue())));
                        } else {
                            if (globalParties.get(indexRight).getValueStr() == null) {
                                globalParties.get(indexRight).setValueStr(globalVars.get(indexLeft).getValueStr());
                            } else {
                                globalParties.get(indexRight).setValueStr(globalParties.get(indexRight).getValueStr() + globalVars.get(indexLeft).getValueStr());
                            }
                        }
                        globalVars.get(indexRight).setType(t1);
                    } else if (!partyLeft && everyParty && globalLeft) {
                        for (Party p : globalParties) {
                            Type t1 = tc.getCorrectType(globalVars.get(indexLeft), index);

                            if (!(t1 instanceof StringType)) {
                                p.setValue(((float) (p.getValue() + (float) globalVars.get(indexLeft).getValue())));
                            } else {
                                if (p.getValueStr() == null) {
                                    p.setValueStr(globalVars.get(indexLeft).getValueStr());
                                } else {
                                    p.setValueStr(p.getValueStr() + globalVars.get(indexLeft).getValueStr());
                                }
                            }
                            globalVars.get(indexRight).setType(t1);
                        }
                    } else if (!partyLeft && partyRight && !globalLeft) {
                        Type t1 = tc.getCorrectType(vars.get(indexLeft), index);

                        if (!(t1 instanceof StringType)) {
                            globalParties.get(indexRight).setValue(((float) (globalParties.get(indexRight).getValue() + (float) vars.get(indexLeft).getValue())));
                        } else {
                            if (globalParties.get(indexRight).getValueStr() == null) {
                                globalParties.get(indexRight).setValueStr(vars.get(indexLeft).getValueStr());
                            } else {
                                globalParties.get(indexRight).setValueStr(globalParties.get(indexRight).getValueStr() + vars.get(indexLeft).getValueStr());
                            }
                        }
                        vars.get(indexRight).setType(t1);
                    } else if (!partyLeft && !globalLeft && everyParty) {
                        for (Party p : globalParties) {
                            Type t1 = tc.getCorrectType(vars.get(indexLeft), index);

                            if (!(t1 instanceof StringType)) {
                                p.setValue(((float) (p.getValue() + (float) vars.get(indexLeft).getValue())));
                            } else {
                                if (p.getValueStr() == null) {
                                    p.setValueStr(vars.get(indexLeft).getValueStr());
                                } else {
                                    p.setValueStr(p.getValueStr() + vars.get(indexLeft).getValueStr());
                                }
                            }
                            vars.get(indexLeft).setType(t1);
                        }
                    } else if (!globalLeft && !globalRight) {
                        valid = tc.isTypeCorrect(vars.get(indexRight), vars.get(indexLeft), index);

                        if (valid) {
                            Type t1 = tc.getCorrectType(vars.get(indexRight), index);
                            Type t2 = tc.getCorrectType(vars.get(indexLeft), index);

                            if (!(t2 instanceof StringType)) {
                                vars.get(indexRight).setValue((float) (vars.get(indexRight).getValue() + (float) vars.get(indexLeft).getValue()));
                            } else {
                                if (vars.get(indexRight).getValueStr() == null) {
                                    vars.get(indexRight).setValueStr(vars.get(indexLeft).getValueStr());
                                } else {
                                    vars.get(indexRight).setValueStr(vars.get(indexRight).getValueStr() + vars.get(indexLeft).getValueStr());
                                }
                            }
                            vars.get(indexRight).setType(t1);
                            vars.get(indexLeft).setType(t2);
                        }
                    } else if (globalLeft && !globalRight) {
                        valid = tc.isTypeCorrect(vars.get(indexRight), globalVars.get(indexLeft), index);

                        if (valid) {
                            Type t1 = tc.getCorrectType(vars.get(indexRight), index);
                            Type t2 = tc.getCorrectType(globalVars.get(indexLeft), index);

                            if (!(t2 instanceof StringType)) {
                                vars.get(indexRight).setValue((float) (vars.get(indexRight).getValue() + (float) globalVars.get(indexLeft).getValue()));
                            } else {
                                if (vars.get(indexRight).getValueStr() == null) {
                                    vars.get(indexRight).setValueStr(globalVars.get(indexLeft).getValueStr());
                                } else {
                                    vars.get(indexRight).setValueStr(vars.get(indexRight).getValueStr() + globalVars.get(indexLeft).getValueStr());
                                }
                            }
                            vars.get(indexRight).setType(t1);
                            globalVars.get(indexLeft).setType(t2);
                        }
                    } else if (!globalLeft && globalRight) {
                        valid = tc.isTypeCorrect(globalVars.get(indexRight), vars.get(indexLeft), index);

                        if (valid) {
                            Type t1 = tc.getCorrectType(globalVars.get(indexRight), index);
                            Type t2 = tc.getCorrectType(vars.get(indexLeft), index);

                            if (!(t2 instanceof StringType)) {
                                globalVars.get(indexRight).setValue((float) (globalVars.get(indexRight).getValue() + (float) vars.get(indexLeft).getValue()));
                            } else {
                                if (globalVars.get(indexRight).getValueStr() == null) {
                                    globalVars.get(indexRight).setValueStr(vars.get(indexLeft).getValueStr());
                                } else {
                                    globalVars.get(indexRight).setValueStr(globalVars.get(indexRight).getValueStr() + vars.get(indexLeft).getValueStr());
                                }
                            }
                            globalVars.get(indexRight).setType(t2);
                            vars.get(indexLeft).setType(t2);
                        }
                    } else if (globalLeft && globalRight) {
                        valid = tc.isTypeCorrect(globalVars.get(indexRight), globalVars.get(indexLeft), index);

                        if (valid) {
                            Type t1 = tc.getCorrectType(globalVars.get(indexRight), index);
                            Type t2 = tc.getCorrectType(globalVars.get(indexLeft), index);

                            if (!(t2 instanceof StringType)) {
                                globalVars.get(indexRight).setValue((float) (globalVars.get(indexRight).getValue() + (float) globalVars.get(indexLeft).getValue()));
                            } else {
                                if (globalVars.get(indexRight).getValueStr() == null) {
                                    globalVars.get(indexRight).setValueStr(globalVars.get(indexLeft).getValueStr());
                                } else {
                                    globalVars.get(indexRight).setValueStr(globalVars.get(indexRight).getValueStr() + globalVars.get(indexLeft).getValueStr());
                                }
                            }
                            globalVars.get(indexRight).setType(t1);
                            globalVars.get(indexLeft).setType(t2);
                        }
                    }

                    if (!valid) {
                        System.out.println("Typing error.");
                    }
                } else if (leftExpr2 != null) {
                    Entity result = new Entity();

                    if (extLeft2) {
                        if (extLeft) {
                            try {
                                result.setValue((float) Double.parseDouble(leftExpr.getId()) + (float) Double.parseDouble(leftExpr2.getId()));
                            } catch (NumberFormatException e) {
                                result.setValueStr(String.format(leftExpr.getId()) + String.format(leftExpr2.getId()));
                            }
                        } else if (globalLeft) {
                            try {
                                result.setValue(globalVars.get(indexLeft).getValue() + (float) Double.parseDouble(leftExpr2.getId()));
                            } catch (NumberFormatException e) {
                                result.setValueStr(globalVars.get(indexLeft).getValueStr() + String.format(leftExpr2.getId()));
                            }
                        } else {
                            try {
                                result.setValue(vars.get(indexLeft).getValue() + (float) Double.parseDouble(leftExpr2.getId()));
                            } catch (NumberFormatException e) {
                                result.setValueStr(vars.get(indexLeft).getValueStr() + String.format(leftExpr2.getId()));
                            }
                        }
                    } else if (globalLeft2) {
                        if (extLeft) {
                            try {
                                result.setValue((float) Double.parseDouble(leftExpr.getId()) + globalVars.get(indexLeft2).getValue());
                            } catch (NumberFormatException e) {
                                result.setValueStr(String.format(leftExpr.getId()) + globalVars.get(indexLeft2).getValueStr());
                            }
                        } else if (globalLeft) {
                            valid = tc.isTypeCorrect(globalVars.get(indexLeft), globalVars.get(indexLeft2), index);

                            if (valid) {
                                Type t1 = tc.getCorrectType(globalVars.get(indexLeft), index);
                                Type t2 = tc.getCorrectType(globalVars.get(indexLeft2), index);

                                if (!(t1 instanceof StringType)) {
                                    result.setValue((float) (globalVars.get(indexLeft).getValue() + (float) globalVars.get(indexLeft2).getValue()));
                                } else {
                                    result.setValueStr(globalVars.get(indexLeft).getValueStr() + globalVars.get(indexLeft2).getValueStr());
                                }
                                globalVars.get(indexLeft).setType(t1);
                                globalVars.get(indexLeft2).setType(t2);
                            }
                        } else {
                            valid = tc.isTypeCorrect(vars.get(indexLeft), globalVars.get(indexLeft2), index);

                            if (valid) {
                                Type t1 = tc.getCorrectType(vars.get(indexLeft), index);
                                Type t2 = tc.getCorrectType(globalVars.get(indexLeft2), index);

                                if (!(t1 instanceof StringType)) {
                                    result.setValue((float) (vars.get(indexLeft).getValue() + (float) globalVars.get(indexLeft2).getValue()));
                                } else {
                                    result.setValueStr(vars.get(indexLeft).getValueStr() + globalVars.get(indexLeft2).getValueStr());
                                }
                                vars.get(indexLeft).setType(t1);
                                globalVars.get(indexLeft2).setType(t2);
                            }
                        }
                    } else {
                        if (extLeft) {
                            try {
                                result.setValue((float) Double.parseDouble(leftExpr.getId()) + vars.get(indexLeft2).getValue());
                            } catch (NumberFormatException e) {
                                result.setValueStr(String.format(leftExpr.getId()) + vars.get(indexLeft2).getValueStr());
                            }
                        } else if (globalLeft) {
                            valid = tc.isTypeCorrect(globalVars.get(indexLeft), vars.get(indexLeft2), index);

                            if (valid) {
                                Type t1 = tc.getCorrectType(globalVars.get(indexLeft), index);
                                Type t2 = tc.getCorrectType(vars.get(indexLeft2), index);

                                if (!(t1 instanceof StringType)) {
                                    result.setValue((float) (globalVars.get(indexLeft).getValue() + (float) vars.get(indexLeft2).getValue()));
                                } else {
                                    result.setValueStr(globalVars.get(indexLeft).getValueStr() + vars.get(indexLeft2).getValueStr());
                                }
                                globalVars.get(indexLeft).setType(t1);
                                vars.get(indexLeft2).setType(t2);
                            }
                        } else {
                            valid = tc.isTypeCorrect(vars.get(indexLeft), vars.get(indexLeft2), index);

                            if (valid) {
                                Type t1 = tc.getCorrectType(vars.get(indexLeft), index);
                                Type t2 = tc.getCorrectType(vars.get(indexLeft2), index);

                                if (!(t1 instanceof StringType)) {
                                    result.setValue((float) (vars.get(indexLeft).getValue() + (float) vars.get(indexLeft2).getValue()));
                                } else {
                                    result.setValueStr(vars.get(indexLeft).getValueStr() + vars.get(indexLeft2).getValueStr());
                                }
                                vars.get(indexLeft).setType(t1);
                                vars.get(indexLeft2).setType(t2);
                            }
                        }
                    }

                    if (globalRight) {
                        Type t1 = tc.getCorrectType(globalVars.get(indexRight), index);
                        Type t2 = null;

                        if (globalLeft) {
                            t2 = tc.getCorrectType(globalVars.get(indexLeft), index);
                        } else if (!globalLeft && globalLeft2) {
                            t2 = tc.getCorrectType(globalVars.get(indexLeft2), index);
                        } else if (!globalLeft && !globalLeft2 && !extLeft) {
                            t2 = tc.getCorrectType(vars.get(indexLeft), index);
                        } else if (!globalLeft && !globalLeft2 && !extLeft2) {
                            t2 = tc.getCorrectType(vars.get(indexLeft2), index);
                        }

                        if (!(t2 instanceof StringType)) {
                            if ((indexRight == indexLeft && globalLeft) || (globalLeft2 && indexRight == indexLeft2)) {
                                globalVars.get(indexRight).setValue((float) ((float) result.getValue()));
                            } else {
                                globalVars.get(indexRight).setValue((float) (globalVars.get(indexRight).getValue() + (float) result.getValue()));
                            }
                        } else {
                            if ((indexRight == indexLeft && globalLeft) || (globalLeft2 && indexRight == indexLeft2)) {
                                globalVars.get(indexRight).setValueStr(result.getValueStr());
                            } else {
                                globalVars.get(indexRight).setValueStr(globalVars.get(indexRight).getValueStr() + result.getValueStr());
                            }
                        }
                        globalVars.get(indexRight).setType(t2);
                    } else if (!globalRight) {
                        Type t1 = tc.getCorrectType(vars.get(indexRight), index);
                        Type t2 = null;

                        if (globalLeft) {
                            t2 = tc.getCorrectType(globalVars.get(indexLeft), index);
                        } else if (!globalLeft && globalLeft2) {
                            t2 = tc.getCorrectType(globalVars.get(indexLeft2), index);
                        } else if (!globalLeft && !globalLeft2 && !extLeft) {
                            t2 = tc.getCorrectType(vars.get(indexLeft), index);
                        } else if (!globalLeft && !globalLeft2 && !extLeft2) {
                            t2 = tc.getCorrectType(vars.get(indexLeft2), index);
                        }

                        if (!(t2 instanceof StringType)) {
                            if ((indexRight == indexLeft && !globalLeft) || (!globalLeft2 && indexRight == indexLeft2)) {
                                vars.get(indexRight).setValue((float) ((float) result.getValue()));
                            } else {
                                vars.get(indexRight).setValue((float) (vars.get(indexRight).getValue() + (float) result.getValue()));
                            }
                        } else {
                            if ((indexRight == indexLeft && !globalLeft) || (!globalLeft2 && indexRight == indexLeft2)) {
                                vars.get(indexRight).setValueStr(result.getValueStr());
                            } else {
                                vars.get(indexRight).setValueStr(vars.get(indexRight).getValueStr() + result.getValueStr());
                            }
                        }
                        vars.get(indexRight).setType(t2);
                    }
                }
            } else if (s.getOperator().equals("ASSETUP")) {
                AssetEntity leftExpr = (AssetEntity) s.getLeftExpression();
                AssetEntity rightExpr = (AssetEntity) s.getRightExpression();

                int indexLeft = findAsset(leftExpr.getId(), assetEntities);
                int indexRight = findAsset(rightExpr.getId(), assetEntities);
                boolean globalLeft = false;
                boolean globalRight = false;
                boolean partyLeft = false;
                boolean partyRight = false;

                if (indexLeft == -1) {
                    indexLeft = findAsset(leftExpr.getId(), globalAssetEntities);

                    if (indexLeft == -1) {
                        indexLeft = findParty(leftExpr.getId());
                        partyLeft = true;
                    } else {
                        globalLeft = true;
                    }
                }
                if (indexRight == -1) {
                    indexRight = findAsset(rightExpr.getId(), globalAssetEntities);

                    if (indexRight == -1) {
                        indexRight = findParty(rightExpr.getId());
                        partyRight = true;
                    } else {
                        globalRight = true;
                    }
                }

                if (partyLeft && partyRight) {
                    if (s.getFract() != 0) {
                        globalParties.get(indexLeft).moveAsset(globalParties.get(indexRight), ((float) (s.getFract()) * globalParties.get(indexLeft).getValueAsset()));
                    } else {
                        String nameFract = s.getFractExpression();
                        int indexFract = findVar(nameFract, vars);
                        float valFract = vars.get(indexFract).getValue();
                        globalParties.get(indexLeft).moveAsset(globalParties.get(indexRight), ((float) (valFract) * globalParties.get(indexLeft).getValueAsset()));
                    }
                } else if (!partyLeft && partyRight && globalLeft) {
                    if (s.getFract() != 0) {
                        globalAssetEntities.get(indexLeft).withdraw(globalParties.get(indexRight), ((float) s.getFract()) * globalAssetEntities.get(indexLeft).getValue());
                    } else {
                        String nameFract = s.getFractExpression();
                        int indexFract = findVar(nameFract, vars);
                        float valFract = vars.get(indexFract).getValue();
                        globalAssetEntities.get(indexLeft).withdraw(globalParties.get(indexRight), ((float) valFract) * globalAssetEntities.get(indexLeft).getValue());
                    }
                } else if (!partyLeft && partyRight && !globalLeft) {
                    if (s.getFract() != 0) {
                        assetEntities.get(indexLeft).withdraw(globalParties.get(indexRight), ((float) s.getFract()) * assetEntities.get(indexLeft).getValue());
                    } else {
                        String nameFract = s.getFractExpression();
                        int indexFract = findVar(nameFract, vars);
                        float valFract = vars.get(indexFract).getValue();
                        assetEntities.get(indexLeft).withdraw(globalParties.get(indexRight), ((float) valFract) * assetEntities.get(indexLeft).getValue());
                    }
                } else if (!globalLeft && !globalRight) {
                    if (s.getFract() != 0) {
                        assetEntities.get(indexLeft).move(((float) s.getFract()) * assetEntities.get(indexLeft).getValue(), assetEntities.get(indexRight));
                    } else {
                        String nameFract = s.getFractExpression();
                        int indexFract = findVar(nameFract, vars);
                        float valFract = vars.get(indexFract).getValue();
                        assetEntities.get(indexLeft).move(((float) valFract) * assetEntities.get(indexLeft).getValue(), assetEntities.get(indexRight));
                    }
                } else if (globalLeft && !globalRight) {
                    if (s.getFract() != 0) {
                        globalAssetEntities.get(indexLeft).move(((float) s.getFract()) * globalAssetEntities.get(indexLeft).getValue(), assetEntities.get(indexRight));
                    } else {
                        String nameFract = s.getFractExpression();
                        int indexFract = findVar(nameFract, vars);
                        float valFract = vars.get(indexFract).getValue();
                        globalAssetEntities.get(indexLeft).move(((float) valFract) * globalAssetEntities.get(indexLeft).getValue(), assetEntities.get(indexRight));
                    }
                } else if (!globalLeft && globalRight) {
                    System.out.println(globalAssetEntities.get(indexRight).getId() + " = " + globalAssetEntities.get(indexRight).getValue());
                    System.out.println(assetEntities.get(indexLeft).getId() + " = " + assetEntities.get(indexLeft).getValue());

                    if (s.getFract() != 0) {
                        assetEntities.get(indexLeft).move(((float) s.getFract()) * assetEntities.get(indexLeft).getValue(), globalAssetEntities.get(indexRight));
                        //assets.get(indexLeft).setValue((float) (((float)(-s.getFract())))*assets.get(indexLeft).getValue());
                    } else {
                        String nameFract = s.getFractExpression();
                        int indexFract = findVar(nameFract, vars);
                        float valFract = vars.get(indexFract).getValue();
                        assetEntities.get(indexLeft).move(((float) valFract) * assetEntities.get(indexLeft).getValue(), globalAssetEntities.get(indexRight));
                        //assets.get(indexLeft).setValue(((float)(-valFract))*assets.get(indexLeft).getValue());
                    }
                } else if (globalLeft && globalRight) {
                    if (s.getFract() != 0) {
                        globalAssetEntities.get(indexLeft).move(((float) s.getFract()) * globalAssetEntities.get(indexLeft).getValue(), globalAssetEntities.get(indexRight));
                        //globalAssets.get(indexLeft).setValue((float)((-s.getFract()))*globalAssets.get(indexLeft).getValue());
                    } else {
                        String nameFract = s.getFractExpression();
                        int indexFract = findVar(nameFract, vars);
                        float valFract = vars.get(indexFract).getValue();
                        globalAssetEntities.get(indexLeft).move(((float) valFract) * globalAssetEntities.get(indexLeft).getValue(), globalAssetEntities.get(indexRight));
                        //globalAssets.get(indexLeft).setValue(((float)(-valFract))*globalAssets.get(indexLeft).getValue());
                    }
                }
            }
        }
        return valid;
    }
}
