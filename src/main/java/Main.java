import compiler.Core;
import compiler.models.contract.ContractToCompile;
import compiler.models.dto.compilecontract.CompileContractRequest;
import compiler.module.CompilerModule;
import lcp.lib.communication.module.channel.ModelChannelUtils;
import lcp.lib.exceptions.communication.module.RegisterModuleException;
import lcp.lib.exceptions.communication.module.channel.RegisterChannelException;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.BasicConfigurator;
import storage.models.dto.asset.getassetinfo.GetAssetInfoRequest;
import storage.module.StorageModule;

@Slf4j
public class Main {
    public static void main(String[] args) {
        BasicConfigurator.configure();

        Core core = new Core();

        // Declare modules
        CompilerModule compilerModule = new CompilerModule();
        StorageModule storageModule = new StorageModule();

        storageModule.seed();

        // Setup channels
        try {
            ModelChannelUtils.setupChannel(core, compilerModule);
            ModelChannelUtils.setupChannel(compilerModule, storageModule);
        } catch (RegisterModuleException | RegisterChannelException e) {
            // TODO: handle it
            throw new RuntimeException(e);
        }

        String sourceCode = "stipula BikeRental {\n" +
                "    asset wallet:stipula_coin_asd345\n" +
                "    field cost, rentingTime, use_code\n" +
                "    init Inactive\n" +
                "\n" +
                "    agreement (Lender, Borrower)(cost, rentingTime){\n" +
                "        Lender, Borrower: cost, rentingTime\n" +
                "    } ==> @Inactive\n" +
                "\n" +
                "    @Inactive Lender : offer(z)[] {\n" +
                "        z -> use_code;\n" +
                "        _\n" +
                "    } ==> @Proposal\n" +
                "\n" +
                "    @Proposal Borrower : accept()[y]\n" +
                "        (y == cost) {\n" +
                "            y -o wallet;\n" +
                "            now + rentingTime >>\n" +
                "                @Using {\n" +
                "                    wallet -o Lender\n" +
                "                } ==> @End\n" +
                "    } ==> @Using\n" +
                "\n" +
                "    @Using Borrower : end()[] {\n" +
                "        wallet -o Lender;\n" +
                "        _\n" +
                "    } ==> @End\n" +
                "}\n";
        
        log.debug(core.sendAndReceive(CompilerModule.class.getSimpleName(), new CompileContractRequest(new ContractToCompile(sourceCode))).toString());
    }
}
