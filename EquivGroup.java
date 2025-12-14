public class Solution {
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

        for (int num : nums) {
            boolean foundGroup = false;
            // [G0, G1, G2, G3...]
            for (int i = 0; i < representatives.size(); i++) {
                int rep = representatives.get(i);
                if (equiv.test(num, rep)) {
                    equivGroups.get(i).add(num);
                    foundGroup = true;
                    break;
                }
            }
            // if it does not belong to any existing equiv groups
            // it by itself is an equiv group
            if (!foundGroup) {
                List<Integer> newGroup = new ArrayList<>();
                newGroup.add(num);
                equivGroups.add(newGroup);
                representatives.add(num);
            }
        }

        return equivGroups;
    }
}
