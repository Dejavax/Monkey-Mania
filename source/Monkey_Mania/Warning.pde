class Warning{
 
  float x, y;
  
  Warning(){
    x = width/2 - 230;
    y = 100;
  
  }
  void drawwarning(){
    
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