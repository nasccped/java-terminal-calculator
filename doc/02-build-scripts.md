02 | Build Scripts
==================

There are two different build scripts available for use:
1. A Makefile
2. A Python file (named as `pymake.py`)

They both works well, so... which one should you use?

About The Makefile
------------------

Makefile is the most common build script for programs based on **C**
lang, but it works with any language compilation/'interpretation'
call.

They're simple to build, use and understand. They commonly come
by default with some UNIX distro/OS. It can also be easily installed
with a linux package manager. So... what's the problem?

The GNU Make isn't easy to install on a Windows OS, even with winget.

I strongly recommend downloading GNU Make via your OS package manager
because it's a lot simpler. But if you're at Windows _(or if you are
scared by the GNU collection 😰)_, you can use the python script,
which I'll talk about next.

About The Python Script
-----------------------

The python builder script for this program, called `pymake.py` is an
alternative for those scared by bash syntax and error reports at
command line. It's a little bit complex compared with the Makefile,
but still easy to use.

Usage
-----

All the steps for correct usage of this project can be found at the
root README.md, on [usage] section.

Potential Bugs
--------------

I've already tested both scripts (python and make) and they don't
have bugs 🐞. The occurrence of bugs in they both is extremely rare, but
if it does happen, it could be due to the following factors:

- dir names: The **source dir** must always be _"src"_, the
  **output dir** must always be _"out"_, so on. If the
  **source dir** is renamed, the builder won't find the him. If some
  folder inside _"src"_ is renamed, the builder won't find the
  `.java` files. It'll end in an error.

- variable names: There are several variables in `Makefile` /
  `pymake.py`, they serve to store the name of **dirs** and **files**
  in our project, if these names are changed, the builder won't find
  the target files, even if dir names are untouched!

If you found any error, it's probably by some of the actions cited
above. If you don't know how to fix, consider removing the local
project and cloning it again by following the [usage][usage-section]
steps. But, if this error persists, please, open an issue.

[usage-section]: https://github.com/nasccped/java-terminal-calculator?tab=readme-ov-file#usage-
