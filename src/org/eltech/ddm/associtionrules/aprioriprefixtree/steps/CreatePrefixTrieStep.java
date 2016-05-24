package org.eltech.ddm.associtionrules.aprioriprefixtree.steps;


import java.util.ArrayList;
import java.util.List;

import org.eltech.ddm.associationrules.Transaction;
import org.eltech.ddm.associtionrules.aprioriprefixtree.model.AprioriPrefixTrieModel;
import org.eltech.ddm.associtionrules.tree.Trie;
import org.eltech.ddm.inputdata.MiningInputStream;
import org.eltech.ddm.miningcore.MiningException;
import org.eltech.ddm.miningcore.algorithms.Step;
import org.eltech.ddm.miningcore.miningfunctionsettings.EMiningFunctionSettings;
import org.eltech.ddm.miningcore.miningmodel.EMiningModel;

public class CreatePrefixTrieStep extends Step{

	public CreatePrefixTrieStep(EMiningFunctionSettings settings) throws MiningException {
		super(settings);
	}

	@Override
	protected EMiningModel execute(MiningInputStream inputData, EMiningModel model) throws MiningException {
		AprioriPrefixTrieModel modelA = (AprioriPrefixTrieModel) model;
		Transaction transaction = modelA.getTransactionList().getTransaction(modelA.getCurrentTransaction());
		int k = modelA.getCurrentLargeItemSets() + 1;
		insertIntoTrie(modelA.getTrie(), transaction, k);
		return modelA;
	}
	
	public void insertIntoTrie(Trie trie, Transaction transaction, int k) {
		List<String> transactionItemIDList = transaction.getItemIDList();
		if (transactionItemIDList.size() < k) {
			return;
		}

		int indexes[] = new int[k];
		for (int i = 0; i < indexes.length; i++) {
			indexes[i] = i;
		}

		boolean flag = true;
		while (flag) {
			List<String> elements = new ArrayList<String>();
			for (int i : indexes) {
				elements.add(transactionItemIDList.get(i));
			}
			elements.sort(null);
			
			trie.put(elements);
			
			int n = 1;
			while (flag) {
				indexes[indexes.length - n]++;
				if (indexes[indexes.length - n] < transactionItemIDList.size() - n + 1) {
					for (int h = indexes.length - n + 1; h < indexes.length; h++) {
						indexes[h] = indexes[h - 1] + 1;
					}
					n = 1;
					break;
				} else {
					n++;
					if (n == k + 1) {
						flag = false;
					}
				}
			}
		}
	}

}