/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg5810110549;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.GeneralPath;
public class Bonus {

    int x, y, width, height;
    private boolean alive = true;
    public Bonus(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        int xPoints[] = {9, 15, 0, 18, 3};
        int yPoints[] = {0, 18, 6, 6, 18};

        Graphics2D g2d = (Graphics2D) g;
        GeneralPath star = new GeneralPath();

        star.moveTo(xPoints[0] + x, yPoints[0] + y);
        for (int i = 1; i < xPoints.length; i++) {
            star.lineTo(xPoints[i] + x, yPoints[i] + y);
        }
        star.closePath();

        g2d.setColor(Color.YELLOW);
        g2d.fill(star);
    }
    public void proceed(){
		y+=10;
		if(y > 600){
			alive = false;
		}
	}
    public boolean isAlive(){
		return alive;
	}
}