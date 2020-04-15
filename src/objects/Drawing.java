package objects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.Random;

import framework.GameObject;
import framework.Handler;
import framework.ID;
import input.MouseHandler;
import input.MouseTrail;
import window.Game;

public class Drawing extends GameObject {
	
	private Game game;
	private Handler handler;
	private double gravity = 0.98;
	private double energyloss = .65;
	private double dt = .3;
	public static double aci=0;
	private int uzunluk = 100;
	private int i,j = 0;
	private int i1,i2,i3,i4;
	private int j1,j2,j3,j4;
	private int radius;
	private MouseTrail mouseTrail = new MouseTrail(30);
	private Color color;
	private Random r = new Random();
	private MouseHandler mouseHandler = new MouseHandler();
	

	public Drawing(float x, float y, ID id,Handler handler,Game game) {
		super(x, y, id);
		this.game = game;
		this.handler = handler;
		radius = width = 30;
		height =30;
		setVelX(r.nextInt(10)+1);
		
	}

	@Override
	public void tick() {
		
		color = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255),200);
	
		
		i = (int) ((int) uzunluk * Math.cos(aci));
		j = (int) ((int) uzunluk * Math.sin(aci));
		
		i1 = game.WIDTH/2+ (int) ((int) uzunluk * Math.cos(aci)) ;
		j1 = game.HEIGHT/2-(int) ((int) uzunluk * Math.sin(aci));
		
		i2 = game.WIDTH/2 + (int) ((int) uzunluk * Math.cos(aci + (0.5*Math.PI)));
		j2 = game.HEIGHT/2-(int) ((int) uzunluk * Math.sin(aci + (0.5*Math.PI)));
		
		i3 = game.WIDTH/2+ (int) ((int) uzunluk * Math.cos(aci + (1*Math.PI)));
		j3 = game.HEIGHT/2-(int) ((int) uzunluk * Math.sin(aci + (1*Math.PI)));
		
		i4 = game.WIDTH/2 +(int) ((int) uzunluk * Math.cos(aci + (1.5*Math.PI)));
		j4 = game.HEIGHT/2-(int) ((int) uzunluk * Math.sin(aci + (1.5*Math.PI)));
		


		
		if(mouseHandler.isButtonLeft()){
			aci = aci + (0.05*Math.PI);
		}else if(mouseHandler.isButtonRight()){
			
			aci = aci - (0.05*Math.PI);
		}
		else{
			aci = aci + (0.005*Math.PI);
		}
		
	    if (y > 0) {
            mouseTrail.addTrail((int)x + radius/2, (int)y + radius/2);

        }
        else {
            mouseTrail.standBy();
        }

	}

	@Override
	public void render(Graphics2D g) {
		

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		

		
		g.setColor(color);
		
		g.setStroke(new BasicStroke(5));
		g.drawLine(game.WIDTH/2, game.HEIGHT/2, game.WIDTH/2 - i*2, game.HEIGHT/2 -j*2);
		g.drawLine(game.WIDTH/2, game.HEIGHT/2, game.WIDTH/2 + i*2, game.HEIGHT/2 +j*2);
		
		g.setStroke(new BasicStroke(15));
		g.drawLine((int)x,(int)y, game.WIDTH/2 - i, game.HEIGHT/2 -j);
		g.drawLine((int)x,(int)y, game.WIDTH/2 + i, game.HEIGHT/2 +j);
		
		g.drawLine(i1, j1, i2, j2);
		g.drawLine(i2, j2, i3, j3);
		g.drawLine(i3, j3, i4, j4);
		g.drawLine(i4, j4, i1, j1);
	
	}


	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height);
	}

}
