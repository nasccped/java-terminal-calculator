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
3. **Make or Python:** Instead of using a specific IDE for Java, this
   program was developed in an external IDE. To avoid compiling all
   the files manually you can use the Makefile present in the root
   directory. Just have [GNU Make][gnu-make-website] installed on
   your machine. _(Recently I added a builder in **Python**. You can
   use it too. Ceck third step at [Usage](#usage-) section)_

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

2. **Remove git:** enter on the cloned repo and clean the unnecessary git
   folder:
   ```shell
   cd java-terminal-calculator
   rm -rf .git # for bash/shell terminal
   # If you're on Windows Powershell, you can use this:
   # Remove-Item -Recurse -Force .git
   ```

3. **Compile, run and clear:** to use this program, you'll need to
   compile and run it, right? instead of using and IDE, I chose
   to use two build scripts:
   - Makefile, a simple but efficient one;
   - Python, an alternative for those that don't have GNU make on
     their machines.

   | [![NOTE BADGE][note-badge]](#) | Use the following table as guide to use both scripts. You can chose the one you prefer. If something goes wrong, consider read the [doc][doc-readme] / opening an issue |
   |-|-|


   <table>
      <tr>
         <th>        </th>
         <th>Makefile</th>
         <th>Python  </th>
      </tr>
      <tr>
        <th align="left">Script call</th>
          <td>make                                         </td>
          <td>python (maybe python3 if you're at UNIX like)</td>
      </tr>
      <tr>
        <th align="left">Usage</th>
          <td>make <i>command</i>                                                     </td>
          <td>python pymake.py <i>command</i> (read the tip section bellow this table)</td>
      </tr>
      <tr>
         <th align="left">Command 1 (all / no args)</th>
           <td> -                                                                </td>
           <td>print the script usage, such as available commands and his effects</td>
      </tr>
      <tr>
         <th align="left">Command 2 (build)</th>
           <td colspan="2">build the source code outputs (.class files)</td>
      </tr>
      <tr>
         <th align="left">Command 3 (run)</th>
           <td colspan="2">run the compilation outputs (.class files)</td>
      </tr>
      <tr>
         <th align="left">Command 4 (clean)</th>
           <td colspan="2">clear the compilation outpus (.class files)</td>
      </tr>
   </table>

   <table>
      <tr>
         <th colspan="2" align="center"><img alt="TIP BADGE" src="https://img.shields.io/badge/tip-238636?style=for-the-badge"></th>
      <tr>
      <tr>
         <th>1</th>
         <td>
            If you're at an UNIX like system, you can turn the
            python's builder into an executable with the following command:</br>
            <strong>chmod +x pymake.py</strong></br>
            Now, you can run the script by calling
            `./pymake.py \<arg\>` instead of `python pymake.py`
         </td>
      </tr>
      <tr>
         <th>2</th>
         <td>
            You can use multiple commands at once, but make sure if
            they're at the correct order:</br>
            <strong>./pymake.py build run clean</strong>
            <i>will work</i></br>
            <strong>./pymake.py run clean build</strong>
            <i>won't work. How can JVM execute a not compiled source?</i>
         </td>
      </tr>
   </table>

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

<!-- badges -->
[note-badge]: https://img.shields.io/badge/note-1F6FEB?style=for-the-badge
