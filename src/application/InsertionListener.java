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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.uci.ics.jung.algorithms.layout.*;
import edu.uci.ics.jung.visualization.VisualizationViewer;

import siena.Broker;
import siena.Link;
import simulator.Reconfiguration;

public class InsertionListener implements ActionListener{

	private State state;
	private VisualizationViewer<Broker, Link> vv;
	private Layout<Broker, Link> layout;
	private MatricesPanel matrices;
	
	public InsertionListener(State s, VisualizationViewer<Broker, Link> v, Layout<Broker, Link> l, MatricesPanel mp){
		state = s;		
		vv=v;
		layout=l;
		matrices = mp;
	}
	
	@SuppressWarnings("rawtypes")
	public void actionPerformed(ActionEvent actionEvent) {		
		for (int j=0; j<state.getNumInsertion(); j++){
			Reconfiguration.start(state);						
			((FRLayout)layout).initialize();
		    ((FRLayout)layout).step();
		    vv.repaint();
		    matrices.update();
		}
	}

}
