package formbeans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mybeans.form.FormBean;

public class TransitionForm extends FormBean{
	
	private String[] fundIdList, priceList;
	private String transitDay;
	
	public String[] getFundIdList() {return fundIdList;}
	public String[] getPriceList() {return priceList; }
	public String getTransitDay() {return transitDay; }
	
	public void setFundIdList(String[] i) {
		fundIdList = i;
	}
	
	public void setPriceList(String[] l) {
		priceList = l;
	}
	
	public void setTransitDay(String d) {transitDay = d; }
	
	public List<String> getValidationErrors() {
		
		List<String> errors = new ArrayList<String>();

		if (transitDay == null) {
			errors.add("Transition Date cannot be empty");
		}
		
		if (priceList == null || priceList.length == 0) {
			errors.add("Please input price.");
			return errors;
		}	
		
		
		
		if (priceList != null) {
			for (int i = 0; i < priceList.length; i++) {
				System.out.println("shabi@@@@@@@@@@" + i);

				
				if (priceList[i].equals("")) {
					errors.add("Please input price.");
					return errors;
				}
				if(priceList[i].isEmpty()){
					errors.add("Please input price.");
					return errors;
				}
				try{
					double p = Double.parseDouble(priceList[i]);
				}catch(NumberFormatException e){
					errors.add("Please enter a numeric price value");
					return errors;
				}
				if(!priceList[i].matches("^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$")){
					errors.add("Please enter a numeric price value");
					return errors;
				}else{
					boolean range = (Double.parseDouble(priceList[i]) > 0) 
							&& (Double.parseDouble(priceList[i]) < 1000);
					System.out.println("shabi@@@@@@@@@@");

					if (!range){
							
						errors.add("Input price must be less than $1000. Please input reasonable fund price.");
						return errors;
					}
					
					if(((Double.parseDouble(priceList[i])*1000)/10)%1>0){
						errors.add("Only two decimal price is allowed");
						return errors;
					}
					if((Double.parseDouble(priceList[i]))<1.00){
						errors.add("The minimum price of fund is 1 $.");
						return errors;
					}
				}
			}
		}		
		return errors;

	}
	
}
