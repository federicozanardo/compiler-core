package models.assets;

import lombok.Getter;

import java.io.Serializable;

@Getter
public abstract class AssetConfig implements Serializable {
    /**
     * It is a name is defined that can be easily remembered by a person.
     */
    private final String assetName;

    /**
     * It corresponds to what is a ticker of a company listed on the stock exchange.
     */
    private final String unitName;

    /**
     * It indicates the maximum amount of assets that can exist over time. The maximum quantity also includes decimal values.
     */
    private final int supply;

    /**
     * It indicates how many parts a single unit can consist of.
     */
    private final int decimals;

    public AssetConfig(String assetName, String unitName, int supply, int decimals) {
        this.assetName = assetName;
        this.unitName = unitName;
        this.supply = supply;
        this.decimals = decimals;
    }

    @Override
    public String toString() {
        return "AssetConfig{" +
                "assetName='" + assetName + '\'' +
                ", unitName='" + unitName + '\'' +
                ", supply=" + supply +
                ", decimals=" + decimals +
                '}';
    }
}
