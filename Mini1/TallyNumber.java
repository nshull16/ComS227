package mini1;

public class TallyNumber {

    private int tallyInt;
    private java.lang.String tallyString;

    public TallyNumber(int givenValue)
    {
        if(givenValue < 0) {
            tallyInt = -1;
            tallyString = "";
        }
        else {
            tallyInt = givenValue;
            tallyString = tallyIntToString(tallyInt);
        }
    }

    public TallyNumber(java.lang.String givenString)
    {
        if(isInvalid(givenString) || givenString.equals("")) {
            tallyInt = -1;
            tallyString = "";
        }
        else {
            tallyInt = tallyStringToInt(givenString);
            tallyString = givenString;
        }
    }

    public void add(TallyNumber other)
    {
        tallyInt += other.getIntValue();
        tallyString = tallyIntToString(tallyInt);
        normalize();
    }

    public void combine(TallyNumber other)
    {
        String[] digits_1 = getStringValue().split(" ");
        String[] digits_2 = other.getStringValue().split(" ");
        String combined = "";
        int i = 0;
        if(digits_1.length > digits_2.length) {
            for (i = 0; i < digits_1.length - digits_2.length; i++) combined += digits_1[i] + " ";
            for(int j=0;j<digits_2.length;j++) combined +=  digits_1[i+j] + digits_2[j] + " ";
        }
        else {
            for (i = 0; i < digits_2.length - digits_1.length; i++) combined += digits_2[i] + " ";
            for(int j=0;j<digits_1.length;j++) combined +=  digits_1[j] + digits_2[i+j] + " ";
        }
        tallyString = combined.substring(0, combined.length()-1);
        tallyInt = tallyStringToInt(tallyString);
    }

    public int getIntValue()
    {
        return tallyInt;
    }

    public java.lang.String getStringValue()
    {
        return tallyString;
    }

    public void normalize()
    {
        tallyString = tallyIntToString(tallyStringToInt(tallyString));
    }

    private java.lang.String tallyIntToString(int tallyInt)
    {
        int[] digits = new int[Integer.toString(tallyInt).length()];
        int i;
        for(i=digits.length-1;i>=0;i--)
        {
            digits[i] = tallyInt % 10;
            tallyInt /= 10;
        }
        String stringRep = "";
        for(i=0;i<digits.length;i++)
        {
            String digit = "";
            for(int j=0;j<digits[i]/5;j++) digit += "*";
            for(int j=0;j<digits[i]%5;j++) digit += "|";
            if(digits[i] == 0) digit = "0";
            stringRep += digit + " ";
        }
        return stringRep.substring(0, stringRep.length()-1); //I think this is gross but whatever
    }

    private int tallyStringToInt(java.lang.String tallyString)
    {
        int tallyNum = 0;
        java.lang.String[] digits = tallyString.split(" ");
        for(int i=0;i<digits.length;i++)
        {
            java.lang.String index = digits[i];
            int digitVal = 0;
            for(int j=0;j<index.length();j++)
            {
                digitVal += (index.charAt(j) == '|') ? 1 : (index.charAt(j) == '*') ? 5 : 0;
            }
            tallyNum += digitVal * (Math.pow(10,digits.length-i-1));
        }
        return tallyNum;
    }

    private boolean isInvalid(java.lang.String tallyString)
    {
        for(int i=0;i<tallyString.length();i++)
            if(tallyString.charAt(i) != '|' && tallyString.charAt(i) != '*' && tallyString.charAt(i) != '0' && tallyString.charAt(i) != ' ')
                return true;
        return false;
    }
}