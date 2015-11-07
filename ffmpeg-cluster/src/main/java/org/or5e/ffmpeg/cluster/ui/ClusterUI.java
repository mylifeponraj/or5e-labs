package org.or5e.ffmpeg.cluster.ui;

import javax.swing.JFrame;

public class ClusterUI extends JFrame{
	private static final long serialVersionUID = 1L;

	public ClusterUI() {
		super("ClusterUI");
		setSize(1280, 720);
		setResizable(Boolean.FALSE);
		setAlwaysOnTop(Boolean.TRUE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		ClusterUI ui = new ClusterUI();
		ui.setVisible(Boolean.TRUE);
	}
}
