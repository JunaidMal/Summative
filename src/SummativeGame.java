import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Color;
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
    Rectangle ground = new Rectangle(0, 890, 1275, 40);
    
     // random obstacle
    Rectangle obstacle = new Rectangle(400, 520, 50, 40);
    
    // MAP VARIABLES ENDS HERE
    
    ArrayList<Rectangle> bullet = new ArrayList();
   
    Iterator<Rectangle> it = bullet.iterator();
    
    // YOUR GAME VARIABLES WOULD GO HERE
    
    // PLAYER 1 VARIABLES START HERE
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
    
    // for player1
    boolean forward = false;
    boolean backward = false;
  
    //for player1
    int bulletSpeed = 5;
    // +1 - Right
    // -1 - Left
    int bulletDirection = 1;
    
    // for player1
    boolean fireRight1 = false;
    // whether or not the bullet for player 1 is travelling through the air
    boolean p1Pending = false;
    
     int delay = 1200;
    long nextTime = 0;
    
    // PLAYER 1 VARIABLES END HERE //
    
    // PLAYER 2 VARIABLES START HERE // 
    boolean fireLeft = false;
    
    
    
    
    // PLAYER 2 VARIABLES END HERE //
    
    
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
        
        g.fillRect(player1.x, player1.y, player1.width, player1.height);
        g.fillRect(ground.x, ground.y, ground.width, ground.height);
        g.fillRect(obstacle.x, obstacle.y, obstacle.width, obstacle.height);
        
      
        
        for(Rectangle bullets: bullet){
             g.fillRect(bullets.x, bullets.y, bullets.width, bullets.height);
            }
        
        
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
            
            if(fireRight1){
                if(p1Pending == false){
                   bullet.add(new Rectangle(player1.x, player1.y, 10,10));
                   passed = false;
                   p1Pending = true;
                }
                   
                   
               }
            
            for(Rectangle bullets: bullet){
                
                    
                if (passed) {
                    
                    p1Pending = false;
                    passed = false;
                }
                
                 bullets.x += bulletSpeed;
             
                
            
             
           // for(Rectangle bullets: bullet){
               
               
            
               
               
            }
            
            if(startTime > nextTime){
                 nextTime = startTime + delay;
                p1Pending = false;
            }
//            System.out.println(startTime);
            
            
           
            
            
          
            
       
            dy = dy + gravity;
            
            collision();
            
            if(jump && !inAir) {
           
                inAir = true;
                dy = -15;
            }
            
            if(dy > 10) {
                dy = 10;
            }
            
            player1.y = player1.y + dy;
            
            moving();
            
            // controls speed of the player
            double movementTransfer = playerDirection * (0.7) * horizVel;
            
            int movement1 = (int) movementTransfer;
            
            player1.x = player1.x + movement1;
            
            
            
            
            
            
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
            };
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
            
            if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                jump = true;
                inAir = false;
            }
            
            if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                forward = true;
                backward = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                forward = false;
                backward = true;
            }
            
            if(e.getKeyCode() == KeyEvent.VK_M) {
                fireRight1 = true;
            }
            
            
        }
        
        // if a key has been released
        @Override
        public void keyReleased(KeyEvent e){
            
            if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                forward = false;
            }
             if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                backward = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_M) {
                fireRight1 = false;
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
        if(player1.intersects(ground)){
            dy = 0;
        }
        if(player1.y + 0.95*(player1.height) > ground.y){
            player1.y = ground.y - player1.height;
            
        }
        if(player1.intersects(obstacle)){
            horizVel = 0;
            player1.x = obstacle.x - player1.width;
                
        }
    }
    
    public void moving() {
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
            horizVel = horizVel + 2;
        }
        
        if(horizVel >= 16) {
            horizVel = 16;
        }
        
        if(horizVel <= -16) {
            horizVel = -16;
        }
    }
    
    public void p1Shooting() {
        if(fireRight1) {
            
        }
    }
}