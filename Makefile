CC=javac
RC=java
SRC=./src
OUT=./out
TST=./tests
PKGNS=. utils calculator
PKGLS=$(addprefix $(SRC)/nasccped/jtc/,$(PKGNS))
FLS=$(foreach direc,$(PKGLS),$(wildcard $(direc)/*.java))
TST_FLS=$(wildcard $(SRC)/$(TST)/*.java)
MAIN_RUN=nasccped/jtc/JavaTermCalc
MAIN_TEST=JTCTests
EXPEC=$(foreach javaf,$(FLS),$(patsubst ./src/nasccped/jtc/%.java,out/nasccped/jtc/%.class,$(javaf)))
TST_EXPEC=$(foreach classf,$(filter-out %JavaTermCalc.class,$(EXPEC)),$(patsubst out/%,tests/%,$(classf))) $(TST)/$(MAIN_TEST).class

all: clean build run

clean: $(OUT)
	rm -rf $<

build: $(FLS)
	$(CC) $^ -d $(OUT)

run: $(EXPEC)
	$(RC) --class-path $(OUT) $(MAIN_RUN)

bdtst: $(filter-out %JavaTermCalc.java, $(FLS)) $(TST_FLS)
	$(CC) $^ -d $(TST)

rntst: $(TST_EXPEC)
	$(RC) --class-path $(TST) $(MAIN_TEST)

cltst: $(TST)
	rm -rf $<

.PHONY: all clean build run tests tclean
