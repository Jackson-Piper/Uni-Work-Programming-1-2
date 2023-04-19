import java.io.Serializable;
// TODO: Add appropriate imports here

public class Main {
    static class Account implements Serializable {
        private int accountNumber;
        private String cardHolder;
        private int balance;

        Account(int accountNumber, String cardHolder) {
            this.accountNumber = accountNumber;
            this.cardHolder = cardHolder;
            this.balance = 0;
        }

        public int getAccountNumber() {
            return accountNumber;
        }

        public String getCardHolder() {
            return cardHolder;
        }

        public int getBalance() {
            return balance;
        }

        @Override
        public String toString() {
            return getAccountNumber() + ", " + getCardHolder() + ", " + getBalance();
        }
    }

    public static void serialize(Account obj, String fileName) throws Exception {
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(obj);
            out.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static Account deserialize(String fileName) throws Exception {
       Account obj;
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
            obj = (Account) in.readObject();
            in.close();
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return obj;
        // FINISH
    }

    public static void main(String[] args) {
        try {
            serialize(new Account(33333, "John Smith"), "myObject.txt");
            Account obj = deserialize("myObject.txt");
            System.out.println(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}