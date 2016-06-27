class grass {
 
   PImage BT;
   
 void placement(int x, int y){
  //Walls
    BT = loadImage("grass.png");
  for(x = 0; x < width; x+=32){
    for( y = 0; y < height; y+=32){
      image(BT, x, y);
      }
    }
  }
  
}