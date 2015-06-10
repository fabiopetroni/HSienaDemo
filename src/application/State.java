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

import java.util.LinkedList;
import java.util.ListIterator;

import cloud.Storage;
import siena.*;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;

public class State {
	
	public static final int MAX_NUM_OF_INTERFACES = 5;
	
	private Graph<Broker, Link> graph;
	private LinkedList<Broker> brokers_list;
	int size;
	int next_id;
	private Storage storage;
	private int num_insertion;
	
	public State(){
		graph = new SparseMultigraph<Broker, Link>();
		brokers_list = new LinkedList<Broker>();
		size=0;
		next_id=1;
		storage = new Storage();
		num_insertion = 1;
	}
	
	public int getNumBrokers(){
		return brokers_list.size();
	}
	
	public int getNumInsertion(){
		return num_insertion;
	}
	
	public void setNumInsertion(int x){
		num_insertion =x;
	}
	
	public Graph<Broker, Link> getGraph(){
		return graph;
	}
	
	public int getSize(){
		return size;
	}
	
	public int getNextId(){
		return next_id;
	}
	
	public void addBroker(Broker x){
		if (!brokers_list.contains(x)){
			brokers_list.add(x);
			graph.addVertex(x);
			size++;
			next_id++;
		}
		else System.out.println("ERROR (State.addBroker) broker ["+x+"] already present");
	}
	
	public ListIterator<Broker> getBrokersList(){
		return brokers_list.listIterator();
	}
	
	public Broker getBroker(int i){
		if (i<size){
			return brokers_list.get(i);
		}
		else return null;		
	}
	
	public Storage getStorage(){
		return storage;
	}
	
	public int getBrokerPosition(Broker b){
		//has to be changed when the removal feature will be added
		return b.getId()-1;
	}

}
