# 204845929 302443403
# shefiro thalleo

compile: bin
	find src -name "*.java" > sources.txt
	javac -Xlint -cp  biuoop-1.4.jar -d bin @sources.txt

run:
	java -cp biuoop-1.4.jar:resources:bin arkanoid/game/Ass6Game

bin:
	mkdir bin

jar:
	jar cfm Ass6Game.jar Manifest -C bin/ . -C resources/ .

check:
	java -jar checkstyle-5.7-all.jar -c biuoop.xml -r src/arkanoid

