package configuration;

import java.text.DecimalFormat;


public class InsulinGlucagonCalculation {
	private static double calculatedinsulindose;
	private static double calculatedGlucagondose;
	
//	public static Double computeIDose(double currentBSL) {
//		calculatedinsulindose = 0;
//			if (currentBSL >= StaticValues.MaximumBloodSugarLevel) {
//				StaticValues.insulinToCarbo = StaticValues.CarbohydrateIntake / StaticValues.ICR;
//				System.out.println("Insulin to carbo : " + StaticValues.insulinToCarbo);
//				
//				double insulinCorrectionFactor = ((currentBSL - StaticValues.TargetBSL) / StaticValues.ISF);
//				System.out.println("Insulin Correction Factor : " + insulinCorrectionFactor);
//				
//				calculatedinsulindose = insulinCorrectionFactor + StaticValues.insulinToCarbo;			
//				System.out.println("Insulin required : " + calculatedinsulindose);
//			}
//		return calculatedinsulindose;
//	}
	
	public static Double getInsulinDosage(double currentBSL) {
		calculatedinsulindose = 0;
			if (currentBSL >= StaticValues.MaximumBloodSugarLevel) {
				// only inject maximum of 1 unit of insulin
				double insulinCorrectionFactor = Math.min(1, (((currentBSL - StaticValues.MaximumBloodSugarLevel) / StaticValues.ISF)));

				calculatedinsulindose = Double.parseDouble(new DecimalFormat("##.##").format(insulinCorrectionFactor));
			}
		return calculatedinsulindose;
	}
	
	public static Double getInsulinDosageValue(double currentBSL) {
		calculatedinsulindose = 0;
		if(currentBSL >= StaticValues.MaximumBloodSugarLevel) {
			double insulinCorrectionFactor = (getChangeInBSForInsulin(currentBSL)) / StaticValues.ISF;

			calculatedinsulindose = Double.parseDouble(new DecimalFormat("##.##").format(insulinCorrectionFactor));
		}
		return calculatedinsulindose;
	}
	
	private static double getChangeInBSForInsulin(double currentBSL) {
		if(currentBSL > 120 && currentBSL <= 130) {
			return 5;
		}
		else if(currentBSL > 130 && currentBSL <= 150) {
			return 10;
		}
		else if(currentBSL > 150 && currentBSL <= 180) {
			return 15;
		}
		else if(currentBSL > 180 && currentBSL <=220) {
			return 20;
		}
		return 25;
	}
	
	private static double getChangeInBSForGlucagon(double currentBSL) {
		if(currentBSL >= 65 && currentBSL < 70) {
			return 3;
		}
		return 6;
	}
	
	public static Double getGlucagonDosage(double currentBSL) {
		calculatedGlucagondose = 0;
		if (currentBSL < StaticValues.MinimumBloodSugarLevel) {
			// only inject maximum of 2 units at a time
			calculatedGlucagondose = Math.min(2 , (StaticValues.MinimumBloodSugarLevel - currentBSL) / 3);
			calculatedGlucagondose = Double.parseDouble(new DecimalFormat("##.##").format(calculatedGlucagondose));
		}
		return calculatedGlucagondose;
	}
	
	public static Double getGlucagonDosageValue(double currentBSL) {
		calculatedGlucagondose = 0;
		if (currentBSL < StaticValues.MinimumBloodSugarLevel) {
			calculatedGlucagondose = getChangeInBSForGlucagon(currentBSL) / 3;
			calculatedGlucagondose = Double.parseDouble(new DecimalFormat("##.##").format(calculatedGlucagondose));
		}
		return calculatedGlucagondose;
	}

}
