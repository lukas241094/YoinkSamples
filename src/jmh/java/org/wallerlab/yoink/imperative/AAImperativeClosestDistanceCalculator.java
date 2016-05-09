package org.wallerlab.yoink.imperative;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.wallerlab.yoink.domain.Atom;
import org.wallerlab.yoink.domain.Coord;
import org.wallerlab.yoink.domain.Molecule;
import org.wallerlab.yoink.service.AtomAtomDistanceCalculator;



public class AAImperativeClosestDistanceCalculator {
	
	private AtomAtomDistanceCalculator distanceCalculator = new AtomAtomDistanceCalculator();
	
	public double calculateClosestDistance(Molecule core,Molecule molecule){
		List<Double> distances = new ArrayList<Double>();
		for (Atom atom : molecule.getAtoms()) {
			for (Atom coreAtom : core.getAtoms()){

				
			double tempdistance = distanceCalculator.calculateDistance(coreAtom,
					atom);
			distances.add(tempdistance);
			
			}
		}
		double distance = Collections.min(distances);
		return distance;

	}
}
