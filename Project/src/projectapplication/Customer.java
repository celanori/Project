package projectapplication;

//Customer class manage to set customer variables of new customer
public class Customer {

    //Customer class variables
    private String custName;
    private double billAmount;
    private String serviceLevel;
    private double tipLevel;
    private double taxAmount;

    public Customer() {
    }

    public Customer(String custName, double billAmount, String serviceLevel, double tipLevel, double taxAmount) {
        this.custName = custName;
        this.billAmount = billAmount;
        this.serviceLevel = serviceLevel;
        this.tipLevel = tipLevel;
        this.taxAmount = taxAmount;
    }

    // Customer class methods
    
    //Get Customer Name Method
    public String getCustomerName() {
        return custName;
    }
    //Set Customer Name Method
    public void setCustomerName(String strUserInput) {
        if (strUserInput != " ") {
            this.custName = strUserInput;
        }
    }
    //Get Bill Amount Method  
    public double getBillAmount() {
        return billAmount;
    }
    //Set Bill Amount Method
    public void setBillAmount(double numUserInput) {
        if (numUserInput > 0 && numUserInput <= 750) {
            this.billAmount = numUserInput;    
        }
    }
    //Get Service Level Method    
    public String getServiceLevel() {
        return serviceLevel;
    }
    //Set Service Level Method
    public void setServiceLevel(double numUserInput) {
        if (numUserInput == 1) {
            this.serviceLevel = "Poor";
            setTipLevel(0.0);
        } else if (numUserInput == 2) {
            this.serviceLevel = "Fair";
            setTipLevel(0.10);
        } else if (numUserInput == 3) {
            this.serviceLevel = "Good";
            setTipLevel(0.20);
        } else if (numUserInput == 4) {
            this.serviceLevel = "Excellent";
            setTipLevel(0.25);
        }
    }
    //Get Tip Level Method
    public double getTipLevel() {
        return tipLevel;
    }
    //Set Tip Level Method
    public void setTipLevel(double tipLevel) {
        this.tipLevel = tipLevel;
    }
    //Get Tax Amount Method
    public double getTaxAmount() {
        return taxAmount;
    }
    //Set Tax Amount Method
    public void setTaxAmount() {
        this.taxAmount = 0.0725;
    }

}
