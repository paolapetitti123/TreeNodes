/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treenode;
import java.util.LinkedList;
import java.util.Queue;

public class MyBST<E extends Comparable<E>> implements MyTree<E>
{
    private TreeNode<E> root;
    private int size;
    
    public MyBST()
    {
        root = null;
        size = 0;
    }
    
    public MyBST(E[] objects)
    {
        for (E object : objects) 
        {
            insert(object);
        }
    }
 
   @Override
    public boolean search(E element) 
    {
       TreeNode<E> current = root;
       while(current != null)
       {
           if (element.compareTo(current.element) < 0)
           {
               current = current.left;
           }
           else if (element.compareTo(current.element) > 0)
           {
               current = current.right;
           }
           else 
               return true;
       }
       return false;
    }

   @Override
    public boolean insert(E element) 
    {
        if(root == null)
        {
            root = new TreeNode(element);
        }
        else
        {
            TreeNode<E> current = root;
            TreeNode<E> parent = current;
            while(current != null)
            {
                if(element.compareTo(current.element) < 0)
                {
                    parent = current;
                    current = current.left;
                }
                else if(element.compareTo(current.element) > 0)
                {
                    parent = current;
                    current = current.right;
                }
                else
                    return false;
            }
                if(element.compareTo(parent.element) <0)
                    parent.left = new TreeNode(element);
                else
                    parent.right = new TreeNode(element);
        }
        size++;
        return true;
    }
    
   @Override
    public boolean delete(E element)
    {
       TreeNode<E> current = root;  
       TreeNode<E> parent = null;
       
       while(current != null)
       {
           if(element.compareTo(current.element) < 0)
           {
               parent = current;
               current = current.left;
           }
           else if(element.compareTo(current.element) > 0)
           {
               parent = current;
               current = current.right;
           }
           else
               break;
       }
       
       if (current == null)
       {
           return false;
       }
       
       
       if(current.left == null)
       {
           if(parent == null)
           {
               root = current.right;
           }
           else
           {
               if(element.compareTo(parent.element) < 0)
               {
                   parent.left = current.right;
               }
               else 
               {
                   parent.right = current.right;
               }
           }
       }
       
       else
       {
           TreeNode<E> pRM = current;
           TreeNode<E> rMost = current.left;
           
           while(rMost.right != null)
           {
               pRM = rMost;
               rMost = rMost.right;
           }
           
           current.element = rMost.element;
           
           if(pRM.right == rMost)
           {
               pRM.right = rMost.left;
           }
           else
           {
               pRM.left = rMost.left;
           }
       }
       size--;
       return true;
    }
    
    public int sumLeftBranch()
    {
        int sum = 0;
        TreeNode<E> current = root;
        
        while(current != null)
        {
            sum += (Integer)current.element;
            current = current.left;
        }
        
        return sum;
    }
    
    public E getElemAtLevel_LeftBranch(int theLevel)
    {
        TreeNode<E> current = root;
        
        for(int i = 1; i < theLevel; i++)
        {
            current = current.left;
        }
        
        return current.element;
        
    }
    
    public E getMax()
    {
        TreeNode<E> current = root;
        
        while(current.right != null)
        {
           current = current.right;
        }
        return current.element;
    }
    
    
    public E parentOf(E element)
    {
       TreeNode<E> current = root;
       TreeNode<E> parent = current;
       
       while(current != null)
       {
           if (element.compareTo(current.element) < 0)
           {
               parent = current;
               current = current.left;
           }
           else if (element.compareTo(current.element) > 0)
           {
               parent = current;
               current = current.right;
           }
           else return parent.element;
       }
       return parent.element;
    }
    
    public int getNumberOfLeaves()
    {
        return getNumberOfLeaves(root);
    }
  
    public int getNumberOfLeaves(TreeNode<E> node)
    {
        if(node == null)
            return 0;
        else if(node.left == null && node.right == null)
            return 1;
        else
            return getNumberOfLeaves(node.left) + getNumberOfLeaves(node.right);
        
    }
    
    
    public boolean isFullBST()
    {
      if (root == null) 
          return false;
      else 
      {
          int leftSubTree = depth(root.left); 
          int rightSubTree = depth(root.right);
          int balance = Math.abs(leftSubTree - rightSubTree);
          return (balance == 0);
      }
    }

    private int depth(TreeNode current) 
    {
        if (current == null) 
        {
            return 0;
        } 
        else 
        {
            int leftDepth = depth(current.left);
            int rightDepth = depth(current.right);
            return (Math.max(leftDepth, rightDepth) + 1);
        }
    }
    
    public void breadthFirstTraversal()
    {
       Queue<TreeNode<E>> queue = new LinkedList<>();
       
       if(root != null)
       {
           queue.add(root);
       }
       
       while(!queue.isEmpty())
       {
           TreeNode<E> temp = queue.remove();
           System.out.print(temp.element + " ");
           
           if(temp.left != null)
           {
               queue.add(temp.left);
           }
           if(temp.right != null)
           {
               queue.add(temp.right);
           }
       }
    }
    
    
    public int height()
    {
        return height(root) - 1;
    }

