package SPB.utilidadesPruebas;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Reintento implements IRetryAnalyzer {

	int nIntentos = 0;
	int maxIntentos = 2;
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if (nIntentos < maxIntentos) {
			nIntentos+=1;
			return true;			
		}
		return false;
	}

}
