Map<String, int[]> showEpisodeToUniqueViewers = new HashMap<>();
// <String, Set[]>
void process_log(string show, int episode, int user_id) {
  if (!showEpisodeToUniqueViewers.containsKey(show)) {
    showEpisideToUniqueViewers.put(show, new int[10]);
  }
  showEpisideToUniqueViewers.get(show)[episode - 1]++;
}

void print_results() {
	for (String show : showEpisodeToUniqueViewers.keySet()) {
  	if (showEpisodeToUniqueViewers.get(show)[9] == 0) {
    	print(show + String.valueOf(-1));
    } else {
    	int viewersFinished = showEpisodeToUniqueViewers.get(show)[9];
      for (int i = 0; i <= 8; i++) {
      	if (showEpisodeToUniqueViewers.get(show)[i] * 0.7 <= (viewersFinished)) {      
        	print(show + String.valueOf(i + 1));
          break;
        }
      }
    }
  }
}
