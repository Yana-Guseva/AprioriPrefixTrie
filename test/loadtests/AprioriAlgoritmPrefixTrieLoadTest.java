package loadtests;

import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.eltech.ddm.associationrules.AssociationRule;
import org.eltech.ddm.associationrules.AssociationRulesFunctionSettings;
import org.eltech.ddm.associationrules.Item;
import org.eltech.ddm.associationrules.ItemSet;
import org.eltech.ddm.associationrules.apriori.AprioriAlgorithm;
import org.eltech.ddm.associationrules.apriori.AprioriMiningModel;
import org.eltech.ddm.associationrules.apriori.partition.PartitionAlgorithm;
import org.eltech.ddm.associtionrules.aprioriprefixtree.model.AprioriPrefixTrieAlgorithm;
import org.eltech.ddm.inputdata.MiningArrayStream;
import org.eltech.ddm.inputdata.MiningInputStream;
import org.eltech.ddm.inputdata.file.MiningArffStream;
import org.eltech.ddm.miningcore.MiningException;
import org.eltech.ddm.miningcore.algorithms.MiningAlgorithm;
import org.eltech.ddm.miningcore.miningdata.ELogicalData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

//@Ignore
public class AprioriAlgoritmPrefixTrieLoadTest extends AprioriPrefixTrieLoadlTest {
	
	@Before
	public void setUp() throws Exception {
	}

	
	@Test
	public void testAprioriAlgoritm() throws MiningException {
		System.out.println("----- AprioriPrefixTrieAlgorithm -------");
		
//		for(int i=0; i < dataSets.length; i++){
			setSettings(8);
		
			AprioriPrefixTrieAlgorithm algorithm = new AprioriPrefixTrieAlgorithm(miningSettings);
			System.out.println("Start algorithm");
			miningModel = (AprioriMiningModel) algorithm.buildModel(inputData);

			System.out.println("Finish algorithm. Calculation time: " + algorithm.getTimeSpentToBuildModel());
			
			verifyModel();
			
//		}
	}
	
}
