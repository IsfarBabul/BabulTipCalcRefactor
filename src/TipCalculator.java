public class TipCalculator {
    int numPeople;
    int couponPercentage;
    double salesTaxPercentage;
    int tipPercentage;
    double totalBillBeforeTip;
    double donation;
    public TipCalculator(int numPeople, int couponPercentage, double salesTaxPercentage, int tipPercentage) {
        this.numPeople = numPeople;
        this.couponPercentage = couponPercentage;
        this.salesTaxPercentage = salesTaxPercentage;
        this.tipPercentage = tipPercentage;
        totalBillBeforeTip = 0.0;
        donation = 0.0;
    }

    public double getTotalBillBeforeTip() {
        return totalBillBeforeTip;
    }
    public void addMeal(double cost) {
        totalBillBeforeTip += cost;
    }
    public double couponReduction() {
        return totalBillBeforeTip * (couponPercentage * .01);
    }
    public double totalBillWithCoupon() {
        return totalBillBeforeTip - couponReduction();
    }
    public double salesTaxAmount() {
        return totalBillWithCoupon() * (salesTaxPercentage * .01);
    }
    public double totalBillWithCouponAndSalesTax() {
        return totalBillWithCoupon() + salesTaxAmount();
    }
    public double tipAmount() {
        return totalBillWithCouponAndSalesTax() * (tipPercentage * .01);
    }
    public double totalBillAfterCouponSalesTaxAndTip() {
        return totalBillWithCouponAndSalesTax() + tipAmount();
    }
    public double perPersonCostBeforeAppliedBonuses() {
        return totalBillBeforeTip / numPeople;
    }
    public double perPersonSalesTaxAmount() {
        return salesTaxAmount() / numPeople;
    }
    public double perPersonTipAmount() {
        return tipAmount() / numPeople;
    }
    public void addDonation(boolean roundUp) {
        double totalBill = totalBillAfterCouponSalesTaxAndTip();
        if (roundUp) {
            double initialTotalBill = totalBill;
            if (totalBill % (int) totalBill > 0.5) {
                totalBill += 0.5;
            } else {
                totalBill++;
            }
            totalBill = (int) totalBill;
            donation = totalBill - initialTotalBill;
        }
    }
    public void setDonation(double donation) {
        this.donation = donation;
    }
    public double getDonation() {
        return donation;
    }
    public double getTotalBill() {
        return totalBillAfterCouponSalesTaxAndTip() + getDonation();
    }
    public double perPersonTotalCost() {
        return totalBillAfterCouponSalesTaxAndTip() / 7;
    }
}
