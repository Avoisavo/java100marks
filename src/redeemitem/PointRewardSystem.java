package redeemitem;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jason Paw
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


// Main class
public class PointRewardSystem {
  // please use Main class under `src/main/` instead of writing another main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
<<<<<<< Updated upstream
        int trans_ans; // please use camelCase instead of snake_case
        int genereport_ans; // please use camelCase instead of snake_case
        int repeatgene_ans; // please use camelCase instead of snake_case
=======
        int trans_ans;
        int genereport_ans;
        int repeatgene_ans;
        int askadmin_ans;
>>>>>>> Stashed changes
        boolean contGeneReport = true;
        boolean repeatReport = true;
        boolean contTrans = true;
        boolean contaskadmin = true;
        
        
        /*
        // Create some Transaction objects  THIS IS THE EXAMPLE WHERE IT COLLECT THE DATA BELOW. JUST MODIFY IT
        Transaction transaction1 = new Transaction("John Doe", 100, "Purchase");
        Transaction transaction2 = new Transaction("Jane Smith", 150, "Redemption");
        Transaction transaction3 = new Transaction("Alice Johnson", 200, "Purchase");
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);
        
        //Create a loop
        do{
            try{
                //Provide user input and display the output for transaction history checking option.
                System.out.print("Do you want to check transaction history?[Yes=(1)/No=(0)]: ");
                trans_ans = scanner.nextInt();
                if (trans_ans == 1){
                    //When user want to check transaction history 
                    //Display the transaction history
                    Transaction.displayTransactions(transactions);
                    //Stop the loop
                    contTrans = false;
                }
                else if (trans_ans == 0 ){
                    //When user do not want to check transaction history and stop loop
                    contTrans = false;
                }
                else{
                //When user enter the wrong input. Generate wrong messsage 
                System.out.print("Oops! Something is wrong.Please enter 1 or 0 again.\n");
                scanner.nextLine();
                }
            }
            //When user enter alphablet which is wrong input. Generate wrong message.
            catch(Exception ex){
                System.out.print("Oops! Something is wrong.Please enter 1 or 0 again.\n");
                scanner.nextLine();
            }
         //the loop will stop when conTrans is false.   
        }while (contTrans);
        */
        
       
        // Create reports for different loyalty statuses. THIS IS ONLY EXAMPLE WHERE IT COLLECT THE DATA JUST MODIFY IT
<<<<<<< Updated upstream
        Report goldReport = new GoldStatusReport(2000 ,5000,200); // as per discussion, please refer to UML for updated protocol to handling loyalty status
        Report silverReport = new SilverStatusReport(1500,3000,200); // as per discussion, please refer to UML for updated protocol to handling loyalty status
        Report memberReport = new MemberStatusReport(6500 ,2000,200); // as per discussion, please refer to UML for updated protocol to handling loyalty status
=======
        Report goldReport = new GoldStatusReport(2000 ,5000,200);
        Report silverReport = new SilverStatusReport(1500,3000,200);
        Report memberReport = new ClassicStatusReport(6500 ,2000,200);
>>>>>>> Stashed changes
        Report yearlyRedemptionReport = new RedemptionTrendsReport(6500 ,2000,200);
        Report pointsEarnedReport = new PointsEarnedTrendsReport(1000, 500,200);
        Map<Integer, Integer> yearlyRedeemedAmounts = yearlyRedemptionReport.yearlyRedeemedAmounts;
        for (Map.Entry<Integer, Integer> entry : yearlyRedeemedAmounts.entrySet()) {
            int year = entry.getKey();
            int redeemedAmount = entry.getValue();
            System.out.println("Year: " + year + ", Redeemed Amount: $" + redeemedAmount);
        }

