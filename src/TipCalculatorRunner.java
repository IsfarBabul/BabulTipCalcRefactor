import java.util.Scanner;
import java.util.*;
import java.text.DecimalFormat;

public class TipCalculatorRunner {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        DecimalFormat df_obj = new DecimalFormat("0.00");

        int numPeople;
        int tipPercentage;
        double salesTaxPercentage;
        int couponPercentage;
        String americanState;
        String item;
        int amountOfItems = 0;
        boolean end = false;
        String[] americanStatesList = {"alabama", "alaska", "arizona", "arkansas", "california",
                "colorado", "connecticut", "delaware", "florida", "georgia",
                "hawaii", "idaho", "illinois", "indiana", "iowa",
                "kansas", "kentucky", "louisiana", "maine", "maryland",
                "massachusetts", "michigan", "minnesota", "mississippi", "missouri",
                "montana", "nebraska", "nevada", "new hampshire", "new jersey",
                "new mexico", "new york", "north carolina", "north dakota", "ohio",
                "oklahoma", "oregon", "pennsylvania", "rhode island", "south carolina",
                "south dakota", "tennessee", "texas", "utah", "vermont",
                "virginia", "washington", "west virginia", "wisconsin", "wyoming",
                "american somoa", "guam", "northern mariana islands", "puerto rico", "united states virgin islands"};

        double[] salesTaxList = {4.00, 0.00, 5.60, 6.50, 7.25,
                2.90, 6.35, 0.00, 6.00, 4.00,
                4.00, 6.00, 6.25, 7.00, 6.00,
                6.50, 6.00, 4.45, 5.50, 6.00,
                6.25, 6.00, 6.875, 7.00, 4.225,
                0.00, 5.50, 6.85, 0.00, 6.625,
                5.00, 4.00, 4.75, 5.00, 5.75,
                4.50, 0.00, 6.00, 7.00, 6.00,
                4.50, 7.00, 6.25, 6.10, 6.00,
                5.30, 6.50, 6.00, 5.00, 4.00,
                15.00, 2.00, 0.00, 10.50, 0.00};  //sales tax as of January 1, 2023 by taxfoundation.org

        String[] foodArray = {};

        System.out.println("Welcome to the tip calculator!");
        //number of people
        System.out.print("How many people are in your group: ");
        numPeople = scan.nextInt();
        scan.nextLine();
        while (numPeople <= 0) {
            if (numPeople == 0) {
                System.out.print("Might as well not even order. Try again: ");
            } else {
                System.out.print("Invalid amount. Try again: ");
            }
            numPeople = scan.nextInt();
            scan.nextLine();
        }
        /* which U.S. state or territory your dining in
        (used to calculate sales tax and is applied after coupons and before tips) */
        System.out.println("These are the valid locations we serve in:");
        for (int i = 0; i < 55; i++) {
            System.out.println(americanStatesList[i]);
        }
        int indexNumber = 0;
        while (indexNumber == 0) {
            System.out.print("What U.S. state or territory are you in? ");
            americanState = scan.nextLine();
            americanState = americanState.toLowerCase();
            boolean stopCount = false;
            for (int i = 0; i < 55; i++) {
                if (americanState.equals(americanStatesList[indexNumber])) {
                    stopCount = true;
                }
                if (!stopCount) {
                    indexNumber++;
                }
            }
            while (indexNumber == 55) {
                System.out.println("We do not serve at locations outside the U.S.");
                indexNumber = 0;
            }
        }
        salesTaxPercentage = salesTaxList[indexNumber];
        //enter tip percentage (this is applied after coupons and sales tax)
        System.out.print("What's the tip percentage? (0-100): ");
        tipPercentage = scan.nextInt();
        while (tipPercentage < 0 || tipPercentage > 100) {
            System.out.print("That's not a valid tip percentage. Try again: ");
            tipPercentage = scan.nextInt();
        }
        //enter coupon percentage (this is applied before any sales tax or tips)
        System.out.print("What's the coupon percentage? (0-50): ");
        couponPercentage = scan.nextInt();
        while (couponPercentage < 0 || couponPercentage > 50) {
            System.out.print("That's not a valid coupon percentage. Try again: ");
            couponPercentage = scan.nextInt();
        }

        TipCalculator calc = new TipCalculator(numPeople, couponPercentage, salesTaxPercentage, tipPercentage);

