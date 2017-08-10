package aa.tools;

public class Coin {

    public static final int L = -1;
    public static final int H = 1;

    public static Defect findDefect(int[] c) {
        switch (compare(c[0] + c[1] + c[2] + c[3], c[4] + c[5] + c[6] + c[7])) {
            case EQUALS: // 8+,9+,10+,11+,8-,9-,10-,11-
                switch (compare(c[0] + c[1] + c[2], c[8] + c[9] + c[10])) {
                    case EQUALS: // 11+,11-
                        switch (compare(c[11], c[0])) {
                            case GREATER:
                                return new Defect(11, H);
                            case LESS:
                                return new Defect(11, L);
                            case EQUALS:
                        }
                    case LESS: // 8+,9+,10+
                        switch (compare(c[8], c[9])) {
                            case EQUALS:
                                return new Defect(10, H);
                            case LESS:
                                return new Defect(9, H);
                            case GREATER:
                                return new Defect(8, H);
                        }
                    case GREATER: // 8-,9-,10-
                        switch (compare(c[8], c[9])) {
                            case EQUALS:
                                return new Defect(10, L);
                            case LESS:
                                return new Defect(8, L);
                            case GREATER:
                                return new Defect(9, L);
                        }
                }
            case LESS: // 0-,1-,2-,3-,4+,5+,6+,7+
                switch (compare(c[0] + c[1] + c[6], c[4] + c[2] + c[11])) {
                    case EQUALS://3-,7+,5+
                        switch (compare(c[7], c[5])) {
                            case LESS:
                                return new Defect(5, H);
                            case GREATER:
                                return new Defect(7, H);
                            case EQUALS:
                                return new Defect(3, L);
                        }
                    case LESS:// 0-,1-,4+
                        switch (compare(c[0], c[1])) {
                            case LESS:
                                return new Defect(0, L);
                            case GREATER:
                                return new Defect(1, L);
                            case EQUALS:
                                return new Defect(4, H);
                        }
                    case GREATER:   //6+ or 2- (because sign has changed)
                        switch (compare(c[6], c[11])) {
                            case GREATER:
                                return new Defect(6, H);
                            case EQUALS:
                                return new Defect(2, L);
                        }
                }
            case GREATER: // 0+, 1+, 2+, 3+, 4-, 5-, 6-, 7-
                switch (compare(c[0] + c[1] + c[6], c[4] + c[2] + c[11])) {
                    case EQUALS: //3+,7-,5-
                        switch (compare(c[7], c[5])) {
                            case LESS:
                                return new Defect(7, L);
                            case GREATER:
                                return new Defect(5, L);
                            case EQUALS:
                                return new Defect(3, H);
                        }
                    case LESS: // 2+,6-,5-
                        switch (compare(c[6], c[5])) {
                            case LESS:
                                return new Defect(6, L);
                            case GREATER:
                                return new Defect(5, L);
                            case EQUALS:
                                return new Defect(2, H);
                        }
                    case GREATER: // 0+,1+,4-,5-
                        switch (compare(c[0] + c[4], c[10] + c[11])) {
                            case LESS:
                                return new Defect(4, L);
                            case GREATER:
                                return new Defect(0, H);
                            case EQUALS:
                                return new Defect(1, H);
                        }
                }
        }


        return null;
    }

    private static Sign compare(int left, int right) {
        int diff = left - right;
        return Sign.getById(diff);
    }

    private enum Sign {
        EQUALS(0), LESS(L), GREATER(1);

        private final int id;

        Sign(int id) {
            this.id = id;
        }

        static Sign getById(int id) {
            for (Sign sign : values()) {
                if (id == sign.id)
                    return sign;
            }
            return null;
        }
    }
}
