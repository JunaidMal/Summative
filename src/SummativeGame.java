import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.Iterator;


/**
 *
 * @author ${user}
 */
public class SummativeGame extends JComponent {

    // Height and Width of our game
    static final int WIDTH = 1275;
    static final int HEIGHT = 950;
    
    //Title of the window
    String title = "My Game";

    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    
    
// YOUR GAME VARIABLES WOULD GO HERE
   
    // MAP VARIABLES
                Rectangle ground = new Rectangle(0, 890, 1275, 60);
    
                // Creates divider wall in the middle of the map
                Rectangle wall1 = new Rectangle(630, 0, 5, 950);
                
                Rectangle wall2 = new Rectangle(625, 0, 5, 950);
                
                
                // HOVERPADS
                
                Rectangle hover1 = new Rectangle(650, 500, 200, 15);
                
                Rectangle hover2 = new Rectangle(730, 100, 200, 15);
    
                Rectangle hover3 = new Rectangle(1000, 300, 200, 15);
                
                Rectangle hover4 = new Rectangle(1000, 300, 200, 15);
    // MAP VARIABLES END HERE
    
    // ARRAYS AND ITERATORS GO HERE
                
                // for player 1
                ArrayList<Rectangle> bullet = new ArrayList();
   
                 Iterator<Rectangle> it = bullet.iterator();
                 
               // for player 2
                 ArrayList<Rectangle> bullet2 = new ArrayList();
   
                 Iterator<Rectangle> it2 = bullet2.iterator();
    
    // ARRAYS AND ITERATORS END HERE
    
                 
    // PLAYER 1 VARIABLES START HERE
                 
                // creates the player character variables
                Rectangle player1 = new Rectangle(0, 520, 50, 20);
    
                // gravity
                int gravity = 1;
    
                // rate of horizontal movement
                int dy = 0;
                // horizonal velocity
                double horizVel = 0;
    
                // booleans to initiate jumps/detect whether or not an object is in the air
                boolean inAir = false;
                boolean onGround = true;
                boolean jump;
                boolean passed; 
    
    
                // used to move the player horizontally
                int playerX = player1.x;
                // +1 - Right
                // -1 - Left
                double playerDirection = 1;
    
                // Movement direction booleans for player 1
                boolean forward = false;
                boolean backward = false;
  
                // Bullet speed and direction variables
                int bulletSpeed = 10;
                // +1 - Right
                // -1 - Left
                int bulletDirection = 1;
    
                // Boolean used to identify if the "shooting" button has been pressed
                boolean fireRight1 = false;
                
                // whether or not the bullet for player 1 is travelling through the air
                boolean p1Pending = false;
                
                // Controls the delay in the shooting mechanism a.k.a "fire rate"
                int delay = 800;
                long nextTime = 0;
                
                // Booleans controlling direction player 1 is facing
                
          
                
                
    
    // PLAYER 1 VARIABLES END HERE //
    
    // PLAYER 2 VARIABLES START HERE // 
                
                // creates the player character variables
                Rectangle player2 = new Rectangle(1200, 520, 50, 20);
    
                // gravity
                int gravity2 = 1;
    
                // rate of horizontal movement
                int dy2 = 0;
                // horizonal velocity
                double horizVel2 = 0;
    
                // booleans to initiate jumps/detect whether or not an object is in the air
                boolean inAir2 = false;
                boolean onGround2 = true;
                boolean jump2;
                
                // if the bullet has passed 
                boolean passed2; 
    
    
                // used to move the player horizontally
                int player2X = player2.x;
                // +1 - Right
                // -1 - Left
                double playerDirection2 = 1;
    
                // Movement direction booleans for player 1
                boolean forward2 = false;
                boolean backward2 = false;
  
                // Bullet speed and direction variables
                int bulletSpeed2 = 10;
                // +1 - Right
                // -1 - Left
                int bulletDirection2 = -1;
    
                // Boolean used to identify if the "shooting" button has been pressed
                boolean fireLeft2 = false;
                
                // whether or not the bullet for player 1 is travelling through the air
                boolean p2Pending = false;
    
    
    // PLAYER 2 VARIABLES END HERE //
                
