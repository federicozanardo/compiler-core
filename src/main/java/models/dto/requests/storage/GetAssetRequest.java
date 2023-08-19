package models.dto.requests.storage;

//import connectors.storage.ServiceRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetAssetRequest /*extends ServiceRequest*/ {
    private final String assetId;
}
