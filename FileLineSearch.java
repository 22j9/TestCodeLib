class Solution {
    public List<List<String>> searchFiles(List<List<String>> files, String word) {
        List<List<String>> output = new ArrayList<>();
        for (List<String> oneFile : files) {
            String fileName = oneFile.get(0);
            List<String> lines = oneFile.subList(1, oneFile.size());
            int fileMatches = 0;
            List<String> matchedLines = new ArrayList<>();
            for (String line : lines) {
                int lineMatches = countOccurrences(line.toLowerCase(), word);
                if (lineMatches > 0) {
                    fileMatches += lineMatches;
                    matchedLines.add(line);
                }
            }
            if (fileMatches > 0) {
                List<String> fileMatchResult = new ArrayList<>();
                fileMatchResult.add(fileName);
                fileMatchResult.add(String.valueOf(fileMatches));
                fileMatchResult.addAll(matchedLines);
                output.add(fileMatchResult);
            }
        }
        output.sort((x, y) -> {
            int countComparison = Integer.compare(Integer.parseInt(y.get(1)), Integer.parseInt(x.get(1)));
            if (countComparison != 0) {
                return countComparison;
            }
            return x.get(0).compareTo(y.get(0));
        });
        
        return output;
    }

    private int countOccurrences(String line, String word) {
        int count = 0;
        int index = 0;
        while ((index = line.indexOf(word, index)) != -1) {
            count++;
            index += word.length();
        }
        return count;
    }
}