    // SCORE KEEPING VARIABLES START HERE // 
                
                // score storage for both player 1 and 2
                int player1Score = 0;
                int player2Score = 0;
                
                // arial font for scoreboard
                Font myFont = new Font("Arial", Font.BOLD, 75);
    
    
    // GAME VARIABLES END HERE   

    
    // Constructor to create the Frame and place the panel in
    // You will learn more about this in Grade 12 :)
    public SummativeGame(){
        // creates a windows to show my game
        JFrame frame = new JFrame(title);

        // sets the size of my game
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // adds the game to the window
        frame.add(this);

        // sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // shows the window to the user
        frame.setVisible(true);
        
        // add listeners for keyboard and mouse
        frame.addKeyListener(new Keyboard());
        Mouse m = new Mouse();
        
        this.addMouseMotionListener(m);
        this.addMouseWheelListener(m);
        this.addMouseListener(m);
    }
    
    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE
        
        // creating player 1
        g.fillRect(player1.x, player1.y, player1.width, player1.height);
        
        // creating player 2
        g.fillRect(player2.x, player2.y, player2.width, player2.height);
        
        // creating ground
        g.fillRect(ground.x, ground.y, ground.width, ground.height);
        
        // creating divider walls
        g.fillRect(wall1.x, wall1.y, wall1.width, wall1.height);
        g.fillRect(wall2.x, wall2.y, wall2.width, wall2.height);
      
        // creates bullets for player 1
        for(Rectangle bullets: bullet){
             g.fillRect(bullets.x, bullets.y, bullets.width, bullets.height);
            }
        
        // creates bullets for player 2
        for(Rectangle bullets2: bullet2){
             g.fillRect(bullets2.x, bullets2.y, bullets2.width, bullets2.height);
            }
        
        // SCOREBOARD
        g.setFont(myFont);
        g.setColor(Color.red);
        g.drawString("" + player1Score, WIDTH/2-100, 75);
        g.drawString("" + player2Score, WIDTH/2+50, 75);
        
        // HOVERPADS
        g.fillOval(hover1.x, hover1.y, hover1.width, hover1.height);
        g.fillOval(hover2.x, hover2.y, hover2.width, hover2.height);
        g.fillOval(hover3.x, hover3.y, hover3.width, hover3.height);
        
