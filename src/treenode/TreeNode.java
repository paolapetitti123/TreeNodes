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
class TreeNode<E> 
{
    E element;
    
    TreeNode<E> left;
    TreeNode<E> right;
    
    public TreeNode(E o)
    {
        element = o;
    }      
}
