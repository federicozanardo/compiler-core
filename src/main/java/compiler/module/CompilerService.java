package compiler.module;

import compiler.models.contract.ContractCompiled;
import compiler.module.ast.StipulaCompiler;
import compiler.module.ast.Type;
import compiler.module.ast.TypeInference;
import compiler.module.lexer.StipulaLexer;
import compiler.module.ast.TypeChecking;
import compiler.module.parser.StipulaParser;
import lcp.lib.datastructures.Pair;
import lcp.lib.datastructures.Triple;
import lcp.lib.dfa.states.DfaState;
import lcp.lib.dfa.states.FinalStates;
import lcp.lib.dfa.transitions.TransitionData;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

class CompilerService {
    private static final Logger logger = LoggerFactory.getLogger(CompilerService.class);
    private final CompilerModule module;

    public CompilerService(CompilerModule module) {
        this.module = module;
    }

    public ContractCompiled compile(String sourceCode) throws IOException {
        logger.debug("compile: " + sourceCode);
        logger.info("compile: Compiling...");

                InputStream is = new ByteArrayInputStream(sourceCode.getBytes(StandardCharsets.UTF_8));
        ANTLRInputStream input = new ANTLRInputStream(is);
        StipulaLexer lexer = new StipulaLexer((CharStream) input);
        CommonTokenStream tokens = new CommonTokenStream((TokenSource) lexer);
        StipulaParser parser = new StipulaParser((TokenStream) tokens);

        if (parser.getNumberOfSyntaxErrors() == 0) {
            ParseTree parseTree = parser.prog();

            // Type checking
            TypeChecking typeChecking = new TypeChecking();
            @SuppressWarnings("unchecked")
            Map<Pair<String, Integer>, Type> types = (Map<Pair<String, Integer>, Type>) typeChecking.visit(parseTree);
            ArrayList<Pair<String, ArrayList<Pair<String, Type>>>> functionParameters = typeChecking.getFunctionParameters();
            ArrayList<String> contractNames = typeChecking.getContractNames();

            // Type inference
            TypeInference typeInference = new TypeInference(types, contractNames, functionParameters);

            // Get the types of function arguments
            Map<String, ArrayList<String>> functionTypes = typeInference.getFunctionTypes();

            // Get global variables
            Map<Pair<String, Integer>, Type> globalVariables = typeInference.getGlobalVariables();

            // Compile
            StipulaCompiler stipulaCompiler = new StipulaCompiler(globalVariables, functionTypes, module);
            String bytecode = (String) stipulaCompiler.visit(parseTree);
            logger.info("compile: Compilation successful");
            logger.debug("compile: Bytecode => \n" + bytecode);

            // Set the initial state
            DfaState initialState = stipulaCompiler.getInitialState();

            // Set the final state
            FinalStates finalStates = stipulaCompiler.getFinalStates();

            // Set the DFA transitions
            ArrayList<Triple<DfaState, DfaState, TransitionData>> transitions = stipulaCompiler.getTransitions();

            // Create and return the new contract
            return new ContractCompiled(sourceCode, bytecode, initialState, finalStates, transitions);
        }

        return null; // FIXME
        // TODO: Return a ErrorResponse: Error while compiling
    }
}
