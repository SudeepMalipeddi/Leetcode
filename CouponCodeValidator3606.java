import java.util.*;

public class CouponCodeValidator3606 {
    private int var = 0;

    public boolean check(String code) {
        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            if (!Character.isLetterOrDigit(c) && c != '_') {
                return false;
            }
        }
        return true;
    }

    public boolean checkBusiness(String business) {
        if (!business.equals("electronics") && !business.equals("grocery") && !business.equals("pharmacy")
                && !business.equals("restaurant")) {
            return false;
        }
        return true;
    }

    // public List<String> validateCoupons(String[] code, String[] businessLine,
    // boolean[] isActive) {

    // }
    private void changeVar(int chg) {
        this.var = chg;
    }

    public static void main(String[] args) {
        CouponCodeValidator3606 c = new CouponCodeValidator3606();
        CouponCodeValidator3606 c1 = new CouponCodeValidator3606();
        System.out.println(c.var);
        c.changeVar(2);
        System.out.println(c.var);

        System.out.println(c1.var);
    }
}
