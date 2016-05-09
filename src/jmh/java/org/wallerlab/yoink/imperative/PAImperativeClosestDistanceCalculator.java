package org.wallerlab.yoink.imperative;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.wallerlab.yoink.domain.Atom;
import org.wallerlab.yoink.domain.Coord;
import org.wallerlab.yoink.domain.Molecule;
import org.wallerlab.yoink.service.PointAtomDistanceCalculator;

public class PAImperativeClosestDistanceCalculator {
	
	private PointAtomDistanceCalculator distanceCalculator = new PointAtomDistanceCalculator();
	
	public double calculateClosestDistance(Coord core,Molecule molecule){
		List<Double> distances = new ArrayList<Double>();
		for (Atom atom : molecule.getAtoms()) {
			double tempdistance = distanceCalculator.calculateDistance(core,
					atom);
			distances.add(tempdistance);
		}
		double distance = Collections.min(distances);
		return distance;
	}
	
	

}
