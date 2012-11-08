package edu.wpi.cs.cs4341.project2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import edu.wpi.cs.cs4341.project2.constraints.CapacityConstraint;
import edu.wpi.cs.cs4341.project2.constraints.Constraint;
import edu.wpi.cs.cs4341.project2.constraints.EqualBinaryConstraint;
import edu.wpi.cs.cs4341.project2.constraints.ExclusiveUnaryConstraint;
import edu.wpi.cs.cs4341.project2.constraints.InclusiveUnaryConstraint;
import edu.wpi.cs.cs4341.project2.constraints.MutuallyExclusiveBinaryConstraint;
import edu.wpi.cs.cs4341.project2.constraints.NotEqualBinaryConstraint;
import edu.wpi.cs.cs4341.project2.constraints.WeightConstraint;

public class Project2 {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("A filename must be specified.");
			return;
		}
		
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(args[0]));
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			return;
		}
		
		String line;
		int section = 0;
		
		ArrayList<Item> itemsList = new ArrayList<Item>();
		ArrayList<Bag> bagsList = new ArrayList<Bag>();
		ArrayList<Constraint> constraintList = new ArrayList<Constraint>();
		Item[] items = null;
		Bag[] bags = null;
		Constraint[] constraints = null;
		
		try {
			while ((line = reader.readLine()) != null) {
				if (line.length() >= 5 && line.substring(0, 5).equals("#####")) {
					section++;
					
					// After processing variables (Items) section, get items array.
					if (section == 2) {
						items = new Item[itemsList.size()];
						itemsList.toArray(items);
						itemsList = null;
					}
					// After processing values (Bags) section, get bags array and build WeightConstraints.
					else if (section == 3) {
						bags = new Bag[bagsList.size()];
						bagsList.toArray(bags);
						//bagsList = null;
						
						for (int i = 0; i < bags.length; i++) {
							constraintList.add(new WeightConstraint(bags[i], items));
						}
					}
					
					continue;
				}
				
				switch (section) {
					// variables
					case 1:	itemsList.add(Item.fromString(line));
							break;
					// values
					case 2:	bagsList.add(Bag.fromString(line));
							break;
					// fitting limits (CapacityConstraint)
					case 3:	for (int i = 0; i < bags.length; i++) {
								constraintList.add(CapacityConstraint.fromString(line, bags[i], items));
							}
							break;
					// unary inclusive
					case 4:	constraintList.add(InclusiveUnaryConstraint.fromString(line, items, bags));
							break;
					// unary exclusive
					case 5:	constraintList.add(ExclusiveUnaryConstraint.fromString(line, items, bags));
							break;
					// binary equals
					case 6:	constraintList.add(EqualBinaryConstraint.fromString(line, items));
							break;
					// binary not equals
					case 7:	constraintList.add(NotEqualBinaryConstraint.fromString(line, items));
							break;
					// mutual exclusive
					case 8:	constraintList.add(MutuallyExclusiveBinaryConstraint.fromString(line, items, bags));
							break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		constraints = new Constraint[constraintList.size()];
		constraintList.toArray(constraints);
		constraintList = null;
	}
}
