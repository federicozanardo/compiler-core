package compiler.ast;

import lcp.lib.datastructures.Pair;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class TypeInference {
    private final Map<Pair<String, Integer>, Type> types;
    private final ArrayList<String> contractNames;
    private final ArrayList<Pair<String, ArrayList<Pair<String, Type>>>> functionParameters;

    public TypeInference(
            Map<Pair<String, Integer>, Type> types,
            ArrayList<String> contractNames,
            ArrayList<Pair<String, ArrayList<Pair<String, Type>>>> functionParameters
    ) {
        this.types = types;
        this.contractNames = contractNames;
        this.functionParameters = functionParameters;
    }

    public Map<Pair<String, Integer>, Type> getTypes() {
        return types;
    }

    public Map<Pair<String, Integer>, Type> getGlobalVariables() {
        Map<Pair<String, Integer>, Type> globalVariables = new LinkedHashMap<Pair<String, Integer>, Type>();

        for (Pair<String, Integer> type : types.keySet()) {
            if (type.getSecond() == 0 && types.get(type) instanceof AssetType) {
                globalVariables.put(type, types.get(type));
            } else if (type.getSecond() == 0 && !(types.get(type) instanceof AssetType)) {
                globalVariables.put(type, types.get(type));
            }
        }

        return globalVariables;
    }

    public void printMap() {
        Map<Pair<String, Integer>, Type> assets = new LinkedHashMap<Pair<String, Integer>, Type>();
        Map<Pair<String, Integer>, Type> fields = new LinkedHashMap<Pair<String, Integer>, Type>();
        Map<Pair<String, Integer>, Type> functions = new LinkedHashMap<Pair<String, Integer>, Type>();

        for (Pair<String, Integer> type : types.keySet()) {
            if (type.getSecond() == 0 && types.get(type) instanceof AssetType) {
                assets.put(type, types.get(type));
            } else if (type.getSecond() == 0 && !(types.get(type) instanceof AssetType)) {
                fields.put(type, types.get(type));
            } else {
                for (int i = 0; i < functionParameters.size(); i++) {
                    if (i == type.getSecond() - 1) {
                        for (Pair<String, Type> element : functionParameters.get(i).getSecond()) {
                            if (element.getFirst().equals(type.getFirst())) {
                                functions.put(type, types.get(type));
                            }
                        }
                    }
                }
            }
        }

        if (assets.size() > 0) {
            System.out.println("Assets:");
            printGlobal(assets);
        }

        if (fields.size() > 0) {
            System.out.println("Fields:");
            printGlobal(fields);
        }

        System.out.println("Functions:");
        printFunctions(functions);
    }

    public void printGlobal(Map<Pair<String, Integer>, Type> map) {
        for (Pair<String, Integer> pair : map.keySet()) {
            System.out.println("\t" + pair.getFirst() + " type: " + map.get(pair).getTypeName());
        }
    }

    public Map<String, ArrayList<String>> getFunctionTypes() {
        Map<Pair<String, Integer>, Type> assets = new LinkedHashMap<Pair<String, Integer>, Type>();
        Map<Pair<String, Integer>, Type> fields = new LinkedHashMap<Pair<String, Integer>, Type>();
        Map<Pair<String, Integer>, Type> functions = new LinkedHashMap<Pair<String, Integer>, Type>();
        for (Pair<String, Integer> type : types.keySet()) {
            if (type.getSecond() == 0 && types.get(type) instanceof AssetType) {
                assets.put(type, types.get(type));
            } else if (type.getSecond() == 0 && !(types.get(type) instanceof AssetType)) {
                fields.put(type, types.get(type));
            } else {
                for (int i = 0; i < functionParameters.size(); i++) {
                    if (i == type.getSecond() - 1) {
                        for (Pair<String, Type> element : functionParameters.get(i).getSecond()) {
                            if (element.getFirst().equals(type.getFirst())) {
                                functions.put(type, types.get(type));
                            }
                        }
                    }
                }
            }
        }

        Map<String, ArrayList<String>> functionTypes = new LinkedHashMap<>();
        for (int i = 0; i < contractNames.size(); i++) {
            ArrayList<String> types = new ArrayList<>();

            for (Pair<String, Integer> pair : functions.keySet()) {
                if (pair.getSecond() - 1 == i) {
                    types.add(functions.get(pair).getTypeName());
                }
            }
            functionTypes.put(contractNames.get(i), types);
        }
        return functionTypes;
    }

    public void printFunctions(Map<Pair<String, Integer>, Type> map) {
        Map<Pair<String, Integer>, Type> types;
        Map<Pair<String, Integer>, Type> assetTypes;

        for (int i = 0; i < contractNames.size(); i++) {
            types = new LinkedHashMap<>();
            assetTypes = new LinkedHashMap<>();

            for (Pair<String, Integer> pair : map.keySet()) {
                if (pair.getSecond() - 1 == i) {
                    if (map.get(pair) instanceof AssetType) {
                        assetTypes.put(pair, map.get(pair));
                    } else {
                        types.put(pair, map.get(pair));
                    }
                }
            }
            printFunction(types, assetTypes, i);
        }
    }

    public void printFunction(Map<Pair<String, Integer>, Type> types, Map<Pair<String, Integer>, Type> assetTypes, int index) {
        String string = "\t" + contractNames.get(index) + "(";
        for (Pair<String, Integer> pair : types.keySet()) {
            string += types.get(pair).getTypeName() + ",";
        }

        if (types.size() > 0) {
            string = string.substring(0, string.length() - 1) + ")";
        } else {
            string += ")";
        }

        string = string + "[";
        for (Pair<String, Integer> pair : assetTypes.keySet()) {
            string += assetTypes.get(pair).getTypeName() + ",";
        }

        if (assetTypes.size() > 0) {
            string = string.substring(0, string.length() - 1) + "]";
        } else {
            string += "]";
        }

        System.out.println(string);
    }

    public Type getCorrectType(Field v1, int index) {
        Type toRet = null;
        for (Pair<String, Integer> pair : types.keySet()) {
            if (pair.getFirst().equals(v1.getId()) && (index == pair.getSecond() || pair.getSecond() == 0)) {
                toRet = types.get(pair);
            }
        }
        return toRet;
    }

    public boolean isTypeCorrect(Field v1, Field v2, int index) {
        boolean correct = true;
        Type t1 = null;
        Type t2 = null;

        for (Pair<String, Integer> pair : types.keySet()) {
            if (pair.getFirst().equals(v1.getId()) && (index == pair.getSecond() || pair.getSecond() == 0)) {
                t1 = types.get(pair);
            } else if (pair.getFirst().equals(v2.getId()) && (index == pair.getSecond() || pair.getSecond() == 0)) {
                t2 = types.get(pair);
            }
        }

        if (t1 instanceof GeneralType) {
            if (!(t2 instanceof GeneralType)) {
                t1 = t2;
            }
        } else if (t2 instanceof GeneralType) {
            if (!(t1 instanceof GeneralType)) {
                t2 = t1;
            }
        } else if (!(t2 instanceof GeneralType) && !(t1 instanceof GeneralType) && !t1.getTypeName().equals(t2.getTypeName())) {
            if ((t1 instanceof AssetType && t2 instanceof RealType) || (t2 instanceof AssetType && t1 instanceof RealType)) {
                correct = true;
            } else {
                correct = false;
            }
        }

        return correct;
    }
}
