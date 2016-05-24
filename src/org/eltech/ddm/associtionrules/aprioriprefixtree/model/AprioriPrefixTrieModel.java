package org.eltech.ddm.associtionrules.aprioriprefixtree.model;

import java.util.List;

import org.eltech.ddm.associationrules.AssociationRulesFunctionSettings;
import org.eltech.ddm.associationrules.ItemSet;
import org.eltech.ddm.associationrules.ItemSets;
import org.eltech.ddm.associationrules.apriori.AprioriMiningModel;
import org.eltech.ddm.associtionrules.tree.Trie;
import org.eltech.ddm.miningcore.MiningException;
import org.eltech.ddm.miningcore.miningmodel.EMiningModel;

public class AprioriPrefixTrieModel extends AprioriMiningModel{
	
	protected Trie trie;
	
	public Trie getTrie() {
		return trie;
	}

	public void setTrie(Trie trie) {
		this.trie = trie;
	}

	public AprioriPrefixTrieModel(AssociationRulesFunctionSettings settings) throws MiningException {
		super(settings);
	}
	
	@Override
	public void join(List<EMiningModel> joinModels) throws MiningException {

		for(EMiningModel mm: joinModels){
			if(mm == this)
				continue;
			AprioriPrefixTrieModel aptmm = (AprioriPrefixTrieModel) mm;
			int i = getCurrentLargeItemSets();
			if (i >= largeItemSetsList.size()) {
				largeItemSetsList.add(new ItemSets());
			}
			if (aptmm.getLargeItemSetsList().size() > i) {
				for(ItemSet is: aptmm.getLargeItemSetsList().get(i)){
					boolean isContains = false;
					int index = largeItemSetsList.get(i).indexOf(is);
					if(index >= 0){
						ItemSet lis = largeItemSetsList.get(i).get(index);
						lis.setSupportCount(lis.getSupportCount() + is.getSupportCount());
						lis.getTIDList().addAll(is.getTIDList());
						isContains = true;
					}
					if(!isContains)
						largeItemSetsList.get(i).add(is);
				}
			}
			
			largeItemSetsList.get(i - 1).clear();
			largeItemSetsList.get(i - 1).addAll(aptmm.getLargeItemSetsList().get(i - 1));
		}
		
	}

}
