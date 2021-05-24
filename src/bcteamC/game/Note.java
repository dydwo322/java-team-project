package bcteamC.game;


import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import com.sun.tools.javac.Main;

import bcteamC.BURhythmstar;

public class Note extends Thread{
	private Image noteBasicImage = new ImageIcon(Main.class.getResource("/images/image-game-noteBasic.png")).getImage();//노트 이미지
	private int x, y = 580 - (1000 / BURhythmstar.SLEEP_TIME * BURhythmstar.NOTE_SPEED) * BURhythmstar.REACH_TIME;// y축 고정 & 노트가 생성되고 1초후에 판정라인에 도달
	private String noteType;
	
	
	public Note(String noteType) {
		if(noteType.equals("S")) {
			x = 228;
		}
		else if(noteType.equals("D")) {
			x = 332;
		}
		else if(noteType.equals("F")) {
			x = 436;
		}
		else if(noteType.equals("Space")) {
			x = 540;
		}
		else if(noteType.equals("J")) {
			x = 744;
		}
		else if(noteType.equals("K")) {
			x = 848;
		}
		else if(noteType.equals("L")) {
			x = 952;
		}
		this.noteType = noteType;
	}
	public void screenDraw(Graphics2D g) {
		if(noteType.equals("Space"))
		{
			g.drawImage(noteBasicImage, x, y, null);
		}
		else
		{
			g.drawImage(noteBasicImage, x, y, null);
			g.drawImage(noteBasicImage, x + 100, y, null);
		}
	}
	public void drop() {
		y += BURhythmstar.NOTE_SPEED;
		
	}
	@Override
	public void run() {
		try {
			while (true) {
				drop();
				Thread.sleep(BURhythmstar.SLEEP_TIME);//1초에 Y좌표 픽셀 700만큼 내려감
			}
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
