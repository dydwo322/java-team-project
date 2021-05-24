package bcteamC.panel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import bcteamC.BURhythmstar;

public class GameStartPanel extends JPanel {
	
	private ImageIcon gameBackButtonImage = new ImageIcon(BURhythmstar.class.getResource("/images/button-game-back.png"));
	private ImageIcon gameBackEnteredButtonImage = new ImageIcon(BURhythmstar.class.getResource("/images/button-game-back-enter.png"));

	private JButton gameBackButton = new JButton(gameBackButtonImage);
	
	public GameStartPanel() {
		setBounds(0, 0, BURhythmstar.SCREEN_WIDTH, BURhythmstar.SCREEN_HEIGHT);
		setBackground(new Color(255, 0, 0, 0));
		setLayout(null);
		
		/* gameBackButton */
		gameBackButton.setBounds(20, 50, 60, 60);
		gameBackButton.setBorderPainted(false);
		gameBackButton.setContentAreaFilled(false);
		gameBackButton.setFocusPainted(false);
		gameBackButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// 게임 선택 화면 이동
				BURhythmstar.changePanel("gameSelect");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				gameBackButton.setIcon(gameBackEnteredButtonImage);
				gameBackButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				gameBackButton.setIcon(gameBackButtonImage);
				gameBackButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		add(gameBackButton);
	}
	
}
