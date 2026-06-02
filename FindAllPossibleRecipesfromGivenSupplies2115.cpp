/*
 * Problem: LeetCode 2115 - Find All Possible Recipes from Given Supplies
 *
 * Problem Statement:
 * Given recipes, their ingredients, and initial supplies, determine which recipes
 * can be created. A recipe becomes creatable when all its ingredients are
 * available — either from the initial supplies or from other recipes that have
 * already been created. Return the list of creatable recipes in any order.
 *
 * Intuition:
 * Model as a directed graph: each ingredient points to the recipes that depend on
 * it. Treat supplies as starting nodes (indegree 0). Use Kahn's algorithm
 * (topological sort) to propagate availability: when a recipe's indegree reaches
 * zero, all its ingredients are satisfied and it becomes available, potentially
 * unlocking further recipes.
 *
 * Approach:
 *   1. Initialize adj (unordered_map from ingredient string → list of dependent
 *      recipe strings) and indegree (unordered_map from recipe string → count of
 *      unmet ingredient dependencies, initially 0 for all recipes).
 *   2. For each recipe i (0 to recipes.size()-1):
 *      - For each ingredient ing in ingredients[i]:
 *        - Append recipes[i] to adj[ing] (this ingredient helps make this recipe).
 *        - Increment indegree[recipes[i]] (one more unmet dependency).
 *   3. Push all initial supplies into a queue<string> q.
 *   4. While q is not empty:
 *      - Pop curr from q.
 *      - For each dep (dependent recipe) in adj[curr]:
 *        - Decrement indegree[dep].
 *        - If indegree[dep] == 0: push dep to q and append dep to res.
 *   5. Return res.
 *
 * Time Complexity: O(R + I) where R = number of recipes and I = total number of
 * ingredient entries across all recipes.
 * Space Complexity: O(R + I) for the adjacency map, indegree map, and queue.
 *
 * Edge Cases:
 * - Circular recipe dependencies: recipes in a cycle never reach indegree 0,
 *   so they are correctly excluded from the result.
 * - Recipe with no ingredients (indegree 0 initially): the queue only starts with
 *   supplies, not recipes — but if supplies themselves are recipes that can be
 *   created via 0-ingredient recipes, they'd need separate handling (not present
 *   in this code; problem guarantees supplies are base ingredients).
 * - Empty supplies: no starting nodes in queue → no recipes created → returns [].
 * - Duplicate supplies: multiple pushes to queue; indegree correctly decremented
 *   only to 0, not below.
 *
 * Dry Run:
 * recipes = ["bread"], ingredients = [["yeast","flour"]],
 * supplies = ["yeast","flour","corn"]
 *
 * adj: "yeast"→["bread"], "flour"→["bread"]
 * indegree: "bread"=2
 * q = ["yeast","flour","corn"], res = []
 *
 * pop "yeast": dep="bread" → indegree["bread"]=1 (not 0)
 * pop "flour": dep="bread" → indegree["bread"]=0 → q.push("bread"), res=["bread"]
 * pop "corn": adj["corn"] is empty → nothing
 * pop "bread": adj["bread"] is empty → nothing
 * Queue empty. Return ["bread"].
 *
 * Correctness Check:
 * Kahn's algorithm correctly enumerates all nodes reachable from initial sources
 * in a directed graph. Recipes in dependency cycles are never enqueued because
 * their indegree never drops to 0, matching the problem's requirement that
 * mutually-dependent recipes cannot be created.
 */

#include <iostream>
#include <bits/stdc++.h>
using namespace std;

class Solution
{
public:
    vector<int> findThePrefixCommonArray(vector<int> &A, vector<int> &B)
    {
        unordered_set<int> set;
        set.insert(1);
    }
    vector<string> findAllRecipes(vector<string> &recipes, vector<vector<string>> &ingredients, vector<string> &supplies)
    {
        unordered_map<string, vector<string>> adj;
        unordered_map<string, int> indegree;

        for (string &r : recipes)
        {
            indegree[r] = 0;
        }

        for (int i = 0; i < recipes.size(); i++)
        {
            for (string &ing : ingredients[i])
            {
                adj[ing].push_back(recipes[i]);
                indegree[recipes[i]]++;
            }
        }

        // for(int i = 0; i < indegree.size();i++){
        //     if
        // }
        queue<string> q;

        for (string &supply : supplies)
        {
            q.push(supply);
        }

        vector<string> res;

        while (!q.empty())
        {
            string curr = q.front();
            q.pop();

            for (string &dep : adj[curr])
            {
                indegree[dep]--;

                if (indegree[dep] == 0)
                {
                    q.push(dep);
                    res.push_back(dep);
                }
            }
        }

        return res;
    }
};

int main()
{

    return 0;
}