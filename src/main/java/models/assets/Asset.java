package models.assets;

import java.io.Serializable;

public record Asset(String id, AssetConfig asset) implements Serializable {

    @Override
    public String toString() {
        return "Asset{" +
                "id='" + id + '\'' +
                ", asset=" + asset +
                '}';
    }
}
