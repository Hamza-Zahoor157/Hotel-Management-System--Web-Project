public class Credentials{
    private String name;
    private String password;
    
    public Credentials(){
        name = "";
        password = "";    
    }

    public Credentials(String name,String password){
        this.name = name;
        this.password = password;    
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

        public String toString(){
        return "Customer Name: "+ name +", Password: "+ password +"...";
    }

}