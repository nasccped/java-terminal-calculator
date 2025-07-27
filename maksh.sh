#!/usr/bin/sh

CC=javac
RC=java
SRC=src
OUT=out
MAIN=JavaTermCalc

RED="\e[91m"
GREEN="\e[92m"
CYAN="\e[96m"
RESET="\e[0m"
VALID_SUBCOMMANDS=("b" "build" "c" "clean" "h" "help" "r" "run")

abort() {
  echo "Aborting"
  exit $1
}

test_subcmd_validity() {
  for subcmd in "${VALID_SUBCOMMANDS[@]}"; do
    if [[ "$subcmd" == "$1" ]]; then
      return
    fi
  done
  echo -e "The \`$RED$1$RESET\` isn't a valid subcommand"
  echo -e "Try using \`${GREEN}./maksh.sh help${RESET}\`"
  abort 1
}

build_bytecode() {
  echo -e "Building ${GREEN}source's bytecode${RESET}"
  $CC $(find $SRC -type f | grep ".java") -d $OUT
  if [ ! $? -eq 0 ]; then
    echo -e "Previous command returned an ${RED}error${RESET}"
    abort 1
  fi
}

clear_bytecode() {
  echo -e "Cleaning ${GREEN}source's bytecode${RESET}"
  if [ ! -d $OUT ]; then
    echo -e "'out' dir doesn't exists. ${RED}Nothing to clear${RESET}!"
    abort 0
  fi
  rm -rf $OUT
}

help_message() {
  echo "Available subcommands are:"
  echo "  - b | build"
  echo "  - c | clean"
  echo "  - h | help"
  echo "  - r | run"
}

run_bytecode() {
  local main_run="$OUT/$MAIN.class"
  if [ ! -f $main_run ]; then
    echo -e "The '$CYAN$main_run$RESET' file ${RED}doesn't exists${RESET}"
    echo -e "Try using \`${GREEN}./maksh.sh build${RESET}\` before"
    abort 1
  fi
  echo -e "Running the '$GREEN$main_run$RESET' file..."
  sleep 2
  $RC --class-path $OUT $MAIN
}

main() {
  for subcmd in "$@"; do
    test_subcmd_validity $subcmd
  done
  for subcmd in "$@"; do
    case $subcmd in
      "b" | "build")
        build_bytecode
        ;;
      "c" | "clean")
        clear_bytecode
        ;;
      "h" | "help")
        help_message
        ;;
      *)
        run_bytecode
        ;;
    esac
  done
}

main $@
