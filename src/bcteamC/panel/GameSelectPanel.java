package bcteamC.panel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bcteamC.BURhythmstar;

public class GameSelectPanel extends JPanel {
	
	private ImageIcon prevButtonImage = new ImageIcon(BURhythmstar.class.getResource("/images/button-prev.png"));
	private ImageIcon nextButtonImage = new ImageIcon(BURhythmstar.class.getResource("/images/button-next.png"));
	private ImageIcon normalButtonImage = new ImageIcon(BURhythmstar.class.getResource("/images/button-normal.png"));
	private ImageIcon hardButtonImage = new ImageIcon(BURhythmstar.class.getResource("/images/button-hard.png"));
	
	private ImageIcon prevEnteredButtonImage = new ImageIcon(BURhythmstar.class.getResource("/images/button-prev-enter.png"));
	private ImageIcon nextEnteredButtonImage = new ImageIcon(BURhythmstar.class.getResource("/images/button-next-enter.png"));
	private ImageIcon normalEnteredButtonImage = new ImageIcon(BURhythmstar.class.getResource("/images/button-normal-enter.png"));
	private ImageIcon hardEnteredButtonImage = new ImageIcon(BURhythmstar.class.getResource("/images/button-hard-enter.png"));
	
	private JButton prevButton = new JButton(prevButtonImage);
	private JButton nextButton = new JButton(nextButtonImage);
	private JButton normalButton = new JButton(normalButtonImage);
	private JButton hardButton = new JButton(hardButtonImage);
	
	public GameSelectPanel() {
		setBounds(0, 0, BURhythmstar.SCREEN_WIDTH, BURhythmstar.SCREEN_HEIGHT);
		setBackground(new Color(255, 0, 0, 0));
		setLayout(null);
		
		/* prevButton */
		prevButton.setBounds(140, 310, 180, 60);
		prevButton.setBorderPainted(false);
		prevButton.setContentAreaFilled(false);
		prevButton.setFocusPainted(false);
		prevButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				BURhythmstar.clickPrev();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				prevButton.setIcon(prevEnteredButtonImage);
				prevButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				prevButton.setIcon(prevButtonImage);
				prevButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		add(prevButton);
		
		/* nextButton */
		nextButton.setBounds(960, 310, 180, 60);
		nextButton.setBorderPainted(false);
		nextButton.setContentAreaFilled(false);
		nextButton.setFocusPainted(false);
		nextButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				BURhythmstar.clickNext();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				nextButton.setIcon(nextEnteredButtonImage);
				nextButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				nextButton.setIcon(nextButtonImage);
				nextButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		add(nextButton);
		
		/* Label */
		JLabel lbl_normal = new JLabel("Normal :");
		lbl_normal.setFont(new Font("Lato", Font.BOLD, 30));
		lbl_normal.setBounds(370, 560, 200, 60);
		lbl_normal.setForeground(Color.white);
		add(lbl_normal);
		
		JLabel lbl_hard = new JLabel("Hard :");
		lbl_hard.setFont(new Font("Lato", Font.BOLD, 30));
		lbl_hard.setBounds(700, 560, 200, 60);
		lbl_hard.setForeground(Color.white);
		add(lbl_hard);
		
		/* textField */
		JTextField txt_normal = new JTextField();
		txt_normal.setFont(new Font("Lato", Font.PLAIN, 30));
		txt_normal.setBounds(500, 560, 150, 60);
		// DB 연동 - normal 점수 세팅
		txt_normal.setText("000000");
		txt_normal.setOpaque(false);
		txt_normal.setForeground(Color.white);
		txt_normal.setBorder(null);
		add(txt_normal);
		
		JTextField txt_hard = new JTextField();
		txt_hard.setFont(new Font("Lato", Font.PLAIN, 30));
		txt_hard.setBounds(800, 560, 150, 60);
		// DB 연동 - hard 점수 세팅
		txt_hard.setText("000000");
		txt_hard.setOpaque(false);
		txt_hard.setForeground(Color.white);
		txt_hard.setBorder(null);
		add(txt_hard);
		
		/* normalButton */
		normalButton.setBounds(400, 630, 180, 60);
		normalButton.setBorderPainted(false);
		normalButton.setContentAreaFilled(false);
		normalButton.setFocusPainted(false);
		normalButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// 게임 화면 이동(normal 난이도)
				BURhythmstar.changePanel("gameStart");
				BURhythmstar.gameStart("Normal");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				normalButton.setIcon(normalEnteredButtonImage);
				normalButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				normalButton.setIcon(normalButtonImage);
				normalButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		add(normalButton);
		
		/* hardButton */
		hardButton.setBounds(720, 630, 180, 60);
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// 게임 화면 이동(hard 난이도)
				BURhythmstar.changePanel("gameStart");
				BURhythmstar.gameStart("Hard");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				hardButton.setIcon(hardEnteredButtonImage);
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonImage);
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		add(hardButton);
	}
}
