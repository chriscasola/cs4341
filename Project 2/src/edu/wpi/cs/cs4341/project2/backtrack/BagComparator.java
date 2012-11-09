package edu.wpi.cs.cs4341.project2.backtrack;

import java.util.Comparator;
import java.util.List;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.DAItem;
import edu.wpi.cs.cs4341.project2.Item;
import edu.wpi.cs.cs4341.project2.Project2;
import edu.wpi.cs.cs4341.project2.constraintgraph.Graph;
import edu.wpi.cs.cs4341.project2.constraints.BinaryConstraint;
import edu.wpi.cs.cs4341.project2.constraints.Constraint.Satisfaction;

public class BagComparator implements Comparator<Bag> {

	protected Item currItem;

	public BagComparator(Item currItem) {
		this.currItem = currItem;
	}

	@Override
	public int compare(Bag o1, Bag o2) {
		Graph graph = Project2.getGraph();
		List<BinaryConstraint> constraints = graph.getOutgoingConstraints((DAItem)currItem);

		int numChoicesBag1 = 0;
		int numChoicesBag2 = 0;

		currItem.setAssignedBag(null);
		currItem.setAssignedBag(o1);

		for (BinaryConstraint currConst : constraints) {
			DAItem rightItem = (DAItem)currConst.getItems()[1];
			if (rightItem.getAssignedBag() != null) {
				continue;
			}
			for (Bag rBag : rightItem.getDomain()) {
				rightItem.setAssignedBag(null);
				rightItem.setAssignedBag(rBag);
				if (currConst.satisfied() == Satisfaction.BROKEN) {
					// do nothing
				}
				else {
					numChoicesBag1++;
				}
				rightItem.setAssignedBag(null);
			}
		}
		
		currItem.setAssignedBag(null);
		currItem.setAssignedBag(o2);
		
		for (BinaryConstraint currConst : constraints) {
			DAItem rightItem = (DAItem)currConst.getItems()[1];
			if (rightItem.getAssignedBag() != null) {
				continue;
			}
			for (Bag rBag : rightItem.getDomain()) {
				rightItem.setAssignedBag(null);
				rightItem.setAssignedBag(rBag);
				if (currConst.satisfied() == Satisfaction.BROKEN) {
					// do nothing
				}
				else {
					numChoicesBag2++;
				}
				rightItem.setAssignedBag(null);
			}
		}
		
		currItem.setAssignedBag(null);
		
		if (numChoicesBag1 > numChoicesBag2) {
			return -1;
		}
		else if (numChoicesBag1 < numChoicesBag2) {
			return 1;
		}
		else {
			return 0;
		}
	}

}