        do{
            try{
<<<<<<< Updated upstream
                //Provide user input and display the output of report menu.
                System.out.print("-------------------------------------------------------\nWhich report do you want to look?\n-------------------------------------------------------\n(1) Overall Member Customer Yearly Report  \n(2) Overall Silver Customer Yearly Report \n(3) Overall Gold Customer Yearly Report   \n(4) Yearly Redemption Report  \n(5) Yearly Points Earned \n(0) Cancel\nPlease enter in the range[0 to 5]: ");
                genereport_ans = scanner.nextInt();
                
                switch (genereport_ans) {
                    case 1:
                        // Generate member customer report
                        memberReport.generateReport();
                        //Create a loop
                        // please put this in a method!!! avoid writing unmaintainable code!!!
                        do {
                            try{
                                //Provide user input and display the output of repeat report options.
                                System.out.print("Do you want to look at report again?[Yes=(1)/No=(0)]: ");
                                repeatgene_ans = scanner.nextInt();
                                //When user want to look at report again
                                //Display report menu again.
                                if(repeatgene_ans == 1){
                                    //stop the loop
                                    repeatReport = false;
                                }
                                //When user does not want to look at report again
                                else if(repeatgene_ans == 0){
                                    //stop all the loop
                                    repeatReport = false;
                                    contGeneReport = false;
                                }
                                else{
                                    //When user enter out of the range of 1 to 0, display the error message.
                                    System.out.println("Error input. Please try enter 1 or 0 only.\n");
                                    scanner.nextLine();
                                    repeatReport = true;}
                            }
                            //When user is not enter number input, display the error message.
                            catch(Exception ex){
                                System.out.println("Error input. Please try enter 1 or 0 only.\n");
                                scanner.nextLine();
                                repeatReport = true;
                            }
                            
                        }//Stop the loop when repeatReport is false.
                        while(repeatReport);
                        break;
                    case 2:
                        // Generate Silver customer report
                        silverReport.generateReport();
                        // please put this in a method!!! avoid writing unmaintainable code!!!
                        do {
                            try{
                                //Provide user input and display the output of repeat report options.
                                System.out.print("Do you want to look at report again?[Yes=(1)/No=(0)]: ");
                                repeatgene_ans = scanner.nextInt();
                                //When user want to look at report again
                                //Display report menu again.
                                if(repeatgene_ans == 1){
                                    //stop the loop
                                    repeatReport = false;
                                }
                                //When user does not want to look at report again
                                else if(repeatgene_ans == 0){
                                    //stop all the loop
                                    repeatReport = false;
                                    contGeneReport = false;
                                }
                                else{
                                    //When user enter out of the range of 1 to 0, display the error message.
                                    System.out.print("Error input. Please try enter 1 or 0 only.\n");
                                    scanner.nextLine();
                                    repeatReport = true;}
                            }
                            //When user is not enter number input, display the error message.
                            catch(Exception ex){
                                System.out.print("Error input. Please try enter 1 or 0 only.\n");
                                scanner.nextLine();
                                repeatReport = true;
                            }
                            
                        }//Stop the loop when repeatReport is false.
                        while(repeatReport);
                        break;
                    case 3:
                        // Generate Gold customer report
                        goldReport.generateReport();

                        // please put this in a method!!! avoid writing unmaintainable code!!!
                        do {
                            try{
                                //Provide user input and display the output of repeat report options.
                                System.out.print("Do you want to look at report again?[Yes=(1)/No=(0)]: ");
                                repeatgene_ans = scanner.nextInt();
                                //When user want to look at report again
                                //Display report menu again.
                                if(repeatgene_ans == 1){
                                    //stop the loop
                                    repeatReport = false;
                                }
                                //When user does not want to look at report again
                                else if(repeatgene_ans == 0){
                                    //stop all the loop
                                    repeatReport = false;
                                    contGeneReport = false;
                                }
                                else{
                                    //When user enter out of the range of 1 to 0, display the error message.
                                    System.out.print("Error input. Please try enter 1 or 0 only.\n");
                                    scanner.nextLine();
                                    repeatReport = true;}
                            }
                            //When user is not enter number input, display the error message.
                            catch(Exception ex){
                                System.out.print("Error input. Please try enter 1 or 0 only.\n");
                                scanner.nextLine();
                                repeatReport = true;
                            }
                            
                        }while(repeatReport);
                        break;
                    case 4:
                        // Add yearly redeemed amounts THIS IS ONLY EXAMPLE WHERE IT COLLECT THE DATA JUST MODIFY IT
                        yearlyRedemptionReport.addYearlyRedeemedAmount(2020, 1500);
                        yearlyRedemptionReport.addYearlyRedeemedAmount(2021, 1800);
                        yearlyRedemptionReport.addYearlyRedeemedAmount(2022, 2200);
                        // Generate reports
                        yearlyRedemptionReport.generateReport();
                        // please put this in a method!!! avoid writing unmaintainable code!!!
                        do {
                            try{
                                //Provide user input and display the output of repeat report options.
                                System.out.print("Do you want to look at report again?[Yes=(1)/No=(0)]: ");
                                repeatgene_ans = scanner.nextInt();
                                //When user want to look at report again
                                //Display report menu again.
                                if(repeatgene_ans == 1){
                                    //stop the loop
                                    repeatReport = false;
                                }
                                //When user does not want to look at report again
                                else if(repeatgene_ans == 0){
                                    //stop all the loop
                                    repeatReport = false;
                                    contGeneReport = false;
                                }
                                else{
                                    //When user enter out of the range of 1 to 0, display the error message.
                                    System.out.print("Error input. Please try enter 1 or 0 only.\n");
                                    scanner.nextLine();
                                    repeatReport = true;}
                            }
                            //When user is not enter number input, display the error message.
                            catch(Exception ex){
                                System.out.print("Error input. Please try enter 1 or 0 only.\n");
                                scanner.nextLine();
                                repeatReport = true;
                            }
                        }//Stop the loop when repeatReport is false.
                        while(repeatReport);
                        break;
                    case 5:
                        // Adding points earned data for each year THIS IS ONLY EXAMPLE WHERE IT COLLECT THE DATA JUST MODIFY IT
                        pointsEarnedReport.addPointsEarnedData(2020, 500);
                        pointsEarnedReport.addPointsEarnedData(2021, 700);
                        // Generating the report
                        pointsEarnedReport.generateReport();

                        // please put this in a method!!! avoid writing unmaintainable code!!!
                        do {
                            try{
                                //Provide user input and display the output of repeat report options.
                                System.out.print("Do you want to look at report again?[Yes=(1)/No=(0)]: ");
                                repeatgene_ans = scanner.nextInt();
                                //When user want to look at report again
                                //Display report menu again.
                                if(repeatgene_ans == 1){
                                    //stop the loop
                                    repeatReport = false;
                                }
                                //When user does not want to look at report again
                                else if(repeatgene_ans == 0){
                                    //stop all the loop
                                    repeatReport = false;
                                    contGeneReport = false;
                                }
                                else{
                                    //When user enter out of the range of 1 to 0, display the error message.
                                    System.out.print("Error input. Please try enter 1 or 0 only.\n");
                                    scanner.nextLine();
                                    repeatReport = true;}
                            }
                            //When user is not enter number input, display the error message.
                            catch(Exception ex){
                                System.out.print("Error input. Please try enter 1 or 0 only.\n");
                                scanner.nextLine();
                                repeatReport = true;
                            }
                            
                        }//Stop the loop when repeatReport is false.
                        while(repeatReport);
                        break;
                    case 0:
                        contGeneReport = false;
                        break;
                    default:
                        //Display error message when user enter out of range.
                        // please provide clearer error message to user. Aim to be as specific as possible
                        System.out.print("Something is wrong. Please enter [0-5] only.\n");
                        scanner.nextLine();
                        break;
                }
                }
            //when user enter wrong input that is not number.
            catch(Exception ex){
                 System.out.print("Oops is wrong. Please try again.\n");
                 scanner.nextLine();
                 }
           }//the loop will stop when contGeneReport is false
        while(contGeneReport);   
        }       
  // please format your code!!!
