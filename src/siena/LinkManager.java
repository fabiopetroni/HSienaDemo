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

import application.*;

public final class LinkManager {
	private State state;
	private LinkManager(State s){
		state = s;
	}

	public static void insertLink(State s, Broker x, Broker y) {
		if (s!=null && x!=null && y!=null && !x.equals(y)){
			LinkManager m = new LinkManager(s);
			Link l = new Link(x,y);
			x.insertLink(m, l);
			y.insertLink(m, l);
			m.state.getGraph().addEdge(l, x, y);
		}
	}

}
