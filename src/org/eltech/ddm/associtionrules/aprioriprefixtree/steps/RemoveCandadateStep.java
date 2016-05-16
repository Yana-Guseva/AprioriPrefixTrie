package org.eltech.ddm.associtionrules.aprioriprefixtree.steps;

import org.eltech.ddm.associationrules.AssociationRulesFunctionSettings;
import org.eltech.ddm.associationrules.ItemSet;
import org.eltech.ddm.associationrules.ItemSets;
import org.eltech.ddm.associationrules.apriori.AprioriMiningModel;
import org.eltech.ddm.associtionrules.aprioriprefixtree.model.AprioriPrefixTrieModel;
import org.eltech.ddm.inputdata.MiningInputStream;
import org.eltech.ddm.miningcore.MiningException;
import org.eltech.ddm.miningcore.algorithms.Step;
import org.eltech.ddm.miningcore.miningfunctionsettings.EMiningFunctionSettings;
import org.eltech.ddm.miningcore.miningmodel.EMiningModel;

public class RemoveCandadateStep extends Step{
	final protected double minSupport;

	public RemoveCandadateStep(EMiningFunctionSettings settings) throws MiningException {
		super(settings);
		minSupport = ((AssociationRulesFunctionSettings)settings).getMinSupport();
	}

	@Override
	protected EMiningModel execute(MiningInputStream inputData, EMiningModel model) throws MiningException {
		AprioriPrefixTrieModel modelA = (AprioriPrefixTrieModel) model;
		int index = ((AprioriMiningModel) model).getCurrentLargeItemSets() - 2;
//		System.out.println(index);
//		System.out.println(Thread.currentThread().getName() + "large " + modelA.getLargeItemSetsList());
//		System.out.println(Thread.currentThread().getName() + "transList " + modelA.getTransactionList());
		if (index >= 0) {
			ItemSets itemsetList = modelA.getLargeItemSetsList().get(index + 1);
				ItemSet itemSet = itemsetList.get(
						((AprioriMiningModel) model).getCurrentItemSet());
//				System.out.println("itemset " + itemSet + " " + itemSet.getSupportCount());
				if(((double)itemSet.getSupportCount()/(double)modelA.getTransactionCount())<minSupport){
					itemsetList.remove(itemSet);
					modelA.setCurrentItemSet(modelA.getCurrentItemSet() - 1);
//					System.out.println("remove " + itemSet);
				} 
		}
//		System.out.println("after remove " + modelA.getLargeItemSetsList());
		return modelA;
	}

}
