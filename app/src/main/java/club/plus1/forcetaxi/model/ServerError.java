package club.plus1.forcetaxi.model;

public class ServerError {
    private String id;
    private String text;

    public ServerError(String id) {
        this.setId(id);
        this.setText(id);

        // Закомментировано для тестирования
        /*
        if(id.isEmpty()){
            this.setId("");
            this.setText("");
            return;
        }

        int resourceId = 0;
        try {
            resourceId = R.string.class.getField(id).getInt(Resources.getSystem());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        if (resourceId == 0){
            this.setId("unknown_error");
            this.setText(Resources.getSystem().getString(R.string.unknown_error));
        } else {
            this.setId(id);
            this.setText(Resources.getSystem().getString(resourceId));
        }
        */
    }

    public ServerError(String id, String text) {
        this.setId(id);
        this.setText(text);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
