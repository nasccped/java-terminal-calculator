Java Terminal Calculator 🧮
==========================

This project aims to help a friend in a side project and work on
knowledge in software structuring + OOP.

Requirements 📋
--------------

To run this project, you'll need:

1. [git](https://git-scm.com/)
2. [java & javac](https://www.oracle.com/java/)

Usage 🪁
-------

1. Clone the repository
```sh
git clone --depth 1 https://github.com/nasccped/java-terminal-calculator && \
cd java-terminal-calculator
```
2. Compile the source code and run

This project was built under the
[kojamp](https://github.com/nasccped/kojamp) project manager, so,
there's three ways to compile and run it:

<details><summary>If you have `kojamp`</summary></br>

compile and run using the `kojamp` CLI:
```sh
make build run
# if you doesn't have make at your machine, you can still use kojamp
# kojamp build && kojamp run
```

</details>

<details><summary>If you don't have kojamp (and is in a 'sh' terminal)</summary></br>

turn the shell script into executable + build and run the source:
```sh
chmod +x ./maksh.sh && ./maksh.sh build run
```

</details>

<details><summary>If you don't have kojamp (and is in Windows - runs '.bat')</summary></br>

compile and run:
```sh
./makbat.bat build run
```

</details>

License
-------

This project is under the [MIT license](./LICENSE).
