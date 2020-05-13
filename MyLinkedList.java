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
            return true;
        }

        ListItem currentItem = this.root;
        while(currentItem != null) {
            int comparison = (currentItem.compareTo(newItem));
            if(comparison < 0) {
                //newItem is greater, move right if possible
                if(currentItem.next() != null) {
                    currentItem = currentItem.next();
                } else {
                    // there is no next, so insert at end of list
                    currentItem.setNext(newItem).setPrevious(currentItem);
                    return true;
                }
            } else if(comparison > 0) {
                //newItem is lesser, insert before
                if(currentItem.previous() != null) {
                    currentItem.previous().setNext(newItem).setPrevious(currentItem.previous()); //5
                    newItem.setNext(currentItem).setPrevious(newItem); //7
                } else {
                    //the node without a previous is the root
                    newItem.setNext(this.root).setPrevious(newItem);
//                    this.root.setPrevious(newItem);
                    this.root = newItem;
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

}
