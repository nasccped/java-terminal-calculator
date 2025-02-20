#!/usr/bin/env python

# required lib to command exists check
import shutil as st
# to return, execute and find
from typing import Optional
import os

# required commands for our script
COMPILE_CMD = "javac"
RUN_CMD = "java"

# project dirs
OUT_DIR = os.path.join(".", "out")
SRC_DIR = os.path.join(".", "src")

BYTECODE_EXT = ".class"
FINAL_TARGET = "nasccped.jtc.JavaTermCalc"

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
            result.append(os.path.join(at, item))

        # if no file extension (possibly a dir)
        elif "." not in item:
            # check if it's valid
            childs = get_java_files(os.path.join(at, item))
            if childs is not None:
                result.extend(childs)

    return result

# clear compilation's output
def py_clear_by_dir(target: str):

    try:
        st.rmtree(target)
        print(f"  {target} has been \x1b[1;32mremoved\x1b[0m!")

    except:
        print(f"  {target} couldn't be \x1b[1;32mremoved\x1b[0m!")
        print("  It probably \x1b[1;31mdoesn't\x1b[0m exists")

def py_build_from_files(files: Optional[list[str]]):

    if files is None:
        print("  The program \x1b[1;31mcouldn't\x1b[1;31m be compiled")
        print("  The source code is missing (returned \x1b[1;31mNone\x1b[0m)")
        print()
        print("  Try using \x1b[1;33m`make build`\x1b[0m tool instead!")
        print()
        print(
            "  You can also check documentation at \x1b[1;32m"
            + "https://github.com/nasccped/java-terminal-calculator/blob/main/doc/doc-README.md"
            + " for more information."
        )
        return

    print("  The program is read to be \x1b[1;32mcompiled\x1b[0m!")
    print("  Any \x1b[1;31merror\x1b[0m or \x1b[1;33mwarning\x1b[0m will be displayed bellow:")
    print()

    os.system(f"{COMPILE_CMD} {' '.join(files)} -d {OUT_DIR}")

def py_run_from(target: str):

    w_out_path = os.path.join(OUT_DIR, target.replace(".", "/"), BYTECODE_EXT)

    abs = os.path.abspath(w_out_path)

    if not os.path.exists(abs):
        print(f"  Target class {abs} \x1b[1;31mcouldn't\x1b[0m be found")
        print("  Try using \x1b[1;33m`build`\x1b[0m command earlier!")
        print()
        print("  If this behavior persists, try using the Makefile instead")
        print("  or you can also check the repository documentation:")
        print("    \x1b[1;32mhttps://github.com/nasccped/java-terminal-calculator/blob/main/doc/doc-README.md\x1b[0m")
        return
    
    print("  The program is \x1b[1;32mready\x1b[0m to run...")

    os.system(f"{RUN_CMD} --class-path {OUT_DIR} {target}")

def main():
    print()

    # if compile command doesn't exists
    if not command_exists(COMPILE_CMD):
        print(f"  \x1b[1;32m{COMPILE_CMD}\x1b[0m command not found. \x1b[1;31mAborting\x1b[0m!")
        print()
        quit()

    # if run command doesn't exists
    if not command_exists(RUN_CMD):
        print(f"  \x1b[1;32m{RUN_CMD}\x1b[0m command not found. \x1b[1;31mAborting\x1b[0m!")
        print()
        quit()

    print("  Everything will work \x1b[1;32mout fine\x1b[0m!")
    print()

if __name__ == "__main__":
    main()
