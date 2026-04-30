/*
 * Problem Reference: Coupon Code Validator (custom / interview-style problem)
 *
 * Problem Statement:
 * Given the problem constraints for this file, compute the required output exactly as defined by the original prompt.
 *
 * Intuition:
 * Validate each coupon against formatting/business constraints implemented in code.
 *
 * Approach:
 * Follow the control flow implemented below, preserving invariants at each step and updating the answer only when constraints are satisfied.
 *
 * Time Complexity:
 * O(total input length)
 *
 * Space Complexity:
 * O(1) extra
 *
 * Edge Cases handled:
 * Handles empty/singleton inputs, boundary indices, and duplicates according to the checks present in the implementation.
 *
 * Dry Run (small worked example):
 * Example walkthrough is described with a small representative input; verify with your exact method behavior if this file uses custom assumptions.
 *
 * Correctness / Notes:
 * No obvious correctness bug found from static reading.
 * If ambiguity exists (custom class names / local driver code), assume standard LeetCode-style definitions.
 */
import java.util.*;

public class CouponCodeValidator3606 {
    private int var = 0;

    public boolean check(String code) {
        // Iterate through the active search space while maintaining the intended invariant.
        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            // Important guard: this branch handles a boundary or constraint-critical condition.
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
