package models.dto.responses.storage;

//import connectors.storage.ServiceResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import models.assets.Asset;

@Data
public class GetAssetResponse /*extends ServiceResponse*/ {
    private final Asset asset;
}
