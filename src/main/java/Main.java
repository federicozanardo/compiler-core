//import connectors.MockAssetStorageServiceHandler;
//import connectors.storage.asset.MockGetAssetInfoAssetStorageConnector;
import models.dto.requests.storage.GetAssetRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        //MockAssetStorageServiceHandler mockAssetStorageServiceHandler = new MockAssetStorageServiceHandler();
        //MockGetAssetInfoAssetStorageConnector connector = new MockGetAssetInfoAssetStorageConnector(mockAssetStorageServiceHandler);

        GetAssetRequest request = new GetAssetRequest("test-asset-id");
        //logger.info(connector.call(request).toString());
        //System.out.println(connector.call(request).toString());
    }
}
