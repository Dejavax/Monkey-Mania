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
  
  void setupsprites(){
    
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
  
  void HomeScreenDraw(int bx, int by, int bw, int bh){
    
    Grass.placement(-width, -height);
    image(Title, width/5, height/7);
    
    spriteUpdate();
    
    image(pmovement[0][0+int(pcurrentFrame)], px, py);
    image(hmovement[0][0+int(hcurrentFrame)], hx, hy);
    
    fill(255, 0, 0);
    rect(bx, by, bw, bh);
    textSize(50);
    fill(0);
    text("PLAY", bx+40, by+65);
  }
  
  void spriteUpdate(){
   
       pcurrentFrame = (pcurrentFrame + 0.2) % 6;
       hcurrentFrame = (hcurrentFrame + 0.2) % 2;
       
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