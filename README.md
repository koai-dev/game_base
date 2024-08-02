Base Component for game 2D android, use compose.
###Features:
- Character class allow create character in game like: user, enemy, bullet, background... with
  ###attrs:
  + Action: IDLE, JUMP, WALK, RUN, ATTACK, CLIMB, DIE, INJURED, DIED, UP, DOWN, LEFT, RIGHT
  + Collider
  + Position
  + Sprites: List sprites follow current action
  ###action:
  + move(x,y)
  + draw
- GameCanvas: to draw all character and game attrs
- SoundRepository: play sound in your game
- Joystick: controller action in game
###Use:
- add maven("https://jitpack.io") to settings.gradle.kts
- add implementation("com.github.koai-dev:game_base:1.1.2") to app/build.gradle.kts
