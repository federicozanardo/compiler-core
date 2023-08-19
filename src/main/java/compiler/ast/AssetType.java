package compiler.ast;

public class AssetType extends Type {
    private String assetId;

    public AssetType(String assetId) {
        this.assetId = assetId;
        this.type = "asset";
    }

    public AssetType() {
        this.assetId = "";
        this.type = "asset";
    }

    public String getAssetId() {
        return assetId;
    }
}
