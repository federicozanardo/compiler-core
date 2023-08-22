package compiler.module.ast;

import compiler.module.parser.StipulaBaseVisitor;
import compiler.module.parser.StipulaParser;
import lcp.lib.datastructures.Pair;
import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class TypeChecking extends StipulaBaseVisitor<Object> {
    private int numberOfTypes = 0;
    private Map<Pair<String, Integer>, Type> types = null;
    private int numberOfScope = 0;
    @Getter
    private ArrayList<String> contractNames = null;
    private ArrayList<String> parties = null;
    @Getter
    private final ArrayList<Pair<String, ArrayList<Pair<String, Type>>>> functionParameters = new ArrayList<Pair<String, ArrayList<Pair<String, Type>>>>();

    public void printMap() {
        System.out.println("printMap: types => " + types);
        for (Pair<String, Integer> type : types.keySet()) {
            System.out.print("var: " + type.getFirst() + " type: " + types.get(type).getTypeName() + " ");

            if (type.getSecond() > 0) {
                int toPrint = type.getSecond() - 1;
                System.out.println("function#" + toPrint + " " + contractNames.get(toPrint));

            }
        }
    }

    public void printMap(Map<Pair<String, Integer>, Type> map) {
        for (Pair<String, Integer> s : map.keySet()) {
            System.out.println("var: " + s.getFirst() + " type: " + map.get(s).getTypeName() + " scope: " + s.getSecond());
        }
    }

    public Map<Pair<String, Integer>, Type> setType(Pair<String, Integer> pair, Type type, Map<Pair<String, Integer>, Type> map) {
        for (Pair<String, Integer> s : map.keySet()) {
            if (s.equals(pair)) {
                map.put(pair, type);
            }
        }
        return map;
    }

    public void addElementsMap(Map<Pair<String, Integer>, Type> toRet) {
        for (Pair<String, Integer> s : toRet.keySet()) {
            if (!isPresent(s, types)) {
                types.put(new Pair<String, Integer>(s.getFirst(), s.getSecond()), toRet.get(s));
            } else {
                Type tmpType = getType(s, types);

                if (tmpType == null || (!(tmpType instanceof RealType) && !(tmpType instanceof BooleanType) && !(tmpType instanceof AssetType) && !(tmpType instanceof TimeType))) {
                    for (Entry<Pair<String, Integer>, Type> entry : types.entrySet()) {
                        if (entry.getKey().getFirst().equals(s.getFirst()) && (entry.getKey().getSecond().equals(s.getSecond()) || entry.getKey().getSecond() == 0)) {
                            entry.setValue(toRet.get(s));
                        }
                    }
                }
            }
        }
    }

    private boolean isPresent(Pair<String, Integer> pair, Map<Pair<String, Integer>, Type> type) {
        boolean present = false;
        for (Pair<String, Integer> s : type.keySet()) {
            if (s.getFirst().equals(pair.getFirst()) && (s.getSecond() == 0 || s.getSecond() == pair.getSecond())) {
                present = true;
            }
        }
        return present;
    }

    private Type getType(Pair<String, Integer> pair, Map<Pair<String, Integer>, Type> type) {
        Type toRet = null;
        for (Pair<String, Integer> s : type.keySet()) {
            if (s.getFirst().equals(pair.getFirst()) && (s.getSecond() == 0 || s.getSecond() == pair.getSecond())) {
                toRet = type.get(s);
            }
        }
        return toRet;
    }

    public Map<Pair<String, Integer>, Type> visitProg(StipulaParser.ProgContext context) {
        types = new LinkedHashMap<Pair<String, Integer>, Type>();
        parties = visitAgreement(context.agreement());

        if (context.assetdecl() != null) {
            Map<Pair<String, Integer>, Type> tmpAssets;
            tmpAssets = visitAssetdecl(context.assetdecl());

            for (Pair<String, Integer> el : tmpAssets.keySet()) {
                types.put(el, tmpAssets.get(el));
            }
        }

        if (context.fielddecl() != null) {
            Map<Pair<String, Integer>, Type> tmpFields;
            tmpFields = visitFielddecl(context.fielddecl());

            for (Pair<String, Integer> el : tmpFields.keySet()) {
                types.put(el, tmpFields.get(el));
            }
        }

        for (StipulaParser.FunContext function : context.fun()) {
            Map<Pair<String, Integer>, Type> tmp = visitFun(function);

            for (Pair<String, Integer> s : tmp.keySet()) {
                if (!isPresent(s, types)) {
                    types.put(new Pair<String, Integer>(s.getFirst(), s.getSecond()), tmp.get(s));
                } else {
                    Type tmpType = getType(s, types);

                    if (tmpType == null || (!(tmpType instanceof RealType) && !(tmpType instanceof BooleanType) && !(tmpType instanceof AssetType) && !(tmpType instanceof TimeType))) {
                        for (Entry<Pair<String, Integer>, Type> entry : types.entrySet()) {
                            if (entry.getKey().getFirst().equals(s.getFirst()) && (entry.getKey().getSecond().equals(s.getSecond()) || entry.getKey().getSecond() == 0)) {
                                entry.setValue(tmp.get(s));
                            }
                        }
                    }
                }
            }

            if (contractNames == null) {
                contractNames = new ArrayList<>();
            }

            contractNames.add(function.ID().getText());
        }
        return types;
    }

    public Map<Pair<String, Integer>, Type> visitAssetdecl(StipulaParser.AssetdeclContext context) {
        Map<Pair<String, Integer>, Type> retAssets = new LinkedHashMap<Pair<String, Integer>, Type>();
        for (int i = 0; i < context.idAsset.size(); i++) {
            retAssets.put(
                    new Pair<String, Integer>(context.idAsset.get(i).getText(), numberOfScope),
                    new AssetType(context.assetId.get(i).getText())
            );
        }
        return retAssets;
    }

    public Map<Pair<String, Integer>, Type> visitFielddecl(StipulaParser.FielddeclContext context) {
        Map<Pair<String, Integer>, Type> retAssets = new LinkedHashMap<>();
        for (int i = 0; i < context.idField.size(); i++) {
            retAssets.put(new Pair<>(context.idField.get(i).getText(), numberOfScope), new GeneralType(numberOfTypes));
            numberOfTypes++;
        }
        return retAssets;
    }

    public ArrayList<String> visitAgreement(StipulaParser.AgreementContext context) {
        ArrayList<String> toRet = new ArrayList<String>();
        for (StipulaParser.PartyContext d : context.party()) {
            toRet.add(d.ID().getText());
        }
        return toRet;
    }

    public Map<Pair<String, Integer>, Type> visitFun(StipulaParser.FunContext context) {
        Map<Pair<String, Integer>, Type> toRet = new LinkedHashMap<Pair<String, Integer>, Type>();
        ArrayList<Pair<String, Type>> tmpFuns = new ArrayList<Pair<String, Type>>();
        numberOfScope++;

        if (context.vardec() != null) {
            for (StipulaParser.VardecContext n : context.vardec()) {
                toRet.put(new Pair<String, Integer>(n.ID().getText(), numberOfScope), new GeneralType(numberOfTypes));
                numberOfTypes++;
                tmpFuns.add(new Pair<String, Type>(n.ID().getText(), new GeneralType(numberOfTypes)));
            }
        }

        if (context.assetdec() != null) {
            for (StipulaParser.AssetdecContext n : context.assetdec()) {
                toRet.put(new Pair<String, Integer>(n.ID().getText(), numberOfScope), new AssetType());
                tmpFuns.add(new Pair<String, Type>(n.ID().getText(), new AssetType()));
            }
        }
        functionParameters.add(new Pair<String, ArrayList<Pair<String, Type>>>(context.ID().getText(), tmpFuns));

        addElementsMap(toRet);

        if (context.prec() != null) {
            Map<Pair<String, Integer>, Type> tmp = visitPrec(context.prec());
            for (Pair<String, Integer> s : tmp.keySet()) {

                if (!isPresent(s, toRet)) {
                    toRet.put(s, tmp.get(s));
                } else {
                    Type tmpType = getType(s, toRet);

                    if (tmpType == null || (!(tmpType instanceof RealType) && !(tmpType instanceof BooleanType) && !(tmpType instanceof AssetType) && !(tmpType instanceof TimeType))) {
                        for (Entry<Pair<String, Integer>, Type> entry : toRet.entrySet()) {
                            if (entry.getKey().getFirst().equals(s.getFirst())) {
                                entry.setValue(tmp.get(s));
                            }
                        }
                    }
                }
            }
        }
        addElementsMap(toRet);

        if (context.stat() != null) {
            for (StipulaParser.StatContext sc : context.stat()) {
                Map<Pair<String, Integer>, Type> tmp = visitStat(sc);

                for (Pair<String, Integer> s : tmp.keySet()) {
                    if (!isPresent(s, toRet)) {
                        toRet.put(s, tmp.get(s));
                    } else {
                        Type tmpType = getType(s, toRet);
                        if (tmpType == null || (!(tmpType instanceof RealType) && !(tmpType instanceof BooleanType) && !(tmpType instanceof AssetType) && !(tmpType instanceof TimeType))) {
                            for (Entry<Pair<String, Integer>, Type> entry : toRet.entrySet()) {
                                if (entry.getKey().getFirst().equals(s.getFirst())) {
                                    entry.setValue(tmp.get(s));
                                }
                            }
                        }
                    }
                }
            }
        }
        addElementsMap(toRet);

        if (context.events() != null) {
            for (StipulaParser.EventsContext sc : context.events()) {
                if (sc.EMPTY() == null) {
                    Map<Pair<String, Integer>, Type> tmp = visitEvents(sc);
                    for (Pair<String, Integer> s : tmp.keySet()) {
                        if (!isPresent(s, toRet)) {
                            toRet.put(s, tmp.get(s));
                        } else {
                            Pair<String, Integer> tmpPair = new Pair<String, Integer>(s.getFirst(), 0);
                            toRet.put(tmpPair, tmp.get(s));
                        }
                    }
                }
            }
        }

        return toRet;
    }

    public Map<Pair<String, Integer>, Type> visitStat(StipulaParser.StatContext context) {
        Map<Pair<String, Integer>, Type> toRet = new LinkedHashMap<Pair<String, Integer>, Type>();

        if (context.ASSETUP() != null) {
            Map<Pair<String, Integer>, Type> tmp = visitValue(context.left);
            for (Pair<String, Integer> s : tmp.keySet()) {
                if (!parties.contains(s.getFirst())) {
                    toRet.put(s, new GeneralType(numberOfTypes));
                    numberOfTypes++;
                }
            }

            Pair<String, Integer> rightVal = new Pair<String, Integer>(context.right.getText(), numberOfScope);
            //Map<Pair<String,Integer>,Type> tmp1 = visitValue(ctx.right);
            //for(Pair<String,Integer> s : tmp1.keySet()) {

            if (!parties.contains(rightVal.getFirst())) {
                toRet.put(rightVal, new GeneralType(numberOfTypes));
                numberOfTypes++;
            }
            //}

            if (context.COMMA() != null) {
                Pair<String, Integer> rightValPlus = new Pair<String, Integer>(context.right.getText(), numberOfScope);
                if (!parties.contains(rightValPlus.getFirst())) {
                    toRet.put(rightValPlus, new GeneralType(numberOfTypes));
                    numberOfTypes++;
                }
            }
        } else if (context.FIELDUP() != null) {
            Map<Pair<String, Integer>, Type> tmp = visitValue(context.left);
            Type typeLeft = null;

            for (Pair<String, Integer> s : tmp.keySet()) {
                if (tmp.get(s) instanceof RealType) {
                    typeLeft = new RealType();
                } else if (tmp.get(s) instanceof BooleanType) {
                    typeLeft = new BooleanType();
                }
            }

            if (typeLeft == null) {
                for (Pair<String, Integer> s : tmp.keySet()) {
                    if (getType(s, types) instanceof RealType) {
                        typeLeft = new RealType();
                    } else if (getType(s, types) instanceof BooleanType) {
                        typeLeft = new BooleanType();
                    }
                }
            }

            for (Pair<String, Integer> s : tmp.keySet()) {
                if (typeLeft != null) {
                    toRet.put(s, typeLeft);
                } else {
                    toRet.put(s, tmp.get(s));
                }
            }

            Pair<String, Integer> rightVal = new Pair<String, Integer>(context.right.getText(), numberOfScope);

            Type typeRight = null;

            if (getType(rightVal, types) instanceof RealType) {
                typeRight = new RealType();
            } else if (getType(rightVal, types) instanceof BooleanType) {
                typeRight = new BooleanType();
            }

            if (typeLeft != null && typeRight != null && !typeLeft.equals(typeRight)) {
                System.out.println("Expressions not of the same type (" + typeLeft.getTypeName() + " and " + typeRight.getTypeName() + ")");
                System.exit(0);
            }

            if (typeLeft != null) {
                toRet.put(rightVal, typeLeft);
            } else {
                toRet.put(rightVal, new GeneralType(numberOfTypes));
                numberOfTypes++;
            }

            if (context.COMMA() != null) {
                Pair<String, Integer> rightValPlus = new Pair<String, Integer>(context.rightPlus.getText(), numberOfScope);

                if (typeLeft != null) {
                    toRet.put(rightValPlus, typeLeft);
                } else {
                    if (!isPresent(rightValPlus, toRet)) {
                        toRet.put(rightVal, new GeneralType(numberOfTypes));
                        numberOfTypes++;
                    } else {
                        toRet.put(rightVal, new GeneralType(numberOfTypes));
                        numberOfTypes++;
                    }
                }
            }
        }

        return toRet;
    }

    public Map<Pair<String, Integer>, Type> visitPrec(StipulaParser.PrecContext ctcontext) {
        Map<Pair<String, Integer>, Type> toRet = new LinkedHashMap<Pair<String, Integer>, Type>();
        toRet = visitExpr(ctcontext.expr());
        return toRet;
    }

    public Map<Pair<String, Integer>, Type> visitExpr(StipulaParser.ExprContext context) {
        Map<Pair<String, Integer>, Type> toRet = new LinkedHashMap<Pair<String, Integer>, Type>();
        Type typeRight = null;

        if (context.right != null) {
            Map<Pair<String, Integer>, Type> tmp = visitExpr(context.right);

            for (Pair<String, Integer> s : tmp.keySet()) {
                if (s.getFirst().equals(context.right.getText()) && s.getSecond() == numberOfScope) {
                    if (!(tmp.get(s) instanceof GeneralType)) {
                        typeRight = tmp.get(s);
                    }
                }
            }
        }

        Type typeLeft = null;
        Map<Pair<String, Integer>, Type> tmp = visitTerm(context.left);

        for (Pair<String, Integer> s : tmp.keySet()) {
            if (!(tmp.get(s) instanceof GeneralType)) {
                typeLeft = tmp.get(s);
            }
        }

        if (typeRight != null && !(typeRight instanceof AssetType)) {
            for (Pair<String, Integer> s : tmp.keySet()) {
                toRet.put(s, typeRight);
            }

            tmp = visitExpr(context.right);
            for (Pair<String, Integer> s : tmp.keySet()) {
                toRet.put(s, typeRight);
            }
        } else if (typeRight != null && (typeRight instanceof AssetType)) {
            for (Pair<String, Integer> s : tmp.keySet()) {
                if (tmp.get(s) instanceof AssetType) {
                    toRet.put(s, typeRight);
                } else {
                    toRet.put(s, new RealType());
                }
            }

            tmp = visitExpr(context.right);
            for (Pair<String, Integer> s : tmp.keySet()) {
                if (tmp.get(s) instanceof AssetType) {
                    toRet.put(s, typeRight);
                } else {
                    toRet.put(s, new RealType());
                }
            }
        } else if (typeLeft != null && !(typeLeft instanceof AssetType)) {
            for (Pair<String, Integer> s : tmp.keySet()) {
                toRet.put(s, typeLeft);
            }

            if (context.right != null) {
                tmp = visitExpr(context.right);

                for (Pair<String, Integer> s : tmp.keySet()) {
                    toRet.put(s, typeLeft);
                }
            }
        } else if (typeLeft != null && (typeLeft instanceof AssetType)) {
            for (Pair<String, Integer> s : tmp.keySet()) {
                if (tmp.get(s) instanceof AssetType) {
                    toRet.put(s, typeLeft);
                } else {
                    toRet.put(s, new RealType());
                }
            }

            if (context.right != null) {
                tmp = visitExpr(context.right);

                for (Pair<String, Integer> s : tmp.keySet()) {
                    if (tmp.get(s) instanceof AssetType) {
                        toRet.put(s, typeLeft);
                    } else {
                        toRet.put(s, new RealType());
                    }
                }
            }
        } else {
            for (Pair<String, Integer> s : tmp.keySet()) {
                toRet.put(s, tmp.get(s));
            }

            if (context.right != null) {
                tmp = visitExpr(context.right);
                for (Pair<String, Integer> s : tmp.keySet()) {
                    toRet.put(s, tmp.get(s));
                }
            }
        }

        return toRet;
    }

    public Map<Pair<String, Integer>, Type> visitTerm(StipulaParser.TermContext context) {
        Map<Pair<String, Integer>, Type> toRet = new LinkedHashMap<Pair<String, Integer>, Type>();
        Type typeRight = null;

        if (context.right != null) {
            Map<Pair<String, Integer>, Type> tmp = visitTerm(context.right);
            for (Pair<String, Integer> s : tmp.keySet()) {
                if (s.getFirst().equals(context.right.getText()) && s.getSecond() == numberOfScope) {
                    if (!(tmp.get(s) instanceof GeneralType)) {
                        typeRight = tmp.get(s);
                    }
                }
            }
        }

        Type typeLeft = null;
        Map<Pair<String, Integer>, Type> tmp = visitFactor(context.left);
        for (Pair<String, Integer> s : tmp.keySet()) {
            if (!(tmp.get(s) instanceof GeneralType)) {
                typeLeft = tmp.get(s);
            }
        }

        if (typeRight != null && !(typeRight instanceof AssetType)) {
            for (Pair<String, Integer> s : tmp.keySet()) {
                toRet.put(s, typeRight);
            }

            tmp = visitTerm(context.right);
            for (Pair<String, Integer> s : tmp.keySet()) {
                toRet.put(s, typeRight);
            }
        } else if (typeRight != null && (typeRight instanceof AssetType)) {
            for (Pair<String, Integer> s : tmp.keySet()) {
                if (tmp.get(s) instanceof AssetType) {
                    toRet.put(s, typeRight);
                } else {
                    toRet.put(s, new RealType());
                }
            }

            tmp = visitTerm(context.right);
            for (Pair<String, Integer> s : tmp.keySet()) {
                if (tmp.get(s) instanceof AssetType) {
                    toRet.put(s, typeRight);
                } else {
                    toRet.put(s, new RealType());
                }
            }
        } else if (typeLeft != null && !(typeLeft instanceof AssetType)) {
            for (Pair<String, Integer> s : tmp.keySet()) {
                toRet.put(s, typeLeft);
            }

            if (context.right != null) {
                tmp = visitTerm(context.right);
                for (Pair<String, Integer> s : tmp.keySet()) {
                    toRet.put(s, typeLeft);
                }
            }
        } else if (typeLeft != null && (typeLeft instanceof AssetType)) {
            for (Pair<String, Integer> s : tmp.keySet()) {
                if (tmp.get(s) instanceof AssetType) {
                    toRet.put(s, typeLeft);
                } else {
                    toRet.put(s, new RealType());
                }
            }

            if (context.right != null) {
                tmp = visitTerm(context.right);

                for (Pair<String, Integer> s : tmp.keySet()) {
                    if (tmp.get(s) instanceof AssetType) {
                        toRet.put(s, typeLeft);
                    } else {
                        toRet.put(s, new RealType());
                    }
                }
            }
        } else {
            for (Pair<String, Integer> s : tmp.keySet()) {
                toRet.put(s, tmp.get(s));
            }

            if (context.right != null) {
                tmp = visitTerm(context.right);
                for (Pair<String, Integer> s : tmp.keySet()) {
                    toRet.put(s, tmp.get(s));
                }
            }
        }

        return toRet;
    }

    public Map<Pair<String, Integer>, Type> visitFactor(StipulaParser.FactorContext context) {
        Map<Pair<String, Integer>, Type> toRet = new LinkedHashMap<Pair<String, Integer>, Type>();
        Type typeRight = null;

        if (context.right != null) {
            Map<Pair<String, Integer>, Type> tmp = visitValue(context.right);

            for (Pair<String, Integer> s : tmp.keySet()) {
                if (s.getFirst().equals(context.right.getText()) && s.getSecond() == numberOfScope) {
                    if (!(tmp.get(s) instanceof GeneralType)) {
                        typeRight = tmp.get(s);
                    }
                }
            }
        }

        Type typeLeft = null;
        Map<Pair<String, Integer>, Type> tmp = visitValue(context.left);

        for (Pair<String, Integer> s : tmp.keySet()) {
            if (!(tmp.get(s) instanceof GeneralType)) {
                typeLeft = tmp.get(s);
            }
        }

        if (typeRight != null && !(typeRight instanceof AssetType)) {
            for (Pair<String, Integer> s : tmp.keySet()) {
                toRet.put(s, typeRight);
            }

            tmp = visitValue(context.right);
            for (Pair<String, Integer> s : tmp.keySet()) {
                toRet.put(s, typeRight);
            }
        } else if (typeRight != null && (typeRight instanceof AssetType)) {
            for (Pair<String, Integer> s : tmp.keySet()) {
                if (tmp.get(s) instanceof AssetType) {
                    toRet.put(s, typeRight);
                } else {
                    toRet.put(s, new RealType());
                }
            }

            tmp = visitValue(context.right);
            for (Pair<String, Integer> s : tmp.keySet()) {
                if (tmp.get(s) instanceof AssetType) {
                    toRet.put(s, typeRight);
                } else {
                    toRet.put(s, new RealType());
                }
            }
        } else if (typeLeft != null && !(typeLeft instanceof AssetType)) {
            for (Pair<String, Integer> s : tmp.keySet()) {
                toRet.put(s, typeLeft);
            }

            if (context.right != null) {
                tmp = visitValue(context.right);
                for (Pair<String, Integer> s : tmp.keySet()) {
                    toRet.put(s, typeLeft);
                }
            }
        } else if (typeLeft != null && (typeLeft instanceof AssetType)) {
            for (Pair<String, Integer> s : tmp.keySet()) {
                if (tmp.get(s) instanceof AssetType) {
                    toRet.put(s, typeLeft);
                } else {
                    toRet.put(s, new RealType());
                }
            }

            if (context.right != null) {
                tmp = visitValue(context.right);

                for (Pair<String, Integer> s : tmp.keySet()) {
                    if (tmp.get(s) instanceof AssetType) {
                        toRet.put(s, typeLeft);
                    } else {
                        toRet.put(s, new RealType());
                    }
                }
            }
        } else {
            for (Pair<String, Integer> s : tmp.keySet()) {
                toRet.put(s, tmp.get(s));
            }

            if (context.right != null) {
                tmp = visitValue(context.right);

                for (Pair<String, Integer> s : tmp.keySet()) {
                    toRet.put(s, tmp.get(s));
                }
            }
        }

        return toRet;
    }

    public Map<Pair<String, Integer>, Type> visitEvents(StipulaParser.EventsContext context) {
        Map<Pair<String, Integer>, Type> toRet = new LinkedHashMap<Pair<String, Integer>, Type>();
        Map<Pair<String, Integer>, Type> tmp = visitExpr(context.expr());

        for (Pair<String, Integer> s : tmp.keySet()) {
            toRet.put(s, new TimeType());
        }

        return toRet;
    }

    public Map<Pair<String, Integer>, Type> visitValue(StipulaParser.ValueContext context) {
        Map<Pair<String, Integer>, Type> toRet = new LinkedHashMap<Pair<String, Integer>, Type>();

        if (context.expr() != null) {
            Map<Pair<String, Integer>, Type> tmp = visitExpr(context.expr());

            for (Pair<String, Integer> s : tmp.keySet()) {
                toRet.put(s, tmp.get(s));
            }
        } else if (context.RAWSTRING() != null) {
            toRet.put(new Pair<String, Integer>(context.RAWSTRING().getText(), numberOfScope), new StringType());
        } else if (context.ID() != null) {
            boolean flag = false;

            for (Pair<String, Integer> pair : types.keySet()) {
                if (pair.getFirst().equals(context.ID().getText()) && pair.getSecond() == numberOfScope) {
                    toRet.put(new Pair<String, Integer>(context.ID().getText(), numberOfScope), types.get(pair));
                    flag = true;
                }
            }

            if (!flag) {
                toRet.put(new Pair<String, Integer>(context.ID().getText(), numberOfScope), new GeneralType(numberOfTypes));
                numberOfTypes++;
            }
        } else if (context.number() != null) {
            toRet.put(new Pair<String, Integer>(context.number().getText(), numberOfScope), new RealType());
            numberOfTypes++;
        } else if (context.TRUE() != null) {
            toRet.put(new Pair<String, Integer>(context.TRUE().getText(), numberOfScope), new BooleanType());
        } else if (context.FALSE() != null) {
            toRet.put(new Pair<String, Integer>(context.FALSE().getText(), numberOfScope), new BooleanType());
        } else if (context.NOW() != null) {
            toRet.put(new Pair<String, Integer>(context.NOW().getText(), numberOfScope), new TimeType());
        }
        return toRet;
    }
}
