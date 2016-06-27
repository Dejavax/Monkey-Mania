class Player{
  
  float x, y;
  PImage spriteSheet;
  PImage fightSheet;
  PImage [][] movement;
  PImage [][] fight;
  boolean inMotion;
  int currentDirection;
  float currentFrame;
  boolean FIGHT;
  int lives;

  final int DOWN = 0, UP = 1, LEFT = 2, RIGHT = 3;
  
  Player(){
    
    inMotion = false;
    FIGHT = false;
    currentDirection = 0;
    x = width/2;
    y = height/2;
  
    lives = 5;
    setupSprites();
  }
  
  void setupSprites(){
    
    movement = new PImage[4][6];
    fight = new PImage[4][6];
    spriteSheet = loadImage("linkspritesheet.png");
    
    for(int i = 0; i < 6; i ++){
     movement[0][i] = spriteSheet.get(0 + 64 * i, 0, 64, 64);
     movement[1][i] = spriteSheet.get(0 + 64 * i, 64, 64, 64);
     movement[2][i] = spriteSheet.get(0 + 64 * i, 128, 64, 64);
     movement[3][i] = spriteSheet.get(0 + 64 * i, 192, 64, 64);
    }
    for(int i = 0; i < 6; i ++){
      fight[0][i] = spriteSheet.get(0 + 64 * i, 256, 64, 64);
      fight[1][i] = spriteSheet.get(0 + 64 * i, 320, 64, 64);
      fight[2][i] = spriteSheet.get(0 + 64 * i, 384, 64, 64);
      fight[3][i] = spriteSheet.get(0 + 64 * i, 448, 64, 64);
    }
  }
  
  void drawPlayer(){
   
    if(inMotion && FIGHT == false){
      image(movement[currentDirection][0 + int(currentFrame)], x, y);
    }else if(!inMotion && !FIGHT){ 
      image(movement[currentDirection][0], x, y);
    }
    if(FIGHT){
      image(fight[currentDirection][0 + int(currentFrame)], x, y);
    }
    
}
    
  void update(int xDelta, int yDelta, boolean attack){
    
    if(inMotion && !FIGHT){
      currentFrame = (currentFrame + 0.2) % 6;
    }
    if(FIGHT){
      currentFrame = (currentFrame + 0.2) % 6;
    }
    
    inMotion = true;
    FIGHT = attack;
  
    if(xDelta == 0 && yDelta == 0 || FIGHT){
      inMotion = false;
    }else if(FIGHT == false && xDelta == -2 ){
      currentDirection = LEFT;
    }else if(FIGHT == false && xDelta == +2){
      currentDirection = RIGHT; 
    }else if(FIGHT == false && yDelta == -2){
      currentDirection = UP;
    }else if(FIGHT == false && yDelta == +2){
      currentDirection = DOWN;
    }
    
    if(FIGHT == true){
      x = x + xDelta;
      y = y + yDelta;
    }else {
    x = x + xDelta;
    y = y + yDelta;

    if(isPlayerOffScreen(x, y)){
      x = x - xDelta;
      y = y - yDelta;
    }
    }
  }
  
  boolean isPlayerOffScreen(float x, float y){
    if(x + 10 < 0 || x > width - 25 || y + 5< 0 || y > height - 30){
      return true;
    }else{
      return false; 
    }
  }
}