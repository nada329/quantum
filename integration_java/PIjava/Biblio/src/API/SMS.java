/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import entity.User;
import service.MyServices;

/**
 *
 * @author asus
 */
public class SMS {
    public static int userid;
    private static MyServices myServices = new MyServices();
    User UserConneter;
    
    public static final String ACCOUNT_SID = "ACeb44233073ccef87125180677b121fbd";
    public static final String AUTH_TOKEN = "e3f21f1a5c59cc9ca31d3650e58f9632";

    public static void sendSms(){
  // Find your Account Sid and Token at twilio.com/user/account
 
    
    /*User loggedUser = LoginController.getInstance().getLoggedUser();        
    User e=myServices.chercherUtilisateurByid(pi.Pi.Id_user_connecte);          
        */

    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    Message message = Message.creator(new PhoneNumber("+21650816132"),
        new PhoneNumber("+12728081342"), 
        "Bonjour, un nouveau livre vient d'etre empreinté veuillez verifier").create();

    System.out.println(message.getSid());
  }

    
    
}
