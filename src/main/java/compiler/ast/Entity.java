package compiler.ast;

class Entity {
    public final String name;
    private float value = 0;
    private String valueStr = "";

    public Entity() {
        name = "";
    }

    public Entity(String n) {
        name = n;
    }

    public Entity(String n, int v) {
        name = n;
        value = v;
    }

    public void setValue(float val) {
        value = val;
    }

    public void setValueStr(String str) {
        valueStr = str;
    }

    public String getValueStr() {
        return valueStr;
    }

    public float getValue() {
        return value;
    }

    public String getId() {
        return name;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", valueStr='" + valueStr + '\'' +
                '}';
    }
}
