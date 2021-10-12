package projectapplication;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

//Controller class manage the connection from View JavaFX and Customer class
public class Controller {

    private Customer newCust;
    private String totalBill;
    
    //Set new customer variables from customer class
    Controller(String custName, double billAmount, int serviceLevel) {
        newCust = new Customer();
        newCust.setCustomerName(custName);
        newCust.setBillAmount(billAmount);
        newCust.setServiceLevel(serviceLevel);
        newCust.setTaxAmount();
        setTotalBill();
    }

    public void setTotalBill() { //To calculate the total bill
        double a = newCust.getBillAmount() * newCust.getTipLevel();
        double b = newCust.getBillAmount() * newCust.getTaxAmount();
        totalBill = String.valueOf(newCust.getBillAmount() + a + b);
    }

    public String getTotalBill() {
        return totalBill;
    }

    public String printInfo() { //calculate the data 
        
        double a = newCust.getBillAmount() * newCust.getTipLevel();
        double b = newCust.getBillAmount() * newCust.getTaxAmount();
        
        String data = "Name: " + newCust.getCustomerName() + "\n";
        data += "Level of Service: " + String.valueOf(newCust.getServiceLevel()) + "\n";
        data += "Bill Amount: $" + String.valueOf(Math.round(newCust.getBillAmount() * 100.00) / 100.0) + "\n";
        data += "Tax Rate: $" +String.valueOf(Math.round(b * 100.00) / 100.00) + "\n";
        data += "Tip Amount: $" + String.valueOf(Math.round(a * 100.00) / 100.00) + "\n";
        
        data += "Total Bill: $" + String.valueOf(Math.round((newCust.getBillAmount() + a + b) * 100.0) / 100.0) + "\n";
        
       
        try { //print data to external file
            Writer wr = new FileWriter("printInfo.txt");
            wr.write(data);
            wr.flush();
            wr.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        return data;
    }

    public String serviceImage() { // To set the image according to the service
        String serviceImage = "images/";
        if (newCust.getServiceLevel().equals("Poor")) {
            serviceImage += "poor.jpg";
        } else if (newCust.getServiceLevel().equals("Fair")) {
            serviceImage += "fair.jpg";
        } else if (newCust.getServiceLevel().equals("Good")) {
            serviceImage += "good.jpg";
        } else if (newCust.getServiceLevel().equals("Excellent")) {
            serviceImage += "excellent.jpg";
        }
        return serviceImage;
    }
}
