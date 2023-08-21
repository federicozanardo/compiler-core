package compiler.module.ast;

public class Party extends Entity {
    private final String name;
    private String userId;
    private final AssetEntity valueAssetEntity = new AssetEntity();
    private float value = 0;
    private String valueStr = "";

    public Party(String n) {
        name = n;
    }

    public void setUserId(String s) {
        userId = s;
    }

    public String getUserId() {
        return userId;
    }

    public void setValue(float v) {
        value = v;
    }

    public void setValueAsset(float v) {
        valueAssetEntity.increase(v);
    }

    public void moveAsset(Party d, float val) {
        valueAssetEntity.withdraw(d, val);
    }

    public void setValueStr(String s) {
        valueStr = s;
    }

    public float getValue() {
        return value;
    }

    public float getValueAsset() {
        return valueAssetEntity.getValue();
    }

    public String getValueStr() {
        return valueStr;
    }

    public String getId() {
        return name;
    }

    public AssetEntity getAsset() {
        return valueAssetEntity;
    }

    public void printParty() {
        if (value == 0 && valueAssetEntity.getValue() == 0 && valueStr.equals("")) {
            System.out.println(name);
        } else if (value != 0 && valueAssetEntity.getValue() == 0 && valueStr.equals("")) {
            System.out.println(name + ":");
            System.out.print('\t');
            System.out.print('\t');
            System.out.println("value " + value);
        } else if (value != 0 && valueAssetEntity.getValue() != 0 && valueStr.equals("")) {
            System.out.println(name + ":");
            System.out.print('\t');
            System.out.print('\t');
            System.out.println("value " + value);
            System.out.print('\t');
            System.out.print('\t');
            System.out.println("asset value " + valueAssetEntity.getValue());
        } else if (value != 0 && valueAssetEntity.getValue() != 0 && !valueStr.equals("")) {
            System.out.println(name + ":");
            System.out.print('\t');
            System.out.print('\t');
            System.out.println("value " + value);
            System.out.print('\t');
            System.out.print('\t');
            System.out.println("asset value " + valueAssetEntity.getValue());
            System.out.print('\t');
            System.out.print('\t');
            System.out.println("string value " + valueStr);
        } else if (value == 0 && valueAssetEntity.getValue() != 0 && !valueStr.equals("")) {
            System.out.println(name + ":");
            System.out.print('\t');
            System.out.print('\t');
            System.out.println("asset value " + valueAssetEntity.getValue());
            System.out.print('\t');
            System.out.print('\t');
            System.out.println("string value " + valueStr);
        } else if (value == 0 && valueAssetEntity.getValue() == 0 && !valueStr.equals("")) {
            System.out.println(name + ":");
            System.out.print('\t');
            System.out.print('\t');
            System.out.println("string value " + valueStr);
        } else if (value != 0 && valueAssetEntity.getValue() == 0 && !valueStr.equals("")) {
            System.out.println(name + ":");
            System.out.print('\t');
            System.out.print('\t');
            System.out.println("value " + value);
            System.out.print('\t');
            System.out.print('\t');
            System.out.println("string value " + valueStr);
        } else if (value == 0 && valueAssetEntity.getValue() != 0 && valueStr.equals("")) {
            System.out.println(name + ":");
            System.out.print('\t');
            System.out.print('\t');
            System.out.println("asset value " + valueAssetEntity.getValue());
        }
    }

    @Override
    public String toString() {
        return "Party{" +
                "name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                ", valueAsset=" + valueAssetEntity +
                ", value=" + value +
                ", valueStr='" + valueStr + '\'' +
                '}';
    }
}
