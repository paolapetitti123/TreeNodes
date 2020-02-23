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
public class Student implements Comparable<Student>
{
    private String idNumber;
    private String name;
    private double gpa;
    
    Student(String i, String n, double g)
    {
        idNumber = i;
        name = n;
        gpa = g;
    }
    
    @Override
    public int compareTo(Student o)
    {
        if(this.gpa > o.gpa)
        {
            return +1;
        }
        else
        {
            if(this.gpa == o.gpa)
            {
                return 0;
            }
            else 
                return -1;
        }
    }
    
    public double getGpa() 
    { 
        return gpa;
    }
    
    public String getName()
    {
       return name;
    }
    
     public String toString()
     {
         return "\nName: " + 
                 this.name + 
                 " ID: " + 
                 this.idNumber + 
                 " Gpa: " + 
                 this.gpa;
     }
     
     
     
}