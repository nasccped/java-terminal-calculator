CMD = kojamp

build:
	$(CMD) build

run:
	$(CMD) run

# fast run
fr:
	$(CMD) build && $(CMD) run

.PHONY: fr run build