=======
                    //Provide user input and display the output of report menu.
                    System.out.print("Please enter your admin password: ");
                    genereport_ans = scanner.nextInt();
                    if (genereport_ans == 1234){
                        contaskadmin = false;
                    }else{
                        System.out.print("Oops is wrong password. Please try again.\n");
                    }
            }
            catch(Exception ex){
                    System.out.print("Oops is wrong password. Please try again.\n");
                    scanner.nextLine();
                    }
            
            }while(contaskadmin);
            //Create a loop     
            do{
                try{
                    //Provide user input and display the output of report menu.
                    System.out.print("\n-------------------------------------------------------\nWhich report do you want to look?\n-------------------------------------------------------\n(1) Overall Member Customer Yearly Report  \n(2) Overall Silver Customer Yearly Report \n(3) Overall Gold Customer Yearly Report   \n(4) Yearly Redemption Report  \n(5) Yearly Points Earned \n(0) Cancel\nPlease enter in the range[0 to 5]: ");
                    genereport_ans = scanner.nextInt();

                    switch (genereport_ans) {
                        case 1:
                            // Generate member customer report
                            memberReport.generateReport();
                            //Create a loop
                            do {
                                try{
                                    //Provide user input and display the output of repeat report options.
                                    System.out.print("Do you want to look at report again?[Yes=(1)/No=(0)]: ");
                                    repeatgene_ans = scanner.nextInt();
                                    //When user want to look at report again
                                    //Display report menu again.
                                    if(repeatgene_ans == 1){
                                        //stop the loop
                                        repeatReport = false;
                                    }
                                    //When user does not want to look at report again
                                    else if(repeatgene_ans == 0){
                                        //stop all the loop
                                        repeatReport = false;
                                        contGeneReport = false;
                                    }
                                    else{
                                        //When user enter out of the range of 1 to 0, display the error message.
                                        System.out.print("Error input. Please try enter 1 or 0 only.\n");
                                        scanner.nextLine();
                                        repeatReport = true;}
                                }
                                //When user is not enter number input, display the error message.
                                catch(Exception ex){
                                    System.out.print("Error input. Please try enter 1 or 0 only.\n");
                                    scanner.nextLine();
                                    repeatReport = true;
                                }

                            }//Stop the loop when repeatReport is false.
                            while(repeatReport);
                            break;
                        case 2:
                            // Generate Silver customer report
                            silverReport.generateReport();
                            do {
                                try{
                                    //Provide user input and display the output of repeat report options.
                                    System.out.print("Do you want to look at report again?[Yes=(1)/No=(0)]: ");
                                    repeatgene_ans = scanner.nextInt();
                                    //When user want to look at report again
                                    //Display report menu again.
                                    if(repeatgene_ans == 1){
                                        //stop the loop
                                        repeatReport = false;
                                    }
                                    //When user does not want to look at report again
                                    else if(repeatgene_ans == 0){
                                        //stop all the loop
                                        repeatReport = false;
                                        contGeneReport = false;
                                    }
                                    else{
                                        //When user enter out of the range of 1 to 0, display the error message.
                                        System.out.print("Error input. Please try enter 1 or 0 only.\n");
                                        scanner.nextLine();
                                        repeatReport = true;}
                                }
                                //When user is not enter number input, display the error message.
                                catch(Exception ex){
                                    System.out.print("Error input. Please try enter 1 or 0 only.\n");
                                    scanner.nextLine();
                                    repeatReport = true;
                                }

                            }//Stop the loop when repeatReport is false.
                            while(repeatReport);
                            break;
                        case 3:
                            // Generate Gold customer report
                            goldReport.generateReport();
                            do {
                                try{
                                    //Provide user input and display the output of repeat report options.
                                    System.out.print("Do you want to look at report again?[Yes=(1)/No=(0)]: ");
                                    repeatgene_ans = scanner.nextInt();
                                    //When user want to look at report again
                                    //Display report menu again.
                                    if(repeatgene_ans == 1){
                                        //stop the loop
                                        repeatReport = false;
                                    }
                                    //When user does not want to look at report again
                                    else if(repeatgene_ans == 0){
                                        //stop all the loop
                                        repeatReport = false;
                                        contGeneReport = false;
                                    }
                                    else{
                                        //When user enter out of the range of 1 to 0, display the error message.
                                        System.out.print("Error input. Please try enter 1 or 0 only.\n");
                                        scanner.nextLine();
                                        repeatReport = true;}
                                }
                                //When user is not enter number input, display the error message.
                                catch(Exception ex){
                                    System.out.print("Error input. Please try enter 1 or 0 only.\n");
                                    scanner.nextLine();
                                    repeatReport = true;
                                }

                            }while(repeatReport);
                            break;
                        case 4:
                            // Add yearly redeemed amounts THIS IS ONLY EXAMPLE WHERE IT COLLECT THE DATA JUST MODIFY IT
                            yearlyRedemptionReport.addYearlyRedeemedAmount(2020, 1500);
                            yearlyRedemptionReport.addYearlyRedeemedAmount(2021, 1800);
                            yearlyRedemptionReport.addYearlyRedeemedAmount(2022, 2200);
                            // Generate reports
                            yearlyRedemptionReport.generateReport();
                            do {
                                try{
                                    //Provide user input and display the output of repeat report options.
                                    System.out.print("Do you want to look at report again?[Yes=(1)/No=(0)]: ");
                                    repeatgene_ans = scanner.nextInt();
                                    //When user want to look at report again
                                    //Display report menu again.
                                    if(repeatgene_ans == 1){
                                        //stop the loop
                                        repeatReport = false;
                                    }
                                    //When user does not want to look at report again
                                    else if(repeatgene_ans == 0){
                                        //stop all the loop
                                        repeatReport = false;
                                        contGeneReport = false;
                                    }
                                    else{
                                        //When user enter out of the range of 1 to 0, display the error message.
                                        System.out.print("Error input. Please try enter 1 or 0 only.\n");
                                        scanner.nextLine();
                                        repeatReport = true;}
                                }
                                //When user is not enter number input, display the error message.
                                catch(Exception ex){
                                    System.out.print("Error input. Please try enter 1 or 0 only.\n");
                                    scanner.nextLine();
                                    repeatReport = true;
                                }
                            }//Stop the loop when repeatReport is false.
                            while(repeatReport);
                            break;
                        case 5:
                            // Adding points earned data for each year THIS IS ONLY EXAMPLE WHERE IT COLLECT THE DATA JUST MODIFY IT
                            pointsEarnedReport.addPointsEarnedData(2020, 500);
                            pointsEarnedReport.addPointsEarnedData(2021, 700);
                            // Generating the report
                            pointsEarnedReport.generateReport();
                            do {
                                try{
                                    //Provide user input and display the output of repeat report options.
                                    System.out.print("Do you want to look at report again?[Yes=(1)/No=(0)]: ");
                                    repeatgene_ans = scanner.nextInt();
                                    //When user want to look at report again
                                    //Display report menu again.
                                    if(repeatgene_ans == 1){
                                        //stop the loop
                                        repeatReport = false;
                                    }
                                    //When user does not want to look at report again
                                    else if(repeatgene_ans == 0){
                                        //stop all the loop
                                        repeatReport = false;
                                        contGeneReport = false;
                                    }
                                    else{
                                        //When user enter out of the range of 1 to 0, display the error message.
                                        System.out.print("Error input. Please try enter 1 or 0 only.\n");
                                        scanner.nextLine();
                                        repeatReport = true;}
                                }
                                //When user is not enter number input, display the error message.
                                catch(Exception ex){
                                    System.out.print("Error input. Please try enter 1 or 0 only.\n");
                                    scanner.nextLine();
                                    repeatReport = true;
                                }

                            }//Stop the loop when repeatReport is false.
                            while(repeatReport);
                            break;
                        case 0:
                            contGeneReport = false;
                            break;
                        default:
                            //Display error message when user enter out of range.
                            System.out.print("Something is wrong. Please enter [0-5] only.\n");
                            scanner.nextLine();
                            break;
                    }
                    }
                //when user enter wrong input that is not number.
                catch(Exception ex){
                     System.out.print("Oops is wrong. Please try again.\n");
                     scanner.nextLine();
                     }
               }//the loop will stop when contGeneReport is false
            while(contGeneReport);  
         
    }       
>>>>>>> Stashed changes
}





