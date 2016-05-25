# 204845929 302443403
# shefiro thalleo

compile: bin
	find src -name "*.java" > sources.txt
	javac -cp biuoop-1.4.jar -d bin @sources.txt 

run:
	java -cp biuoop-1.4.jar:bin arkanoid/game/Ass5Game

bin:
	mkdir bin

jar:
	jar cfm ass5game.jar Manifest -C bin/ .	

check:
	java -jar checkstyle-5.7-all.jar -c biuoop.xml src/arkanoid/animation/Animation.java \
src/arkanoid/animation/AnimationRunner.java \
src/arkanoid/animation/CountdownAnimation.java \
src/arkanoid/game/Counter.java \
src/arkanoid/game/Ass5Game.java \
src/arkanoid/game/EndScreen.java \
src/arkanoid/game/GameEnvironment.java \
src/arkanoid/game/GameLevel.java \
src/arkanoid/game/PauseScreen.java \
src/arkanoid/geometry/Line.java \
src/arkanoid/geometry/Point.java \
src/arkanoid/geometry/Rectangle.java \
src/arkanoid/geometry/Velocity.java \
src/arkanoid/levels/GameFlow.java \
src/arkanoid/levels/Level1.java \
src/arkanoid/levels/Level2.java \
src/arkanoid/levels/Level3.java \
src/arkanoid/levels/Level4.java \
src/arkanoid/levels/LevelInformation.java \
src/arkanoid/listeners/BallRemover.java \
src/arkanoid/listeners/BlockRemover.java \
src/arkanoid/listeners/HitListener.java \
src/arkanoid/listeners/HitNotifier.java \
src/arkanoid/listeners/ScoreTrackingListener.java \
src/arkanoid/sprites/Back.java \
src/arkanoid/sprites/Ball.java \
src/arkanoid/sprites/Block.java \
src/arkanoid/sprites/Circle.java \
src/arkanoid/sprites/Collidable.java \
src/arkanoid/sprites/CollisionInfo.java \
src/arkanoid/sprites/FullCircle.java \
src/arkanoid/sprites/LevelNameIndicator.java \
src/arkanoid/sprites/LivesIndicator.java \
src/arkanoid/sprites/Paddle.java \
src/arkanoid/sprites/ScoreIndicator.java \
src/arkanoid/sprites/Sprite.java \
src/arkanoid/sprites/SpriteCollection.java