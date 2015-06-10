// Copyright (C) 2015 Fabio Petroni
// Contact:   http://www.fabiopetroni.com
//
// This file is part of HSienaSimulator.
//
// HSienaSimulator is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// HSienaSimulator is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with HSienaSimulator.  If not, see <http://www.gnu.org/licenses/>.
//
// Based on the publication:
// - Fabio Petroni and Leonardo Querzoni (2012): HSIENA: a hybrid publish/subscribe system. 
//   in Proceedings of the 1st International Workshop on Dependable and Secure Computing for 
//   Large-scale Complex Critical Infrastructures (DESEC-LCCI), 2012.

package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import siena.Broker;
import siena.Link;

import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;

@SuppressWarnings("serial")
public class SwitchPanel extends JPanel implements ActionListener{
	private VisualizationViewer<Broker, Link> vv;
	
	JButton button_SWITCH;
	JLabel label;
	JLabel title;
	
	public SwitchPanel(VisualizationViewer<Broker, Link> v){
		super(new BorderLayout());
		vv=v;
		

		button_SWITCH = new JButton("SWITCH");
		button_SWITCH.addActionListener(this);
		button_SWITCH.setFont(new Font("Sans", Font.BOLD, 24));
		button_SWITCH.setBackground(Color.BLACK);
		button_SWITCH.setForeground(Color.WHITE);

		
		label = new JLabel("TRANSFORMING MODE");
		label.setFont(new Font("Sans", Font.BOLD, 12));
		title = new JLabel("HSIENA DEMO");
		title.setFont(new Font("Sans", Font.BOLD, 20));
		title.setForeground(Color.WHITE);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(Color.BLACK);
		titlePanel.add(title);
		
		add(titlePanel, BorderLayout.NORTH);
		add(label, BorderLayout.CENTER);
		add(button_SWITCH, BorderLayout.SOUTH);
		
		setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void actionPerformed(ActionEvent e) {		
		
		DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
		
		if (label.getText().equals("TRANSFORMING MODE")){		
			gm.setMode(ModalGraphMouse.Mode.PICKING);
			label.setText("PICKING MODE");
		}
		else{
			gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
			label.setText("TRANSFORMING MODE");
		}		
		vv.setGraphMouse(gm);
		vv.repaint();
	}
}
