//
// Name: Zhuang, Alex
//
// Description:
// Implement a Set ADT using Singly Linked List which is comprised of generic Nodes. It has functions such as
// finding the complement, union, intersection of sets.
public class LinkSet<T> implements SetInterface<T>{
    protected Node<T> head;
    protected Node<T> tail;
    private int length;
    public LinkSet(){        
    }
    public LinkSet(LinkSet<T> set){
        Node<T> t = set.head;
        while(t!=null){
            add(t.getData());
            t = t.getNext();
        }
    }    
    public boolean remove(T searchValue) {
    @SuppressWarnings("unchecked")
    Node<T> tmpNode = head;
    Node<T> prevNode = tmpNode;
    if(length==0){
              return false;
            }
         else if(length==1){
             if(head.getData().equals(searchValue)){            
              head = null;                
              length--;
              return true; 
             }
            }
    else if(length>1){
       while (tmpNode != null) {
        if (tmpNode.getData().equals(searchValue)) {
            if (tmpNode == head) {
                head.setNext(head.getNext());
                length--;
                return true;                
            } 
            else { 
                prevNode.setNext(tmpNode.getNext());
                length--;
                return true;
            }            
        } 
            else { 
             prevNode = tmpNode;
             tmpNode = tmpNode.next;
         }        
        }    
        }
    return false;
    }
    
    public SetInterface<T> intersection(SetInterface<T> set){        
        LinkSet<T> result = new LinkSet<>();    
        int count = length;
        Node<T> t = head;
        SetInterface<T> c = new LinkSet<>((LinkSet<T>)set); 
        LinkSet<T> copy = (LinkSet<T>) c;
        while(t!=null && count>0){
            if(copy.contains(t.getData())){                
                result.add(t.getData());
                count--;
                t = t.getNext();
            }
            else{                
                t = t.getNext();
                count--;
            }
        }
        return (SetInterface<T>)result;
    }
    @Override
    public SetInterface<T> union(SetInterface<T> set){
        SetInterface<T> copy = new LinkSet<>((LinkSet<T>)set);  
        LinkSet<T> result = (LinkSet<T>) copy;
        Node<T> t = head;
        while(t!=null){
            result.add(t.getData());
            t = t.getNext();
        }
        return (SetInterface<T>) result;
    }
    @Override
    public SetInterface<T> complement(SetInterface<T> set){       
        LinkSet<T> result = new LinkSet<>();
        Node<T> t = head;
        int count = length;
        LinkSet<T> copy = (LinkSet<T>) set;
        while(t!=null && count >0){
            if(!copy.contains(t.getData())){
                result.add(t.getData());
                count--;
                t = t.getNext();
            }
            else{
                t = t.getNext();
                count--;
            }
        }
        return (SetInterface<T>) result;
    }       
    @Override
    public boolean add(T data){
        @SuppressWarnings("unchecked")
        Node<T> nptr = new Node(data);                 
        if(!contains(data)){
        if(head == null) 
        {
            head = nptr;
            tail = head;
            length++;
            return true;            
        }
        else 
        {
            tail.setNext(nptr);            
            tail = nptr; 
            length++;
            return true;            
        }
        }
        return false;
    }        
               
    @Override
    public int length(){
        return length;
    }
    @Override
    public boolean equals(SetInterface<T> set){
        boolean result = true;        
        SetInterface<T> copy = new LinkSet<>((LinkSet<T>) set);
        LinkSet<T> copy2 = (LinkSet<T>) copy;
        Node<T> t = copy2.head;        
        Node<T> temp = head;
        while(result == true && t!=null){
            if(!contains(t.getData())){
                result = false;
            }
            else if(!set.contains(temp.getData())){
                return false;
            }
            else{
                t = t.getNext();  
                temp = temp.getNext();
            }
        }
        return result;
    }
    
    @Override
    public boolean subset(SetInterface<T> set){
        boolean result = true;
        SetInterface<T> copy = new LinkSet<>((LinkSet<T>)set);
        LinkSet<T> copy2 = (LinkSet<T>) copy;
        Node<T> t = copy2.head;  
        if(length==0){
                result = true;
            }      
        while(result == true && t!=null){
            if(!copy.contains(t.getData())){
                result = false;
            }      
            else{
                t = t.getNext();
            }            
        }
        return result;
    }
    @Override
    public boolean contains(T data){
        boolean result = false;
        Node<T> t = head;
        if(length==0){
            return false;
        }
        while(result == false && t!=null){
            if(t.getData() == data){
                result = true;
            }
            else{
                t = t.getNext();
            }
        }
        return result;
    }
    
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder("{");
        Node<T> t = head;
        if(length == 0){
            return "{ }";            
        }
        if(length == 1){
            return "{" +head.getData() +"}";
        }
        if(length > 1){
           if(t==head){               
               str.append(t.getData());
               str.append(", ");
               t = t.getNext();
           }
           while(t.getNext()!=null){
               str.append(t.getData());
               str.append(", ");
               t = t.getNext();
           }
           if(t.getNext() == null){
               str.append(t.getData());
               str.append("}");
           }           
        }
        return str.toString();
    }
}
