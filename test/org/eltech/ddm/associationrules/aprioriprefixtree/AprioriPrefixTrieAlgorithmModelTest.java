package org.eltech.ddm.associationrules.aprioriprefixtree;

import static org.junit.Assert.*;

import org.eltech.ddm.associationrules.AssociationRulesFunctionSettings;
import org.eltech.ddm.associationrules.apriori.AprioriModelTest;
import org.eltech.ddm.associationrules.apriori.dhp.DHPAlgorithm;
import org.eltech.ddm.associationrules.apriori.dhp.DHPMiningModel;
import org.eltech.ddm.associtionrules.aprioriprefixtree.model.AprioriPrefixTrieAlgorithm;
import org.eltech.ddm.associtionrules.aprioriprefixtree.model.AprioriPrefixTrieModel;
import org.eltech.ddm.inputdata.MiningInputStream;
import org.eltech.ddm.miningcore.MiningException;
import org.junit.Test;

public class AprioriPrefixTrieAlgorithmModelTest extends AprioriModelTest{

	@Override
	protected AprioriPrefixTrieModel buildModel(AssociationRulesFunctionSettings miningSettings, MiningInputStream inputData)
			throws MiningException {
		AprioriPrefixTrieAlgorithm algorithm = new AprioriPrefixTrieAlgorithm(miningSettings);
		AprioriPrefixTrieModel model = (AprioriPrefixTrieModel) algorithm.buildModel(inputData);

		System.out.println("calculation time [s]: " + algorithm.getTimeSpentToBuildModel());

		return model;
	}

}
