/*
 * Problem: Coupon Code Validator (Interview-style problem)
 *
 * Problem Statement:
 * Implement a validation system for coupon codes and business categories. A coupon code is valid 
 * if it contains only alphanumeric characters or underscores, and a business is valid if it 
 * belongs to a predefined set of allowed categories.
 *
 * Intuition:
 * Validation often requires checking every element of an input against a set of rules. For strings, 
 * this means iterating through characters; for categories, this means comparing against a whitelist.
 *
 * Approach:
 * 1. The `check` method iterates through the string character by character, using `Character.isLetterOrDigit` 
 *    and a manual check for underscores to ensure the format is correct.
 * 2. The `checkBusiness` method uses logical AND/OR operations to verify if the input matches 
 *    one of the four allowed business types.
 * 3. The class also demonstrates basic Object-Oriented principles regarding instance variables (`var`).
 *
 * Time Complexity:
 * O(N) for `check(String code)`, where N is the length of the string, as we visit each character once.
 * O(1) for `checkBusiness(String business)`, as we compare against a fixed number of strings.
 *
 * Space Complexity:
 * O(1) extra space, as we only use a few primitive variables regardless of input size.
 *
 * Edge Cases:
 * - Empty string in `check`: The loop won't execute, returning true (depends on business requirements).
 * - Case sensitivity: "Electronics" would fail `checkBusiness` because it expects lowercase "electronics".
 * - Null inputs: Would throw a NullPointerException (not explicitly handled in this snippet).
 *
 * Dry Run:
 * check("Sale_20"):
 * - 'S', 'a', 'l', 'e' are letters (OK)
 * - '_' is underscore (OK)
 * - '2', '0' are digits (OK)
 * Result: true
 *
 * Correctness Check:
 * The logic correctly implements the described constraints. Note that `checkBusiness` could be 
 * more efficiently implemented using a `HashSet` for O(1) average lookup if the list of businesses grows.
 */
import java.util.*;

public class CouponCodeValidator3606 {
    // Instance variable to demonstrate object state isolation
    private int var = 0;

    public boolean check(String code) {
        // Iterate through the active search space while maintaining the intended invariant.
        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            // Important guard: this branch handles a boundary or constraint-critical condition.
            // We only allow letters, digits, and underscores. Any other character invalidates the code.
            if (!Character.isLetterOrDigit(c) && c != '_') {
                return false;
            }
        }
        // If we finish the loop without returning false, all characters passed validation.
        return true;
    }

    public boolean checkBusiness(String business) {
        // Whitelist validation: check if the input matches any of the allowed business categories.
        // Note: String comparison in Java should use .equals(), not ==.
        if (!business.equals("electronics") && !business.equals("grocery") && !business.equals("pharmacy")
                && !business.equals("restaurant")) {
            return false;
        }
        return true;
    }

    // public List<String> validateCoupons(String[] code, String[] businessLine,
    // boolean[] isActive) {

    // }
    
    // Setter method to modify the instance-specific variable 'var'
    private void changeVar(int chg) {
        this.var = chg;
    }

    public static void main(String[] args) {
        // Create two distinct objects to demonstrate that instance variables are not shared.
        CouponCodeValidator3606 c = new CouponCodeValidator3606();
        CouponCodeValidator3606 c1 = new CouponCodeValidator3606();
        
        System.out.println(c.var); // Initially 0
        c.changeVar(2);            // Update 'var' for instance 'c'
        System.out.println(c.var); // Now 2

        // Instance 'c1' remains unaffected by changes to 'c'
        System.out.println(c1.var); // Still 0
    }
}
