package bcteamC;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import bcteamC.game.Game;
import bcteamC.game.KeyListener;
import bcteamC.music.Music;
import bcteamC.music.Track;
import bcteamC.panel.GameSelectPanel;
import bcteamC.panel.GameStartPanel;
import bcteamC.panel.JoinPanel;
import bcteamC.panel.LoginPanel;
import bcteamC.panel.StartPanel;

public class BURhythmstar extends JFrame {
	
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	public static final int NOTE_SPEED =3;// 노트 속도
	public static final int SLEEP_TIME =10;// 노트가 얼마간의 시간간격으로 떨어지는지에 대한 변수
	public static final int REACH_TIME =2;// 노트가 생성되고 판정대까지 도달하는 시간이라 생각하면됨

	public static void main(String[] args) {
		new BURhythmstar();
	}
	
	/* Background & init */
	private Image screenImage;
	private Graphics screenGraphic;
	private static Image backgroundImage = new ImageIcon(BURhythmstar.class.getResource("/images/background-intro.jpg")).getImage();
	private static Image gameSelectBackgroundImage = new ImageIcon(BURhythmstar.class.getResource("/images/background-game-select.jpg")).getImage();
	private static Image gameStartBackgroundImage = new ImageIcon(BURhythmstar.class.getResource("/images/background-game-start.jpg")).getImage();
	
	private JLabel menubar = new JLabel(new ImageIcon(BURhythmstar.class.getResource("/images/background-menubar.png")));
	private ImageIcon closeButtonImage = new ImageIcon(BURhythmstar.class.getResource("/images/button-close.png"));
	private ImageIcon closeEnteredButtonImage = new ImageIcon(BURhythmstar.class.getResource("/images/button-close-enter.png"));
	private JButton closeButton = new JButton(closeButtonImage);
	private int mouseX, mouseY;
	
	/* Panel */
	private static StartPanel startPanel;
	private static JoinPanel joinPanel;
	private static LoginPanel loginPanel;
	private static GameSelectPanel gameSelectPanel;
	private static GameStartPanel gameStartPanel;
	private static boolean isGameSelectPanel = false;
	private static boolean isGameStartPanel = false;
	
	/* Track */
	private static ArrayList<Track> track = new ArrayList<Track>();
	private static Music introMusic, selectedMusic;
	private static Image selectedImage = new ImageIcon(BURhythmstar.class.getResource("/images/image-Jim Yosef-Link.png")).getImage();
	private static int nowSelected = 0;
	
	/* Game */
	public static Game game;
	
	public BURhythmstar() {
		backgroundInit();
		
		addKeyListener(new KeyListener());
		setFocusable(true);
		
		track.add(new Track("image-Jim Yosef-Link.png", "Jim Yosef-Link.mp3", "Jim Yosef - Link"));
		track.add(new Track("image-Tobu-Candy Land.png", "Tobu-Candy Land.mp3", "Tobu - Candy Land"));
		track.add(new Track("image-Tobu&Itro-Sunburst.png", "Tobu&Itro-Sunburst.mp3", "Tobu&Itro - Sunburst"));
		
		introMusic = new Music("Joakim Karud-Fresh Start.mp3", true);
		introMusic.start();
		
		startPanel = new StartPanel();
		add(startPanel);
		
		joinPanel = new JoinPanel();
		add(joinPanel);
		
		loginPanel = new LoginPanel();
		add(loginPanel);
		
		gameSelectPanel = new GameSelectPanel();
		add(gameSelectPanel);
		
		gameStartPanel = new GameStartPanel();
		add(gameStartPanel);
		
		changePanel("start");
	}

	public void paint(Graphics g) {
		screenImage = createImage(SCREEN_WIDTH, SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D)screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}
	
	public void screenDraw(Graphics2D g) {
		g.drawImage(backgroundImage, 0, 0, null);
		if(isGameSelectPanel) {
			g.drawImage(selectedImage, 340, 100, null);
		}
		if(isGameStartPanel) {
			game.screenDraw(g);
		}
		
		paintComponents(g);
		try {
			Thread.sleep(5);
		} catch(Exception e) {
			e.printStackTrace();
		}
		this.repaint();
	}
	
	public void backgroundInit() {
		setUndecorated(true);
		setTitle("BURhythmstar");
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		
		closeButton.setBounds(1245, 0, 30, 30);
		closeButton.setBorderPainted(false);
		closeButton.setContentAreaFilled(false);
		closeButton.setFocusPainted(false);
		closeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				closeButton.setIcon(closeEnteredButtonImage);
				closeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				closeButton.setIcon(closeButtonImage);
				closeButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		add(closeButton);
		
		menubar.setBounds(0, 0, 1280, 30);
		menubar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menubar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menubar);
	}

	public static void changePanel(String string) {
		startPanel.setVisible(false);
		joinPanel.setVisible(false);
		loginPanel.setVisible(false);
		gameSelectPanel.setVisible(false);
		gameStartPanel.setVisible(false);
		
		if(string.equals("login")) {
			loginPanel.setVisible(true);
		} else if(string.equals("join")) {
			joinPanel.setVisible(true);
		} else if(string.equals("gameSelect")) {
			gameSelectPanel.setVisible(true);
			
			if(introMusic != null)
				introMusic.close();
			if(game != null)
				game.close();

			backgroundImage = gameSelectBackgroundImage;
			
			isGameSelectPanel = true;
			isGameStartPanel = false;
			
			selectTrack(nowSelected);
		} else if(string.equals("gameStart")) {
			gameStartPanel.setVisible(true);
			
			if(selectedMusic != null) {
				selectedMusic.close();
			}
			
			backgroundImage = gameStartBackgroundImage;
			
			isGameSelectPanel = false;
			isGameStartPanel = true;
		} else {
			startPanel.setVisible(true);
		}
		
	}

	public static void selectTrack(int nowSelected) {
		if(selectedMusic != null) {
			selectedMusic.close();
		}
		
		selectedImage = new ImageIcon(BURhythmstar.class.getResource("/images/"+ track.get(nowSelected).getImage())).getImage();
		selectedMusic = new Music(track.get(nowSelected).getMusicName(), true);
		selectedMusic.start();
	}

	public static void clickPrev() {
		if(nowSelected > 0) {
			nowSelected--;	
		} else {
			nowSelected = track.size() - 1;
		}
		
		selectTrack(nowSelected);
	}
	
	public static void clickNext() {
		if(nowSelected < track.size()-1) {
			nowSelected++;
		} else {
			nowSelected = 0;
		}
		
		selectTrack(nowSelected);
	}
	
	public static void gameStart(String diff) {
		game = new Game(track.get(nowSelected).getMusicName(), track.get(nowSelected).getTitleName(), diff);
		game.start();//game에서 run함수가 실행되어 노트가 떨어짐
	}
	
}