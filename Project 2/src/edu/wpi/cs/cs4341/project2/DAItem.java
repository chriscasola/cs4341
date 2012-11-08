package edu.wpi.cs.cs4341.project2;

import java.util.ArrayList;
import java.util.List;

/**
 * This item adds a domain aware capability to Items
 *
 */
public class DAItem extends Item {

	protected List<Bag> domain;
	
	public DAItem(char id, int weight, List<Bag> bags) {
		super(id, weight);
		domain = new ArrayList<Bag>();
		for (Bag bag : bags) {
			domain.add(bag);
		}
	}

	public List<Bag> getDomain() {
		return domain;
	}
}
