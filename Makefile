CC=javac
RC=java
SRC=./src
OUT=./out
PKGNS=nasccped.jtc nasccped.jtc/utils
PKGLS=$(addprefix $(SRC)/,$(PKGNS))
FLS=$(foreach direc,$(PKGLS),$(wildcard $(direc)/*.java))
MAIN_RUN=nasccped/jtc/JavaTermCalc
EXPEC=$(foreach javaf,$(FLS),$(patsubst ./src/nasccped.jtc/%.java,out/nasccped/jtc/%.class,$(javaf)))

all: clean build run

clean: $(OUT)
	rm -rf $<

build: $(FLS)
	$(CC) $^ -d $(OUT)

run: $(EXPEC)
	$(RC) --class-path $(OUT) $(MAIN_RUN)

.PHONY: all clean build run
