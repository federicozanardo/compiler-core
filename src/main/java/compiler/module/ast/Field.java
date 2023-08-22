package compiler.module.ast;

import lombok.Getter;

public class Field extends Entity {
    private final String name;
    @Getter
    private float value = 0;
    @Getter
    private String valueStr = null;
    private boolean valueBool;
    @Getter
    private Type type = null;

    public Field() {
        name = "";
        value = 0;
    }

    public Field(String n) {
        name = n;
        value = 0;
    }

    public Field(String n, float v) {
        name = n;
        value = v;
    }

    public void setValue(float val) {
        value = val;
    }

    public void setType(Type t) {
        type = t;
    }

    public void setValueStr(String s) {
        valueStr = s;
    }

    public String getId() {
        return name;
    }

    public void printField() {
        if (type != null && (type instanceof StringType)) {
            System.out.println(type.getTypeName() + " " + name + ": " + valueStr);
        } else if (type instanceof TimeType && valueStr != null) {
            System.out.println(type.getTypeName() + " " + name + ": " + valueStr);
        } else if (type != null && type instanceof BooleanType) {
            System.out.println(type.getTypeName() + " " + name + ": " + valueBool);
        } else if (value != 0) {
            System.out.println(type.getTypeName() + " " + name + ": " + value);
        } else if (type != null) {
            System.out.println(type.getTypeName() + " " + name);
        } else {
            System.out.println(name);
        }
    }
}
