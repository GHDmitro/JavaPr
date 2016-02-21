package homeWork1;

/**
 * Created by macbookair on 15.02.16.
 */
public class TestClass {

    @Save
    public int number = 10;
    @Save
    private String str = "Str";

    public int getNumber() {
        return number;
    }

    public String getStr() {
        return str;
    }

    public void setNumber(int number) {

        this.number = number;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
