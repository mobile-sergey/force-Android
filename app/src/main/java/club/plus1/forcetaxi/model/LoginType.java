package club.plus1.forcetaxi.model;

public enum LoginType {
    email, phone;

    @Override
    public String toString() {
        if (this == LoginType.email) {
            return "Электронная почта";
        } else {
            return "Мобильный телефон";
        }
    }
}
