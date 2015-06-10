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

package simulator;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

import application.State;
import siena.Broker;
import siena.LinkManager;



public class Reconfiguration {
	
	public static void start(State state){
		Broker node = new Broker(state);
		
		if(state.getSize()==1){
			
			//Create new matrices 1x1 in order to initialize the data structures
			int[][]D = new int[1][1]; 
			D[0][0]=0;
			int[][]pred = new int[1][1]; 
			pred[0][0] = node.getId();			
			state.getStorage().setD(D);
			state.getStorage().setPred(pred);
			
			System.out.println("(Reconfiguration.physicalOverlay) There are no nodes in the system ");
		}//There are no nodes in the system
		else{
			LinkedList<Integer> neighbours = physicalOverlay(state,node);
			bcastLayer(state,node,neighbours);
		}		
	}
	
	private static LinkedList<Integer> physicalOverlay(State state, Broker node){		
		//A NEW NODE WANTS TO JOIN THE SYSTEM
	
		//Pick a random number of neigbours from 1 to state.MAX_NUM_OF_INTERFACES
		Random generator = new Random();
		@SuppressWarnings("static-access")
		int n = (generator.nextInt(state.MAX_NUM_OF_INTERFACES))%state.getSize(); //The number of interface must be smaller (or equal) than the number of node in the system
		if (n==0) n=1;
		//System.out.println("(Reconfiguration.physicalOverlay) Random number of neigbours= "+n);
		
		//Connect the new node to n random neighbours
		LinkedList<Integer> visited = new LinkedList<Integer>();
		for (int i = 0; i<n; i++){
			int r = generator.nextInt(state.getSize()-1); //-1 because the new broker cannot be connected to itself
			while(visited.contains(r)){
				r = (r+1)%(state.getSize()-1);
			}
			visited.add(r);
			Broker neighbour = state.getBroker(r);
			LinkManager.insertLink(state, node, neighbour);
		}			
		return visited;
	}

	private static void bcastLayer(State state, Broker node, LinkedList<Integer> neighbours){
		int en = state.getStorage().getEpochNumber();	//reads the current epoch number en from the storage service;
		int[][] D = state.getStorage().getD();	//reads Di and P redi ;
		int[][] pred = state.getStorage().getPred();
		
		int [][] newD = new int[D.length+1][D.length+1];
		int [][] newPred = new int[D.length+1][D.length+1];
		
		//executes the specific insertion or removal algorithm;
		Algorithm.insertion(state, D, pred, neighbours, newD, newPred);
		
		//stores Di+1 and P redi+1 on the storage service
		state.getStorage().setD(newD);
		state.getStorage().setPred(newPred);
		
		//print the two matrices
		//Algorithm.print(newD,newPred);		
		
		//updates the current epoch to en + 1;
		en++;
		state.getStorage().setEpochNumber(en);
		
		//floods the system with an Epoch Update (EU) message
		ListIterator<Broker> it = state.getBrokersList();
		while(it.hasNext()){
			Broker b = it.next();
			b.epochUpdate();
		}
	}
}