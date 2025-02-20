Java Terminal Calculator 🧮
==========================

This project aims to help a friend in a side project and work on
knowledge in software structuring + OOP.

Project Requirements 📋
----------------------

To run this project, you'll need:

1. **Git:** you will need some kind of VCS
   _(Version Control System)_. I strongly recomend
   [Git][git-website].
2. **OpenJDK:** you will need the official _Java tools_ collection
   that can be found at the [Oracle website][oracle-website].
3. **Make:** Instead of using a specific IDE for Java, this program
   was developed in an external IDE. To avoid compiling all the files
   manually you can use the Makefile present in the root directory.
   Just have [GNU Make][gnu-make-website] installed on your machine.

> [!WARNING]
> 
> This project was built in OpenJDK 17 (17.0.14) from
> **Alpine Linux** package manager (`apk`). If you're using an older
> version, it probably won't work. If you're using a newer version /
> other OS, it may not work. If so, consider opening an Issue at
> [issues page][issue-page]

Usage 🪁
-------

Here are some steps to get, compile and use this project:

1. **Clone:** open you terminal and clone the repository:
   ```shell
   git clone https://github.com/nasccped/java-terminal-calculator
   ```

2. **Clear:** enter on the cloned repo and clean the unnecessary git
   folder:
   ```shell
   cd java-terminal-calculator
   rm -rf .git # for bash/shell terminal
   # If you're on Windows Powershell, you can use this:
   # Remove-Item -Recurse -Force .git
   ```

3. **Compile:** use the Makefile tool to compile the program. This
   command will print something like _"javac src/..."_. If is there
   some error, the program won't be compiled and an error message
   will be displayed.
   ```shell
   make build
   ```

4. **Enjoy:** now, you can run the program by using the same
   Makefile:
   ```shell
   make run
   ```

5. **Source clear (extra):** If you've already used the program and
   want to remove all compiled outputs from you machine, you can use
   the Makefile for it too:
   ```shell
   make clean
   ```

Extra ☝️
-------

Did you want to know more about this project? Check the
[doc][doc-readme] folder!

<!-- links -->
[git-website]: https://git-scm.com/
[oracle-website]: https://www.oracle.com/
[gnu-make-website]: https://www.gnu.org/software/make/
[issue-page]: https://github.com/nasccped/java-terminal-calculator/issues
[doc-readme]: https://github.com/nasccped/java-terminal-calculator/blob/main/doc/doc-README.md
