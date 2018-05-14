/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg5810110549;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        private ArrayList<Buff> buffs = new ArrayList<Buff>();
        private ArrayList<Bonus> star = new ArrayList<Bonus>();
	private SpaceShip v;	
	private SpaceShip v2;
	private Timer timer;
	
	private long score = 0;
        private long score2 = 0;
        private int time = 0;
        private int count = 0;
        private int live = 1;
        private int live2 = 1;
	private double difficulty = 0.1;
	
	public GameEngine(GamePanel gp, SpaceShip v,SpaceShip v2) {
		this.gp = gp;
		this.v = v;		
		this.v2 = v2;
		gp.sprites.add(v);
		gp.sprites.add(v2);
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
			}
		});
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
	}
	
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		enemies.add(e);
	}
	private void generateBuff(){
		Buff e = new Buff((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		buffs.add(e);
	}
        private void generateStar(){
		Bonus e = new Bonus((int)(Math.random()*390), 30);
		gp.star.add(e);
		star.add(e);
	}
	private void process(){
            
                if(Math.random() < 0.01){
			generateStar();
                }
              
		if(Math.random() < difficulty){
			generateEnemy();
                       
		}
                if(Math.random() < (0.07))
                            generateBuff();
		
		Iterator<Enemy> e_iter = enemies.iterator();
                Iterator<Buff> b_iter = buffs.iterator();
                Iterator<Bonus> c_iter = star.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
			}
		}
                while(b_iter.hasNext()){
                        
			Buff b = b_iter.next();
			b.proceed();
                        
                }
                
                while(c_iter.hasNext()){
			Bonus c = c_iter.next();
			c.proceed();
                        if(!c.isAlive()){
                        c_iter.remove();
                        gp.star.remove(c);
                        }
                }
            
		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				live=0;
				return;
			}
		}
                
                Rectangle2D.Double vr2 = v2.getRectangle();
		Rectangle2D.Double er2;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr2)){
                                live2=0;
				return;
			}
		}
                if(live==0&&live2==0)
                    die();
                
                for(Buff e : buffs){
			er = e.getRectangle();
			if(er.intersects(vr)&&live==1){
				score += (100*difficulty*10);
                                count++;
                                if(count%10==0)
                                    difficulty+=0.03;
			}
		}
                for(Buff e : buffs){
			er2 = e.getRectangle();
			if(er2.intersects(vr2)&&live2==1){
				score2 += (100*difficulty*10);
                                count++;
                                if(count%10==0)
                                    difficulty+=0.03;
			}
		}
                
                for(Bonus e : star){
                    if(live==1)
			score+=5;
                    if(live2==1)
                        score2+=5;
		}
	}
	
	public void die(){
		timer.stop();
                gp.gameOver(this);
	}
	
	void controlVehicle(KeyEvent e) {
            if(live==1)
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v.move(-1);
			break;
		case KeyEvent.VK_RIGHT:
			v.move(1);
			break;
                }
            if(live2==1)
            switch (e.getKeyCode()){
                case KeyEvent.VK_A:
			v2.move(-1);
			break;
		case KeyEvent.VK_D:
			v2.move(1);
			break;
            }
		switch (e.getKeyCode()) {
		
                case KeyEvent.VK_F9:
			difficulty += 0.1;
			break;
                case KeyEvent.VK_F8:
                    if(difficulty>0.1)
			difficulty -= 0.1;
			break;
                case KeyEvent.VK_F1:
                    if(timer.isRunning())
			timer.stop();
                    else
			timer.start();
			break;
                
		}
	}
        public double getLevel(){
		return difficulty*10;
	}
	public long getScore(){
		return score;
	}
	public long getScore2(){
		return score2;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}
