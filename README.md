Java Terminal Calculator 🧮
==========================

This project aims to help a friend in a side project and work on
knowledge in software structuring + OOP.

> [!NOTE]
>
> This page is just a short brief of the project. If you want to get
> full details, take a look at
> [`docs/00-README.md`](./blob/main/docs/00-README.md)!

Requirements 📋
--------------

To run this project, you'll need:

1. [git](https://git-scm.com/)
2. [java & javac](https://www.oracle.com/java/)
3. [kojamp](https://crates.io/crates/kojamp) project manager
   _(optional)_

Usage 🪁
-------

Here are some steps to get, compile and use this project:

1. **Clone:** open you terminal and clone the repository
   ```sh
   git clone --depth 1 https://github.com/nasccped/java-terminal-calculator
   ```

2. **Remove git:** enter on the cloned repo and clean the unnecessary git
   folder
   ```sh
   cd java-terminal-calculator
   rm -rf .git # for bash/shell terminal
   # If you're on Windows Powershell, you can use this:
   # Remove-Item -Recurse -Force .git
   ```

3. **Running:** compile the program's source code and run the
   bytecode
   ```sh
   kojamp build && kojamp run
   ```

> [!TIP]
>
> Alternative ways to compile and run can be found at `docs`
> directory!
