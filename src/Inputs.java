import java.util.Scanner;

public class Inputs<T> {
    public <T> Object getInput(T inputType) throws Exception{
        Scanner sc = new Scanner(System.in);
        if (inputType.equals(Integer.class)){
           return sc.nextInt();
        }else if (inputType.equals(String.class)){
            return sc.next();
        }else if (inputType.equals(Long.class)){
            return sc.nextLong();
        }else{
         throw new Exception(CustomizedException.Err_Input_TYPE.getMessage());
        }
    }
}
