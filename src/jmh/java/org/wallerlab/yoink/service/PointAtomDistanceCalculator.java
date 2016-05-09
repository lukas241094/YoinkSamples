package org.wallerlab.yoink.service;

import org.wallerlab.yoink.domain.Atom;
import org.wallerlab.yoink.domain.Coord;

public class PointAtomDistanceCalculator {
	
	public double calculateDistance(Coord core,Atom moleculeAtom){

		double distance = Math.sqrt((Math.pow(core.getX()-moleculeAtom.getX(), 2.0))+
									(Math.pow(core.getY()-moleculeAtom.getY(), 2.0))+
									(Math.pow(core.getZ()-moleculeAtom.getZ(), 2.0)));
		
		return distance;
	}

}
