class Node {
    int val;
    List<Node> children;
    
    public Node() {
        this.val = 0;
        this.children = new ArrayList<>();
    }
    
    public Node(int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }
    
    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children != null ? children : new ArrayList<>();
    }
}

public class Solution {
    int minLeafPathSum = Integer.MAX_VALUE;
    public int minPathSum(Node root) {
        if (root == null) {
            return 0;
        }
        minPathSum(root, 0);
        return minLeafPathSum;
    }

    private void minPathSum(Node currentNode, int currentSum) {
        if (currentNode.children.isEmpty()) {
            currentSum += currentNode.val;
            minLeafPathSum = Math.min(minLeafPathSum, currentSum);
            return;
        }
        currentSum += currentNode.val;
        for (Node child : currentNode.children) {
            minPathSum(child, currentSum);
        }
    }

    // Helper function to serialize an N-ary tree from a list (level-order serialization)
    public static Node buildTree(List<Integer> data) {
        if (data == null || data.isEmpty() || data.get(0) == null) {
            return null;
        }

        Node root = new Node(data.get(0));
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        
        int i = 2; 
        while (!queue.isEmpty() && i < data.size()) {
            Node parent = queue.poll();
            
            while (i < data.size() && data.get(i) != null) {
                Node child = new Node(data.get(i));
                parent.children.add(child);
                queue.offer(child);
                i++;
            }
            
            i++;
        }
                
        return root;
    }
}
