/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg5810110549;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	private BufferedImage bi;	
	Graphics2D big;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();
        ArrayList<Bonus> star = new ArrayList<Bonus>();
	public GamePanel() {
		bi = new BufferedImage(400, 600, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		big.setBackground(Color.darkGray);
	}

	public void updateGameUI(GameReporter reporter){
            
		big.clearRect(0, 0, 400, 600);
		
		big.setColor(Color.WHITE);		
		big.drawString(String.format("Level= %1.0f ",reporter.getLevel()), 320, 20);
                big.drawString(String.format("ScoreV2=%08d", reporter.getScore2()), 270, 590);
                big.drawString(String.format("ScoreV=%08d", reporter.getScore()), 0, 590);
		for(Sprite s : sprites){
			s.draw(big);
		}
		for(Bonus s : star){
			s.draw(big);
		}
		repaint();
	}
        public void gameOver(GameReporter reporter){
            
		big.clearRect(0, 0, 400, 600);
		
		big.setColor(Color.WHITE);		
            big.drawString(String.format("GAME OVER!!!"), 150, 100);
                if(reporter.getScore()>reporter.getScore2())
                    big.drawString(String.format("Player 1 Win!!"), 150, 300);
                else
                    big.drawString(String.format("Player 2 Win!!"), 150, 300);
                big.drawString(String.format("Player1 Score=%08d", reporter.getScore()), 120, 350);
                big.drawString(String.format("Player2 Score=%08d", reporter.getScore2()), 120, 400);
                big.drawString(String.format("Kanthawat  Phukaew     5810110549"), 0, 590);
		
	}
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}

}
