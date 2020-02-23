/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treenode;

/**
 *
 * @author cstuser
 */


public interface MyTree<E> 
{
    public boolean search(E e);
    public boolean insert(E e);
    public boolean delete(E e);
    public void inOrder();
    public void preOrder();
    public void postOrder();
    public int getSize();
    public void clear();
}