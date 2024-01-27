package Heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Heap {
    private List<Integer> heapList;
    private int size;

    public Heap() {
        heapList = new ArrayList<>();
        size = 0;
    }

    public static int getParentIndex(int currentIndex) {
        int parentIndex = (currentIndex - 1) / 2;
        return parentIndex;
    }

    public static int getLeftChildIndex(int parentIndex) {
        int leftIndex = 2 * parentIndex + 1;
        return leftIndex;
    }

    public static int getRightChildIndex(int parentIndex) {
        int rightIndex = 2 * parentIndex + 2;
        return rightIndex;
    }

    public int getMinimum() {
        int minimum = size > 0 ? heapList.get(0) : -1;
        return minimum;
    }

    public void heapify(int rootNodeIndex) {
        int currentIndex = rootNodeIndex;
        while (currentIndex < size) {
            int leftIndex = getLeftChildIndex(currentIndex);
            int rightIndex = getRightChildIndex(currentIndex);

            if (leftIndex < size && heapList.get(leftIndex) < heapList.get(currentIndex) &&
                    rightIndex < size && heapList.get(leftIndex) < heapList.get(rightIndex)) {
                int temp = heapList.get(currentIndex);
                heapList.set(currentIndex, heapList.get(leftIndex));
                heapList.set(leftIndex, temp);
                currentIndex = leftIndex;
                continue;
            } else if (rightIndex < size && heapList.get(rightIndex) < heapList.get(currentIndex)) {
                int temp = heapList.get(currentIndex);
                heapList.set(currentIndex, heapList.get(rightIndex));
                heapList.set(rightIndex, temp);
                currentIndex = rightIndex;
                continue;
            }
            break;
        }
    }

    public int extractMinimumNode() {
        if (size == 0) {
            return -1;
        }
        int minimum = heapList.get(0);
        int lastElement = size - 1;

        heapList.set(0, heapList.get(lastElement));
        heapList.set(lastElement, minimum);
        heapList.remove(lastElement);

        size--;

        heapify(0);

        return minimum;
    }

    public void reverseHeapify(int currentNodeIndex) {
        int currentIndex = currentNodeIndex;
        while (currentIndex > 0) {
            int parentIndex = getParentIndex(currentIndex);

            if (heapList.get(currentIndex) < heapList.get(parentIndex)) {
                int temp = heapList.get(currentIndex);
                heapList.set(currentIndex, heapList.get(parentIndex));
                heapList.set(parentIndex, temp);
                currentIndex = parentIndex;
            } else {
                break;
            }
        }
    }

    public void insertNewNode(int newNode) {
        if (size > 0) {
            heapList.add(size, newNode);
            reverseHeapify(size);
            size++;
        } else {
            heapList.add(size, newNode);
            size++;
        }
    }

    public void decreaseKey(int index, int newValue) {
        heapList.set(index, newValue);
        reverseHeapify(index);
    }

    public void delete(int index) {
        heapList.set(index, -1);
        reverseHeapify(index);
        extractMinimumNode();
        size--;
    }
}

public class MinHeap {
    public static void main(String[] args) {
        Heap gateA = new Heap();
        Heap gateB = new Heap();
        int numberOfApron = 10;
        String[] numberOfAirplaneTransactions = {
                "L SGP-506",
                "L HAN-278",
                "L BKK-398",
                "L HAN-279",
                "L SGP-507",
                "T HAN-278",
                "L KLA-237",
                "L DEL-346",
                "T HAN-279",
                "L DEL-347",
                "L HAN-280",
                "L BKK-399",
                "L KLA-238",
                "T HAN-280",
                "T BKK-398",
                "L SGP-508",
                "L DEL-348",
                "L KLA-239",
                "L BKK-400",
                "T SGP-506",
                "L HAN-281"
        };
        Map<String, Integer> airplaneAMap = new HashMap<>();
        Map<String, Integer> airplaneBMap = new HashMap<>();
        for (int i = 1; i <= numberOfApron / 2; i++) {
            gateA.insertNewNode(i);
            gateB.insertNewNode(i);
        }
        for (String transaction : numberOfAirplaneTransactions) {
            String[] parts = transaction.split(" ");
            String type = parts[0];
            String planeName = parts[1];
            if (type.equals("L")) {
                int minA = gateA.getMinimum();
                int minB = gateB.getMinimum();
                if (minA == -1 && minB == -1) {
                    System.out.println("REDIRECT");
                } else if (minA == -1) {
                    minB = gateB.extractMinimumNode();
                    airplaneBMap.put(planeName, minB);
                    System.out.println("B " + minB);
                } else if (minB == -1) {
                    minA = gateA.extractMinimumNode();
                    airplaneAMap.put(planeName, minA);
                    System.out.println("A " + minA);
                } else if (minA <= minB) {
                    minA = gateA.extractMinimumNode();
                    airplaneAMap.put(planeName, minA);
                    System.out.println("A " + minA);
                } else {
                    minB = gateB.extractMinimumNode();
                    airplaneBMap.put(planeName, minB);
                    System.out.println("B " + minB);
                }
            } else if (type.equals("T")) {
                if (airplaneAMap.containsKey(planeName)) {
                    int apron = airplaneAMap.get(planeName);
                    gateA.insertNewNode(apron);
                } else if (airplaneBMap.containsKey(planeName)) {
                    int apron = airplaneBMap.get(planeName);
                    gateB.insertNewNode(apron);
                }
            }
        }
    }
}


