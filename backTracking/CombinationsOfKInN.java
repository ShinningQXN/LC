Combinations of k in n

Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
https://leetcode.com/problems/combinations/

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        combineWithBackTracking(n, k, 1, new ArrayList<>(), result);
        return result;
    }
    
    private void combineWithBackTracking(int n, int k, int start, List<Integer> record, List<List<Integer>> combinations) {
        if (k == 0) {
            combinations.add(new ArrayList<>(record));
            return;
        }
        
        for (int i = start; i <= n - k + 1; i++) { // 剪枝
            record.add(i);
            combineWithBackTracking(n, k - 1, i + 1, record, combinations); // k--
            record.remove(record.size() - 1);
        }
    }
}

注意可以剪枝

Python解法：
class Solution(object):
    def combine(self, n, k):
        """
        :type n: int
        :type k: int
        :rtype: List[List[int]]
        """
        if k > n or n < 0:
            return None
        result = []
        self.dfs(n, k, 1, [], result)
        return result
    
    def dfs(self, n, k, index, path, result):
        if len(path) == k:
            result.append(path)
        for i in range(index, n + 1):
            self.dfs(n, k, i + 1, path + [i], result)
path直接往下传和append并pop时间并无多大差别

神仙解法：
class Solution:
    def combine(self, n: int, k: int) -> List[List[int]]:
        if k == 0:
            return [[]]
        return [pre + [i] for i in range (k, n + 1) for pre in self.combine(i - 1, k - 1)]