CC=javac
RC=java
SRC=./src
OUT=./out
TST=./tests
PKGNS=. utils
PKGLS=$(addprefix $(SRC)/nasccped/jtc/,$(PKGNS))
FLS=$(foreach direc,$(PKGLS),$(wildcard $(direc)/*.java))
TST_FLS=$(wildcard $(SRC)/$(TST)/*.java)
MAIN_RUN=nasccped/jtc/JavaTermCalc
MAIN_TEST=JTCTests
EXPEC=$(foreach javaf,$(FLS),$(patsubst ./src/nasccped/jtc/%.java,out/nasccped/jtc/%.class,$(javaf)))

all: clean build run

clean: $(OUT)
	rm -rf $<

build: $(FLS)
	$(CC) $^ -d $(OUT)

run: $(EXPEC)
	$(RC) --class-path $(OUT) $(MAIN_RUN)

tests: $(filter-out %JavaTermCalc.java, $(FLS)) $(TST_FLS)
	$(CC) $^ -d $(TST)
	$(RC) --class-path $(TST) $(MAIN_TEST)

tclean:
	@if [ -d $(TST) ]; then \
		echo "rm -rf $(TST)"; \
		rm -rf $(TST);        \
	else \
		echo "$(TST) dir doesn't exists. Nothing to remove!"; \
	fi;

.PHONY: all clean build run tests tclean
