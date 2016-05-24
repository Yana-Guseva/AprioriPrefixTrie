package org.eltech.ddm.associtionrules.aprioriprefixtree.steps;

import org.eltech.ddm.associationrules.ItemSet;
import org.eltech.ddm.associtionrules.aprioriprefixtree.model.AprioriPrefixTrieModel;
import org.eltech.ddm.inputdata.MiningInputStream;
import org.eltech.ddm.miningcore.MiningException;
import org.eltech.ddm.miningcore.algorithms.Step;
import org.eltech.ddm.miningcore.miningfunctionsettings.EMiningFunctionSettings;
import org.eltech.ddm.miningcore.miningmodel.EMiningModel;

public class GetCandidateSupportStep extends Step{

	public GetCandidateSupportStep(EMiningFunctionSettings settings) throws MiningException {
		super(settings);
	}

	@Override
	protected EMiningModel execute(MiningInputStream inputData, EMiningModel model) throws MiningException {
		AprioriPrefixTrieModel modelA = (AprioriPrefixTrieModel) model;
		ItemSet itemSet = modelA.getLargeItemSetsList().get(modelA.getCurrentLargeItemSets()).get(modelA.getCurrentCandidate());
		itemSet.setSupportCount(modelA.getTrie().get(itemSet.getItemIDList()));
		return modelA;
	}

}
