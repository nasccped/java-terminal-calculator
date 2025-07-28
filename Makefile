CMD=kojamp
OUT_DIR=out
OUT_FILE=$(OUT_DIR)/JavaTermCalc.class

a: all

all:
	@echo "Commands:"
	@echo "  [a]ll (prints this screen)"
	@echo "  [b]uild"
	@echo "  [c]lean"
	@echo "  [r]un"

b: build
r: run
c: clean

build:
	@echo "$(CMD) build"
	@$(CMD) build >/dev/null && echo "Ok"

run: $(OUT_FILE)
	$(CMD) run

$(OUT_FILE):
	@echo "$(CMD) build"
	@$(CMD) build >/dev/null && echo "OK"

clean: $(OUT_DIR)
	rm -rf $<

.PHONY: a all b build c clean r run
