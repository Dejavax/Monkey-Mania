import ddf.minim.*;
import ddf.minim.analysis.*;
import ddf.minim.effects.*;
import ddf.minim.signals.*;
import ddf.minim.spi.*;
import ddf.minim.ugens.*;

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

void setup(){
  
  ICON = loadImage("icon.png");
  
  minim = new Minim(this);
  backgroundSound = minim.loadFile("digitalnative.mp3", 2048);
  fightingBackground = minim.loadFile("digitalbackground.mp3", 2048);
  gamedone = minim.loadFile("gamedone.mp3", 2048);
  hurt1 = minim.loadFile("injured.wav", 2048);
  hurt2 = minim.loadFile("injured.wav", 2048);
  hurt3 = minim.loadFile("injured.wav", 2048);
  
  CopyRight = loadImage("CopyRight.png");
  
fullScreen();
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

void draw(){
  
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

void changeAppIcon(PImage img){
 
  final PGraphics pg = createGraphics(16, 16, JAVA2D);
  
  pg.beginDraw();
  pg.image(img, 0, 0, 16, 16);
  pg.endDraw();
  
  frame.setIconImage(pg.image);
  
}

void changeAppTitle(String title){
  
  surface.setTitle(title);
  
}

void move(){
 
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

 boolean isButton1Pressed(int bx, int by, int bw, int bh){
    
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
  
  boolean isButton2Pressed(int bx, int by, int bw, int bh){
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
boolean isButton3Pressed(int bx, int by, int bw, int bh){
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
boolean isButton4Pressed(int bx, int by, int bw, int bh){
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
  
  boolean isEnterPressed(){
    if(keys[ENTER]){
      return true;
    }else{
      return false; 
    }
  }
void keyPressed(){ 
  
  if(keyCode > keyAmount){
    keyCode = '0';
  }
    keys[keyCode] = true;
}

void keyReleased(){
  
 if(keyCode > keyAmount){
   keyCode = '0';
 }
 
  keys[keyCode] = false;
  
}