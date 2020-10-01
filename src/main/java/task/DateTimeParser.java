package task;

import duke_exception.BadInputException;

abstract class DateTimeParser {
    private static final char SEPERATOR = '-';
    public static DateTime parseDateTIme(String dateTime) throws BadInputException{
        if (!(isValidDateTime(dateTime))){
            throw new BadInputException("The format of the date time is wrong!");
        }
        try {
            String[] splitDateTime = dateTime.split(String.valueOf(SEPERATOR));
            return new DateTime(Integer.parseInt(splitDateTime[2]), Integer.parseInt(splitDateTime[0]),
                    Integer.parseInt(splitDateTime[1]), Integer.parseInt(splitDateTime[3]));
        }
        catch (NumberFormatException e){
            throw new BadInputException("Please input Integers for date time!");
        }
    }

    /***
     * A valid date time string must be of the format YYYY-MM-DD-TTTT
     * @param dateTime
     * @return
     */
    private static boolean isValidDateTime(String dateTime){
        return dateTime.length() == 15 && dateTime.charAt(4) == SEPERATOR
                && dateTime.charAt(7) == SEPERATOR && dateTime.charAt(10) == SEPERATOR;
    }
}
