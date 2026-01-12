public class Solution {
    // Time O(N*G) -> O(n^2) Space O(n)
    public List<List<Integer>> partitionArray(List<Integer> nums, BiPredicate<Integer, Integer> equiv) {
        if (nums.isEmpty()) {
            return new ArrayList<>();
        }
        // Use a list to maintain the order of groups as they are discovered.\
        // [G0, G1, G2...]
        List<List<Integer>> equivGroups = new ArrayList<>();
        // Store a representative element for each group to check for equivalence.
        // [repG0, repG1, repG2...]
        List<Integer> representatives = new ArrayList<>();
        // above are in the same order -> implicit mapping
        for (int num : nums) {
            boolean foundItsGroup = false;
            // [G0, G1, G2, G3...]
            // check with every rep from existing groups
            for (int i = 0; i < representatives.size(); i++) {
                int rep = representatives.get(i);
                if (equiv.test(num, rep)) {
                    equivGroups.get(i).add(num);
                    foundItsGroup = true;
                    break;
                }
            }
            // if it does not belong to any existing equiv groups
            // it by itself is an equiv group, and it's its first rep
            if (!foundItsGroup) {
                List<Integer> newGroup = new ArrayList<>();
                newGroup.add(num);
                equivGroups.add(newGroup);
                representatives.add(num);
            }
        }
        return equivGroups;
    }
}
