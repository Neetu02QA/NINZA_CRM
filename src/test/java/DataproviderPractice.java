import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataproviderPractice {
	
	@DataProvider
	public Object[][] loginDetails(){
		
		Object[][] obj = new Object[3][2];
		
		obj[0][0]="Pra";
		obj[0][1]="Pra@12";
		obj[1][0]="sra";
		obj[1][1]="sra@12";
		obj[2][0]="dra";
		obj[2][1]="dra@12";
		
		return obj;
		
	}
	
	@Test(dataProvider = "loginDetails")
	public void login(String un, String pwd) {
		System.out.println(un+"============="+ pwd);
	}

}
