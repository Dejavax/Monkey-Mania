class NPC{
 
 float x, y; 
 float speedX, speedY;
 float currentFrame;
 
 int lives;
 int currentDirection;
 int points = 0;
 
 PImage[][] movement;
 PImage spriteSheet;
 
 boolean inMotion;
  
  points Points;
  
  
 final int RIGHT = 0, LEFT = 1, DOWN = 2, UP = 3;  
  
  NPC(){
    
    Points = new points();
    
    currentDirection = 0;
   // currentFrame = 0;
    inMotion = true;
    
    x = random(50, width - 50);
    y = random(50, height - 50);
  
    speedX = 0;
    speedY = 0;
    
    lives = 20;
    
    setupnpc();
  }
  
  void setupnpc(){
    
    movement = new PImage[4][2];
    spriteSheet = loadImage("MonkeyHands.png");
    
   for(int i = 0; i < 2; i ++){
      
     movement[0][i] = spriteSheet.get(0 + 32 * i, 0, 32, 32);
     movement[1][i] = spriteSheet.get(0 + 32 * i, 32, 32, 32);
     movement[2][i] = spriteSheet.get(0 + 32 * i, 64, 32, 32);
     movement[3][i] = spriteSheet.get(0 + 32 * i, 96, 32, 32);
     
    }
    
  }
   
  void health(int direction){
    
    if(direction == 3){
      fill(255);
      rect(x-18, y+32, 60, 15);
    fill(0, 255, 0);
      rect(x-13, y+34, lives*2.5, 10);
    }else{
    fill(255);
      rect(x-18, y-10, 60, 15);
    fill(0, 255, 0);
      rect(x-13, y-8, lives*2.5, 10);
    }
  }
  
  void drawnpc(float px, float py, boolean[] button, int ph, int pcd){
    
    update(px, py, button, ph, pcd);
    
    health(currentDirection);
    
    if(inMotion){
      image(movement[currentDirection][0 + int(currentFrame)], x, y);
    }else{
      image(movement[currentDirection][0], x, y);
    }
    
  }
  
  void update(float px, float py, boolean[] button, int ph, int pcd){
    
     if(inMotion){
       currentFrame = (currentFrame + 0.2) % 2;
     }
     
     if(abs(px - x) > abs(py - y)){
       
       if(px > x){
         speedX = 1;
       }else{      
        speedX = -1; 
       }
     }else{
       
       if(py > y){
         speedY = 1;
       }else{
         speedY = -1;
       }
     }
     
     if(speedX > 0){
      currentDirection = RIGHT;
    }
    if(speedX < 0){
      currentDirection = LEFT;
    }
    if(speedY > 0){
      currentDirection = DOWN;
    }
    if(speedY < 0){
      currentDirection = UP;
    }
     
    if(x + 32 > px &&
     x < px + 64 &&
     y + 32 > py &&
     y < py + 64 && 
     !button[' '] 
     ){
    
      ph --;
       
  }
 /* if(x + 32 > px &&
     x < px + 64 &&
     y + 32 > py &&
     y < py + 64 && 
     button[' '] &&
     lives > 0){
     
     lives --;
       
  }*/
  /*if(button[' ']){
    speedX = 0;
    speedY = 0;
    
  }*/
  if(button[' '] &&
     pcd == 0 &&
     x + 32 > px &&
     x < px + 64 &&
     y  < py+64 &&
     y > py + 32 ){
    
       lives --;
  }
  if(button[' '] &&
     pcd == 1 &&
     x + 32 > px &&
     x < px + 64 &&
     y + 32 > py &&
     y < py + 32 ){
    
       lives --;
  }
  if(button[' '] &&
     pcd == 2 &&
      x   < px+32 &&
     x > px  &&
     y + 32 > py &&
     y < py + 64 ){
    
       lives --;
  }
  if(button[' '] &&
     pcd == 3 &&
     x  > px+32 &&
     x < px + 64 &&
     y + 32 > py &&
     y < py + 64 ){
    
       lives --;
  }
  if(lives == 0){
    x  = random(50, width - 50);
    y  = random(50, height - 50);
    
    points ++;
    Points.drawpoints(points);
    
    lives = 20;
  }
  
  if(isPlayerInjured(x, y, px, py, button)){
    
    boolean dead = isPlayerInjured(x, y, px, py, button);
    
    playerlives.update(dead);
    float s = random(0,1);
    
    if(s >= 0.5){
      x = random(50, 200); 
    }else{
    x = random(width - 200, width-50);
    }
    y = random(50, height - 50);
    
  }
  
 
    x = x += speedX;
    y = y += speedY;
 
}
  
  /*boolean isOffScreen(float x, float y){
    
  }*/
  
  boolean isPlayerInjured(float nx, float ny, float px, float py, boolean[] button){
    if(nx + 32 > px &&
     nx < px + 64 &&
     ny + 32 > py &&
     ny < py + 64 &&
     !button[' ']){
      return true;
    }else{
     return false; 
    }
  }
  
}