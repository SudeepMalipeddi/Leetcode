/*
Problem Statement:
- Compute x raised to power n efficiently.

Intuition:
- Binary exponentiation uses exponent bits to square base and multiply selected powers.

Approach:
- Use long exponent for safe abs(n), multiply answer when bit is 1, square base each step.

Time Complexity:
- O(log |n|).

Space Complexity:
- O(1).

Edge Cases:
- Negative exponent returns reciprocal of accumulated power.

Dry Run:
- x=2,n=10: multiply when bits set, final answer 1024.
*/
class Powxn50 {
    public double myPow1(double x, int n) {
        double ans = 1.0;
        long nn = n;
        
        if (nn < 0)
            nn = -nn;
        
        while (nn > 0) {
            
            // Multiply answer when current exponent bit is 1.
            if (nn % 2 == 1) {
                ans = ans * x;
                nn--;
            } else {
                // Square base each step while halving exponent.
                x *= x;
                nn /= 2;
            }
        }
        
        if (n < 0)
            ans = (double) (1.0) / (double) (ans);
        return ans;
    }
}
