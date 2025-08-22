package app.service;

import app.dao.UserDAO;
import app.model.User;
import app.util.DBConnection;

import javax.print.attribute.HashAttributeSet;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Base64;

public class AuthService {
    private UserDAO userDAO=new UserDAO();
    public boolean register (String name,String email,String pass)throws Exception{
        if(userDAO.findByUsername(name)!=null)
            return false;

        MessageDigest messageDigest=MessageDigest.getInstance("SHA-256");

        byte[] b= messageDigest.digest(pass.getBytes(StandardCharsets.UTF_8));

        String s= Base64.getEncoder().encodeToString(b);

        User user=new User(name,email,s);

        return userDAO.insertUser(user);


    }
    public User login(String userName,String pass) throws Exception{
        User user=userDAO.findByUsername(userName);

        if(user==null)return null;
        MessageDigest messageDigest=MessageDigest.getInstance("SHA-256");
        byte[] b= messageDigest.digest(pass.getBytes());
        String s=Base64.getEncoder().encodeToString(b);
        if(user.getPasswordHash().equals(s)){return user;}
        else return null;


    }
    public static void main(String[] args)throws Exception  {
        AuthService auth=new AuthService();
       User u=auth.login("moamen","1234567");
       if(u!=null) System.out.println("k");
    }
}
