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

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ListIterator;
import siena.Broker;
import siena.Interface;
import siena.Link;
import org.apache.commons.collections15.Transformer;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.control.GraphMouseListener;

@SuppressWarnings("rawtypes")
public class BrokersMouseListener implements GraphMouseListener {
	
	private State state;
	private BasicVisualizationServer<Broker, Link> vv;
	
	public BrokersMouseListener(State s, BasicVisualizationServer<Broker, Link> v){
		state = s;		
		vv=v;
	}
	
	@Override
	public void graphClicked(Object arg0, MouseEvent arg1) {
		
		//Reset the selected variable for all the broker
		ListIterator<Broker> it = state.getBrokersList();
		while (it.hasNext()){
			it.next().resetSelected();
		}
		
		Broker broker = (Broker) arg0;
		
		//SET the selected variable for THE SPECIFIC broker
		broker.setSelected();
		Transformer<Broker,Paint> vertexPaint = new Transformer<Broker,Paint>() {
				public Paint transform(Broker b) {
					if (b.selected()){
						return Color.GREEN;
					}
					return Color.GRAY;
				}
		};
		
		//Reset the selected variable for all the link
		it = state.getBrokersList();
		while (it.hasNext()){
			Broker b = it.next();
			ListIterator<Interface> intit = b.getInterfaces();
			while (intit.hasNext()){
				Interface ix = intit.next();
				ix.getLink().resetSelected();
			}
		}
		
		//SET the selected variable for THE SPECIFIC links
		it = state.getBrokersList();
		while (it.hasNext()){
			Broker b = it.next();
			ListIterator<Interface> intit = b.getInterfaces();
			if (b.equals(broker)){
				while (intit.hasNext()){
					Interface ix = intit.next();
					ix.getLink().setSelected();
				}
			}
			else{
				while (intit.hasNext()){
					Interface ix = intit.next();
					ix.getLink().setSelectedBcastFunction(state.getBrokerPosition(broker));
				}
			}
		}
		
		Transformer<Link, Stroke> edgeStrokeTransformer =	new Transformer<Link, Stroke>() {
				public Stroke transform(Link link) {
					
					if (link.selected()){
						final Stroke edgeStroke = new BasicStroke(2);
						return edgeStroke;
					}
					else{
						float[] dot = {1.0f, 3.0f};
						final Stroke edgeStroke = new BasicStroke(1.0f,BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f, dot, 0f);
						return edgeStroke;
					}
				}				
		};

		
		vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
		vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);

		//((FRLayout)layout).initialize();
        //((FRLayout)layout).step();
        vv.repaint();	
	}

	@Override
	public void graphPressed(Object arg0, MouseEvent arg1) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void graphReleased(Object arg0, MouseEvent arg1) {
		// TODO Auto-generated method stub		
	}

}
