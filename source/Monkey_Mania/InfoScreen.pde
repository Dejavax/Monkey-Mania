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
  
  void spritesetup(){
    
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
  
  void InfoScreenDraw(int bx, int by, int bw, int bh){
  
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