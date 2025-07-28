@echo off
setlocal enabledelayedexpansion

set CC=javac
set RC=java
set SRC=src
set OUT=out
set MAIN=JavaTermCalc

set RED=[91m
set GREEN=[92m
set CYAN=[96m
set RESET=[0m

set VALID_SUBCOMMANDS=b build c clean h help r run

set jfiles=

call :main %*
exit /b

:abort
echo Aborting
exit /b %1

:test_subcmd_validity
for %%s in (%VALID_SUBCOMMANDS%) do (
    if "%%s"=="%1" (
        exit /b 0
    )
)
echo The `%RED%%1%RESET%` isn't a valid subcommand
echo Try using `%GREEN%maksh.bat help%RESET%`
call :abort 1
exit /b

:build_bytecode
for /r src %%f in (*.java) do (
    set "jfiles=!jfiles! %%f"
)
echo Building %GREEN%source's bytecode%RESET%
%CC% %jfiles% -d %OUT%
if not errorlevel 0 (
    echo Previous command returned an %RED%error%RESET%
    call :abort 1
)
exit /b

:clear_bytecode
echo Cleaning %GREEN%source's bytecode%RESET%
if not exist %OUT% (
    echo 'out' dir doesn't exists. %RED%Nothing to clear%RESET%!
    call :abort 0
)
rmdir /s /q %OUT%
exit /b

:help_message
echo Available subcommands are:
echo   - b ^| build
echo   - c ^| clean
echo   - h ^| help
echo   - r ^| run
exit /b

:run_bytecode
set main_run=%OUT%\%MAIN%.class
if not exist "%main_run%" (
    echo The '%CYAN%!main_run!%RESET%' file %RED%doesn't exists%RESET%
    echo Try using `%GREEN%maksh.bat build%RESET%` before
    call :abort 1
)
echo Running the '%GREEN%!main_run!%RESET%' file...
timeout /t 2 >nul
%RC% --class-path %OUT% %MAIN%
exit /b

:main
:: Validate all subcommands first
for %%a in (%*) do (
    call :test_subcmd_validity %%a
)

:: Process each subcommand
for %%a in (%*) do (
    if "%%a"=="b" call :build_bytecode
    if "%%a"=="build" call :build_bytecode
    if "%%a"=="c" call :clear_bytecode
    if "%%a"=="clean" call :clear_bytecode
    if "%%a"=="h" call :help_message
    if "%%a"=="help" call :help_message
    if "%%a"=="r" call :run_bytecode
    if "%%a"=="run" call :run_bytecode
)
exit /b
