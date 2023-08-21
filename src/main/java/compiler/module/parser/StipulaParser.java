package compiler.module.parser;// Generated from Stipula.g4 by ANTLR 4.10

import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class StipulaParser extends Parser {
    static {
        RuntimeMetaData.checkVersion("4.10", RuntimeMetaData.VERSION);
    }

    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
            new PredictionContextCache();
    public static final int
            SEMIC = 1, COLON = 2, COMMA = 3, DOT = 4, EQ = 5, NEQ = 6, IMPL = 7, ASM = 8, ASSETUP = 9,
            FIELDUP = 10, PLUS = 11, MINUS = 12, TIMES = 13, DIV = 14, AT = 15, TRUE = 16, FALSE = 17,
            LPAR = 18, RPAR = 19, SLPAR = 20, SRPAR = 21, CLPAR = 22, CRPAR = 23, LEQ = 24, GEQ = 25,
            LE = 26, GE = 27, OR = 28, AND = 29, NOT = 30, EMPTY = 31, NOW = 32, TRIGGER = 33, IF = 34,
            ELSEIF = 35, ELSE = 36, STIPULA = 37, ASSET = 38, FIELD = 39, AGREEMENT = 40, INTEGER = 41,
            DOUBLE = 42, BOOLEAN = 43, STRING = 44, PARTY = 45, INIT = 46, RAWSTRING = 47, INT = 48,
            REAL = 49, WS = 50, ID = 51, OTHER = 52, LINECOMENTS = 53, BLOCKCOMENTS = 54, ERR = 55;
    public static final int
            RULE_prog = 0, RULE_agreement = 1, RULE_assetdecl = 2, RULE_fielddecl = 3,
            RULE_fun = 4, RULE_assign = 5, RULE_dec = 6, RULE_type = 7, RULE_state = 8,
            RULE_party = 9, RULE_vardec = 10, RULE_assetdec = 11, RULE_varasm = 12,
            RULE_stat = 13, RULE_ifelse = 14, RULE_events = 15, RULE_prec = 16, RULE_expr = 17,
            RULE_term = 18, RULE_factor = 19, RULE_value = 20, RULE_real = 21, RULE_number = 22;

    private static String[] makeRuleNames() {
        return new String[]{
                "prog", "agreement", "assetdecl", "fielddecl", "fun", "assign", "dec",
                "type", "state", "party", "vardec", "assetdec", "varasm", "stat", "ifelse",
                "events", "prec", "expr", "term", "factor", "value", "real", "number"
        };
    }

    public static final String[] ruleNames = makeRuleNames();

    private static String[] makeLiteralNames() {
        return new String[]{
                null, "';'", "':'", "','", "'.'", "'=='", "'!='", "'==>'", "'='", "'-o'",
                "'->'", "'+'", "'-'", "'*'", "'/'", "'@'", "'true'", "'false'", "'('",
                "')'", "'['", "']'", "'{'", "'}'", "'<='", "'>='", "'<'", "'>'", "'||'",
                "'&&'", "'!'", "'_'", "'now'", "'>>'", "'if'", "'else if'", "'else'",
                "'stipula'", "'asset'", "'field'", "'agreement'", "'int'", "'real'",
                "'bool'", "'string'", "'party'", "'init'"
        };
    }

    private static final String[] _LITERAL_NAMES = makeLiteralNames();

    private static String[] makeSymbolicNames() {
        return new String[]{
                null, "SEMIC", "COLON", "COMMA", "DOT", "EQ", "NEQ", "IMPL", "ASM", "ASSETUP",
                "FIELDUP", "PLUS", "MINUS", "TIMES", "DIV", "AT", "TRUE", "FALSE", "LPAR",
                "RPAR", "SLPAR", "SRPAR", "CLPAR", "CRPAR", "LEQ", "GEQ", "LE", "GE",
                "OR", "AND", "NOT", "EMPTY", "NOW", "TRIGGER", "IF", "ELSEIF", "ELSE",
                "STIPULA", "ASSET", "FIELD", "AGREEMENT", "INTEGER", "DOUBLE", "BOOLEAN",
                "STRING", "PARTY", "INIT", "RAWSTRING", "INT", "REAL", "WS", "ID", "OTHER",
                "LINECOMENTS", "BLOCKCOMENTS", "ERR"
        };
    }

    private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
    public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

    /**
     * @deprecated Use {@link #VOCABULARY} instead.
     */
    @Deprecated
    public static final String[] tokenNames;

    static {
        tokenNames = new String[_SYMBOLIC_NAMES.length];
        for (int i = 0; i < tokenNames.length; i++) {
            tokenNames[i] = VOCABULARY.getLiteralName(i);
            if (tokenNames[i] == null) {
                tokenNames[i] = VOCABULARY.getSymbolicName(i);
            }

            if (tokenNames[i] == null) {
                tokenNames[i] = "<INVALID>";
            }
        }
    }

    @Override
    @Deprecated
    public String[] getTokenNames() {
        return tokenNames;
    }

    @Override

    public Vocabulary getVocabulary() {
        return VOCABULARY;
    }

    @Override
    public String getGrammarFileName() {
        return "Stipula.g4";
    }

    @Override
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override
    public String getSerializedATN() {
        return _serializedATN;
    }

    @Override
    public ATN getATN() {
        return _ATN;
    }

    public StipulaParser(TokenStream input) {
        super(input);
        _interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    public static class ProgContext extends ParserRuleContext {
        public Token contract_id;
        public Token init_state;

        public TerminalNode STIPULA() {
            return getToken(StipulaParser.STIPULA, 0);
        }

        public TerminalNode CLPAR() {
            return getToken(StipulaParser.CLPAR, 0);
        }

        public TerminalNode INIT() {
            return getToken(StipulaParser.INIT, 0);
        }

        public AgreementContext agreement() {
            return getRuleContext(AgreementContext.class, 0);
        }

        public TerminalNode CRPAR() {
            return getToken(StipulaParser.CRPAR, 0);
        }

        public List<TerminalNode> ID() {
            return getTokens(StipulaParser.ID);
        }

        public TerminalNode ID(int i) {
            return getToken(StipulaParser.ID, i);
        }

        public AssetdeclContext assetdecl() {
            return getRuleContext(AssetdeclContext.class, 0);
        }

        public FielddeclContext fielddecl() {
            return getRuleContext(FielddeclContext.class, 0);
        }

        public List<FunContext> fun() {
            return getRuleContexts(FunContext.class);
        }

        public FunContext fun(int i) {
            return getRuleContext(FunContext.class, i);
        }

        public ProgContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_prog;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).enterProg(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).exitProg(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StipulaVisitor) return ((StipulaVisitor<? extends T>) visitor).visitProg(this);
            else return visitor.visitChildren(this);
        }
    }

    public final ProgContext prog() throws RecognitionException {
        ProgContext _localctx = new ProgContext(_ctx, getState());
        enterRule(_localctx, 0, RULE_prog);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(46);
                match(STIPULA);
                setState(47);
                ((ProgContext) _localctx).contract_id = match(ID);
                setState(48);
                match(CLPAR);
                setState(50);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == ASSET) {
                    {
                        setState(49);
                        assetdecl();
                    }
                }

                setState(53);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == FIELD) {
                    {
                        setState(52);
                        fielddecl();
                    }
                }

                setState(55);
                match(INIT);
                setState(56);
                ((ProgContext) _localctx).init_state = match(ID);
                setState(57);
                agreement();
                setState(59);
                _errHandler.sync(this);
                _la = _input.LA(1);
                do {
                    {
                        {
                            setState(58);
                            fun();
                        }
                    }
                    setState(61);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                } while (_la == AT || _la == ID);
                setState(63);
                match(CRPAR);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class AgreementContext extends ParserRuleContext {
        public TerminalNode AGREEMENT() {
            return getToken(StipulaParser.AGREEMENT, 0);
        }

        public List<TerminalNode> LPAR() {
            return getTokens(StipulaParser.LPAR);
        }

        public TerminalNode LPAR(int i) {
            return getToken(StipulaParser.LPAR, i);
        }

        public List<PartyContext> party() {
            return getRuleContexts(PartyContext.class);
        }

        public PartyContext party(int i) {
            return getRuleContext(PartyContext.class, i);
        }

        public List<TerminalNode> RPAR() {
            return getTokens(StipulaParser.RPAR);
        }

        public TerminalNode RPAR(int i) {
            return getToken(StipulaParser.RPAR, i);
        }

        public List<VardecContext> vardec() {
            return getRuleContexts(VardecContext.class);
        }

        public VardecContext vardec(int i) {
            return getRuleContext(VardecContext.class, i);
        }

        public TerminalNode CLPAR() {
            return getToken(StipulaParser.CLPAR, 0);
        }

        public TerminalNode CRPAR() {
            return getToken(StipulaParser.CRPAR, 0);
        }

        public TerminalNode IMPL() {
            return getToken(StipulaParser.IMPL, 0);
        }

        public TerminalNode AT() {
            return getToken(StipulaParser.AT, 0);
        }

        public StateContext state() {
            return getRuleContext(StateContext.class, 0);
        }

        public List<TerminalNode> COMMA() {
            return getTokens(StipulaParser.COMMA);
        }

        public TerminalNode COMMA(int i) {
            return getToken(StipulaParser.COMMA, i);
        }

        public List<AssignContext> assign() {
            return getRuleContexts(AssignContext.class);
        }

        public AssignContext assign(int i) {
            return getRuleContext(AssignContext.class, i);
        }

        public AgreementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_agreement;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).enterAgreement(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).exitAgreement(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StipulaVisitor) return ((StipulaVisitor<? extends T>) visitor).visitAgreement(this);
            else return visitor.visitChildren(this);
        }
    }

    public final AgreementContext agreement() throws RecognitionException {
        AgreementContext _localctx = new AgreementContext(_ctx, getState());
        enterRule(_localctx, 2, RULE_agreement);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                {
                    setState(65);
                    match(AGREEMENT);
                    setState(66);
                    match(LPAR);
                    setState(67);
                    party();
                    setState(72);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la == COMMA) {
                        {
                            {
                                setState(68);
                                match(COMMA);
                                setState(69);
                                party();
                            }
                        }
                        setState(74);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                    setState(75);
                    match(RPAR);
                    setState(76);
                    match(LPAR);
                    setState(77);
                    vardec();
                    setState(82);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la == COMMA) {
                        {
                            {
                                setState(78);
                                match(COMMA);
                                setState(79);
                                vardec();
                            }
                        }
                        setState(84);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                    setState(85);
                    match(RPAR);
                    setState(86);
                    match(CLPAR);
                    setState(88);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    do {
                        {
                            {
                                setState(87);
                                assign();
                            }
                        }
                        setState(90);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    } while (_la == ID);
                    setState(92);
                    match(CRPAR);
                    setState(93);
                    match(IMPL);
                    setState(94);
                    match(AT);
                    setState(95);
                    state();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class AssetdeclContext extends ParserRuleContext {
        public Token ID;
        public List<Token> idAsset = new ArrayList<Token>();
        public List<Token> assetId = new ArrayList<Token>();

        public TerminalNode ASSET() {
            return getToken(StipulaParser.ASSET, 0);
        }

        public List<TerminalNode> COLON() {
            return getTokens(StipulaParser.COLON);
        }

        public TerminalNode COLON(int i) {
            return getToken(StipulaParser.COLON, i);
        }

        public List<TerminalNode> ID() {
            return getTokens(StipulaParser.ID);
        }

        public TerminalNode ID(int i) {
            return getToken(StipulaParser.ID, i);
        }

        public List<TerminalNode> COMMA() {
            return getTokens(StipulaParser.COMMA);
        }

        public TerminalNode COMMA(int i) {
            return getToken(StipulaParser.COMMA, i);
        }

        public AssetdeclContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_assetdecl;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).enterAssetdecl(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).exitAssetdecl(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StipulaVisitor) return ((StipulaVisitor<? extends T>) visitor).visitAssetdecl(this);
            else return visitor.visitChildren(this);
        }
    }

    public final AssetdeclContext assetdecl() throws RecognitionException {
        AssetdeclContext _localctx = new AssetdeclContext(_ctx, getState());
        enterRule(_localctx, 4, RULE_assetdecl);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(97);
                match(ASSET);
                setState(98);
                ((AssetdeclContext) _localctx).ID = match(ID);
                ((AssetdeclContext) _localctx).idAsset.add(((AssetdeclContext) _localctx).ID);
                setState(99);
                match(COLON);
                setState(100);
                ((AssetdeclContext) _localctx).ID = match(ID);
                ((AssetdeclContext) _localctx).assetId.add(((AssetdeclContext) _localctx).ID);
                setState(107);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == COMMA) {
                    {
                        {
                            setState(101);
                            match(COMMA);
                            setState(102);
                            ((AssetdeclContext) _localctx).ID = match(ID);
                            ((AssetdeclContext) _localctx).idAsset.add(((AssetdeclContext) _localctx).ID);
                            setState(103);
                            match(COLON);
                            setState(104);
                            ((AssetdeclContext) _localctx).ID = match(ID);
                            ((AssetdeclContext) _localctx).assetId.add(((AssetdeclContext) _localctx).ID);
                        }
                    }
                    setState(109);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class FielddeclContext extends ParserRuleContext {
        public Token ID;
        public List<Token> idField = new ArrayList<Token>();

        public TerminalNode FIELD() {
            return getToken(StipulaParser.FIELD, 0);
        }

        public List<TerminalNode> ID() {
            return getTokens(StipulaParser.ID);
        }

        public TerminalNode ID(int i) {
            return getToken(StipulaParser.ID, i);
        }

        public List<TerminalNode> COMMA() {
            return getTokens(StipulaParser.COMMA);
        }

        public TerminalNode COMMA(int i) {
            return getToken(StipulaParser.COMMA, i);
        }

        public FielddeclContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_fielddecl;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).enterFielddecl(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).exitFielddecl(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StipulaVisitor) return ((StipulaVisitor<? extends T>) visitor).visitFielddecl(this);
            else return visitor.visitChildren(this);
        }
    }

    public final FielddeclContext fielddecl() throws RecognitionException {
        FielddeclContext _localctx = new FielddeclContext(_ctx, getState());
        enterRule(_localctx, 6, RULE_fielddecl);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(110);
                match(FIELD);
                setState(111);
                ((FielddeclContext) _localctx).ID = match(ID);
                ((FielddeclContext) _localctx).idField.add(((FielddeclContext) _localctx).ID);
                setState(116);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == COMMA) {
                    {
                        {
                            setState(112);
                            match(COMMA);
                            setState(113);
                            ((FielddeclContext) _localctx).ID = match(ID);
                            ((FielddeclContext) _localctx).idField.add(((FielddeclContext) _localctx).ID);
                        }
                    }
                    setState(118);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class FunContext extends ParserRuleContext {
        public Token funId;

        public List<PartyContext> party() {
            return getRuleContexts(PartyContext.class);
        }

        public PartyContext party(int i) {
            return getRuleContext(PartyContext.class, i);
        }

        public TerminalNode COLON() {
            return getToken(StipulaParser.COLON, 0);
        }

        public List<TerminalNode> LPAR() {
            return getTokens(StipulaParser.LPAR);
        }

        public TerminalNode LPAR(int i) {
            return getToken(StipulaParser.LPAR, i);
        }

        public List<TerminalNode> RPAR() {
            return getTokens(StipulaParser.RPAR);
        }

        public TerminalNode RPAR(int i) {
            return getToken(StipulaParser.RPAR, i);
        }

        public TerminalNode SLPAR() {
            return getToken(StipulaParser.SLPAR, 0);
        }

        public TerminalNode SRPAR() {
            return getToken(StipulaParser.SRPAR, 0);
        }

        public TerminalNode CLPAR() {
            return getToken(StipulaParser.CLPAR, 0);
        }

        public TerminalNode SEMIC() {
            return getToken(StipulaParser.SEMIC, 0);
        }

        public TerminalNode CRPAR() {
            return getToken(StipulaParser.CRPAR, 0);
        }

        public TerminalNode IMPL() {
            return getToken(StipulaParser.IMPL, 0);
        }

        public List<TerminalNode> AT() {
            return getTokens(StipulaParser.AT);
        }

        public TerminalNode AT(int i) {
            return getToken(StipulaParser.AT, i);
        }

        public List<StateContext> state() {
            return getRuleContexts(StateContext.class);
        }

        public StateContext state(int i) {
            return getRuleContext(StateContext.class, i);
        }

        public TerminalNode ID() {
            return getToken(StipulaParser.ID, 0);
        }

        public List<TerminalNode> COMMA() {
            return getTokens(StipulaParser.COMMA);
        }

        public TerminalNode COMMA(int i) {
            return getToken(StipulaParser.COMMA, i);
        }

        public List<VardecContext> vardec() {
            return getRuleContexts(VardecContext.class);
        }

        public VardecContext vardec(int i) {
            return getRuleContext(VardecContext.class, i);
        }

        public List<AssetdecContext> assetdec() {
            return getRuleContexts(AssetdecContext.class);
        }

        public AssetdecContext assetdec(int i) {
            return getRuleContext(AssetdecContext.class, i);
        }

        public PrecContext prec() {
            return getRuleContext(PrecContext.class, 0);
        }

        public List<StatContext> stat() {
            return getRuleContexts(StatContext.class);
        }

        public StatContext stat(int i) {
            return getRuleContext(StatContext.class, i);
        }

        public List<EventsContext> events() {
            return getRuleContexts(EventsContext.class);
        }

        public EventsContext events(int i) {
            return getRuleContext(EventsContext.class, i);
        }

        public FunContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_fun;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).enterFun(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).exitFun(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StipulaVisitor) return ((StipulaVisitor<? extends T>) visitor).visitFun(this);
            else return visitor.visitChildren(this);
        }
    }

    public final FunContext fun() throws RecognitionException {
        FunContext _localctx = new FunContext(_ctx, getState());
        enterRule(_localctx, 8, RULE_fun);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                {
                    setState(123);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la == AT) {
                        {
                            {
                                setState(119);
                                match(AT);
                                setState(120);
                                state();
                            }
                        }
                        setState(125);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                    setState(126);
                    party();
                    setState(131);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la == COMMA) {
                        {
                            {
                                setState(127);
                                match(COMMA);
                                setState(128);
                                party();
                            }
                        }
                        setState(133);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                    setState(134);
                    match(COLON);
                    setState(135);
                    ((FunContext) _localctx).funId = match(ID);
                    setState(136);
                    match(LPAR);
                    setState(145);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (_la == ID) {
                        {
                            setState(137);
                            vardec();
                            setState(142);
                            _errHandler.sync(this);
                            _la = _input.LA(1);
                            while (_la == COMMA) {
                                {
                                    {
                                        setState(138);
                                        match(COMMA);
                                        setState(139);
                                        vardec();
                                    }
                                }
                                setState(144);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                            }
                        }
                    }

                    setState(147);
                    match(RPAR);
                    setState(148);
                    match(SLPAR);
                    setState(157);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (_la == ID) {
                        {
                            setState(149);
                            assetdec();
                            setState(154);
                            _errHandler.sync(this);
                            _la = _input.LA(1);
                            while (_la == COMMA) {
                                {
                                    {
                                        setState(150);
                                        match(COMMA);
                                        setState(151);
                                        assetdec();
                                    }
                                }
                                setState(156);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                            }
                        }
                    }

                    setState(159);
                    match(SRPAR);
                    setState(164);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (_la == LPAR) {
                        {
                            setState(160);
                            match(LPAR);
                            setState(161);
                            prec();
                            setState(162);
                            match(RPAR);
                        }
                    }

                    setState(166);
                    match(CLPAR);
                    setState(168);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    do {
                        {
                            {
                                setState(167);
                                stat();
                            }
                        }
                        setState(170);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    } while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TRUE) | (1L << FALSE) | (1L << LPAR) | (1L << EMPTY) | (1L << NOW) | (1L << IF) | (1L << RAWSTRING) | (1L << INT) | (1L << REAL) | (1L << ID))) != 0));
                    setState(172);
                    match(SEMIC);
                    setState(174);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    do {
                        {
                            {
                                setState(173);
                                events();
                            }
                        }
                        setState(176);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    } while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << TRUE) | (1L << FALSE) | (1L << LPAR) | (1L << EMPTY) | (1L << NOW) | (1L << RAWSTRING) | (1L << INT) | (1L << REAL) | (1L << ID))) != 0));
                    setState(178);
                    match(CRPAR);
                    setState(179);
                    match(IMPL);
                    setState(180);
                    match(AT);
                    setState(181);
                    state();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class AssignContext extends ParserRuleContext {
        public List<PartyContext> party() {
            return getRuleContexts(PartyContext.class);
        }

        public PartyContext party(int i) {
            return getRuleContext(PartyContext.class, i);
        }

        public TerminalNode COLON() {
            return getToken(StipulaParser.COLON, 0);
        }

        public List<VardecContext> vardec() {
            return getRuleContexts(VardecContext.class);
        }

        public VardecContext vardec(int i) {
            return getRuleContext(VardecContext.class, i);
        }

        public List<TerminalNode> COMMA() {
            return getTokens(StipulaParser.COMMA);
        }

        public TerminalNode COMMA(int i) {
            return getToken(StipulaParser.COMMA, i);
        }

        public AssignContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_assign;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).enterAssign(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).exitAssign(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StipulaVisitor) return ((StipulaVisitor<? extends T>) visitor).visitAssign(this);
            else return visitor.visitChildren(this);
        }
    }

    public final AssignContext assign() throws RecognitionException {
        AssignContext _localctx = new AssignContext(_ctx, getState());
        enterRule(_localctx, 10, RULE_assign);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                {
                    setState(183);
                    party();
                    setState(188);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la == COMMA) {
                        {
                            {
                                setState(184);
                                match(COMMA);
                                setState(185);
                                party();
                            }
                        }
                        setState(190);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                    setState(191);
                    match(COLON);
                    setState(192);
                    vardec();
                    setState(197);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la == COMMA) {
                        {
                            {
                                setState(193);
                                match(COMMA);
                                setState(194);
                                vardec();
                            }
                        }
                        setState(199);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class DecContext extends ParserRuleContext {
        public TerminalNode ID() {
            return getToken(StipulaParser.ID, 0);
        }

        public TerminalNode ASSET() {
            return getToken(StipulaParser.ASSET, 0);
        }

        public TerminalNode FIELD() {
            return getToken(StipulaParser.FIELD, 0);
        }

        public DecContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_dec;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).enterDec(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).exitDec(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StipulaVisitor) return ((StipulaVisitor<? extends T>) visitor).visitDec(this);
            else return visitor.visitChildren(this);
        }
    }

    public final DecContext dec() throws RecognitionException {
        DecContext _localctx = new DecContext(_ctx, getState());
        enterRule(_localctx, 12, RULE_dec);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(200);
                _la = _input.LA(1);
                if (!(_la == ASSET || _la == FIELD)) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
                setState(201);
                match(ID);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class TypeContext extends ParserRuleContext {
        public TerminalNode INTEGER() {
            return getToken(StipulaParser.INTEGER, 0);
        }

        public TerminalNode DOUBLE() {
            return getToken(StipulaParser.DOUBLE, 0);
        }

        public TerminalNode BOOLEAN() {
            return getToken(StipulaParser.BOOLEAN, 0);
        }

        public TerminalNode STRING() {
            return getToken(StipulaParser.STRING, 0);
        }

        public TypeContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_type;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).enterType(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).exitType(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StipulaVisitor) return ((StipulaVisitor<? extends T>) visitor).visitType(this);
            else return visitor.visitChildren(this);
        }
    }

    public final TypeContext type() throws RecognitionException {
        TypeContext _localctx = new TypeContext(_ctx, getState());
        enterRule(_localctx, 14, RULE_type);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(203);
                _la = _input.LA(1);
                if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTEGER) | (1L << DOUBLE) | (1L << BOOLEAN) | (1L << STRING))) != 0))) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class StateContext extends ParserRuleContext {
        public TerminalNode ID() {
            return getToken(StipulaParser.ID, 0);
        }

        public StateContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_state;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).enterState(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).exitState(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StipulaVisitor) return ((StipulaVisitor<? extends T>) visitor).visitState(this);
            else return visitor.visitChildren(this);
        }
    }

    public final StateContext state() throws RecognitionException {
        StateContext _localctx = new StateContext(_ctx, getState());
        enterRule(_localctx, 16, RULE_state);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(205);
                match(ID);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class PartyContext extends ParserRuleContext {
        public TerminalNode ID() {
            return getToken(StipulaParser.ID, 0);
        }

        public PartyContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_party;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).enterParty(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).exitParty(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StipulaVisitor) return ((StipulaVisitor<? extends T>) visitor).visitParty(this);
            else return visitor.visitChildren(this);
        }
    }

    public final PartyContext party() throws RecognitionException {
        PartyContext _localctx = new PartyContext(_ctx, getState());
        enterRule(_localctx, 18, RULE_party);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(207);
                match(ID);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class VardecContext extends ParserRuleContext {
        public TerminalNode ID() {
            return getToken(StipulaParser.ID, 0);
        }

        public VardecContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_vardec;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).enterVardec(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).exitVardec(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StipulaVisitor) return ((StipulaVisitor<? extends T>) visitor).visitVardec(this);
            else return visitor.visitChildren(this);
        }
    }

    public final VardecContext vardec() throws RecognitionException {
        VardecContext _localctx = new VardecContext(_ctx, getState());
        enterRule(_localctx, 20, RULE_vardec);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(209);
                match(ID);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class AssetdecContext extends ParserRuleContext {
        public TerminalNode ID() {
            return getToken(StipulaParser.ID, 0);
        }

        public AssetdecContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_assetdec;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).enterAssetdec(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).exitAssetdec(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StipulaVisitor) return ((StipulaVisitor<? extends T>) visitor).visitAssetdec(this);
            else return visitor.visitChildren(this);
        }
    }

    public final AssetdecContext assetdec() throws RecognitionException {
        AssetdecContext _localctx = new AssetdecContext(_ctx, getState());
        enterRule(_localctx, 22, RULE_assetdec);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(211);
                match(ID);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class VarasmContext extends ParserRuleContext {
        public VardecContext vardec() {
            return getRuleContext(VardecContext.class, 0);
        }

        public TerminalNode ASM() {
            return getToken(StipulaParser.ASM, 0);
        }

        public ExprContext expr() {
            return getRuleContext(ExprContext.class, 0);
        }

        public VarasmContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_varasm;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).enterVarasm(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).exitVarasm(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StipulaVisitor) return ((StipulaVisitor<? extends T>) visitor).visitVarasm(this);
            else return visitor.visitChildren(this);
        }
    }

    public final VarasmContext varasm() throws RecognitionException {
        VarasmContext _localctx = new VarasmContext(_ctx, getState());
        enterRule(_localctx, 24, RULE_varasm);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(213);
                vardec();
                setState(214);
                match(ASM);
                setState(215);
                expr();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class StatContext extends ParserRuleContext {
        public ValueContext left;
        public Token operator;
        public Token right;
        public Token rightPlus;

        public TerminalNode EMPTY() {
            return getToken(StipulaParser.EMPTY, 0);
        }

        public ValueContext value() {
            return getRuleContext(ValueContext.class, 0);
        }

        public TerminalNode ASSETUP() {
            return getToken(StipulaParser.ASSETUP, 0);
        }

        public List<TerminalNode> ID() {
            return getTokens(StipulaParser.ID);
        }

        public TerminalNode ID(int i) {
            return getToken(StipulaParser.ID, i);
        }

        public TerminalNode COMMA() {
            return getToken(StipulaParser.COMMA, 0);
        }

        public TerminalNode FIELDUP() {
            return getToken(StipulaParser.FIELDUP, 0);
        }

        public IfelseContext ifelse() {
            return getRuleContext(IfelseContext.class, 0);
        }

        public StatContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_stat;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).enterStat(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).exitStat(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StipulaVisitor) return ((StipulaVisitor<? extends T>) visitor).visitStat(this);
            else return visitor.visitChildren(this);
        }
    }

    public final StatContext stat() throws RecognitionException {
        StatContext _localctx = new StatContext(_ctx, getState());
        enterRule(_localctx, 26, RULE_stat);
        int _la;
        try {
            setState(230);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 20, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(217);
                    match(EMPTY);
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(218);
                    ((StatContext) _localctx).left = value();
                    setState(219);
                    ((StatContext) _localctx).operator = match(ASSETUP);
                    setState(220);
                    ((StatContext) _localctx).right = match(ID);
                    setState(223);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (_la == COMMA) {
                        {
                            setState(221);
                            match(COMMA);
                            setState(222);
                            ((StatContext) _localctx).rightPlus = match(ID);
                        }
                    }

                }
                break;
                case 3:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(225);
                    ((StatContext) _localctx).left = value();
                    setState(226);
                    ((StatContext) _localctx).operator = match(FIELDUP);
                    setState(227);
                    ((StatContext) _localctx).right = _input.LT(1);
                    _la = _input.LA(1);
                    if (!(_la == EMPTY || _la == ID)) {
                        ((StatContext) _localctx).right = (Token) _errHandler.recoverInline(this);
                    } else {
                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                        _errHandler.reportMatch(this);
                        consume();
                    }
                }
                break;
                case 4:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(229);
                    ifelse();
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class IfelseContext extends ParserRuleContext {
        public ExprContext cond;
        public StatContext stat;
        public List<StatContext> ifBranch = new ArrayList<StatContext>();
        public ExprContext expr;
        public List<ExprContext> condElseIf = new ArrayList<ExprContext>();
        public List<StatContext> elseIfBranch = new ArrayList<StatContext>();
        public List<StatContext> elseBranch = new ArrayList<StatContext>();

        public TerminalNode IF() {
            return getToken(StipulaParser.IF, 0);
        }

        public TerminalNode LPAR() {
            return getToken(StipulaParser.LPAR, 0);
        }

        public TerminalNode RPAR() {
            return getToken(StipulaParser.RPAR, 0);
        }

        public List<TerminalNode> CLPAR() {
            return getTokens(StipulaParser.CLPAR);
        }

        public TerminalNode CLPAR(int i) {
            return getToken(StipulaParser.CLPAR, i);
        }

        public List<TerminalNode> CRPAR() {
            return getTokens(StipulaParser.CRPAR);
        }

        public TerminalNode CRPAR(int i) {
            return getToken(StipulaParser.CRPAR, i);
        }

        public List<ExprContext> expr() {
            return getRuleContexts(ExprContext.class);
        }

        public ExprContext expr(int i) {
            return getRuleContext(ExprContext.class, i);
        }

        public List<StatContext> stat() {
            return getRuleContexts(StatContext.class);
        }

        public StatContext stat(int i) {
            return getRuleContext(StatContext.class, i);
        }

        public List<TerminalNode> ELSEIF() {
            return getTokens(StipulaParser.ELSEIF);
        }

        public TerminalNode ELSEIF(int i) {
            return getToken(StipulaParser.ELSEIF, i);
        }

        public TerminalNode ELSE() {
            return getToken(StipulaParser.ELSE, 0);
        }

        public IfelseContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_ifelse;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).enterIfelse(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).exitIfelse(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StipulaVisitor) return ((StipulaVisitor<? extends T>) visitor).visitIfelse(this);
            else return visitor.visitChildren(this);
        }
    }

    public final IfelseContext ifelse() throws RecognitionException {
        IfelseContext _localctx = new IfelseContext(_ctx, getState());
        enterRule(_localctx, 28, RULE_ifelse);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                {
                    setState(232);
                    match(IF);
                    setState(233);
                    match(LPAR);
                    setState(234);
                    ((IfelseContext) _localctx).cond = expr();
                    setState(235);
                    match(RPAR);
                    setState(236);
                    match(CLPAR);
                    setState(237);
                    ((IfelseContext) _localctx).stat = stat();
                    ((IfelseContext) _localctx).ifBranch.add(((IfelseContext) _localctx).stat);
                    setState(241);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TRUE) | (1L << FALSE) | (1L << LPAR) | (1L << EMPTY) | (1L << NOW) | (1L << IF) | (1L << RAWSTRING) | (1L << INT) | (1L << REAL) | (1L << ID))) != 0)) {
                        {
                            {
                                setState(238);
                                ((IfelseContext) _localctx).stat = stat();
                                ((IfelseContext) _localctx).ifBranch.add(((IfelseContext) _localctx).stat);
                            }
                        }
                        setState(243);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                    setState(244);
                    match(CRPAR);
                    setState(259);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la == ELSEIF) {
                        {
                            {
                                setState(245);
                                match(ELSEIF);
                                setState(246);
                                ((IfelseContext) _localctx).expr = expr();
                                ((IfelseContext) _localctx).condElseIf.add(((IfelseContext) _localctx).expr);
                                setState(247);
                                match(CLPAR);
                                setState(248);
                                ((IfelseContext) _localctx).stat = stat();
                                ((IfelseContext) _localctx).elseIfBranch.add(((IfelseContext) _localctx).stat);
                                setState(252);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TRUE) | (1L << FALSE) | (1L << LPAR) | (1L << EMPTY) | (1L << NOW) | (1L << IF) | (1L << RAWSTRING) | (1L << INT) | (1L << REAL) | (1L << ID))) != 0)) {
                                    {
                                        {
                                            setState(249);
                                            ((IfelseContext) _localctx).stat = stat();
                                            ((IfelseContext) _localctx).elseIfBranch.add(((IfelseContext) _localctx).stat);
                                        }
                                    }
                                    setState(254);
                                    _errHandler.sync(this);
                                    _la = _input.LA(1);
                                }
                                setState(255);
                                match(CRPAR);
                            }
                        }
                        setState(261);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                    setState(273);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (_la == ELSE) {
                        {
                            setState(262);
                            match(ELSE);
                            setState(263);
                            match(CLPAR);
                            setState(264);
                            ((IfelseContext) _localctx).stat = stat();
                            ((IfelseContext) _localctx).elseBranch.add(((IfelseContext) _localctx).stat);
                            setState(268);
                            _errHandler.sync(this);
                            _la = _input.LA(1);
                            while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TRUE) | (1L << FALSE) | (1L << LPAR) | (1L << EMPTY) | (1L << NOW) | (1L << IF) | (1L << RAWSTRING) | (1L << INT) | (1L << REAL) | (1L << ID))) != 0)) {
                                {
                                    {
                                        setState(265);
                                        ((IfelseContext) _localctx).stat = stat();
                                        ((IfelseContext) _localctx).elseBranch.add(((IfelseContext) _localctx).stat);
                                    }
                                }
                                setState(270);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                            }
                            setState(271);
                            match(CRPAR);
                        }
                    }

                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class EventsContext extends ParserRuleContext {
        public TerminalNode EMPTY() {
            return getToken(StipulaParser.EMPTY, 0);
        }

        public ExprContext expr() {
            return getRuleContext(ExprContext.class, 0);
        }

        public TerminalNode TRIGGER() {
            return getToken(StipulaParser.TRIGGER, 0);
        }

        public List<TerminalNode> AT() {
            return getTokens(StipulaParser.AT);
        }

        public TerminalNode AT(int i) {
            return getToken(StipulaParser.AT, i);
        }

        public List<TerminalNode> ID() {
            return getTokens(StipulaParser.ID);
        }

        public TerminalNode ID(int i) {
            return getToken(StipulaParser.ID, i);
        }

        public TerminalNode CLPAR() {
            return getToken(StipulaParser.CLPAR, 0);
        }

        public TerminalNode CRPAR() {
            return getToken(StipulaParser.CRPAR, 0);
        }

        public TerminalNode IMPL() {
            return getToken(StipulaParser.IMPL, 0);
        }

        public List<StatContext> stat() {
            return getRuleContexts(StatContext.class);
        }

        public StatContext stat(int i) {
            return getRuleContext(StatContext.class, i);
        }

        public EventsContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_events;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).enterEvents(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).exitEvents(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StipulaVisitor) return ((StipulaVisitor<? extends T>) visitor).visitEvents(this);
            else return visitor.visitChildren(this);
        }
    }

    public final EventsContext events() throws RecognitionException {
        EventsContext _localctx = new EventsContext(_ctx, getState());
        enterRule(_localctx, 30, RULE_events);
        int _la;
        try {
            setState(291);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 27, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(275);
                    match(EMPTY);
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    {
                        setState(276);
                        expr();
                        setState(277);
                        match(TRIGGER);
                        setState(278);
                        match(AT);
                        setState(279);
                        match(ID);
                        setState(280);
                        match(CLPAR);
                        setState(282);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        do {
                            {
                                {
                                    setState(281);
                                    stat();
                                }
                            }
                            setState(284);
                            _errHandler.sync(this);
                            _la = _input.LA(1);
                        } while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TRUE) | (1L << FALSE) | (1L << LPAR) | (1L << EMPTY) | (1L << NOW) | (1L << IF) | (1L << RAWSTRING) | (1L << INT) | (1L << REAL) | (1L << ID))) != 0));
                        setState(286);
                        match(CRPAR);
                        setState(287);
                        match(IMPL);
                        setState(288);
                        match(AT);
                        setState(289);
                        match(ID);
                    }
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class PrecContext extends ParserRuleContext {
        public ExprContext expr() {
            return getRuleContext(ExprContext.class, 0);
        }

        public PrecContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_prec;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).enterPrec(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).exitPrec(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StipulaVisitor) return ((StipulaVisitor<? extends T>) visitor).visitPrec(this);
            else return visitor.visitChildren(this);
        }
    }

    public final PrecContext prec() throws RecognitionException {
        PrecContext _localctx = new PrecContext(_ctx, getState());
        enterRule(_localctx, 32, RULE_prec);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(293);
                expr();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ExprContext extends ParserRuleContext {
        public TermContext left;
        public Token operator;
        public ExprContext right;

        public TermContext term() {
            return getRuleContext(TermContext.class, 0);
        }

        public List<TerminalNode> MINUS() {
            return getTokens(StipulaParser.MINUS);
        }

        public TerminalNode MINUS(int i) {
            return getToken(StipulaParser.MINUS, i);
        }

        public ExprContext expr() {
            return getRuleContext(ExprContext.class, 0);
        }

        public TerminalNode PLUS() {
            return getToken(StipulaParser.PLUS, 0);
        }

        public TerminalNode OR() {
            return getToken(StipulaParser.OR, 0);
        }

        public ExprContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_expr;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).enterExpr(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).exitExpr(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StipulaVisitor) return ((StipulaVisitor<? extends T>) visitor).visitExpr(this);
            else return visitor.visitChildren(this);
        }
    }

    public final ExprContext expr() throws RecognitionException {
        ExprContext _localctx = new ExprContext(_ctx, getState());
        enterRule(_localctx, 34, RULE_expr);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(296);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == MINUS) {
                    {
                        setState(295);
                        match(MINUS);
                    }
                }

                setState(298);
                ((ExprContext) _localctx).left = term();
                setState(301);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << OR))) != 0)) {
                    {
                        setState(299);
                        ((ExprContext) _localctx).operator = _input.LT(1);
                        _la = _input.LA(1);
                        if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << OR))) != 0))) {
                            ((ExprContext) _localctx).operator = (Token) _errHandler.recoverInline(this);
                        } else {
                            if (_input.LA(1) == Token.EOF) matchedEOF = true;
                            _errHandler.reportMatch(this);
                            consume();
                        }
                        setState(300);
                        ((ExprContext) _localctx).right = expr();
                    }
                }

            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class TermContext extends ParserRuleContext {
        public FactorContext left;
        public Token operator;
        public TermContext right;

        public FactorContext factor() {
            return getRuleContext(FactorContext.class, 0);
        }

        public TermContext term() {
            return getRuleContext(TermContext.class, 0);
        }

        public TerminalNode TIMES() {
            return getToken(StipulaParser.TIMES, 0);
        }

        public TerminalNode DIV() {
            return getToken(StipulaParser.DIV, 0);
        }

        public TerminalNode AND() {
            return getToken(StipulaParser.AND, 0);
        }

        public TermContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_term;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).enterTerm(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).exitTerm(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StipulaVisitor) return ((StipulaVisitor<? extends T>) visitor).visitTerm(this);
            else return visitor.visitChildren(this);
        }
    }

    public final TermContext term() throws RecognitionException {
        TermContext _localctx = new TermContext(_ctx, getState());
        enterRule(_localctx, 36, RULE_term);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(303);
                ((TermContext) _localctx).left = factor();
                setState(306);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TIMES) | (1L << DIV) | (1L << AND))) != 0)) {
                    {
                        setState(304);
                        ((TermContext) _localctx).operator = _input.LT(1);
                        _la = _input.LA(1);
                        if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TIMES) | (1L << DIV) | (1L << AND))) != 0))) {
                            ((TermContext) _localctx).operator = (Token) _errHandler.recoverInline(this);
                        } else {
                            if (_input.LA(1) == Token.EOF) matchedEOF = true;
                            _errHandler.reportMatch(this);
                            consume();
                        }
                        setState(305);
                        ((TermContext) _localctx).right = term();
                    }
                }

            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class FactorContext extends ParserRuleContext {
        public ValueContext left;
        public Token operator;
        public ValueContext right;

        public List<ValueContext> value() {
            return getRuleContexts(ValueContext.class);
        }

        public ValueContext value(int i) {
            return getRuleContext(ValueContext.class, i);
        }

        public TerminalNode EQ() {
            return getToken(StipulaParser.EQ, 0);
        }

        public TerminalNode LE() {
            return getToken(StipulaParser.LE, 0);
        }

        public TerminalNode GE() {
            return getToken(StipulaParser.GE, 0);
        }

        public TerminalNode LEQ() {
            return getToken(StipulaParser.LEQ, 0);
        }

        public TerminalNode GEQ() {
            return getToken(StipulaParser.GEQ, 0);
        }

        public TerminalNode NEQ() {
            return getToken(StipulaParser.NEQ, 0);
        }

        public FactorContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_factor;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).enterFactor(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).exitFactor(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StipulaVisitor) return ((StipulaVisitor<? extends T>) visitor).visitFactor(this);
            else return visitor.visitChildren(this);
        }
    }

    public final FactorContext factor() throws RecognitionException {
        FactorContext _localctx = new FactorContext(_ctx, getState());
        enterRule(_localctx, 38, RULE_factor);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(308);
                ((FactorContext) _localctx).left = value();
                setState(311);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << NEQ) | (1L << LEQ) | (1L << GEQ) | (1L << LE) | (1L << GE))) != 0)) {
                    {
                        setState(309);
                        ((FactorContext) _localctx).operator = _input.LT(1);
                        _la = _input.LA(1);
                        if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << NEQ) | (1L << LEQ) | (1L << GEQ) | (1L << LE) | (1L << GE))) != 0))) {
                            ((FactorContext) _localctx).operator = (Token) _errHandler.recoverInline(this);
                        } else {
                            if (_input.LA(1) == Token.EOF) matchedEOF = true;
                            _errHandler.reportMatch(this);
                            consume();
                        }
                        setState(310);
                        ((FactorContext) _localctx).right = value();
                    }
                }

            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ValueContext extends ParserRuleContext {
        public NumberContext number() {
            return getRuleContext(NumberContext.class, 0);
        }

        public TerminalNode ID() {
            return getToken(StipulaParser.ID, 0);
        }

        public TerminalNode NOW() {
            return getToken(StipulaParser.NOW, 0);
        }

        public TerminalNode LPAR() {
            return getToken(StipulaParser.LPAR, 0);
        }

        public ExprContext expr() {
            return getRuleContext(ExprContext.class, 0);
        }

        public TerminalNode RPAR() {
            return getToken(StipulaParser.RPAR, 0);
        }

        public TerminalNode RAWSTRING() {
            return getToken(StipulaParser.RAWSTRING, 0);
        }

        public TerminalNode EMPTY() {
            return getToken(StipulaParser.EMPTY, 0);
        }

        public TerminalNode TRUE() {
            return getToken(StipulaParser.TRUE, 0);
        }

        public TerminalNode FALSE() {
            return getToken(StipulaParser.FALSE, 0);
        }

        public ValueContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_value;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).enterValue(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).exitValue(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StipulaVisitor) return ((StipulaVisitor<? extends T>) visitor).visitValue(this);
            else return visitor.visitChildren(this);
        }
    }

    public final ValueContext value() throws RecognitionException {
        ValueContext _localctx = new ValueContext(_ctx, getState());
        enterRule(_localctx, 40, RULE_value);
        int _la;
        try {
            setState(323);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case INT:
                case REAL:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(313);
                    number();
                }
                break;
                case ID:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(314);
                    match(ID);
                }
                break;
                case NOW:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(315);
                    match(NOW);
                }
                break;
                case LPAR:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(316);
                    match(LPAR);
                    setState(317);
                    expr();
                    setState(318);
                    match(RPAR);
                }
                break;
                case RAWSTRING:
                    enterOuterAlt(_localctx, 5);
                {
                    setState(320);
                    match(RAWSTRING);
                }
                break;
                case EMPTY:
                    enterOuterAlt(_localctx, 6);
                {
                    setState(321);
                    match(EMPTY);
                }
                break;
                case TRUE:
                case FALSE:
                    enterOuterAlt(_localctx, 7);
                {
                    setState(322);
                    _la = _input.LA(1);
                    if (!(_la == TRUE || _la == FALSE)) {
                        _errHandler.recoverInline(this);
                    } else {
                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                        _errHandler.reportMatch(this);
                        consume();
                    }
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class RealContext extends ParserRuleContext {
        public List<NumberContext> number() {
            return getRuleContexts(NumberContext.class);
        }

        public NumberContext number(int i) {
            return getRuleContext(NumberContext.class, i);
        }

        public TerminalNode DOT() {
            return getToken(StipulaParser.DOT, 0);
        }

        public RealContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_real;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).enterReal(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).exitReal(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StipulaVisitor) return ((StipulaVisitor<? extends T>) visitor).visitReal(this);
            else return visitor.visitChildren(this);
        }
    }

    public final RealContext real() throws RecognitionException {
        RealContext _localctx = new RealContext(_ctx, getState());
        enterRule(_localctx, 42, RULE_real);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(325);
                number();
                setState(326);
                match(DOT);
                setState(327);
                number();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class NumberContext extends ParserRuleContext {
        public TerminalNode INT() {
            return getToken(StipulaParser.INT, 0);
        }

        public TerminalNode REAL() {
            return getToken(StipulaParser.REAL, 0);
        }

        public NumberContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_number;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).enterNumber(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof StipulaListener) ((StipulaListener) listener).exitNumber(this);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof StipulaVisitor) return ((StipulaVisitor<? extends T>) visitor).visitNumber(this);
            else return visitor.visitChildren(this);
        }
    }

    public final NumberContext number() throws RecognitionException {
        NumberContext _localctx = new NumberContext(_ctx, getState());
        enterRule(_localctx, 44, RULE_number);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(329);
                _la = _input.LA(1);
                if (!(_la == INT || _la == REAL)) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static final String _serializedATN =
            "\u0004\u00017\u014c\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002" +
                    "\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002" +
                    "\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002" +
                    "\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002" +
                    "\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f" +
                    "\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012" +
                    "\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015" +
                    "\u0002\u0016\u0007\u0016\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000" +
                    "\u0003\u00003\b\u0000\u0001\u0000\u0003\u00006\b\u0000\u0001\u0000\u0001" +
                    "\u0000\u0001\u0000\u0001\u0000\u0004\u0000<\b\u0000\u000b\u0000\f\u0000" +
                    "=\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001" +
                    "\u0001\u0001\u0005\u0001G\b\u0001\n\u0001\f\u0001J\t\u0001\u0001\u0001" +
                    "\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001Q\b\u0001" +
                    "\n\u0001\f\u0001T\t\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0004\u0001" +
                    "Y\b\u0001\u000b\u0001\f\u0001Z\u0001\u0001\u0001\u0001\u0001\u0001\u0001" +
                    "\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001" +
                    "\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002j\b\u0002\n\u0002" +
                    "\f\u0002m\t\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0005" +
                    "\u0003s\b\u0003\n\u0003\f\u0003v\t\u0003\u0001\u0004\u0001\u0004\u0005" +
                    "\u0004z\b\u0004\n\u0004\f\u0004}\t\u0004\u0001\u0004\u0001\u0004\u0001" +
                    "\u0004\u0005\u0004\u0082\b\u0004\n\u0004\f\u0004\u0085\t\u0004\u0001\u0004" +
                    "\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004" +
                    "\u008d\b\u0004\n\u0004\f\u0004\u0090\t\u0004\u0003\u0004\u0092\b\u0004" +
                    "\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004" +
                    "\u0099\b\u0004\n\u0004\f\u0004\u009c\t\u0004\u0003\u0004\u009e\b\u0004" +
                    "\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004" +
                    "\u00a5\b\u0004\u0001\u0004\u0001\u0004\u0004\u0004\u00a9\b\u0004\u000b" +
                    "\u0004\f\u0004\u00aa\u0001\u0004\u0001\u0004\u0004\u0004\u00af\b\u0004" +
                    "\u000b\u0004\f\u0004\u00b0\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004" +
                    "\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005\u00bb\b\u0005" +
                    "\n\u0005\f\u0005\u00be\t\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001" +
                    "\u0005\u0005\u0005\u00c4\b\u0005\n\u0005\f\u0005\u00c7\t\u0005\u0001\u0006" +
                    "\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001" +
                    "\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001" +
                    "\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u00e0" +
                    "\b\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u00e7\b\r\u0001\u000e" +
                    "\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e" +
                    "\u0005\u000e\u00f0\b\u000e\n\u000e\f\u000e\u00f3\t\u000e\u0001\u000e\u0001" +
                    "\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u00fb" +
                    "\b\u000e\n\u000e\f\u000e\u00fe\t\u000e\u0001\u000e\u0001\u000e\u0005\u000e" +
                    "\u0102\b\u000e\n\u000e\f\u000e\u0105\t\u000e\u0001\u000e\u0001\u000e\u0001" +
                    "\u000e\u0001\u000e\u0005\u000e\u010b\b\u000e\n\u000e\f\u000e\u010e\t\u000e" +
                    "\u0001\u000e\u0001\u000e\u0003\u000e\u0112\b\u000e\u0001\u000f\u0001\u000f" +
                    "\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0004\u000f" +
                    "\u011b\b\u000f\u000b\u000f\f\u000f\u011c\u0001\u000f\u0001\u000f\u0001" +
                    "\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u0124\b\u000f\u0001\u0010\u0001" +
                    "\u0010\u0001\u0011\u0003\u0011\u0129\b\u0011\u0001\u0011\u0001\u0011\u0001" +
                    "\u0011\u0003\u0011\u012e\b\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0003" +
                    "\u0012\u0133\b\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u0138" +
                    "\b\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001" +
                    "\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u0144" +
                    "\b\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001" +
                    "\u0016\u0001\u0016\u0000\u0000\u0017\u0000\u0002\u0004\u0006\b\n\f\u000e" +
                    "\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,\u0000\b\u0001" +
                    "\u0000&\'\u0001\u0000),\u0002\u0000\u001f\u001f33\u0002\u0000\u000b\f" +
                    "\u001c\u001c\u0002\u0000\r\u000e\u001d\u001d\u0002\u0000\u0005\u0006\u0018" +
                    "\u001b\u0001\u0000\u0010\u0011\u0001\u000001\u015c\u0000.\u0001\u0000" +
                    "\u0000\u0000\u0002A\u0001\u0000\u0000\u0000\u0004a\u0001\u0000\u0000\u0000" +
                    "\u0006n\u0001\u0000\u0000\u0000\b{\u0001\u0000\u0000\u0000\n\u00b7\u0001" +
                    "\u0000\u0000\u0000\f\u00c8\u0001\u0000\u0000\u0000\u000e\u00cb\u0001\u0000" +
                    "\u0000\u0000\u0010\u00cd\u0001\u0000\u0000\u0000\u0012\u00cf\u0001\u0000" +
                    "\u0000\u0000\u0014\u00d1\u0001\u0000\u0000\u0000\u0016\u00d3\u0001\u0000" +
                    "\u0000\u0000\u0018\u00d5\u0001\u0000\u0000\u0000\u001a\u00e6\u0001\u0000" +
                    "\u0000\u0000\u001c\u00e8\u0001\u0000\u0000\u0000\u001e\u0123\u0001\u0000" +
                    "\u0000\u0000 \u0125\u0001\u0000\u0000\u0000\"\u0128\u0001\u0000\u0000" +
                    "\u0000$\u012f\u0001\u0000\u0000\u0000&\u0134\u0001\u0000\u0000\u0000(" +
                    "\u0143\u0001\u0000\u0000\u0000*\u0145\u0001\u0000\u0000\u0000,\u0149\u0001" +
                    "\u0000\u0000\u0000./\u0005%\u0000\u0000/0\u00053\u0000\u000002\u0005\u0016" +
                    "\u0000\u000013\u0003\u0004\u0002\u000021\u0001\u0000\u0000\u000023\u0001" +
                    "\u0000\u0000\u000035\u0001\u0000\u0000\u000046\u0003\u0006\u0003\u0000" +
                    "54\u0001\u0000\u0000\u000056\u0001\u0000\u0000\u000067\u0001\u0000\u0000" +
                    "\u000078\u0005.\u0000\u000089\u00053\u0000\u00009;\u0003\u0002\u0001\u0000" +
                    ":<\u0003\b\u0004\u0000;:\u0001\u0000\u0000\u0000<=\u0001\u0000\u0000\u0000" +
                    "=;\u0001\u0000\u0000\u0000=>\u0001\u0000\u0000\u0000>?\u0001\u0000\u0000" +
                    "\u0000?@\u0005\u0017\u0000\u0000@\u0001\u0001\u0000\u0000\u0000AB\u0005" +
                    "(\u0000\u0000BC\u0005\u0012\u0000\u0000CH\u0003\u0012\t\u0000DE\u0005" +
                    "\u0003\u0000\u0000EG\u0003\u0012\t\u0000FD\u0001\u0000\u0000\u0000GJ\u0001" +
                    "\u0000\u0000\u0000HF\u0001\u0000\u0000\u0000HI\u0001\u0000\u0000\u0000" +
                    "IK\u0001\u0000\u0000\u0000JH\u0001\u0000\u0000\u0000KL\u0005\u0013\u0000" +
                    "\u0000LM\u0005\u0012\u0000\u0000MR\u0003\u0014\n\u0000NO\u0005\u0003\u0000" +
                    "\u0000OQ\u0003\u0014\n\u0000PN\u0001\u0000\u0000\u0000QT\u0001\u0000\u0000" +
                    "\u0000RP\u0001\u0000\u0000\u0000RS\u0001\u0000\u0000\u0000SU\u0001\u0000" +
                    "\u0000\u0000TR\u0001\u0000\u0000\u0000UV\u0005\u0013\u0000\u0000VX\u0005" +
                    "\u0016\u0000\u0000WY\u0003\n\u0005\u0000XW\u0001\u0000\u0000\u0000YZ\u0001" +
                    "\u0000\u0000\u0000ZX\u0001\u0000\u0000\u0000Z[\u0001\u0000\u0000\u0000" +
                    "[\\\u0001\u0000\u0000\u0000\\]\u0005\u0017\u0000\u0000]^\u0005\u0007\u0000" +
                    "\u0000^_\u0005\u000f\u0000\u0000_`\u0003\u0010\b\u0000`\u0003\u0001\u0000" +
                    "\u0000\u0000ab\u0005&\u0000\u0000bc\u00053\u0000\u0000cd\u0005\u0002\u0000" +
                    "\u0000dk\u00053\u0000\u0000ef\u0005\u0003\u0000\u0000fg\u00053\u0000\u0000" +
                    "gh\u0005\u0002\u0000\u0000hj\u00053\u0000\u0000ie\u0001\u0000\u0000\u0000" +
                    "jm\u0001\u0000\u0000\u0000ki\u0001\u0000\u0000\u0000kl\u0001\u0000\u0000" +
                    "\u0000l\u0005\u0001\u0000\u0000\u0000mk\u0001\u0000\u0000\u0000no\u0005" +
                    "\'\u0000\u0000ot\u00053\u0000\u0000pq\u0005\u0003\u0000\u0000qs\u0005" +
                    "3\u0000\u0000rp\u0001\u0000\u0000\u0000sv\u0001\u0000\u0000\u0000tr\u0001" +
                    "\u0000\u0000\u0000tu\u0001\u0000\u0000\u0000u\u0007\u0001\u0000\u0000" +
                    "\u0000vt\u0001\u0000\u0000\u0000wx\u0005\u000f\u0000\u0000xz\u0003\u0010" +
                    "\b\u0000yw\u0001\u0000\u0000\u0000z}\u0001\u0000\u0000\u0000{y\u0001\u0000" +
                    "\u0000\u0000{|\u0001\u0000\u0000\u0000|~\u0001\u0000\u0000\u0000}{\u0001" +
                    "\u0000\u0000\u0000~\u0083\u0003\u0012\t\u0000\u007f\u0080\u0005\u0003" +
                    "\u0000\u0000\u0080\u0082\u0003\u0012\t\u0000\u0081\u007f\u0001\u0000\u0000" +
                    "\u0000\u0082\u0085\u0001\u0000\u0000\u0000\u0083\u0081\u0001\u0000\u0000" +
                    "\u0000\u0083\u0084\u0001\u0000\u0000\u0000\u0084\u0086\u0001\u0000\u0000" +
                    "\u0000\u0085\u0083\u0001\u0000\u0000\u0000\u0086\u0087\u0005\u0002\u0000" +
                    "\u0000\u0087\u0088\u00053\u0000\u0000\u0088\u0091\u0005\u0012\u0000\u0000" +
                    "\u0089\u008e\u0003\u0014\n\u0000\u008a\u008b\u0005\u0003\u0000\u0000\u008b" +
                    "\u008d\u0003\u0014\n\u0000\u008c\u008a\u0001\u0000\u0000\u0000\u008d\u0090" +
                    "\u0001\u0000\u0000\u0000\u008e\u008c\u0001\u0000\u0000\u0000\u008e\u008f" +
                    "\u0001\u0000\u0000\u0000\u008f\u0092\u0001\u0000\u0000\u0000\u0090\u008e" +
                    "\u0001\u0000\u0000\u0000\u0091\u0089\u0001\u0000\u0000\u0000\u0091\u0092" +
                    "\u0001\u0000\u0000\u0000\u0092\u0093\u0001\u0000\u0000\u0000\u0093\u0094" +
                    "\u0005\u0013\u0000\u0000\u0094\u009d\u0005\u0014\u0000\u0000\u0095\u009a" +
                    "\u0003\u0016\u000b\u0000\u0096\u0097\u0005\u0003\u0000\u0000\u0097\u0099" +
                    "\u0003\u0016\u000b\u0000\u0098\u0096\u0001\u0000\u0000\u0000\u0099\u009c" +
                    "\u0001\u0000\u0000\u0000\u009a\u0098\u0001\u0000\u0000\u0000\u009a\u009b" +
                    "\u0001\u0000\u0000\u0000\u009b\u009e\u0001\u0000\u0000\u0000\u009c\u009a" +
                    "\u0001\u0000\u0000\u0000\u009d\u0095\u0001\u0000\u0000\u0000\u009d\u009e" +
                    "\u0001\u0000\u0000\u0000\u009e\u009f\u0001\u0000\u0000\u0000\u009f\u00a4" +
                    "\u0005\u0015\u0000\u0000\u00a0\u00a1\u0005\u0012\u0000\u0000\u00a1\u00a2" +
                    "\u0003 \u0010\u0000\u00a2\u00a3\u0005\u0013\u0000\u0000\u00a3\u00a5\u0001" +
                    "\u0000\u0000\u0000\u00a4\u00a0\u0001\u0000\u0000\u0000\u00a4\u00a5\u0001" +
                    "\u0000\u0000\u0000\u00a5\u00a6\u0001\u0000\u0000\u0000\u00a6\u00a8\u0005" +
                    "\u0016\u0000\u0000\u00a7\u00a9\u0003\u001a\r\u0000\u00a8\u00a7\u0001\u0000" +
                    "\u0000\u0000\u00a9\u00aa\u0001\u0000\u0000\u0000\u00aa\u00a8\u0001\u0000" +
                    "\u0000\u0000\u00aa\u00ab\u0001\u0000\u0000\u0000\u00ab\u00ac\u0001\u0000" +
                    "\u0000\u0000\u00ac\u00ae\u0005\u0001\u0000\u0000\u00ad\u00af\u0003\u001e" +
                    "\u000f\u0000\u00ae\u00ad\u0001\u0000\u0000\u0000\u00af\u00b0\u0001\u0000" +
                    "\u0000\u0000\u00b0\u00ae\u0001\u0000\u0000\u0000\u00b0\u00b1\u0001\u0000" +
                    "\u0000\u0000\u00b1\u00b2\u0001\u0000\u0000\u0000\u00b2\u00b3\u0005\u0017" +
                    "\u0000\u0000\u00b3\u00b4\u0005\u0007\u0000\u0000\u00b4\u00b5\u0005\u000f" +
                    "\u0000\u0000\u00b5\u00b6\u0003\u0010\b\u0000\u00b6\t\u0001\u0000\u0000" +
                    "\u0000\u00b7\u00bc\u0003\u0012\t\u0000\u00b8\u00b9\u0005\u0003\u0000\u0000" +
                    "\u00b9\u00bb\u0003\u0012\t\u0000\u00ba\u00b8\u0001\u0000\u0000\u0000\u00bb" +
                    "\u00be\u0001\u0000\u0000\u0000\u00bc\u00ba\u0001\u0000\u0000\u0000\u00bc" +
                    "\u00bd\u0001\u0000\u0000\u0000\u00bd\u00bf\u0001\u0000\u0000\u0000\u00be" +
                    "\u00bc\u0001\u0000\u0000\u0000\u00bf\u00c0\u0005\u0002\u0000\u0000\u00c0" +
                    "\u00c5\u0003\u0014\n\u0000\u00c1\u00c2\u0005\u0003\u0000\u0000\u00c2\u00c4" +
                    "\u0003\u0014\n\u0000\u00c3\u00c1\u0001\u0000\u0000\u0000\u00c4\u00c7\u0001" +
                    "\u0000\u0000\u0000\u00c5\u00c3\u0001\u0000\u0000\u0000\u00c5\u00c6\u0001" +
                    "\u0000\u0000\u0000\u00c6\u000b\u0001\u0000\u0000\u0000\u00c7\u00c5\u0001" +
                    "\u0000\u0000\u0000\u00c8\u00c9\u0007\u0000\u0000\u0000\u00c9\u00ca\u0005" +
                    "3\u0000\u0000\u00ca\r\u0001\u0000\u0000\u0000\u00cb\u00cc\u0007\u0001" +
                    "\u0000\u0000\u00cc\u000f\u0001\u0000\u0000\u0000\u00cd\u00ce\u00053\u0000" +
                    "\u0000\u00ce\u0011\u0001\u0000\u0000\u0000\u00cf\u00d0\u00053\u0000\u0000" +
                    "\u00d0\u0013\u0001\u0000\u0000\u0000\u00d1\u00d2\u00053\u0000\u0000\u00d2" +
                    "\u0015\u0001\u0000\u0000\u0000\u00d3\u00d4\u00053\u0000\u0000\u00d4\u0017" +
                    "\u0001\u0000\u0000\u0000\u00d5\u00d6\u0003\u0014\n\u0000\u00d6\u00d7\u0005" +
                    "\b\u0000\u0000\u00d7\u00d8\u0003\"\u0011\u0000\u00d8\u0019\u0001\u0000" +
                    "\u0000\u0000\u00d9\u00e7\u0005\u001f\u0000\u0000\u00da\u00db\u0003(\u0014" +
                    "\u0000\u00db\u00dc\u0005\t\u0000\u0000\u00dc\u00df\u00053\u0000\u0000" +
                    "\u00dd\u00de\u0005\u0003\u0000\u0000\u00de\u00e0\u00053\u0000\u0000\u00df" +
                    "\u00dd\u0001\u0000\u0000\u0000\u00df\u00e0\u0001\u0000\u0000\u0000\u00e0" +
                    "\u00e7\u0001\u0000\u0000\u0000\u00e1\u00e2\u0003(\u0014\u0000\u00e2\u00e3" +
                    "\u0005\n\u0000\u0000\u00e3\u00e4\u0007\u0002\u0000\u0000\u00e4\u00e7\u0001" +
                    "\u0000\u0000\u0000\u00e5\u00e7\u0003\u001c\u000e\u0000\u00e6\u00d9\u0001" +
                    "\u0000\u0000\u0000\u00e6\u00da\u0001\u0000\u0000\u0000\u00e6\u00e1\u0001" +
                    "\u0000\u0000\u0000\u00e6\u00e5\u0001\u0000\u0000\u0000\u00e7\u001b\u0001" +
                    "\u0000\u0000\u0000\u00e8\u00e9\u0005\"\u0000\u0000\u00e9\u00ea\u0005\u0012" +
                    "\u0000\u0000\u00ea\u00eb\u0003\"\u0011\u0000\u00eb\u00ec\u0005\u0013\u0000" +
                    "\u0000\u00ec\u00ed\u0005\u0016\u0000\u0000\u00ed\u00f1\u0003\u001a\r\u0000" +
                    "\u00ee\u00f0\u0003\u001a\r\u0000\u00ef\u00ee\u0001\u0000\u0000\u0000\u00f0" +
                    "\u00f3\u0001\u0000\u0000\u0000\u00f1\u00ef\u0001\u0000\u0000\u0000\u00f1" +
                    "\u00f2\u0001\u0000\u0000\u0000\u00f2\u00f4\u0001\u0000\u0000\u0000\u00f3" +
                    "\u00f1\u0001\u0000\u0000\u0000\u00f4\u0103\u0005\u0017\u0000\u0000\u00f5" +
                    "\u00f6\u0005#\u0000\u0000\u00f6\u00f7\u0003\"\u0011\u0000\u00f7\u00f8" +
                    "\u0005\u0016\u0000\u0000\u00f8\u00fc\u0003\u001a\r\u0000\u00f9\u00fb\u0003" +
                    "\u001a\r\u0000\u00fa\u00f9\u0001\u0000\u0000\u0000\u00fb\u00fe\u0001\u0000" +
                    "\u0000\u0000\u00fc\u00fa\u0001\u0000\u0000\u0000\u00fc\u00fd\u0001\u0000" +
                    "\u0000\u0000\u00fd\u00ff\u0001\u0000\u0000\u0000\u00fe\u00fc\u0001\u0000" +
                    "\u0000\u0000\u00ff\u0100\u0005\u0017\u0000\u0000\u0100\u0102\u0001\u0000" +
                    "\u0000\u0000\u0101\u00f5\u0001\u0000\u0000\u0000\u0102\u0105\u0001\u0000" +
                    "\u0000\u0000\u0103\u0101\u0001\u0000\u0000\u0000\u0103\u0104\u0001\u0000" +
                    "\u0000\u0000\u0104\u0111\u0001\u0000\u0000\u0000\u0105\u0103\u0001\u0000" +
                    "\u0000\u0000\u0106\u0107\u0005$\u0000\u0000\u0107\u0108\u0005\u0016\u0000" +
                    "\u0000\u0108\u010c\u0003\u001a\r\u0000\u0109\u010b\u0003\u001a\r\u0000" +
                    "\u010a\u0109\u0001\u0000\u0000\u0000\u010b\u010e\u0001\u0000\u0000\u0000" +
                    "\u010c\u010a\u0001\u0000\u0000\u0000\u010c\u010d\u0001\u0000\u0000\u0000" +
                    "\u010d\u010f\u0001\u0000\u0000\u0000\u010e\u010c\u0001\u0000\u0000\u0000" +
                    "\u010f\u0110\u0005\u0017\u0000\u0000\u0110\u0112\u0001\u0000\u0000\u0000" +
                    "\u0111\u0106\u0001\u0000\u0000\u0000\u0111\u0112\u0001\u0000\u0000\u0000" +
                    "\u0112\u001d\u0001\u0000\u0000\u0000\u0113\u0124\u0005\u001f\u0000\u0000" +
                    "\u0114\u0115\u0003\"\u0011\u0000\u0115\u0116\u0005!\u0000\u0000\u0116" +
                    "\u0117\u0005\u000f\u0000\u0000\u0117\u0118\u00053\u0000\u0000\u0118\u011a" +
                    "\u0005\u0016\u0000\u0000\u0119\u011b\u0003\u001a\r\u0000\u011a\u0119\u0001" +
                    "\u0000\u0000\u0000\u011b\u011c\u0001\u0000\u0000\u0000\u011c\u011a\u0001" +
                    "\u0000\u0000\u0000\u011c\u011d\u0001\u0000\u0000\u0000\u011d\u011e\u0001" +
                    "\u0000\u0000\u0000\u011e\u011f\u0005\u0017\u0000\u0000\u011f\u0120\u0005" +
                    "\u0007\u0000\u0000\u0120\u0121\u0005\u000f\u0000\u0000\u0121\u0122\u0005" +
                    "3\u0000\u0000\u0122\u0124\u0001\u0000\u0000\u0000\u0123\u0113\u0001\u0000" +
                    "\u0000\u0000\u0123\u0114\u0001\u0000\u0000\u0000\u0124\u001f\u0001\u0000" +
                    "\u0000\u0000\u0125\u0126\u0003\"\u0011\u0000\u0126!\u0001\u0000\u0000" +
                    "\u0000\u0127\u0129\u0005\f\u0000\u0000\u0128\u0127\u0001\u0000\u0000\u0000" +
                    "\u0128\u0129\u0001\u0000\u0000\u0000\u0129\u012a\u0001\u0000\u0000\u0000" +
                    "\u012a\u012d\u0003$\u0012\u0000\u012b\u012c\u0007\u0003\u0000\u0000\u012c" +
                    "\u012e\u0003\"\u0011\u0000\u012d\u012b\u0001\u0000\u0000\u0000\u012d\u012e" +
                    "\u0001\u0000\u0000\u0000\u012e#\u0001\u0000\u0000\u0000\u012f\u0132\u0003" +
                    "&\u0013\u0000\u0130\u0131\u0007\u0004\u0000\u0000\u0131\u0133\u0003$\u0012" +
                    "\u0000\u0132\u0130\u0001\u0000\u0000\u0000\u0132\u0133\u0001\u0000\u0000" +
                    "\u0000\u0133%\u0001\u0000\u0000\u0000\u0134\u0137\u0003(\u0014\u0000\u0135" +
                    "\u0136\u0007\u0005\u0000\u0000\u0136\u0138\u0003(\u0014\u0000\u0137\u0135" +
                    "\u0001\u0000\u0000\u0000\u0137\u0138\u0001\u0000\u0000\u0000\u0138\'\u0001" +
                    "\u0000\u0000\u0000\u0139\u0144\u0003,\u0016\u0000\u013a\u0144\u00053\u0000" +
                    "\u0000\u013b\u0144\u0005 \u0000\u0000\u013c\u013d\u0005\u0012\u0000\u0000" +
                    "\u013d\u013e\u0003\"\u0011\u0000\u013e\u013f\u0005\u0013\u0000\u0000\u013f" +
                    "\u0144\u0001\u0000\u0000\u0000\u0140\u0144\u0005/\u0000\u0000\u0141\u0144" +
                    "\u0005\u001f\u0000\u0000\u0142\u0144\u0007\u0006\u0000\u0000\u0143\u0139" +
                    "\u0001\u0000\u0000\u0000\u0143\u013a\u0001\u0000\u0000\u0000\u0143\u013b" +
                    "\u0001\u0000\u0000\u0000\u0143\u013c\u0001\u0000\u0000\u0000\u0143\u0140" +
                    "\u0001\u0000\u0000\u0000\u0143\u0141\u0001\u0000\u0000\u0000\u0143\u0142" +
                    "\u0001\u0000\u0000\u0000\u0144)\u0001\u0000\u0000\u0000\u0145\u0146\u0003" +
                    ",\u0016\u0000\u0146\u0147\u0005\u0004\u0000\u0000\u0147\u0148\u0003,\u0016" +
                    "\u0000\u0148+\u0001\u0000\u0000\u0000\u0149\u014a\u0007\u0007\u0000\u0000" +
                    "\u014a-\u0001\u0000\u0000\u0000!25=HRZkt{\u0083\u008e\u0091\u009a\u009d" +
                    "\u00a4\u00aa\u00b0\u00bc\u00c5\u00df\u00e6\u00f1\u00fc\u0103\u010c\u0111" +
                    "\u011c\u0123\u0128\u012d\u0132\u0137\u0143";
    public static final ATN _ATN =
            new ATNDeserializer().deserialize(_serializedATN.toCharArray());

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }
}