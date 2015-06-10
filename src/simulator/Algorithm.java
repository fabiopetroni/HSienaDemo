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

import application.State;

public class Algorithm {
	public static void insertion(State state, int [][] D, int [][] pred, LinkedList<Integer> neighbours, int [][] newD, int[][] newPred){
		int n = D.length;		
		
		//Copy the old matrices in the new
		for (int i = 0; i< n; i++){
			for (int j = 0; j< n; j++){
				newD[i][j] = D[i][j];
				newPred[i][j] = pred[i][j];
			}
		}
		
		for (int j = 0; j< n; j++){
		
			ListIterator<Integer> it = neighbours.listIterator();
		
			int who = it.next();	
			int min = newD[who][j];
			while(it.hasNext()){
				int x = it.next();
				if (newD[x][j]<min){
					min = newD[x][j];
					who = x;
				}
			}
			newD[n][j] = min +1;
			newD[j][n] = min +1;
			newPred[j][n] = state.getBroker(who).getId();
			if (min!=0)
				newPred[n][j] = newPred[who][j];
			else newPred[n][j] = n+1;
			
			D = newD;
			pred = newPred;
		}
		
		newD[n][n] = 0;
		newPred[n][n] = n+1;
		
		int k = n;
		for (int i=0; i<n+1; i++){
			for (int j=0; j<n+1; j++){
				if (newD[i][j]>newD[i][k]+newD[k][j]){
					newD[i][j]= newD[i][k]+newD[k][j];
					newPred[i][j]= newPred[k][j];
				}
			}
		}
		
	}

	public static void print(int [][] newD, int[][] newPred){
		System.out.println("D=");
		for (int i = 0; i<newD.length; i++){
			for (int j = 0; j<newD.length; j++){
				System.out.print(newD[i][j]+" ");
			}
			System.out.println();
		}
		
		System.out.println("pred=");
		for (int i = 0; i<newD.length; i++){
			for (int j = 0; j<newD.length; j++){
				System.out.print(newPred[i][j]+" ");
			}
			System.out.println();
		}
	}

}
