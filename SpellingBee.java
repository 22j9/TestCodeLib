class Solution {
    public List<String> findWords(String requiredLetter, List<String> optionalLetters, List<String> dictionary) {
        Set<Character> reqLtr = new HashSet<>();
        for (char current : requiredLetter.toCharArray()) {
            reqLtr.add(current);
        }
        Set<Character> optionalLtr = new HashSet<>();
        for (String current : optionalLetters) {
            optionalLtr.add(current.charAt(0));
        }
        List<String> beeWords = new ArrayList<>();
        for (String word : dictionary) {
            if (isValid(reqLtr, optionalLtr, word)) {
                beeWords.add(word);
            }
        }
        return beeWords;
    }

    private boolean isValid(Set<Character> reqLtr, Set<Character> optionalLtr, String word) {
        if (word.length() < 4) {
            return false;
        }
        boolean hasRequired = false;
        for (char current : word.toCharArray()) {
            if (!reqLtr.contains(current) && !optionalLtr.contains(current)) {
                return false;
            }
            if (reqLtr.contains(current)) {
                hasRequired = true;
            }
        }
        return hasRequired;
    }
}
