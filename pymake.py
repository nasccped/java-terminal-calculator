#!/usr/bin/env python

# required lib to command exists check
import shutil as st

# required commands for our script
COMPILE_CMD = "javac"
RUN_CMD = "java"

def command_exists(command_name: str) -> bool:
    return st.which(command_name) is not None

def main():

    # if compile command doesn't exists
    if not command_exists(COMPILE_CMD):
        print(f"  \x1b[1;32m{COMPILE_CMD}\x1b[0m command not found. \x1b[1;31mAborting\x1b[0m!")
        quit()

    # if run command doesn't exists
    if not command_exists(RUN_CMD):
        print(f"  \x1b[1;32m{RUN_CMD}\x1b[0m command not found. \x1b[1;31mAborting\x1b[0m!")
        quit()

    print("Everything will work \x1b[1;32mout fine\x1b[0m!")

if __name__ == "__main__":
    main()
