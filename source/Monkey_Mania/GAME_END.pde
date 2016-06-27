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
  
 void gameoverdraw(int bx, int by, int bw, int bh){
  
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