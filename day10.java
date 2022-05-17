/* solution:
* - use hashmap to store the songId and the numPlays
* - use priority queue to show the top n songs played
* - create a class "Pair" that implement Comparable, so that it can compare the object by its num of play & its id in desc/asc order
*/


class Pair implements Comparable<Pair>{
    String str;
    int count;
    public Pair(String str, int count){
        this.str = str;
        this.count = count;
    }
    public int compareTo(Pair obj) {
        if(count == obj.count){
            return obj.str.compareTo(str);
        }
        return count - obj.count;
    }
}



class SongCacheImpl implements SongCache{
      Map<String, Integer> map = new HashMap<>();
      PriorityQueue<Pair> queue = new PriorityQueue<>();

    @Override
    public void recordSongPlays(String songId, int numPlays) {
        map.put(songId, map.getOrDefault(songId, 0)+ numPlays);
        System.out.println(map);

    }

    @Override
    public int getPlaysForSong(String songId) {
       if(songId == null || songId.length()== 0 || !map.containsKey(songId)) {
           return -1;
       }
        return map.get(songId);
    }

    @Override
    public List<String> getTopNSongsPlayed(int n) {
        if (n == 0 || map.isEmpty()) return Arrays.asList();

        for (String key: map.keySet()){
            queue.offer(new Pair(key, map.get(key)));
            if (queue.size() > n) {
                queue.poll();
            }
        }

   
        String[] arr = new String[n];
        for (int i = n - 1; i >= 0; i--) {
            arr[i] = (queue.poll().str);
        }

        List<String> res = Arrays.asList(arr);
        return res;
    }
}




interface SongCache {
    /**
     * Record number of plays for a song.
     */
    void recordSongPlays(String songId, int
            numPlays);
    /**
     * Fetch the number of plays for a song.
     *
     We are looking for both correctness and good coding style. Please make sure that your code is well-designed and architected, in addition to being algorithmically efficient.
     See this unit test for guidance.
     * @return the number of plays, or -1 if the
    song ID is unknown.
     */
    int getPlaysForSong(String songId);
    /**
     * Return the top N songs played, in descending
     order of number of plays.
     */
    List<String> getTopNSongsPlayed(int n);
}
