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

import siena.*;
import simulator.Reconfiguration;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import edu.uci.ics.jung.algorithms.layout.*;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;

public class Main {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main (String [] args){
		State state= new State();
		
		//Insert the first node
		Reconfiguration.start(state);		
		
		Layout<Broker, Link> layout = new FRLayout<Broker, Link>(state.getGraph());
		layout.setSize(new Dimension(500,500)); // sets the initial size of the space
		// The BasicVisualizationServer<V,E> is parameterized by the edge types
		VisualizationViewer<Broker, Link> vv = new VisualizationViewer<Broker, Link>(layout);
		vv.setPreferredSize(new Dimension(800,800)); //Sets the viewing area size
		
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
		
		DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
        gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
        vv.setGraphMouse(gm);
        vv.addGraphMouseListener(new BrokersMouseListener(state, vv));
        
        MatricesPanel matrices = new MatricesPanel(state);

		JFrame frame = new JFrame("Event Notification Service");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel p = new JPanel(new GridLayout(2,1));
		
		p.add(new SwitchPanel(vv));
		p.add(new InsertionCheckBox(state, vv,layout,matrices));
		Container contentPane = frame.getContentPane();
		contentPane.add(p, BorderLayout.EAST);
		contentPane.add(vv, BorderLayout.CENTER);
		contentPane.add(matrices, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
	}	
}
