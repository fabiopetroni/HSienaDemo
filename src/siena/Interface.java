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

public class Interface{
	private Broker broker;
	private Link link;
	private LinkedList<Integer> bcast_function;
	
	public Interface(Broker b, Link l){
		broker = b;
		link = l;
		bcast_function = new LinkedList<Integer>();
	}
	
	public Broker getBroker(){
		return broker;
	}
	
	public Link getLink(){
		return link;
	}
	
	public LinkedList<Integer> getBcastFunction(){
		return bcast_function;
	}
	
	public void setBcastFunction(LinkedList<Integer> new_function){
		bcast_function = new_function;
	}
	
	public boolean equals(Object o){
		if (o==null || o.getClass()!=this.getClass())
			return false;
		Interface i = (Interface) o;
		return (i.broker==this.broker) && (i.link==this.link);
	}
}