package bcteamC.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.sun.tools.javac.Main;

import bcteamC.BURhythmstar;
import bcteamC.music.Music;

public class Game extends Thread {
	
	private Image noteRouteLineImage = new ImageIcon(BURhythmstar.class.getResource("/images/image-game-noteRouteLine.png")).getImage();
	private Image judgmentLineImage = new ImageIcon(BURhythmstar.class.getResource("/images/image-game-judgementLine.png")).getImage();
	private Image gameInfoImage = new ImageIcon(BURhythmstar.class.getResource("/images/image-game-gameInfo.png")).getImage();
	private Image noteRouteSImage = new ImageIcon(BURhythmstar.class.getResource("/images/image-game-noteRoute.png")).getImage();
	private Image noteRouteDImage = new ImageIcon(BURhythmstar.class.getResource("/images/image-game-noteRoute.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(BURhythmstar.class.getResource("/images/image-game-noteRoute.png")).getImage();
	private Image noteRouteSpace1Image = new ImageIcon(BURhythmstar.class.getResource("/images/image-game-noteRoute.png")).getImage();
	private Image noteRouteSpace2Image = new ImageIcon(BURhythmstar.class.getResource("/images/image-game-noteRoute.png")).getImage();
	private Image noteRouteJImage = new ImageIcon(BURhythmstar.class.getResource("/images/image-game-noteRoute.png")).getImage();
	private Image noteRouteKImage = new ImageIcon(BURhythmstar.class.getResource("/images/image-game-noteRoute.png")).getImage();
	private Image noteRouteLImage = new ImageIcon(BURhythmstar.class.getResource("/images/image-game-noteRoute.png")).getImage();
	
	private Music gameMusic;
	private String musicName;
	private String titleName;
	private String diff;
	
	
	ArrayList<Note> noteList = new ArrayList<Note>();//노트들을 생성되고 나서부터 관리해주는 배열
	
	private int pointX;
	
	public Game(String musicName, String titleName, String diff) {
		this.musicName = musicName;
		this.titleName = titleName;
		this.diff = diff;
		gameMusic = new Music(this.musicName, false);
	}

	
	public void screenDraw(Graphics2D g) {
		// noteRouteImage 
		// x좌표 : 228, 332, 436, 540, 640, 744, 848, 952
		Image[] imageList = {noteRouteSImage, noteRouteDImage, noteRouteFImage, noteRouteSpace1Image, noteRouteSpace2Image, noteRouteJImage, noteRouteKImage, noteRouteLImage};
		pointX = 228;
		for(int i=0; i<8; i++) {
			g.drawImage(imageList[i], pointX, 30, null);
			
			if(i==3)
				pointX += 100;
			else
				pointX += 104;
		}
		
		// noteRouteLineImage 
		// x좌표 : 224, 328, 432, 536, 740, 844, 948, 1052
		pointX = 224;
		for(int i=0; i<8; i++) {
			g.drawImage(noteRouteLineImage, pointX, 30, null);
			
			if(i==3) 
				pointX += 204;
			else 				
				pointX += 104;
		}
		
		
		// 판정 라인
		g.drawImage(gameInfoImage, 0, 660, null);
		g.drawImage(judgmentLineImage, 0, 580, null);
		//
		
		//리스트에 있는 노트를 하나씩 돌면서 그려줌(판정 라인보다 위쪽에 있어야됨)
				for(int i = 0; i< noteList.size();i++) {
					Note note = noteList.get(i);
					note.screenDraw(g);
				}
		/* 노트 애니메이션 구현 */
	
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		
		
		// 키보드 표시
		// x좌표 : 270, 374, 478, 582, 786, 890, 994 
		g.setColor(Color.DARK_GRAY);
		g.setFont(new Font("Arial", Font.PLAIN, 25));
		String[] stringList = {"S", "D", "F", "Space Bar", "J", "K", "K"};
		pointX = 270;
		for(int i=0; i<7; i++) {
			g.drawString(stringList[i], pointX, 609);
			
			if(i==3)
				pointX += 204;
			else
				pointX += 104;
		}
		
		// 하단 좌측 - 곡명
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(titleName, 20, 702);

		// 하단 중앙 - 점수
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Elephant", Font.BOLD, 30));
		g.drawString("000000", 565, 702);
		
		// 하단 우측 - 난이도
		if(diff.equals("Normal"))
			g.setColor(Color.green);
		else 
			g.setColor(Color.red);
		
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(diff, 1160, 702);
	}
	
	@Override
	public void run() {
		dropNotes();
	}

	public void close() {
		gameMusic.close();
		this.interrupt();
	}
	//곡과 난이도에 따라 노트가 떨어지게하는 애니메이션 구사
	public void dropNotes() {
		Beat[] beats = null;
		if(titleName.equals("Jim Yosef-Link") & diff.equals("Normal")){
				int startTime = 4460 - BURhythmstar.REACH_TIME * 1000;
				int gap = 125;
				beats = new Beat[] {
						new Beat(startTime,"Space"),
						new Beat(startTime + gap *2,"Space"),
						new Beat(startTime + gap *4,"S"),
						new Beat(startTime + gap *6,"D"),
						new Beat(startTime + gap *8,"F"),
						new Beat(startTime + gap *10,"D"),
						new Beat(startTime + gap *12,"S"),
						new Beat(startTime + gap *14,"J"),
						new Beat(startTime + gap *16,"K"),
						new Beat(startTime + gap *18,"L"),
						new Beat(startTime + gap *20,"K"),
						new Beat(startTime + gap *22,"J"),
				};
				
		}
		else if(titleName.equals("Jim Yosef-Link") & diff.equals("Normal")){
			int startTime = 1000;
			beats = new Beat[] {
					new Beat(startTime,"Space"),
			};
		}
		else if(titleName.equals("Tobu-Candy Land") & diff.equals("Normal")){
			int startTime = 1000;
			beats = new Beat[] {
					new Beat(startTime,"Space"),
			};
		}
		else if(titleName.equals("Tobu&Itro-Sunburst") & diff.equals("Hard")){
			int startTime = 1000;
			beats = new Beat[] {
					new Beat(startTime,"Space"),
			};
		}
		else if(titleName.equals("Tobu-Candy Land") & diff.equals("Hard")){
			int startTime = 1000;
			beats = new Beat[] {
					new Beat(startTime,"Space"),
			};
		}
		else if(titleName.equals("Tobu-Candy Land") & diff.equals("Hard")){
			int startTime = 1000;
			beats = new Beat[] {
					new Beat(startTime,"Space"),
			};
		}
		int i = 0;
		//곡이 재생되는 시점을 실시간으로 파악해서 해당위치에 걸맞는 노트를 떨구는것 구현
		gameMusic.start();
		while(i < beats.length && !isInterrupted()) {
			boolean dropped = false;
			if(beats[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}
			if(!dropped) {
				try {
					Thread.sleep(5);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void pressS() {
		noteRouteSImage = new ImageIcon(BURhythmstar.class.getResource("/images/image-game-noteRoutePress.png")).getImage();
	}
	
	public void releaseS() {
		noteRouteSImage = new ImageIcon(BURhythmstar.class.getResource("/images/image-game-noteRoute.png")).getImage();
	}
	
	public void pressD() {
		noteRouteDImage = new ImageIcon(BURhythmstar.class.getResource("/images/image-game-noteRoutePress.png")).getImage();
	}
	
	public void releaseD() {
		noteRouteDImage = new ImageIcon(BURhythmstar.class.getResource("/images/image-game-noteRoute.png")).getImage();
	}
	
	public void pressF() {
		noteRouteFImage = new ImageIcon(BURhythmstar.class.getResource("/images/image-game-noteRoutePress.png")).getImage();
	}
	
	public void releaseF() {
		noteRouteFImage = new ImageIcon(BURhythmstar.class.getResource("/images/image-game-noteRoute.png")).getImage();
	}
	
	public void pressSpace() {
		noteRouteSpace1Image = new ImageIcon(BURhythmstar.class.getResource("/images/image-game-noteRoutePress.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(BURhythmstar.class.getResource("/images/image-game-noteRoutePress.png")).getImage();
	}
	
	public void releaseSpace() {
		noteRouteSpace1Image = new ImageIcon(BURhythmstar.class.getResource("/images/image-game-noteRoute.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(BURhythmstar.class.getResource("/images/image-game-noteRoute.png")).getImage();
	}
	
	public void pressJ() {
		noteRouteJImage = new ImageIcon(BURhythmstar.class.getResource("/images/image-game-noteRoutePress.png")).getImage();
	}
	
	public void releaseJ() {
		noteRouteJImage = new ImageIcon(BURhythmstar.class.getResource("/images/image-game-noteRoute.png")).getImage();
	}
	
	public void pressK() {
		noteRouteKImage = new ImageIcon(BURhythmstar.class.getResource("/images/image-game-noteRoutePress.png")).getImage();
	}
	
	public void releaseK() {
		noteRouteKImage = new ImageIcon(BURhythmstar.class.getResource("/images/image-game-noteRoute.png")).getImage();
	}
	
	public void pressL() {
		noteRouteLImage = new ImageIcon(BURhythmstar.class.getResource("/images/image-game-noteRoutePress.png")).getImage();
	}
	
	public void releaseL() {
		noteRouteLImage = new ImageIcon(BURhythmstar.class.getResource("/images/image-game-noteRoute.png")).getImage();
	}
	
}
