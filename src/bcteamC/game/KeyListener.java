package bcteamC.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import bcteamC.BURhythmstar;

public class KeyListener extends KeyAdapter {

	@Override
	public void keyPressed(KeyEvent e) {
		if(BURhythmstar.game == null) {
			return;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_S) {
			BURhythmstar.game.pressS();
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			BURhythmstar.game.pressD();
		}
		if(e.getKeyCode() == KeyEvent.VK_F) {
			BURhythmstar.game.pressF();
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			BURhythmstar.game.pressSpace();
		}
		if(e.getKeyCode() == KeyEvent.VK_J) {
			BURhythmstar.game.pressJ();
		}
		if(e.getKeyCode() == KeyEvent.VK_K) {
			BURhythmstar.game.pressK();
		}
		if(e.getKeyCode() == KeyEvent.VK_L) {
			BURhythmstar.game.pressL();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(BURhythmstar.game == null) {
			return;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_S) {
			BURhythmstar.game.releaseS();
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			BURhythmstar.game.releaseD();
		}
		if(e.getKeyCode() == KeyEvent.VK_F) {
			BURhythmstar.game.releaseF();
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			BURhythmstar.game.releaseSpace();
		}
		if(e.getKeyCode() == KeyEvent.VK_J) {
			BURhythmstar.game.releaseJ();
		}
		if(e.getKeyCode() == KeyEvent.VK_K) {
			BURhythmstar.game.releaseK();
		}
		if(e.getKeyCode() == KeyEvent.VK_L) {
			BURhythmstar.game.releaseL();
		}
	}
	
}
