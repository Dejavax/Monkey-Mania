import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import ddf.minim.*; 
import ddf.minim.analysis.*; 
import ddf.minim.effects.*; 
import ddf.minim.signals.*; 
import ddf.minim.spi.*; 
import ddf.minim.ugens.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Monkey_Mania extends PApplet {








Minim minim;
AudioPlayer backgroundSound;
AudioPlayer fightingBackground;
AudioPlayer gamedone;
AudioPlayer hurt1;
AudioPlayer hurt2;
AudioPlayer hurt3;

int code = 1019;

int keyAmount = 222;

boolean[] keys  = new boolean[222];
Player player;
PlayerLives playerlives;
grass Grass;
Warning warning;
NPC npc;
points Points;

Credits credits;
HomeScreen home;
InfoScreen info;
gameover Gameover;

PImage CopyRight;

ArrayList <NPC> non = new ArrayList<NPC>();

int numPC = 20;

int scene;
int points = 0;
int bx, by, bw, bh;
int bx2, by2, bw2, bh2;
int bx3, by3, bw3, bh3;
int bx4, by4, bw4, bh4;

PImage ICON;
final static String TITLE = "Monkey Mania";

public void setup(){
  
  ICON = loadImage("icon.png");
  
  minim = new Minim(this);
  backgroundSound = minim.loadFile("digitalnative.mp3", 2048);
  fightingBackground = minim.loadFile("digitalbackground.mp3", 2048);
  gamedone = minim.loadFile("gamedone.mp3", 2048);
  hurt1 = minim.loadFile("injured.wav", 2048);
  hurt2 = minim.loadFile("injured.wav", 2048);
  hurt3 = minim.loadFile("injured.wav", 2048);
  
  CopyRight = loadImage("CopyRight.png");
  

//size(500, 500);
 
 changeAppIcon(ICON);
  changeAppTitle(TITLE);
 
 player = new Player();
 playerlives = new PlayerLives();
 Grass = new grass();
 Points = new points();
 
 warning = new Warning();
 home = new HomeScreen();
 info = new InfoScreen();
 Gameover = new gameover();
 credits = new Credits();
 
 scene = 0;
 
 bx = width/2 - 100;
 by = height/2;
 bw = 200;
 bh = 100;
 
 bx2 = width/2+200;
 by2 = height/2+200;
 bw2 = 200;
 bh2 = 100;
 
 bx3 = width/2+200;
 by3 = height/2+200;
 bw3 = 200;
 bh3 = 100;

 bx4 = width-250;
 by4 = 50;
 bw4 = 200;
 bh4 = 100;
 
 for(int i = 0; i < numPC; i++){
   non.add(new NPC());
 }
 
}

public void draw(){
  
  if(scene == 0){
    scene = 0;
    
    warning.drawwarning();
    
    if(mousePressed){
      scene = 1;
    }
  }
  if(scene == 1){
    scene = 1;
    backgroundSound.play();
    home.HomeScreenDraw(bx, by, bw, bh);
    fill(255);
    textSize(15);
    text("LINES OF CODE = "+code, 50, 50);
    
    fill(0, 0, 0, 100);
    rect(bx4, by4, bw4, bh4);
    
    fill(255);
    textSize(30);
    text("CREDITS", bx4+35, by4+60);
    
    if(isButton1Pressed(bx, by, bw, bh)){
      scene = 2;
    }
    if(isButton4Pressed(bx4, by4, bw4, bh4)){
      scene = 5;
    }
  }
  if(scene == 2){
   
    scene = 2;
    info.InfoScreenDraw(bx2, by2, bw2, bh2);
   if(isButton2Pressed(bx2, by2, bw2, bh2)){
     scene = 3;
      playerlives.lives = 0;
      player.x = width/2;
      player.y = height/2;
      backgroundSound.pause();
      backgroundSound.rewind();
   }
  }
  if(scene == 3){
   
    if(playerlives.dead && playerlives.lives == 1){
      //fightingBackground.pause();
      hurt1.play();
    }
     if(playerlives.dead && playerlives.lives == 2){
       //hurt1.pause();
       hurt2.play();
    }
     if(playerlives.dead && playerlives.lives == 3){
      // hurt2.pause();
       hurt3.play();
    }
    
   // if(!playerlives.dead){
     fightingBackground.play(); 
   // }
    
    scene = 3;
      background(255);
    Grass.placement(-width, -height);
      move();
    player.drawPlayer();
    if(Points.acpoint >= 10){
      numPC *= 2;
    }
    for(int i = 0; i < numPC; i++){
        non.get(i).drawnpc(player.x, player.y, keys, player.lives, player.currentDirection);
    }
 
    playerlives.drawHearts();
    //text(Points.acpoint, width/2, 50);
  
  
    if(playerlives.lives >= 4){
        scene = 4;
        
      hurt1.pause();
      hurt1.rewind();
      hurt2.pause();
      hurt2.rewind();
      hurt3.pause();
      hurt3.rewind();
      fightingBackground.pause();
      fightingBackground.rewind();
    }
  
  }
  if(scene == 4){
    scene = 4;
    
    gamedone.play();
    
    Gameover.gameoverdraw(bx3, by3, bw3, bh3);
    
    if(isButton3Pressed(bx3, by3, bw3, bh3)){
      scene = 1;
      gamedone.pause();
      gamedone.rewind();
    }
  }
  
  if(scene == 5){
    scene = 5;
    
    credits.drawCredits();
    
    if(isEnterPressed()){
      scene = 1;
    }
  }
  fill(255);
  textSize(25);
  text("BETA 2.1.5", 50, height - 25);
  image(CopyRight, 49, height - 115);
}

public void changeAppIcon(PImage img){
 
  final PGraphics pg = createGraphics(16, 16, JAVA2D);
  
  pg.beginDraw();
  pg.image(img, 0, 0, 16, 16);
  pg.endDraw();
  
  frame.setIconImage(pg.image);
  
}

public void changeAppTitle(String title){
  
  surface.setTitle(title);
  
}

public void move(){
 
  int xDelta = 0;
  int yDelta = 0;
  boolean attack = false;
 
  if(keys[87] || keys[38]){
    yDelta-=2;
  }
  if(keys[83] || keys[40]){
    yDelta += 2;
  }
  if(keys[68] || keys[39]){
    xDelta += 2;
  }
  if(keys[65] || keys[37]){
    xDelta -= 2;
  }
  if(keys[' ']){
    attack = true;
  }
  
  player.update(xDelta, yDelta, attack);

}

 public boolean isButton1Pressed(int bx, int by, int bw, int bh){
    
    if(mousePressed &&
       mouseX >= bx &&
       mouseX <= bx+bw &&
       mouseY >= by &&
       mouseY <= by+bh){
      return true;
    }else{
     return false; 
    }

  }
  
  public boolean isButton2Pressed(int bx, int by, int bw, int bh){
     if(mousePressed &&
       mouseX >= bx &&
       mouseX <= bx+bw &&
       mouseY >= by &&
       mouseY <= by+bh){
      return true;
    }else{
     return false; 
    }
  }
public boolean isButton3Pressed(int bx, int by, int bw, int bh){
     if(mousePressed &&
       mouseX >= bx &&
       mouseX <= bx+bw &&
       mouseY >= by &&
       mouseY <= by+bh){
      return true;
    }else{
     return false; 
    }
  }
public boolean isButton4Pressed(int bx, int by, int bw, int bh){
     if(mousePressed &&
       mouseX >= bx &&
       mouseX <= bx+bw &&
       mouseY >= by &&
       mouseY <= by+bh){
      return true;
    }else{
     return false; 
    }
  }
  
  public boolean isEnterPressed(){
    if(keys[ENTER]){
      return true;
    }else{
      return false; 
    }
  }
public void keyPressed(){ 
  
  if(keyCode > keyAmount){
    keyCode = '0';
  }
    keys[keyCode] = true;
}

public void keyReleased(){
  
 if(keyCode > keyAmount){
   keyCode = '0';
 }
 
  keys[keyCode] = false;
  
}
class grass {
 
   PImage BT;
   
 public void placement(int x, int y){
  //Walls
    BT = loadImage("grass.png");
  for(x = 0; x < width; x+=32){
    for( y = 0; y < height; y+=32){
      image(BT, x, y);
      }
    }
  }
  
}
class Credits{
 
  float x, y;
  
  Credits(){
   
    x = width/2 - 100;
    y = 100;
    
  }
  
  public void drawCredits(){
    
    background(0);
    
    fill(255);
    textSize(15);
   
    text("CREATED BY: Steven Reid", x, y);
    text("MUSIC BY: Eric Skiff", x, y + 50);
    text("PRODUCED BY: Steven Reid", x, y+100);
    text("TESTED BY: Mr.Elsdon, Landon Reid, and Steven Reid", x, y+150);
    text("A GAME BY: Steven Reid", x, y +200);
    text("Steven Reid, Likes being in the credits", x, y+250);
    text("ShoutOut to My Mom", x, y+300);
    text("StoryLine Developed By Steven Reid",x ,y+350);
    
    textSize(20);
    text("PRESS ENTER TO GO BACK...", width/2 - 230, height - 50);
    
    }
  
}
class gameover{
 
  grass Grass;
  
  PImage playerDead;
  PImage word;

  float x, y, wx, wy;
  
 gameover(){
   
   Grass = new grass();
   
   x = width/2 - 60;
   y = height/2 + 0;
   wx = width/4 - 70;
   wy = height/4;
 }
  
 public void gameoverdraw(int bx, int by, int bw, int bh){
  
   word = loadImage("GAMEOVER.png");
   playerDead = loadImage("playerdead.png");
   
   Grass.placement(-width, -height);
   
   fill(0, 0, 0, 150);
   rect(50, 50, width-100, height-100);
  
   image(playerDead, x, y);
   image(word, wx, wy);
   
   fill(255, 0, 0);
   rect(bx, by, bw, bh);
   
   fill(0);
   textSize(40);
   text("RESTART", bx+15, by+65);
 }
}

class PlayerLives{

 PImage HeartSheet;
 PImage [][] Hearts;
 float lives; 
 float x, y;
 
 boolean dead;
 
  PlayerLives(){
    
    dead = false;
    
    x = 30;
    y = 30;
    lives = 0;
 
    setupHearts();
}
  
  public void setupHearts(){
    
    Hearts = new PImage[1][5];
    HeartSheet = loadImage("Lives.png");
    
    
    Hearts[0][0] = HeartSheet.get(0 * 64, 0, 64, 64);
    Hearts[0][1] = HeartSheet.get(1 * 64, 0, 64, 64);
    Hearts[0][2] = HeartSheet.get(2 * 64, 0, 64, 64);
    Hearts[0][3] = HeartSheet.get(3 * 64, 0, 64, 64);
    Hearts[0][4] = HeartSheet.get(4 * 64, 0, 64, 64);
    
    
  }
  
  public void drawHearts(){
     /*if(lives == 4){
      image(Hearts[0][4], x, y);
     }else if(lives == 3){
       image(Hearts[0][3], x, y);
     }else if(lives == 2){
       image(Hearts[0][2], x, y);
     }else if(lives == 1){
       image(Hearts[0][1], x, y);
     }else if(lives == 0){
       image(Hearts[0][0], x, y); 
     }else{
       image(Hearts[0][4], x, y); 
     }*/
     
     image(Hearts[0][0 + PApplet.parseInt(lives)], x, y);
     
  }
  
  public void update(boolean death){
    
    dead = death;
    
    if(dead){
      
      if(lives < 4){
      lives+=1;
 
      }else{
       lives += 0; 
   
      }
    }
  }    
    
  }
class HomeScreen{

  grass Grass;
  
  PImage Title;
  PImage Player;
  PImage[][] pmovement;
  PImage Hand;
  PImage[][] hmovement;
  
  float pcurrentFrame;
  float px;
  float py;
  
  float hcurrentFrame;
  float hx;
  float hy;
  
  int xDelta;
  
  HomeScreen(){
   
    Grass = new grass();
    
    Title = loadImage("Title.png");
    
    xDelta = -2;
    
    px = width+50;
    py = width/2-50;
    hx = width+300;
    hy = width/2-15;
    
    setupsprites();
    
  }
  
  public void setupsprites(){
    
    pmovement = new PImage[1][6];
    Player = loadImage("linkspritesheet.png");  
    
    for(int i = 0; i < 6; i++){
      pmovement[0][i] = Player.get(0+64*i, 128, 64, 64);
   
    }
    hmovement = new PImage[1][2];
    Hand = loadImage("MonkeyHands.png");
    
    for(int i = 0; i < 2; i++){
      hmovement[0][i] = Hand.get(0+32*i, 32, 32, 32);
    }
    
  }
  
  public void HomeScreenDraw(int bx, int by, int bw, int bh){
    
    Grass.placement(-width, -height);
    image(Title, width/5, height/7);
    
    spriteUpdate();
    
    image(pmovement[0][0+PApplet.parseInt(pcurrentFrame)], px, py);
    image(hmovement[0][0+PApplet.parseInt(hcurrentFrame)], hx, hy);
    
    fill(255, 0, 0);
    rect(bx, by, bw, bh);
    textSize(50);
    fill(0);
    text("PLAY", bx+40, by+65);
  }
  
  public void spriteUpdate(){
   
       pcurrentFrame = (pcurrentFrame + 0.2f) % 6;
       hcurrentFrame = (hcurrentFrame + 0.2f) % 2;
       
       if(px+64 <= 0){
         px = width+100;
       }
       if(hx+32 <= 0){
         hx = px+250;
       }
       
       px += xDelta;
       
       hx += xDelta;
    
  }
  
}
class InfoScreen{
 
  PImage Player;
  PImage[][] pmovement;
  float px, py;
  
  PImage Hand;
  PImage[][] hmovement;
  float hx, hy;
  
  grass Grass;
  
  InfoScreen(){
    
    Grass = new grass();
    
    px = width/2+200;
    py = height/2-100;
    hx = px - 15;
    hy = py + 30;
    
   spritesetup(); 
  }
  
  public void spritesetup(){
    
    pmovement = new PImage[1][6];
    Player = loadImage("linkspritesheet.png");
    
    for(int i = 0; i < 6; i++){
      pmovement[0][i] = Player.get(0+64*i, 384, 64, 64);
    }
    
    hmovement = new PImage[1][2];
    Hand = loadImage("MonkeyHands.png");
    
    for(int i = 0; i < 2; i++){
      hmovement[0][i] = Hand.get(0+32*i, 0, 32,32);
    }
    
  }
  
  public void InfoScreenDraw(int bx, int by, int bw, int bh){
  
    Grass.placement(-width, -height);
    
    fill(0, 0, 0, 150);
    rect(50, 50, width-100, height-100);
    
    fill(255);
    textSize(50);
    text("HOW TO PLAY", 100, 100);
    text("GOAL", 100, 350);
    textSize(25);
    text("To Move Use The 'W' 'S' 'A' 'D' keys", 100, 200);
    text("To Attack Hold the SpaceBar", 100, 250);
    
    text("The Goal Of The Game Is To Kill As Many", 100, 400);
    text("Monkey Hands As Possible Without Dying", 100, 450);
    
    image(hmovement[0][0], hx, hy);
    image(pmovement[0][1], px, py);
    
    fill(255, 0, 0);
    rect(bx, by, bw, bh);
    
    textSize(50);
    fill(0);
    text("PLAY", bx+40, by+70);
    
  }
  
}
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
  
  public void setupnpc(){
    
    movement = new PImage[4][2];
    spriteSheet = loadImage("MonkeyHands.png");
    
   for(int i = 0; i < 2; i ++){
      
     movement[0][i] = spriteSheet.get(0 + 32 * i, 0, 32, 32);
     movement[1][i] = spriteSheet.get(0 + 32 * i, 32, 32, 32);
     movement[2][i] = spriteSheet.get(0 + 32 * i, 64, 32, 32);
     movement[3][i] = spriteSheet.get(0 + 32 * i, 96, 32, 32);
     
    }
    
  }
   
  public void health(int direction){
    
    if(direction == 3){
      fill(255);
      rect(x-18, y+32, 60, 15);
    fill(0, 255, 0);
      rect(x-13, y+34, lives*2.5f, 10);
    }else{
    fill(255);
      rect(x-18, y-10, 60, 15);
    fill(0, 255, 0);
      rect(x-13, y-8, lives*2.5f, 10);
    }
  }
  
  public void drawnpc(float px, float py, boolean[] button, int ph, int pcd){
    
    update(px, py, button, ph, pcd);
    
    health(currentDirection);
    
    if(inMotion){
      image(movement[currentDirection][0 + PApplet.parseInt(currentFrame)], x, y);
    }else{
      image(movement[currentDirection][0], x, y);
    }
    
  }
  
  public void update(float px, float py, boolean[] button, int ph, int pcd){
    
     if(inMotion){
       currentFrame = (currentFrame + 0.2f) % 2;
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
    
    if(s >= 0.5f){
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
  
  public boolean isPlayerInjured(float nx, float ny, float px, float py, boolean[] button){
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
  
  public void setupSprites(){
    
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
  
  public void drawPlayer(){
   
    if(inMotion && FIGHT == false){
      image(movement[currentDirection][0 + PApplet.parseInt(currentFrame)], x, y);
    }else if(!inMotion && !FIGHT){ 
      image(movement[currentDirection][0], x, y);
    }
    if(FIGHT){
      image(fight[currentDirection][0 + PApplet.parseInt(currentFrame)], x, y);
    }
    
}
    
  public void update(int xDelta, int yDelta, boolean attack){
    
    if(inMotion && !FIGHT){
      currentFrame = (currentFrame + 0.2f) % 6;
    }
    if(FIGHT){
      currentFrame = (currentFrame + 0.2f) % 6;
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
  
  public boolean isPlayerOffScreen(float x, float y){
    if(x + 10 < 0 || x > width - 25 || y + 5< 0 || y > height - 30){
      return true;
    }else{
      return false; 
    }
  }
}
class points{
 
  int acpoint;
  
  points(){
    acpoint = 0;
  }
  
  public void drawpoints(int point){
    acpoint += point;
  }
  
}
class Warning{
 
  float x, y;
  
  Warning(){
    x = width/2 - 230;
    y = 100;
  
  }
  public void drawwarning(){
    
    background(0);
    
    fill(255, 0, 0);
    textSize(100);
    text("WARNING", x, y);
    
    fill(255);
    textSize(30);
   
    text("This game may be addictive.", x, y+150);

    text("PRESS MOUSE IF YOU CAN HANDLE IT...", x, height - 50);
    text("PRESS ESC TO ESCAPE...", x, height-100);
    
  };
}
  public void settings() { 
fullScreen(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#FF0000", "Monkey_Mania" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
