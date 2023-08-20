package models.dto.responses.storage;

import lcp.lib.models.assets.Asset;
import lombok.Data;

@Data
public class GetAssetResponse /*extends ServiceResponse*/ {
    private final Asset asset;
}