    private int height(TreeNode<E> root)
    {
        if(root == null)
        {
            return 0;
        }
        else
            return 1 + (int)Math.max(height(root.left), height(root.right));
    }
    
    
    public void pathOf(E element)
    {
       TreeNode<E> current = root;
       System.out.println(current.element); // printing root element since the root won't change
       while(current != null)
       {
           if (element.compareTo(current.element) < 0)
           {
               current = current.left;
               System.out.println(current.element);
           }
           else if (element.compareTo(current.element) > 0)
           {
               current = current.right;
               System.out.println(current.element);
           }
           else
               break;
       }
    }
    
    
    
    @Override
    public void inOrder() 
    {
        inOrder(root);
    }
    
    public void inOrder(TreeNode<E> node)
    {
        if(node == null)
            return;
        
        else
        {
            inOrder(node.left);
            System.out.println(node.element + " ");
            inOrder(node.right);
        }
    }
     
    @Override
    public void preOrder() 
    {
       preOrder(root);
    }

    protected void preOrder(TreeNode<E> root) 
    {
       if (root == null) 
        {
        return;
        }
        
        else
        {
            System.out.print(root.element + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }
    
    @Override  /*Postorder traversal from the root */
    public void postOrder() 
    {
        postOrder(root);
    }

    protected void postOrder(TreeNode<E> root) 
    {
        if (root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.element + " ");
    }

    @Override
    public int getSize() 
    {
       return size;
    }

    @Override
    public void clear() 
    {
        root = null;
        size = 0;
    }

    void mirror()
    {
        Mirror(root);
    }
    
    TreeNode Mirror(TreeNode node)
    {
        if(node == null)
        {
            return node;
        }
        
        TreeNode left = Mirror(node.left);
        TreeNode right = Mirror(node.right);
        
        node.left = right;
        node.right = left;
        
        return node;
    }
    
    public boolean insertM(E element) 
    {
        if(root == null)
        {
            root = new TreeNode(element);
        }
        else
        {
            TreeNode<E> current = root;
            TreeNode<E> parent = current;
            while(current != null)
            {
                if(element.compareTo(current.element) < 0)
                {
                    parent = current;
                    current = current.left;
                }
                else if(element.compareTo(current.element) > 0)
                {
                    parent = current;
                    current = current.right;
                }
                else
                    return false;
            }
                if(element.compareTo(parent.element) < 0)
                    parent.left = new TreeNode(element);
                else
                    parent.right = new TreeNode(element);
        }
        size++;
        return true;
    }
    
    public boolean deleteM(E element)
    {
       TreeNode<E> current = root;  
       TreeNode<E> parent = null;
       
       while(current != null)
       {
           if(element.compareTo(current.element) < 0)
           {
               parent = current;
               current = current.right;
           }
           else if(element.compareTo(current.element) > 0)
           {
               parent = current;
               current = current.left;
           }
           else
               break;
       }
       
       if (current == null)
       {
           return false;
       }
       
       
       if(current.right == null)
       {
           if(parent == null)
           {
               root = current.left;
           }
           else
           {
               if(element.compareTo(parent.element) < 0)
               {
                   parent.right = current.left;
               }
               else 
               {
                   parent.left = current.left;
               }
           }
       }
       
       else
       {
           TreeNode<E> pRM = current;
           TreeNode<E> rMost = current.left;
           
           while(rMost.right != null)
           {
               pRM = rMost;
               rMost = rMost.right;
           }
           
           current.element = rMost.element;
           
           if(pRM.right == rMost)
           {
               pRM.right = rMost.left;
           }
           else
           {
               pRM.left = rMost.left;
           }
       }
       size--;
       return true;
    }
    
    public boolean searchM(E element) 
    {
       TreeNode<E> current = root;
       while(current != null)
       {
           if (element.compareTo(current.element) > 0)
           {
               current = current.left;
           }
           else if (element.compareTo(current.element) < 0)
           {
               current = current.right;
           }
           else 
               return true;
       }
       return false;
    }
    
   
    public TreeNode deleteMin()
    {
        return i_deleteMin(root);
    }
    
    private TreeNode i_deleteMin(TreeNode node)
    {
        if(node.left == null)
        {
            return node.right;
        }
        node.left = i_deleteMin(node.left);
        return node;
    }
    
    
    public void inOrderM() 
    {
        inOrderM(root);
    }
    
    public void inOrderM(TreeNode<E> node)
    {
        if(node == null)
            return;
        
        else
        {
            inOrderM(node.left);
            System.out.print(node.element + " ");
            inOrderM(node.right);
        }
    }
    
    public void preOrderM() 
    {
       preOrderM(root);
    }

    protected void preOrderM(TreeNode<E> root) 
    {
        if (root == null) 
        {
            return;
        }
        
        else
        {
            System.out.print(root.element + " ");
            preOrderM(root.left);
            preOrderM(root.right);
        }
    }
    
    
    public void postOrderM() 
    {
        postOrderM(root);
    }

    protected void postOrderM(TreeNode<E> root) 
    {
        if (root == null) return;
        postOrderM(root.left);
        postOrderM(root.right);
        System.out.print(root.element + " ");
    }
    
    
    public static void main(String[] args)
    {
        /*
        Integer[] arr= {44, 10, 88, 9, 22, 77, 99, 8, 11, 33, 66, 100, 55};
        MyBST<Integer> tree = new MyBST<>(arr);   // the constructor should be implemented
        System.out.println(" The sum of the left branch is "+ tree.sumLeftBranch() );
        System.out.println("Element at level 4 is "+ tree.getElemAtLevel_LeftBranch(4));
        System.out.println(" The maximal value in the three is  :"+tree.getMax()); 
        
        // Testing Question 1
        System.out.println("Testing parentOf method: " + tree.parentOf(100));
        
        // Testing Question 2
        Integer[] arr2 = { 54, 47, 60, 40, 48, 57, 69, 33, 42, 50, 56, 65, 41, 49, 53};
        MyBST<Integer> tree2 = new MyBST<>(arr2);
        
         System.out.println("The leaf count of binary tree is : " 
                             + tree.getNumberOfLeaves());
         
         // Testing Question 3
        Integer[] arr3 = {54, 47, 60, 40, 48, 58, 69, 33, 42, 50, 56, 65,
                            30, 35, 41, 43, 49, 53, 55, 57, 63, 68}; 
        MyBST<Integer> tree3 = new MyBST<>(arr3);
         
        System.out.println("Is tree 3 full: " + tree3.isFullBST());
        System.out.println("Is tree 1 full: " + tree.isFullBST());
        
        // Testing Question 4
        tree.breadthFirstTraversal();
        System.out.println();
        System.out.println("Height: " + tree.height());
        
        
        // Testing Delete Method
        System.out.println("Deleting element 22: \n" + tree.delete(22));
        tree.breadthFirstTraversal();
        
        //  testing path of
        System.out.println("\nTesting Path of: ");
        tree.pathOf(33);
    
        
        // THINGS TO TEST STUDENT STUFF
        System.out.print("\n--Student Method testing--\n");
        Student s[] = {new Student("100","Harry",2.5)};
        MyBST<Student> student = new MyBST<>(s);
       
        
        System.out.println("Searching For Bob:");
        System.out.println(student.search(new Student("400","Carry",5.5)));
       
        student.insert(new Student("400","Samantha",5.5));
        student.insert(new Student("200","Carmen",3.5)); 
        student.insert(new Student("500","Bobby",6.5));
        student.insert(new Student("300","Apples",4.5));
        student.insert(new Student("600","Peaches",9.4));
        System.out.print("\nDisplaying inOrder: ");
        student.inOrder();
       
        System.out.println("\nSearching for Carmen: ");
        System.out.println(student.search( new Student("200","Carmen",3.5)));
       
        student.delete(new Student("200","Carmen",3.5));
       
        System.out.println("Searching for  Carmen after delete: " + 
                student.search(new Student("200","Carmen",3.5)));
       
        System.out.print("\nTesting PostOrder: ");
        student.postOrder();
        System.out.print("\n\nTesting PreOrder: ");
        student.preOrder();
*/
        
        
        MyBST tree = new MyBST(); 
        tree.insertM(8);
        tree.insertM(3);
        tree.insertM(10);
        tree.insertM(1);
        tree.insertM(6);
        tree.insertM(14);
        tree.insertM(4);
        tree.insertM(7);
        tree.insertM(13);

  
        /* print inorder traversal of the input tree */
        System.out.println("InOrder traversal of input tree is :"); 
        tree.inOrderM(); 
        System.out.println(""); 
        System.out.println("preOrder traversal of input tree is :"); 
        tree.preOrderM();
        System.out.println(""); 
        System.out.println("postOrder traversal of input tree is :"); 
        tree.postOrderM();
        System.out.println(""); 
        System.out.println("Deleting Minimum Value: ");
        tree.deleteMin();
        tree.inOrderM();
        System.out.println(""); 
        System.out.println(""); 
        
        /* convert tree to its mirror */
        tree.mirror(); 
  
        /* print inorder traversal of the minor tree */
        System.out.println("Inorder traversal of mirror binary tree is : "); 
        tree.inOrderM();
        System.out.println(""); 
        System.out.println("preOrder traversal of mirror binary tree is : "); 
        tree.preOrderM();
        System.out.println("");
        System.out.println("postOrder traversal of mirror binary tree is : "); 
        tree.postOrderM();
        System.out.println(""); 
        
        
       /* delete value 3 from tree */
       System.out.println(""); 
       System.out.println("Deleting the value 3 from tree and printing inorder traversal of mirror binary tree: ");
       tree.deleteM(3);
       tree.inOrderM();
       System.out.println(""); 
        
        
        /* search for value 7 from tree */
        System.out.println("is the value 7 in the tree? : " + tree.searchM(7));
        
        /* search for value 3 from tree */
        System.out.println("is the value 3 in the tree? : " + tree.searchM(3));
        
        System.out.println("");
        System.out.println("Deleting Minimum Value: ");
        tree.deleteMin();
        tree.inOrderM();
    }
 
    
}