        //asking for items and costs
        while (!end) {
            System.out.print("Enter a cost in dollars and cents, e.g. 7.49 (type any negative number to end): ");
            double itemPrice = scan.nextDouble();
            scan.nextLine();
            if (itemPrice >= 0) {
                calc.addMeal(itemPrice);
                System.out.print("Enter the item: ");
                item = scan.nextLine();
                List foodList = new ArrayList(Arrays.asList(foodArray));
                foodList.add(item);
                foodArray = (String[]) foodList.toArray(foodArray);
                amountOfItems++;
            } else {
                end = true;
            }
        }
        //asking for a donation to the totally very well known Rick Astley foundation
        System.out.println("The Rick Astley foundation aspires to help economically disadvantaged singers build a career.");
        System.out.println("Donating even a single dollar will help aspiring new singers to grow.");
        System.out.println("We encourage you to round up your final bill total to donate to the Rick Astley foundation.");
        System.out.println("Would you like to round up your total bill? (yes/no)");
        String roundUpConfirm = scan.nextLine();
        roundUpConfirm = roundUpConfirm.toLowerCase();
        while (!roundUpConfirm.equals("yes") && !roundUpConfirm.equals("no")) {
            System.out.print("Invalid response. Try again: ");
            roundUpConfirm = scan.nextLine();
            roundUpConfirm = roundUpConfirm.toLowerCase();
        }
        boolean roundUp = false;
        double donation = 0;
        if (roundUpConfirm.equals("yes")) {
            roundUp = true;
        } else {
            System.out.print("Would you like to donate a specific amount to the Rick Astley foundation? (yes/no) ");
            String donateConfirm = scan.nextLine();
            donateConfirm = donateConfirm.toLowerCase();
            while (!donateConfirm.equals("yes") && !donateConfirm.equals("no")) {
                System.out.print("Invalid response. Try again: ");
                donateConfirm = scan.nextLine();
                donateConfirm = donateConfirm.toLowerCase();
            }
            if (donateConfirm.equals("yes")) {
                System.out.print("How much (type 0 to not donate)? $");
                donation = scan.nextDouble();
                while (donation < 0) {
                    System.out.print("Invalid response. Try again: ");
                    donation = scan.nextDouble();
                }
                calc.setDonation(donation);
            }
        }
        //output
        System.out.println("-------------------------------");
        //bill with only item prices
        System.out.println("Total bill before coupon, sales tax, and tip: $" + df_obj.format(calc.getTotalBillBeforeTip()));
        System.out.println("Per person cost before coupon, sales tax, and tip: $" + df_obj.format(calc.perPersonCostBeforeAppliedBonuses()));
        //coupon reduction applied
        System.out.println("Coupon Percentage: " + couponPercentage + "%");
        System.out.println("Total reduced by coupon: $" + df_obj.format(calc.couponReduction()));
        System.out.println("Total bill with coupon: $" + df_obj.format(calc.totalBillWithCoupon()));
        //sales tax included
        System.out.println("Sales Tax Percentage: " + salesTaxPercentage + "%");
        System.out.println("Total sales tax: $" + df_obj.format(calc.salesTaxAmount()));
        System.out.println("Sales tax per person: $" + df_obj.format(calc.perPersonSalesTaxAmount()));
        System.out.println("Total bill with coupon and sales tax: $" + df_obj.format(calc.totalBillWithCouponAndSalesTax()));
        //tip factored in
        System.out.println("Tip Percentage: " + tipPercentage + "%");
        System.out.println("Total tip: $" + df_obj.format(calc.tipAmount()));
        System.out.println("Tip per person: $" + df_obj.format(calc.perPersonTipAmount()));
        System.out.println("Total bill after coupon, sales tax, and tip: $" + df_obj.format(calc.totalBillAfterCouponSalesTaxAndTip()));
        //donation added (the if statement used for rounding up total bill)
        calc.addDonation(roundUp);
        System.out.println("Donation: $" + df_obj.format(calc.getDonation()));
        System.out.println("Total Bill Per Person: $" + df_obj.format(calc.perPersonTotalCost()));
        System.out.println("-------------------------------");
        /* prints list of items (I had to learn how to use arrays and arrayLists thanks Mr. Miller for
        making me learn stuff from a different unit for my FIRST AP CSA project! */
        System.out.println("Items ordered: ");
        int i;
        for (i = 0; i < amountOfItems; i++) {
            System.out.println(foodArray[i]);
        }
        System.out.println("-------------------------------");
        //overall total bill that includes everything from tips to donations
        System.out.println("Total bill: $" + df_obj.format(calc.getTotalBill()));
        System.out.println("-------------------------------");

    }
}

