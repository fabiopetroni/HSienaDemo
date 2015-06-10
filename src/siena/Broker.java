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

package siena;

import java.util.LinkedList;
import java.util.ListIterator;
import application.State;

public class Broker {	
	
	private State state;
	private int id;
	private LinkedList<Interface> interfaces;
	private boolean selected;
	
	public Broker(State s){
		state = s;
		id = state.getNextId();
		interfaces = new LinkedList<Interface>();
		state.addBroker(this);
		selected=false;
	}
	
	public boolean selected(){
		return selected;
	}
	
	public void setSelected(){
		selected=true;
	}
	
	public void resetSelected(){
		selected=false;
	}
	
	public int getId(){
		return id;
	}

	public ListIterator<Interface> getInterfaces(){
		return interfaces.listIterator();
	}
	
	public boolean equals(Object o){
		if (o==null || o.getClass()!=this.getClass())
			return false;
		Broker n = (Broker) o;
		return n.id == this.id;
	}
	
	public void insertLink(LinkManager m, Link y){
		Interface i = new Interface(this,y);
		interfaces.add(i);
	}
	
	public String toString(){
		return ""+id;
	}
	
	public void epochUpdate(){
		int position = state.getBrokerPosition(this);
		ListIterator<Interface> it = interfaces.listIterator();
		while (it.hasNext()){
			Interface in = it.next();
			int interface_index = state.getBrokerPosition(in.getLink().getNeighbour(this));
			in.setBcastFunction(state.getStorage().select(interface_index, id, position));
		}
	}
}