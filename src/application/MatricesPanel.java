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
import java.awt.Dimension;
import java.awt.Font;
import java.util.ListIterator;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;

import siena.Broker;

@SuppressWarnings("serial")
public class MatricesPanel extends JPanel{
	
	private State state;
	
	public MatricesPanel(State s){
		super(new BorderLayout());
		state = s;
		update();		
	}
	
	public void update(){
		this.removeAll();
		this.updateUI(); 
		int n = state.getNumBrokers();
		String[] columnNames = new String[n];
		Object[][] RowNames1 = new Object[n][1];
		Object[][] RowNames2 = new Object[n][1];
		
		int i = 0;
		ListIterator<Broker> it = state.getBrokersList();
		while (it.hasNext()){
			String id = it.next().toString();
			columnNames[i]=id;
			RowNames1[i][0]=id;
			RowNames2[i][0]=id;
			i++;
		}
		
		String[] aux = {" "};
		JTable rowNames1 = new JTable(RowNames1,aux);
		rowNames1.setBackground(Color.lightGray);
		JTable rowNames2 = new JTable(RowNames2,aux);
		rowNames2.setBackground(Color.lightGray);
		
		i=0;		
		int[][] D = state.getStorage().getD();				
		Object[][] Ddata = new Object[D.length][D.length];
		for (i=0;i<D.length;i++){
			for (int j=0;j<D.length;j++){
				Ddata[i][j] = new Integer(D[i][j]);
			}
		}
		
		i=0;		
		int[][] pred = state.getStorage().getPred();
		Object[][] Preddata = new Object[pred.length][pred.length];
		for (i=0;i<pred.length;i++){
			for (int j=0;j<pred.length;j++){
				Preddata[i][j] = new Integer(pred[i][j]);
			}
		}
		
		JTable tableD = new JTable(Ddata, columnNames);
		JTableHeader DHeader = tableD.getTableHeader();
		DHeader.setBackground(Color.LIGHT_GRAY);
		JTable tablePred = new JTable(Preddata, columnNames);
		JTableHeader PredHeader = tablePred.getTableHeader();
		PredHeader.setBackground(Color.LIGHT_GRAY);
		
		JScrollPane scrollPaneD = new JScrollPane(tableD);
		scrollPaneD.setPreferredSize(new Dimension(400, 300));
		JScrollPane titleD = new JScrollPane(rowNames1);
		titleD.setPreferredSize(new Dimension(25, 300));
		JPanel Dpan = new JPanel(new BorderLayout());
		Dpan.setBackground(Color.BLACK);
		JLabel labelD = new JLabel("D:");
		labelD.setFont(new Font("Sans", Font.BOLD, 20));
		labelD.setForeground(Color.WHITE);	
		Dpan.add(labelD,BorderLayout.NORTH);
		Dpan.add(titleD,BorderLayout.WEST);
		Dpan.add(scrollPaneD,BorderLayout.CENTER);		
		Dpan.setBorder(BorderFactory.createLineBorder(Color.black,15));
		
		JScrollPane scrollPanePred = new JScrollPane(tablePred);
		scrollPanePred.setPreferredSize(new Dimension(400, 300));
		JScrollPane titlePred= new JScrollPane(rowNames2);
		titlePred.setPreferredSize(new Dimension(25, 300));
		JPanel Predpan = new JPanel(new BorderLayout());
		Predpan.setBackground(Color.BLACK);
		JLabel labelPred = new JLabel("PRED:");
		labelPred.setFont(new Font("Sans", Font.BOLD, 20));
		labelPred.setForeground(Color.WHITE);
		Predpan.add(labelPred,BorderLayout.NORTH);
		Predpan.add(titlePred,BorderLayout.WEST);
		Predpan.add(scrollPanePred,BorderLayout.CENTER);
		Predpan.setBorder(BorderFactory.createLineBorder(Color.black,15));
		
		JLabel epoch = new JLabel("EPOCH NUMBER= "+state.getStorage().getEpochNumber());
		epoch.setFont(new Font("Sans", Font.BOLD, 20));
		
		add(epoch,BorderLayout.NORTH);
		add(Dpan,BorderLayout.WEST);
		add(Predpan,BorderLayout.EAST);	    
		setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		this.repaint();
	}
	
}
