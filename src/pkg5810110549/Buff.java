/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg5810110549;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class Buff extends Sprite{
	
	private int step = 12;
	private boolean point = true;

    public Buff(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
	
	public Buff(int x, int y) {
		super(x, y, 10, 10);
		
	}

	@Override
	public void draw(Graphics2D g) {
		
		g.setColor(Color.blue);
		g.fillRect(x, y, width, height);
		
	}

	public void proceed(){
		y += step;
		
	}
	
	
}