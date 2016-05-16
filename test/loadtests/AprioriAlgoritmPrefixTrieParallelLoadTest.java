package loadtests;

import static org.junit.Assert.fail;

import org.eltech.ddm.associtionrules.aprioriprefixtree.model.AprioriPrefixTrieModel;
import org.eltech.ddm.associtionrules.aprioriprefixtree.model.AprioriPrefixTrieParallelAlgorithm;
import org.eltech.ddm.handlers.ExecutionSettings;
import org.eltech.ddm.handlers.thread.MultiThreadedExecutionEnvironment;
import org.eltech.ddm.inputdata.DataSplitType;
import org.eltech.ddm.miningcore.MiningException;
import org.eltech.ddm.miningcore.miningfunctionsettings.DataProcessingStrategy;
import org.eltech.ddm.miningcore.miningfunctionsettings.MiningModelProcessingStrategy;
import org.eltech.ddm.miningcore.miningtask.EMiningBuildTask;
import org.junit.Test;

//@Ignore
public class AprioriAlgoritmPrefixTrieParallelLoadTest extends AprioriPrefixTrieLoadlTest {
	
private final int NUMBER_HANDLERS = 2;
	
	@Test
	public void testParallelAprioriPrefixTrieAlgorithm() throws MiningException {
		System.out.println("----- ParallelAprioriPrefixTrieAlgorithm (" + NUMBER_HANDLERS + ")-------");
		
		try{
//			for(int i=0; i < dataSets.length; i++){
				setSettings(2);
			
				miningSettings.getAlgorithmSettings().setDataSplitType(DataSplitType.block);
				miningSettings.getAlgorithmSettings().setDataProcessingStrategy(DataProcessingStrategy.SeparatedDataSet);
				miningSettings.getAlgorithmSettings().setModelProcessingStrategy(MiningModelProcessingStrategy.SeparatedMiningModel);
			
				ExecutionSettings executionSettings = new ExecutionSettings();
//				executionSettings.setNumberHandlers(NUMBER_HANDLERS);
				miningSettings.getAlgorithmSettings().setNumberHandlers(NUMBER_HANDLERS);
				MultiThreadedExecutionEnvironment environment = new MultiThreadedExecutionEnvironment(executionSettings); 
				
				AprioriPrefixTrieParallelAlgorithm algorithm = new AprioriPrefixTrieParallelAlgorithm(miningSettings);
	
				EMiningBuildTask buildTask = new EMiningBuildTask();
				buildTask.setInputStream(inputData);
				buildTask.setMiningAlgorithm(algorithm); 
				buildTask.setMiningSettings(miningSettings);
				buildTask.setExecutionEnvironment(environment);
				System.out.println("Start algorithm");
				miningModel = (AprioriPrefixTrieModel) buildTask.execute();
				System.out.println("Finish algorithm");
				
				verifyModel();
//			}
		}
		catch(Exception e){
			e.printStackTrace();
			fail();
		}
	}
	
}
