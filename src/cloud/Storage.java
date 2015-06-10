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

package cloud;

import java.util.LinkedList;

public class Storage {
	
	private int epoch_number;
	private int [][] pred;
	private int [][] D;
	
	public Storage(){
		epoch_number=0;
		D = new int[0][0];  
		pred = new int[0][0];  
	}
	
	public int [][] getPred(){
		return pred;
	}
	
	public int [][] getD(){
		return D;
	}
	
	public int getEpochNumber(){
		return epoch_number;
	}
	
	public void setPred(int [][] m){
		pred = m;
	}
	
	public void setD(int [][] m){
		D=m;
	}
	
	public void setEpochNumber(int en){
		epoch_number = en;
	}
	
	public LinkedList<Integer> select(int column, int id, int position){
		LinkedList<Integer> list = new LinkedList<Integer>();
		for (int i=0; i<pred.length; i++){
			if (i!=position && pred[i][column]==id)
				list.add(i);			
		}
		return list;
	}
}
