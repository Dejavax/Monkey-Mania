
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
  
  void setupHearts(){
    
    Hearts = new PImage[1][5];
    HeartSheet = loadImage("Lives.png");
    
    
    Hearts[0][0] = HeartSheet.get(0 * 64, 0, 64, 64);
    Hearts[0][1] = HeartSheet.get(1 * 64, 0, 64, 64);
    Hearts[0][2] = HeartSheet.get(2 * 64, 0, 64, 64);
    Hearts[0][3] = HeartSheet.get(3 * 64, 0, 64, 64);
    Hearts[0][4] = HeartSheet.get(4 * 64, 0, 64, 64);
    
    
  }
  
  void drawHearts(){
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
     
     image(Hearts[0][0 + int(lives)], x, y);
     
  }
  
  void update(boolean death){
    
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