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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import siena.Broker;
import siena.Link;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.VisualizationViewer;

@SuppressWarnings("serial")
public class InsertionCheckBox extends JPanel implements ActionListener{
    JRadioButton one;
    JRadioButton ten;
    JRadioButton hundred;

    JButton insert;
    
    private State state;
    private VisualizationViewer<Broker, Link> vv;
    private Layout<Broker, Link> layout;
    private MatricesPanel matrices;

    public InsertionCheckBox(State s, VisualizationViewer<Broker, Link> v, Layout<Broker, Link> l, MatricesPanel mp){
    	
    	super(new BorderLayout());    	
    	state = s;		
	vv=v;
	layout=l;
	matrices = mp;
        

        //Create the check boxes.
        one = new JRadioButton("1");
        one.setMnemonic(KeyEvent.VK_C);
        one.setActionCommand("1");
        one.setSelected(true);
        one.setFont(new Font("Sans", Font.BOLD, 15));

        ten = new JRadioButton("10");
        ten.setMnemonic(KeyEvent.VK_G);
        ten.setActionCommand("10");
        ten.setFont(new Font("Sans", Font.BOLD, 15));

        hundred = new JRadioButton("100");
        hundred.setMnemonic(KeyEvent.VK_H);
        hundred.setActionCommand("100");
        hundred.setFont(new Font("Sans", Font.BOLD, 15));
        
        //Register a listener for the check boxes.
        one.addActionListener(this);
        ten.addActionListener(this);
        hundred.addActionListener(this);
        
        ButtonGroup group = new ButtonGroup();
        group.add(one);
        group.add(ten);
        group.add(hundred);
        
        insert = new JButton("INSERT");
	InsertionListener actionListener = new InsertionListener(state, vv,layout,matrices);
	insert.addActionListener(actionListener);
	insert.setFont(new Font("Sans", Font.BOLD, 22));
	insert.setBackground(Color.BLACK);
	insert.setForeground(Color.WHITE);

        /*Put the check boxes in a column in a panel*/        
        JPanel checkPanel = new JPanel(new GridLayout(0, 1));
        checkPanel.add(one);
        checkPanel.add(ten);
        checkPanel.add(hundred);

        ten.setSelected(false);
        hundred.setSelected(false);
        add(checkPanel, BorderLayout.LINE_START);
        add(insert, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		state.setNumInsertion(Integer.parseInt(e.getActionCommand()));
	}
}
