/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpalib.estdat;

import java.util.ArrayList;

/**
 *
 * @author augusto
 */
class TTreeNode {

    char mKey;
    boolean mNew = true;
    protected ArrayList<TTreeNode> mChildren = null;
    protected Object mElement = null;

    public boolean add(Object key, Object element) {
        if (key == null) {
            return false;
        }
        return add(key.toString(), element);
    }

    public boolean add(String key, Object element) {
        if (key == null) {
            return false;
        }
        return add(key, element, 0);
    }

    protected boolean add(String key, Object element, int pos) {
        if (pos < 0) {
            return false;
        }

        if (pos >= key.length()) {
            addElement(element);
            return true;
        }

        if (mNew) {
            mKey = key.charAt(pos);
            mNew = false;
        }
        if (pos == key.length() - 1) {
            addElement(element);
            return true;
        }
        if (mChildren == null) {
            mChildren = new ArrayList<TTreeNode>();
        }

        return addToChild(key, element, pos);
    }

    public boolean addToChild(String key, Object element, int pos) {
        TTreeNode next = searchChild(key, pos + 1);

        if (next == null) {
            next = new TTreeNode();
            mChildren.add(next);
        }

        next.add(key, element, pos + 1);
        return true;
    }

    public void addElement(Object element) {
        mElement = element;
    }

    public TTreeNode searchChild(String key, int pos) {
        TTreeNode res = null;

        if (mChildren == null) {
            return res;
        }
        if (pos < 0) {
            return res;
        }
        if (key == null) {
            return res;
        }
        if (pos >= key.length()) {
            return res;
        }

        char c = key.charAt(pos);

        for (int i = 0; i < mChildren.size(); i++) {
            if (mChildren.get(i).mKey == c) {
                return mChildren.get(i);
            }
        }
        return res;
    }

    public Object get(String key) {
        return get(key, 0);
    }

    protected Object get(String key, int pos) {
        if (key == null) {
            return null;
        }

        if (pos < 0) {
            return null;
        }

        if (pos >= key.length()) {
            return null;
        }

        if (key.charAt(pos) != mKey) {
            return null;
        }

        if (pos == key.length() - 1) {
            return mElement;
        }

        TTreeNode child = searchChild(key, pos + 1);

        if (child == null) {
            return null;
        }
        return child.get(key, pos + 1);

    }
}

public class TTree {

    protected ArrayList<TTreeNode> mRoot = null;

    public TTree() {
    }

    public boolean add(Object key, Object value) {
        if (key == null) {
            return false;
        }
        return add(key.toString(), value);
    }

    public boolean add(String key, Object value) {
        TTreeNode root = searchTree(key);
        if (root == null) {

            root = new TTreeNode();

            if (mRoot == null) {
                mRoot = new ArrayList<TTreeNode>();
            }
            mRoot.add(root);
        }
        return root.add(key, value);
    }

    public Object get(String key) {
        TTreeNode root = searchTree(key);
        if (root == null) {
            return null;
        }
        return root.get(key);
    }

    public TTreeNode searchTree(String key) {
        if (mRoot == null) {
            mRoot = new ArrayList<TTreeNode>();
        }
        if (key == null) {
            return null;
        }

        char k = key.charAt(0);
        TTreeNode node = null;
        for (int i = 0; i < mRoot.size(); i++) {
            node = mRoot.get(i);

            if (node.mKey == k) {
                return (node);
            }
        }
        return null;
    }
}
