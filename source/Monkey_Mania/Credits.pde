class Credits{
 
  float x, y;
  
  Credits(){
   
    x = width/2 - 100;
    y = 100;
    
  }
  
  void drawCredits(){
    
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