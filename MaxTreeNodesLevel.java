class Node {
    Integer val;
    List<Node> children;
    
    public Node(Integer val, List<Node> children) {
        this.val = val;
        this.children = children != null ? children : new ArrayList<>();
    }
    
    public Node(Integer val) {
        this(val, null);
    }
}

public class Solution {
    public int getLevelWithMaxNodes(Node root) {
        int maxNodes = 0;
        int maxLeafLevel = 0;
        if (root == null) {
            return 0;
        }
        Queue<Node> bfsTreeBuffer = new LinkedList<>();
        bfsTreeBuffer.offer(root);
        int currentLevel = 0;
        while (!bfsTreeBuffer.isEmpty()) {
            // also # of nodes in that level
            currentLevel++;
            int levelSize = bfsTreeBuffer.size();
            if (levelSize > maxNodes) {
                maxLeafLevel = currentLevel;
                maxNodes = levelSize;
            }
            for (int i = 0; i < levelSize; i++) {
                Node current = bfsTreeBuffer.poll();
                for (Node child : current.children) {
                    bfsTreeBuffer.offer(child);
                }
            }
        }
        return maxLeafLevel;
    }

    // Helper function to build an N-ary tree from a list (level-order serialization)
    public static Node buildTree(List<Integer> data) {
        if (data == null || data.isEmpty() || data.get(0) == null) {
            return null;
        }

        Node root = new Node(data.get(0));
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root);
        
        int i = 2;
        while (!queue.isEmpty() && i < data.size()) {
            Node parent = queue.poll();
            
            while (i < data.size() && data.get(i) != null) {
                Node child = new Node(data.get(i));
                parent.children.add(child);
                queue.offer(child);
                i += 1;
            }
            
            i += 1;
        }
                
        return root;
    }
}
