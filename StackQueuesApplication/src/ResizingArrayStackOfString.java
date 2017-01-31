
public class ResizingArrayStackOfString {

    private String[] s;
    private int N =0;
    public ResizingArrayStackOfString(int capacity){
        s = new String[capacity];
    }
    public boolean isEmpty(){
        return N==0;
    }
    public void push(String item){
        if(N==s.length) resize(2*s.length);
        s[N++] = item;
    }
   private void resize(int size){
       String[] p= new String[size];
       for(int i=0;i<s.length;i++){
           p[i] = s[i];
       }
       s=p;
   }
   public String pop(){
       String item = s[--N];
       s[N] = null;
       return item;
   }
}
