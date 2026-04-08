#include <stdio.h>
#include <stdbool.h>

bool hasAlternateBits(int n)
{
    int prev = n & 1;
    n >>= 1;
    while (n > 0)
    {
        int curr = n & 1;
        if (curr == prev)
        {
            return false;
        }
        prev = curr;
        n >>= 1;
    }
    return true;
}

int main()
{
    int n = 5;
    bool res = hasAlternateBits(n);
    printf("%d\n", res);
    return 0;
}
