/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CustomFuncs;

/**
 *
 * @author Admin
 */
public class Regex {
    
    private static String rgx = "";
    private static boolean check = false;
    
    public static boolean ktraSoDienThoai(String st) {
        
        rgx = "^(0)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$";
        
        check = st.matches(rgx);
        
        return check;
    }
    
    public static boolean ktraTen(String st) {
        rgx = "[0-9!-\\/]+";
        
        check = !st.matches(rgx);
        
        return check;
    }
}