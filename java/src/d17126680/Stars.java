package d17126680;

import java.util.ArrayList;

import ie.tudublin.Visual;

public class Stars extends Visual
{
    Stars[] star = new Stars[400];
    float speed;
    float x;
    float y;
    float z;
    float pz;
    float angle = 0;
    float smoothedBoxSize = 0;
    boolean drawCube = false;
    float angle2 = 0;
    float smoothedBox2Size = 0; 
    boolean draw2Cube = false;
    float n =0;
    float c = 2;
    float start = 0;
    boolean drawPoints = false;

    ArrayList<PVector> points = new ArrayList<PVector>();

    public Stars() {

        x = random(-width/2, width/2);

        y = random(-height/2, height/2);

        z = random(width/2);
        pz = z;
      }
    
      
    public void settings()
    {
        size(800, 800, P3D);
        println("CWD: " + System.getProperty("user.dir"));
        //fullScreen(P3D, SPAN);
        
    }

    public void keyPressed()
    {
        if (key == ' ')
        {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
            
        }
        if (key == '1')
        {
            drawCube = ! drawCube;
            draw2Cube = false;
            drawPoints = false;
        }
        if (key == '2')
        {
            draw2Cube = ! draw2Cube;
            drawCube = false;
            drawPoints = false;
        }
        if ( key == '3')
        {
            drawPoints = ! drawPoints;
            drawCube = false;
            draw2Cube = false;
            n = 0;
            start = 0;

        }
    }

    public void setup()
    {
        colorMode(HSB);
        noCursor();
        
        setFrameSize(256);

        startMinim();
        loadAudio("DansLaMaison.mp3");
        

        for (int i = 0; i < star.length; i++) {
            star[i] = new Stars();
          }
        
    }

    

    public void draw()
    {
        calculateAverageAmplitude();
        background(0);
        translate(width/2, height/2, -250);
        strokeWeight(1);
        float starsSpeed = 2 + (getAmplitude() * 400);
        speed = lerp(speed, starsSpeed, 1.0f); 

        for (int i = 0; i < star.length; i++) {
            update(star[i]);
            show(star[i]);
        }

        // Drawing one big cube
        if (drawCube) {
            lights();
            noFill();
            stroke(map(getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
            translate(0, 0, -250);
            
            float boxSize = 200 + (getAmplitude() * 600);
            smoothedBoxSize = lerp(smoothedBoxSize, boxSize, 0.1f);        
            rotateY(angle);
            rotateX(angle);           
            strokeWeight(5);
            box(smoothedBoxSize);
            angle += 0.02f;

        }

        // Drawing 2 cubes one inside the other
        if (draw2Cube) {
            lights();
            noFill();
            stroke(map(getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
            translate(0, 0, -250);
            
            float boxSize = 200 + (getAmplitude() * 800);
            smoothedBoxSize = lerp(smoothedBoxSize, boxSize, 0.1f);        
            rotateY(angle);
            rotateX(angle);        
            strokeWeight(5);
            box(smoothedBoxSize);
            angle += 0.02f;

            // Drawing the smaller cube:
            float box2Size = 80 + (getAmplitude() * 400);//map(average, 0, 1, 100, 400); 
            smoothedBox2Size = lerp(smoothedBox2Size, box2Size, 0.2f);        
            rotateY(angle2);
            rotateX(angle2);           
            strokeWeight(3);
            box(smoothedBox2Size);
            angle2 += 0.02f;

        }

        // Drawing points that goes around in a circle
        if (drawPoints)
        {

            rotate((float) (n * 0.3));
            float a = n * radians((float) 137.5);
            float r = c * sqrt(n);
            float x = r * cos(a);
            float y = r * sin(a);
            float hu = n + start;
            hu = (float) (n/3.0 % 360);
            fill(hu, 255, 255);
            noStroke();
            ellipse(x, y, 15, 15);
        
            n += 10;
            start += 10;
        }
    }

    void update(Stars star2) {
        z = z - speed;
  
        if (z < 1) {
          z = width/2;
          x = random(-width/2, width/2);
          y = random(-height/2, height/2);
          pz = z;
        }
    }
    
    void show(Stars star2) {
        fill(255);
        noStroke();
    
        float sx = map(x / z, 0, 1, 0, width/2);
        float sy = map(y / z, 0, 1, 0, height/2);;
    
        float r = map(z, 0, width/2, 15, 0);
        ellipse(sx, sy, r, r);
    

        float px = map(x / pz, 0, 1, 0, width/2);
        float py = map(y / pz, 0, 1, 0, height/2);
    
        pz = z;
    
        stroke(255);
        line(px, py, sx, sy);
    
      }
               
    //   public void drawCube()
    //   {
    //       calculateAverageAmplitude();
    //       background(0);
    //       noFill();
    //       lights();
    //       stroke(map(getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
    //       camera(0, 0, 0, 0, 0, -1, 0, 1, 0);
    //       translate(0, 0, -250);
                 
    //       float boxSize = 50 + (getAmplitude() * 300);//map(average, 0, 1, 100, 400); 
    //       smoothedBoxSize = lerp(smoothedBoxSize, boxSize, 0.2f);        
          
    //       rotateY(angle);
    //       rotateX(angle);
    //       //strokeWeight(1);
    //       //sphere(smoothedBoxSize/ 2);            
    //       strokeWeight(5);
    //       box(smoothedBoxSize);
    //       angle += 0.01f;
    //   }
    //   float angle = 0;
} 