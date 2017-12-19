package com.comdata.ppol.research;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lottery {
	
	
	
	public static void main(String [] args) {
		
		Lottery lottery = new Lottery();
		DecimalFormat df = new DecimalFormat("0.000000");
		
		List<Lottery.TaxBand> taxBands = new ArrayList<Lottery.TaxBand>();
		List<Friend> friends = new ArrayList<Friend>();
				
		Scanner _inScanner = new Scanner(System.in);
		
	
		int cntOfTaxBrands = _inScanner.nextInt();
		
		
		double previousTop = 0;
		for (int i=0;i<cntOfTaxBrands;i++) {
			double bandSize = _inScanner.nextDouble();
			double bandPercentage = _inScanner.nextDouble();
			taxBands.add(new Lottery.TaxBand(previousTop, previousTop +bandSize ,bandPercentage));
			previousTop = previousTop + bandSize;
		}
		
		
		double _aboveAllTax = _inScanner.nextDouble();
		
		int _cntOfFriends =   _inScanner.nextInt();
		
		
		for(int i=0;i<_cntOfFriends;i++) {
			double amountEarned = _inScanner.nextDouble();
			double desiredGift = _inScanner.nextDouble();
			friends.add(new Friend(amountEarned,desiredGift));
		}

		_inScanner.close();
		
		for(int i=0;i<friends.size();i++) {
			Lottery.Friend friend = friends.get(i);
			double desiredGift = friend.desiredGiftAmt;
			double amountEarned = friend.amountEarned;
			double totalInterest = 0;
			
			
		for (int j = 0; j < taxBands.size(); j++) {
					
			Lottery.TaxBand taxBand = taxBands.get(j);
			double giftTax = desiredGift * (taxBand.percentage)/(100-taxBand.percentage);		
			
						if(amountEarned >=taxBand.amountTop) {
							//Skip
						}else if(amountEarned >= taxBand.amountBtm  && amountEarned < taxBand.amountTop) {
							if (Math.abs(amountEarned + desiredGift) >= taxBand.amountTop) {
								
								double bandRange = Math.abs(taxBand.amountTop - (amountEarned==0?taxBand.amountBtm:amountEarned));
								double bandInterest = Math.abs((bandRange * taxBand.percentage / 100));
								totalInterest = Math.abs(totalInterest + bandInterest);
								
							
							}  else if(amountEarned + desiredGift + totalInterest >= taxBand.amountTop) {
								
								double bandInterest = Math.abs(((taxBand.amountTop-(amountEarned)) * taxBand.percentage/100));
								totalInterest = Math.abs(totalInterest + bandInterest);
								
							
							}else {
								double expectedBandRange =desiredGift + totalInterest;
								double expectedBandInterest = Math.abs(expectedBandRange * taxBand.percentage/(100-taxBand.percentage));
								
								if(amountEarned + desiredGift + totalInterest + expectedBandInterest >=taxBand.amountTop) {									
									double bandInterest =  Math.abs(((taxBand.amountTop-(amountEarned)) * taxBand.percentage/100));
									totalInterest = Math.abs(totalInterest + bandInterest);
								}else {
									double bandInterest = expectedBandInterest; 
									totalInterest = Math.abs(totalInterest + bandInterest);
									break;
								}
								
								
								
							}
						} else if (amountEarned < taxBand.amountBtm) {
							if (Math.abs(amountEarned + desiredGift) >= taxBand.amountTop) {
								
								double bandRange = Math.abs(taxBand.amountTop - taxBand.amountBtm);
								double bandInterest = Math.abs((bandRange * taxBand.percentage / 100));
								totalInterest = Math.abs(totalInterest + bandInterest);
								
							
							}  else if(amountEarned + desiredGift + totalInterest >= taxBand.amountTop) {
								
								double bandInterest = Math.abs(((taxBand.amountTop-(taxBand.amountBtm)) * taxBand.percentage/100));
								totalInterest = Math.abs(totalInterest + bandInterest);
								
							
							}else {
								double expectedBandRange = amountEarned + desiredGift + totalInterest - taxBand.amountBtm;
								double expectedBandInterest = Math.abs(expectedBandRange * taxBand.percentage/(100-taxBand.percentage));
								if(amountEarned + desiredGift + totalInterest + expectedBandInterest >=taxBand.amountTop) {									
									double bandInterest =  Math.abs(((taxBand.amountTop-(taxBand.amountBtm)) * taxBand.percentage/100));
									totalInterest = Math.abs(totalInterest + bandInterest);
								}else {									
									double bandInterest = expectedBandInterest; 
									totalInterest = Math.abs(totalInterest + bandInterest);
									break;
								}
							}
						}
						
		}

		Lottery.TaxBand lastTaxBand = taxBands.get(taxBands.size() - 1);
		
		double totalRange = amountEarned + desiredGift + totalInterest;
		
		if(totalRange>lastTaxBand.amountTop) {
			double expectedBandRange = amountEarned + desiredGift + totalInterest - lastTaxBand.amountTop;
			double expectedBandInterest = Math.abs(expectedBandRange * _aboveAllTax/ (100-_aboveAllTax));	
		
		totalInterest = Math.abs(totalInterest + expectedBandInterest);
		}
		String finalOutPut = df.format(Math.abs(desiredGift + totalInterest));
		
		System.out.println(finalOutPut);
		
					/* Uncomment this section for testing 
					 * 
					 * double totalAmount = desiredGift+totalInterest+amountEarned;
					if(totalAmount>lastTaxBand.amountTop) {
						double taxBandRange = totalAmount-lastTaxBand.amountTop;
						totalAmount= totalAmount - (taxBandRange * _aboveAllTax/100);
					}
					
					for (int j =taxBands.size()-1; j>=0; j--) {
						
						Lottery.TaxBand taxBand = taxBands.get(j);
						if(amountEarned<=taxBand.amountTop ) {
									if(totalAmount>=taxBand.amountTop ) {
								
											if(amountEarned<=taxBand.amountBtm) {
												totalAmount = totalAmount - ((taxBand.range) * taxBand.percentage/100 );
											} else {
											double bandRange = taxBand.amountTop-amountEarned;
											totalAmount  =  totalAmount - (bandRange * taxBand.percentage/100);
											}
									}else if(totalAmount>=taxBand.amountBtm){
											if(amountEarned<=taxBand.amountBtm) {
												double bandRange = totalAmount-taxBand.amountBtm;
												totalAmount = totalAmount - ((bandRange) * taxBand.percentage/100 );
											} else {
												double bandRange = totalAmount-amountEarned;
												totalAmount  =  totalAmount - (bandRange * taxBand.percentage/100);
											}
									}
						}
					}
				double verifiedAmount = totalAmount - amountEarned;
					
				if(verifiedAmount==desiredGift || ( Math.abs(verifiedAmount-desiredGift)<=0.000001)) {
					System.out.println(" Test Passed");
				}else {
					System.out.println(" Test Failed");
					System.exit(1);
				}*/
		

			}
		
		System.exit(0);
		
		
		
		
	}
	
	public static class TaxBand{
		private double amountBtm;
		private double amountTop;
		private double percentage;
		private double range;
		public TaxBand(double amountBtm,double amountTop,double percentage) {
			this.amountBtm = amountBtm;
			this.amountTop = amountTop;
			this.percentage = percentage;
			this.range = this.amountTop - this.amountBtm;
		}
		
		
		
		public double getRange() {
			return range;
		}



		public void setRange(double range) {
			this.range = range;
		}



		public double getAmountBtm() {
			return amountBtm;
		}



		public void setAmountBtm(double amountBtm) {
			this.amountBtm = amountBtm;
		}



		public double getAmountTop() {
			return amountTop;
		}



		public void setAmountTop(double amountTop) {
			this.amountTop = amountTop;
		}



		public double getPercentage() {
			return percentage;
		}
		public void setPercentage(double percentage) {
			this.percentage = percentage;
		}
		
		
		
	}
	
	public static class Friend{
		private double amountEarned;
		private double desiredGiftAmt;
		
		public Friend(double amountEarned, double desiredGift) {
			this.amountEarned = amountEarned;
			this.desiredGiftAmt = desiredGift;
		}
		
		public double getAmountEarned() {
			return amountEarned;
		}
		public void setAmountEarned(double amountEarned) {
			this.amountEarned = amountEarned;
		}
		public double getDesiredGiftAmt() {
			return desiredGiftAmt;
		}
		public void setDesiredGiftAmt(double desiredGiftAmt) {
			this.desiredGiftAmt = desiredGiftAmt;
		}
		
		
	}

}
