package redeemitem;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jason Paw
 */


import java.util.Scanner;


// Main class
public class PointRewardSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int trans_ans;
        int genereport_ans;
        int repeatgene_ans;
        int askadmin_ans;

        boolean contGeneReport = true;
        boolean repeatReport = true;
        boolean contTrans = true;
        boolean contaskadmin = true;
        
     
        Report goldReport = new GoldStatusReport();
        Report silverReport = new SilverStatusReport();
        Report classicReport = new ClassicStatusReport();
        Report pointsRedeemedReport = new PointsRedeemedTrendsReport();
      

        do{
            try{
                    //Provide user input and display the output of report menu.
                    System.out.print("Please enter your admin password: ");
                    genereport_ans = scanner.nextInt();
                    if (genereport_ans == 1234){
                        contaskadmin = false;
                    }else{
                        System.out.println("Oops is wrong password. Please try again.\n");
                    }
            }
            catch(Exception ex){
                    System.out.println("Oops is wrong password. Please try again.\n");
                    scanner.nextLine();
                    }
            
            }while(contaskadmin);
        
            //Create a loop     
            do{
                try{
                    //Provide user input and display the output of report menu.
                    System.out.print("\n-------------------------------------------------------\nWhich report do you want to look?\n-------------------------------------------------------\n(1) Overall Member Customer Yearly Report  \n(2) Overall Silver Customer Yearly Report \n(3) Overall Gold Customer Yearly Report  \n(4) Redemption Item Summary Report \n(0) Cancel\nPlease enter in the range[0 to 4]: ");
                    genereport_ans = scanner.nextInt();

                    switch (genereport_ans) {
                        case 1:
                            // Generate member customer report
                            classicReport.fetchRedemptionData();
                            classicReport.generateReport();
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
                            silverReport.fetchRedemptionData();
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
                            goldReport.fetchRedemptionData();
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
                            // Adding points earned data for each year THIS IS ONLY EXAMPLE WHERE IT COLLECT THE DATA JUST MODIFY IT
                            pointsRedeemedReport.fetchRedemptionData();
                            pointsRedeemedReport.generateReport();
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

}





