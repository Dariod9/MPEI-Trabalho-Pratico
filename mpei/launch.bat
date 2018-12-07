if not DEFINED IS_MINIMIZED set IS_MINIMIZED=1 && start "" /min "%~dpnx0" %* && exit
set PATH=D:\jdk1.8.0_111\bin
cd build
cd classes
java euromilhoes.EuromilhoesSimulator
exit