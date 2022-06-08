public enum AccountType {
    INTEREST_FREE_SAVING("حساب پس انداز بدون بهره"),
    INTEREST_FREE_CURRENT_ACCOUNT("حساب جاری بدون بهره"),
    SHORT_TERM_SAVING("پس انداز کوتاه مدت"),
    LONG_TERM_SAVING("پس انداز طولانی مدت");

    public final String label;

    private AccountType(String label) {
        this.label = label;
    }
}
