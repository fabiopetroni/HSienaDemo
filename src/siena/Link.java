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

import java.util.ListIterator;

public class Link {
	
	protected Broker x;
	protected Broker y;
	private boolean selected;
	
	public Link(Broker i, Broker j){
		x=i;
		y=j;
		selected = false;
	}
	
	public boolean selected(){
		return selected;
	}
	
	public void resetSelected(){
		selected=false;
	}
	
	public void setSelected(){
		selected=true;
	}
	
	public boolean equals(Object o){
		if (o==null || o.getClass()!=this.getClass())
			return false;
		Link l = (Link) o;
		return ((l.x == this.x) && (l.y==this.y))||((l.x == this.y) && (l.y==this.x)); 
	}
	
	public Broker getNeighbour(Broker i){
		if (i.equals(x))
			return y;
		else if (i.equals(y))
			return x;
		else return null;
	}
	
	public void setSelectedBcastFunction(int index){
		ListIterator<Interface> it = x.getInterfaces();
		while (it.hasNext()){
			Interface inter = it.next();
			if (inter.getLink().equals(this)){
				if (inter.getBcastFunction().contains(index))
					selected=true;
					break;
			}
		}
		
		it = y.getInterfaces();
		while (it.hasNext()){
			Interface inter = it.next();
			if (inter.getLink().equals(this)){
				if (inter.getBcastFunction().contains(index))
					selected=true;
					break;
			}
		}
		
	}
	
}
