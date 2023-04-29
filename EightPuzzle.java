import java.util.*;

class Node implements Comparable<Node> {
    int[][] state;
    int cost;
    int heuristic;
    Node parent;

    Node(int[][] state, int cost, int heuristic, Node parent) {
        this.state = state;
        this.cost = cost;
        this.heuristic = heuristic;
        this.parent = parent;
    }

    @Override
    public int compareTo(Node other) {
        return (this.cost + this.heuristic) - (other.cost + other.heuristic);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Node) {
            Node node = (Node) other;
            return Arrays.deepEquals(this.state, node.state);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(this.state);
    }
}

public class EightPuzzle {

    static int[][] goalState = {
            {1, 2, 3},
            {8, 0, 4},
            {7, 6, 5}
    };

    static int[][] initialState = {
            {2, 8, 3},
            {1, 6, 4},
            {7, 0, 5}
    };

    static int[][][] moves = {
            {{1, 0}, {0, 1}},
            {{-1, 0}, {0, 1}},
            {{0, -1}, {1, 0}},
            {{0, -1}, {-1, 0}}
    };

    static int manhattan(int[][] state) {
        int distance = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int value = state[i][j];
                if (value != 0) {
                    int row = (value - 1) / 3;
                    int col = (value - 1) % 3;
                    distance += Math.abs(row - i) + Math.abs(col - j);
                }
            }
        }
        return distance;
    }

    static boolean isValid(int i, int j) {
        return i >= 0 && i < 3 && j >= 0 && j < 3;
    }

    static List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        int[][] state = node.state;
        int zeroI = -1, zeroJ = -1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] == 0) {
                    zeroI = i;
                    zeroJ = j;
                    break;
                }
            }
        }
        for (int[][] move : moves) {
            int newZeroI = zeroI + move[0][0];
            int newZeroJ = zeroJ + move[0][1];
            int newI = zeroI + move[1][0];
            int newJ = zeroJ + move[1][1];
            if (isValid(newZeroI, newZeroJ) && isValid(newI, newJ)) {
                int[][] newState = new int[3][3];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        newState[i][j] = state[i][j];
                    }
                }
               
            newState[zeroI][zeroJ] = state[newI][newJ];
            newState[newI][newJ] = 0;
            Node neighbor = new Node(newState, node.cost + 1, manhattan(newState), node);
            neighbors.add(neighbor);
        }
    }
    return neighbors;
}

static List<Node> aStar(Node start, int[][] goalState) {
    List<Node> visited = new ArrayList<>();
    PriorityQueue<Node> queue = new PriorityQueue<>();
    queue.offer(start);
    while (!queue.isEmpty()) {
        Node node = queue.poll();
        if (Arrays.deepEquals(node.state, goalState)) {
            List<Node> path = new ArrayList<>();
            while (node != null) {
                path.add(node);
                node = node.parent;
            }
            Collections.reverse(path);
            return path;
        }
        visited.add(node);
        List<Node> neighbors = getNeighbors(node);
        for (Node neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                queue.offer(neighbor);
            }
        }
    }
    return null;
}

public static void main(String[] args) {
    Node start = new Node(initialState, 0, manhattan(initialState), null);
    List<Node> path = aStar(start, goalState);
    if (path == null) {
        System.out.println("No solution found");
    } else {
        for (Node node : path) {
            for (int[] row : node.state) {
                System.out.println(Arrays.toString(row));
            }
            System.out.println();
        }
    }
}
}