        // GAME DRAWING ENDS HERE
    }


    // This method is used to do any pre-setup you might need to do
    // This is run before the game loop begins!
    public void  preSetup(){
       // Any of your pre setup before the loop starts should go here

       
    }

    // The main game loop
    // In here is where all the logic for my game will go
    public void run() {
        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;

        preSetup();

        // the main game loop section
        // game will end if you set done = false;
        boolean done = false;
        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 
            
            shooting();

            
            // delay timer used to control fire rate for both players
            if(startTime > nextTime){
                 nextTime = startTime + delay;
                p1Pending = false;
                p2Pending = false;
            }
            
            
           
            
            
          
            // gravity physics for player 1 and 2
            gravity();
            
            // collisions for both player 1 and 2
            collision();
            
            // jumping logic for both player 1 and 2
            jumping();

            // movement logic for player 1 and 2
            moving();
            
            scoreBoard();
            
            // controls speed of the player
//            double movementTransfer = playerDirection * (0.7) * horizVel;
            
           
            
            
            
            
            
            
            // GAME LOGIC ENDS HERE 
            // update the drawing (calls paintComponent)
            repaint();

            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            try {
                if (deltaTime > desiredTime) {
                    //took too much time, don't wait
                    Thread.sleep(1);
                } else {
                    // sleep to make up the extra time
                    Thread.sleep(desiredTime - deltaTime);
                }
            } catch (Exception e) {
            }
        }
    }

    

    // Used to implement any of the Mouse Actions
    private class Mouse extends MouseAdapter {
        // if a mouse button has been pressed down
        @Override
        public void mousePressed(MouseEvent e){
            
        }
        
        // if a mouse button has been released
        @Override
        public void mouseReleased(MouseEvent e){
            
        }
        
        // if the scroll wheel has been moved
        @Override
        public void mouseWheelMoved(MouseWheelEvent e){
            
        }

        // if the mouse has moved positions
        @Override
        public void mouseMoved(MouseEvent e){
            
        }
    }
    
    // Used to implements any of the Keyboard Actions
    private class Keyboard extends KeyAdapter{
        // if a key has been pressed down
        @Override
        public void keyPressed(KeyEvent e){
            
            // player 1 presses W to jump
            if(e.getKeyCode() == KeyEvent.VK_W) {
                jump = true;
                inAir = false;
            }
            
            // player 1 presses D to go forwards
            if(e.getKeyCode() == KeyEvent.VK_D) {
                // player will begin moving forward
                forward = true;
                // playing is not moving backwards anymore
                backward = false;
                
                
                
            }
            // player 1 presses A to move backwards
            if(e.getKeyCode() == KeyEvent.VK_A) {
                // player will begin moving backwards
                forward = false;
                // player is not moving forwards anymore
                backward = true;
                
               
            }
            
            // player 1 presses V to fire
            if(e.getKeyCode() == KeyEvent.VK_V) {
                fireRight1 = true;
                
                
            }
            
            // PLAYER 2
            
            // player 2 persses up arrow to jump
            if(e.getKeyCode() == KeyEvent.VK_UP) {
                jump2 = true;
                inAir2 = false;
            }
            // player 2 presses right arrow to move RIGHT
            if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                forward2 = true;
                backward2 = false;
                
                
            }
            // player 2 presses left arrow to move LEFT
            if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                forward2 = false;
                backward2 = true;
                
               
            }
            
            // player 2 presses ENTER to fire (CHANGE SOON)
            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                fireLeft2 = true;
            }
            
        }
        
        // if a key has been released
        @Override
        public void keyReleased(KeyEvent e){
            
            // PLAYER 1
            // Pressing D to go forwards
            if(e.getKeyCode() == KeyEvent.VK_D) {
                forward = false;
            }
            
             if(e.getKeyCode() == KeyEvent.VK_A) {
                backward = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_V) {
                fireRight1 = false;
            }
            
            
            
             //PLAYER 2
            if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                forward2 = false;
            }
             if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                backward2 = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                fireLeft2 = false;
            }
            
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates an instance of my game
        SummativeGame game = new SummativeGame();
                
        // starts the game loop
        game.run();
    }
    
    public void collision() {
        
        // PLAYER 1 COLLISIONS HERE
        
        
                    if(player1.intersects(ground)){
                        dy = 0;
                    }
                    
                    if(player1.y + 0.95*(player1.height) > ground.y){
                        player1.y = ground.y - player1.height;
            
                    }
                    
                    if(player1.intersects(wall2)){
                        horizVel = 0;
                        player1.x = wall2.x - player1.width;
                    }
                    
                    if(player1.x <= 0) {
                        player1.x = 0;
                    }
                    
                    if(player1.y <= 0) {
                        player1.y = 0;
                    }
        
        // PLAYER 1 COLLISIONS END HERE
        
        // PLAYER 2 COLLISIONS START HERE
                    
                    if(player2.intersects(ground)){
                        dy2 = 0;
                    }
                    
                    if(player2.y + 0.95*(player2.height) > ground.y){
                        player2.y = ground.y - player2.height;
            
                    }
                    
                    if(player2.intersects(wall1)){
                        horizVel2 = 0;
                        player2.x = wall1.x + wall1.width;
                
                    }
                    
                    if(player2.x + player2.width >= WIDTH) {
                        player2.x = WIDTH - player2.width;
                    }
                    
                    if(player2.y <= 0) {
                        player2.y = 0;
                    }
                    
                    
        // PLAYER 2 COLLISIONS END HERE
                    
    }
    
    public void moving() {
        
        // PLAYER 1 MOVEMENT GOES HERE
        
                    if(forward) {
                        horizVel = horizVel + 0.5;
                    }
                    if(!forward && !backward) {
                        if(horizVel > 0)
                        horizVel = horizVel - 1;
                    }
        
                    if(backward) {
                        horizVel = horizVel -0.5;
                    }
                    if(!backward && !forward) {
                        if(horizVel < 0)
                        horizVel = horizVel + 0.5;
                    }
        
                    if(horizVel >= 10) {
                        horizVel = 10;
                    }
        
                    if(horizVel <= -10) {
                        horizVel = -10;
                    }
                    
                    int movement1 = (int) horizVel;
            
                    player1.x = player1.x + movement1;
        
        // PLAYER 1 MOVEMENT ENDS HERE
        
        // PLAYER 2 MOVEMENT STARTS HERE
                    
                    // accelerating forwards
                    if(forward2) {
                        horizVel2 = horizVel2 + 0.5;
                    }
                    // descelerating when no button pressed
                    if(!forward2 && !backward2) {
                       if(horizVel2 > 0)
                       horizVel2 = horizVel2 - 1;
                    }
                    
                    // accelerating backwards
                    if(backward2) {
                        horizVel2 = horizVel2 - 0.5;
                    }
                    
                    // descelerating when no button pressed
                    if(!backward2 && !forward2) {
                       if(horizVel2 < 0)
                        horizVel2 = horizVel2 + 0.5;
                    }
                    
                    // limits forward velocity
                    if(horizVel2 >= 10) {
                       horizVel2 = 10;
                    }
                    // limits backwards velocity
                    if(horizVel2 <= -10) {
                     horizVel2 = -10;
                    }
                    
                    // updates movement speed
                    int movement2 = (int) horizVel2;
                    player2.x = player2.x + movement2;
            
        // PLAYER 2 MOVEMENT ENDS HERE
        
        
    }
    
    public void jumping() {
        // FOR PLAYER 1
        if(jump && !inAir) {
           
                inAir = true;
                dy = -15;
            }
            
            if(dy > 10) {
               dy = 10;
            }
            
            player1.y = player1.y + dy;
         
        // FOR PLAYER 2
            
            if(jump2 && !inAir2) {
           
                inAir2 = true;
                dy2 = -15;
            }
            
            if(dy2 > 10) {
               dy2 = 10;
            }
            
            player2.y = player2.y + dy2;
        
            
    }
    
    public void shooting() {
        
        // FOR PLAYER 1
        
        if(fireRight1){
                if(p1Pending == false){
                   bullet.add(new Rectangle(player1.x, player1.y, 10,10));
                  // passed = false;
                   p1Pending = true;
                }
                   
                   
               }
            
            for(Rectangle bullets: bullet){
                
                 bullets.x += bulletSpeed;
               
                
               
            }
            
            // FOR PLAYER 2
            
            if(fireLeft2){
                if(p2Pending == false){
                   bullet2.add(new Rectangle(player2.x, player2.y, 10,10));
                   passed2 = false;
                   p2Pending = true;
                }
                   
                   
               }
            
            for(Rectangle bullets2: bullet2){
                
                 bullets2.x -= bulletSpeed2;
             
            }
        
        
    }
    
    public void gravity() {
        // player 1 gravity effect
            dy = dy + gravity;
            
            // player 2 gravity effect
            dy2 = dy2 + gravity2;
    }
    
    public void scoreBoard() {
        
        // FOR PLAYER 1 SCORE INCREASE
        
        // Runs through bullet array to detect whether every bullet has hit or not
        for (int i = 0; i < bullet.size(); i++) {
            
            // when one of the bullets from player 1 hits player 2, player 1 recieves + 1 to their score
             if(player2.intersects(bullet.get(i))) {
                 
                 player1Score ++;
                 bullet.remove(i);
                 
          }
        }
        
        // FOR PLAYER 2 SCORE INCREASE
        
        // Runs through bullet array to detect whether every bullet has hit or not
        for (int i = 0; i < bullet2.size(); i++) {
            
            // when one of the bullets from player 2 hits player 1, player 2 recieves + 1 to their score
             if(player1.intersects(bullet2.get(i))) {
                 player2Score ++;
                 bullet2.remove(i);
                 
         }
        
        
    }
    }
    
    
}