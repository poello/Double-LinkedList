package com.company;


public class MyLinkedList implements NodeList {
    private ListItem root;

    public MyLinkedList(ListItem root) {
        this.root = root;
    }

    @Override
    public ListItem getRoot() {
        return this.root;
    }

    @Override
    public boolean addItem(ListItem newItem) {
        if(this.root == null) {
            // The list was empty, so this item becomes the head of the list
            this.root = newItem;
//            System.out.println("Root:" + this.root.getValue());
            return true;
        }

        ListItem currentItem = this.root;
//        System.out.println("Root1: " + this.root.getValue());
//        System.out.println("New item: " + newItem.getValue());
        while(currentItem != null) {
            int comparison = (currentItem.compareTo(newItem));
            if(comparison < 0) {
                //newItem is greater, move right if possible
                if(currentItem.next() != null) {
                    currentItem = currentItem.next();
//                    System.out.println("1:" + currentItem.getValue());
                } else {
                    // there is no next, so insert at end of list
                    currentItem.setNext(newItem).setPrevious(currentItem);
//                    System.out.println("curr:" + currentItem.getValue());
//                    System.out.println("2:" + currentItem.next().getValue());
//                    newItem.setPrevious(currentItem);
//                    System.out.println("3:" + newItem.previous().getValue());
                    return true;
                }
            } else if(comparison > 0) {
                //newItem is lesser, insert before
                if(currentItem.previous() != null) {
//                    System.out.println("4:" + currentItem.previous().getValue());
                    currentItem.previous().setNext(newItem).setPrevious(currentItem.previous()); //5
//                    System.out.println("5:" + currentItem.previous().next().getValue());
//                    newItem.setPrevious(currentItem.previous()); //6
//                    System.out.println("6:" + newItem.previous().getValue());
                    newItem.setNext(currentItem).setPrevious(newItem); //7
//                    System.out.println("7:" + newItem.next().getValue());
//                    currentItem.setPrevious(newItem); //8
//                    System.out.println("8:" + currentItem.previous().getValue());
                } else {
                    //the node without a previous is the root
                    newItem.setNext(this.root).setPrevious(newItem);
//                    System.out.println("9:" + newItem.next().getValue());
//                    this.root.setPrevious(newItem);
//                    System.out.println("10:" + this.root.previous().getValue());
                    this.root = newItem;
//                    System.out.println("11:" + this.root.getValue());
                }
                return true;
            } else {
                // equal
                System.out.println(newItem.getValue() + " is already present, not added.");
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean removeItem(ListItem item) {
        ListItem removeItem = this.root;
        while(removeItem != null) {
            if(removeItem.getValue().equals(item.getValue())) {
                if(removeItem.next() == null && removeItem.previous() == null) {
                    this.root = null;
                } else if(removeItem.next() == null) {
                    removeItem.previous().setNext(null);
                } else if(removeItem.previous() == null) {
                    removeItem.next().setPrevious(null);
                    this.root = removeItem.next();
                } else {
                    removeItem.previous().setNext(removeItem.next()).setPrevious(removeItem.previous());
//                    removeItem.next().setPrevious(removeItem.previous());
                }
                return true;
            }
            removeItem = removeItem.next();
        }
        return false;
    }

    @Override
    public void traverse(ListItem root) {
        if(root == null) {
            System.out.println("The list is empty.");
        } else {
            while (root != null) {
                System.out.println(root.getValue());
                root = root.next();
            }
        }
//        if(root != null) {
//            System.out.println(root.getValue());
//            traverse(root.next());
//        }
    }

//    public void printList() {
//        ListItem currentItem = this.root;
//        while(currentItem != null) {
//            System.out.println(currentItem.getValue());
//            currentItem = currentItem.next();
//        }
//    }
}
