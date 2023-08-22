package compiler.module.ast;

import lombok.Getter;

@Getter
public class AssetType extends Type {
    private final String assetId;

    public AssetType(String assetId) {
        this.assetId = assetId;
        this.type = "asset";
    }

    public AssetType() {
        this.assetId = "";
        this.type = "asset";
    }

}
