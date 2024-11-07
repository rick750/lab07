package it.unibo.nestedenum;

import java.util.Comparator;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    @Override
    public Comparator<String> sortByDays() {
        return null;
    }

    @Override
    public Comparator<String> sortByOrder() {
        return null;
    }

    private enum Month {
        JANUARY(31),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);

        private final int days;

        Month(final int days) {
            this.days = days;
        }

        public static Month fromString(final String name) {
            try {
                return valueOf(name);
            } catch (IllegalArgumentException e) {
                Month match = null;
                for (final Month month: values()) {
                    if (month.toString().toLowerCase().startsWith(name.toLowerCase())) {
                        if (match != null) {
                            throw new IllegalArgumentException(name + " is ambiguous: both " + match + " and " + month + " would be valid matches", e);
                        }
                        match = month;
                    }
                }
                if (match == null) {
                    throw new IllegalArgumentException("No matching months for " + name, e);
                }
                return match;
            }
        }

        private static final class SortByDays implements Comparator<String> {
            @Override
            public int compare(final String s1, final String s2) {
                return Integer.compare(Month.fromString(s1).days, Month.fromString(s2).days);
            }
        }
    
        private static final class SortByMonthOrder implements Comparator<String> {
            @Override
            public int compare(final String s1, final String s2) {
                return Month.fromString(s1).compareTo(Month.fromString(s2));
            }
        }
    }
}
