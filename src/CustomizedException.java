public enum CustomizedException {
    ERR_PASSWORD("Wrong Password!"),
    Err_LOGIN("Wrong Information"),
    Err_Input("Wrong Key"),
    Err_Input_TYPE("Wrong Input Type");

    private String msg;
    CustomizedException(String msg) {
        this.msg = msg;
    }
    public String getMessage(){
        return msg;
    }
}
