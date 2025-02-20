#!/usr/bin/env python

# required lib to command exists check
import shutil as st
# to returns and finds
from typing import Optional
import os

# required commands for our script
COMPILE_CMD = "javac"
RUN_CMD = "java"

# project dirs
OUT_DIR = os.path.join(".", "out")

def clear_terminal():
    print("\x1b[2J\x1b[H", end = "")

def command_exists(command_name: str) -> bool:
    return st.which(command_name) is not None

# function to get all java files (recursively) from a given directory
# can return None if directory doesn't exists
# also can raise an error if theres a file with no file extension:
#       - some_dir/file
def get_java_files(at: str) -> Optional[list[str]]:

    # check if path exists
    abspath = os.path.abspath(at)
    if not os.path.exists(abspath):
        return None

    # starts the list
    result = list()

    # foreach item at the given path
    for item in os.listdir(at):

        # if it's a java file
        if item.endswith(".java"):
            result.append(item)

        # if no file extension (possibly a dir)
        elif "." not in item:
            # check if it's valid
            childs = get_java_files(os.path.join(at, item))
            if childs is not None:
                result.extend(childs)

    return result

def py_clear() -> bool:

    try:
        st.rmtree(OUT_DIR)
        return True

    except:
        return False

def main():

    clear_terminal()

    # if compile command doesn't exists
    if not command_exists(COMPILE_CMD):
        print(f"  \x1b[1;32m{COMPILE_CMD}\x1b[0m command not found. \x1b[1;31mAborting\x1b[0m!")
        quit()

    # if run command doesn't exists
    if not command_exists(RUN_CMD):
        print(f"  \x1b[1;32m{RUN_CMD}\x1b[0m command not found. \x1b[1;31mAborting\x1b[0m!")
        quit()

    print("  Everything will work \x1b[1;32mout fine\x1b[0m!")

if __name__ == "__main__":
    main()
