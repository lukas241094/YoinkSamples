package Examples;

import java.util.ArrayList;
import java.util.List;

import org.wallerlab.yoink.domain.Molecule;

public class ExampleStream {
	
	List<Molecule> molecules = new ArrayList<Molecule>();
	
	Molecule m1 = new Molecule(1,3);
	Molecule m2 = new Molecule(2,5);
	
	molecules.add(m1);
	molecules.add(m2);
	

}
